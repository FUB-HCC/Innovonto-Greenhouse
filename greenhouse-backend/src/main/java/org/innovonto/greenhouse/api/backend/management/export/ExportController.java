package org.innovonto.greenhouse.api.backend.management.export;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.innovonto.greenhouse.api.backend.common.challenge.Challenge;
import org.innovonto.greenhouse.api.backend.common.challenge.ChallengeRepository;
import org.innovonto.greenhouse.api.backend.common.idea.Idea;
import org.innovonto.greenhouse.api.backend.common.session.IdeationSession;
import org.innovonto.greenhouse.api.backend.common.session.IdeationSessionRepository;
import org.innovonto.greenhouse.api.backend.management.export.vocabulary.GI2MO;
import org.innovonto.greenhouse.api.backend.management.export.vocabulary.INOV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/export")
public class ExportController {
    private static final Logger log = LoggerFactory.getLogger(ExportController.class);

    private final ChallengeRepository challengeRepository;
    private final IdeationSessionRepository ideationSessionRepository;

    @Autowired
    public ExportController(ChallengeRepository challengeRepository, IdeationSessionRepository ideationSessionRepository) {
        this.challengeRepository = challengeRepository;
        this.ideationSessionRepository = ideationSessionRepository;
    }

    @GetMapping(value = "/challenge/{challengeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> exportAllForChallenge(@PathVariable UUID challengeId) {
        final Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("gi2mo", GI2MO.uri);
        model.setNsPrefix("dcterms", DCTerms.getURI());
        model.setNsPrefix("inov", INOV.uri);

        final Optional<Challenge> optional = challengeRepository.findById(challengeId);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("No challenge for uuid: " + challengeId + " found.");
        }
        final Challenge currentChallenge = optional.get();
        Set<IdeationSession> sessionsForCurrentChallenge = ideationSessionRepository.findByChallengeId(challengeId);
        log.info("Exporting Data for challenge: " + challengeId + " found " + sessionsForCurrentChallenge.size() + " sessions.");
        for (IdeationSession currentSession : sessionsForCurrentChallenge) {
            Resource sessionResource = model.createResource(INOV.SESSION_PREFIX + currentSession.getId());
            List<Idea> ideas = currentSession.getIdeas();
            for (Idea inputIdea : ideas) {
                final Resource outputIdea = model.createResource(INOV.IDEA_PREFIX + inputIdea.getId());
                outputIdea.addProperty(RDF.type, GI2MO.Idea);
                outputIdea.addProperty(DCTerms.created, inputIdea.getCreated().toString());
                outputIdea.addProperty(GI2MO.content, inputIdea.getContent());

                if (inputIdea.getState() != null) {
                    outputIdea.addProperty(INOV.ideaStatus, inputIdea.getState().name());
                }

                outputIdea.addProperty(GI2MO.hasIdeaContest, model.createResource(INOV.IDEA_CONTEST_PREFIX + currentChallenge.getTitle()));
                outputIdea.addProperty(GI2MO.hasSubmissionMethod, model.createResource(INOV.SUBMISSION_METHOD_PREFIX + "brainstorming-app"));
                outputIdea.addProperty(INOV.challenge, model.createResource(INOV.CHALLENGE_PREFIX_LEGACY + currentChallenge.getTitle()));
                outputIdea.addProperty(INOV.createdInSession, sessionResource);
            }
        }
        final StringWriter graphWriter = new StringWriter();
        RDFDataMgr.write(graphWriter, model, RDFFormat.JSONLD_COMPACT_PRETTY);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Content-Disposition", "attachment; filename=\"ideas-" + currentChallenge.getId() + ".json\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(graphWriter.toString());
    }
}
