package de.fuberlin.innovonto.brainstormingapp.security.ideationsession;

import de.fuberlin.innovonto.brainstormingapp.backend.challenge.Challenge;
import de.fuberlin.innovonto.brainstormingapp.backend.challenge.ChallengeRepository;
import de.fuberlin.innovonto.brainstormingapp.backend.session.IdeationSession;
import de.fuberlin.innovonto.brainstormingapp.backend.session.IdeationSessionRepository;
import de.fuberlin.innovonto.brainstormingapp.backend.session.Ideator;
import de.fuberlin.innovonto.brainstormingapp.backend.session.IdeatorRepository;
import de.fuberlin.innovonto.brainstormingapp.security.JwtTokenUtil;
import de.fuberlin.innovonto.brainstormingapp.security.controller.AuthenticationException;
import de.fuberlin.innovonto.brainstormingapp.security.service.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class IdeationSessionController {
    private final IdeatorRepository ideatorRepository;
    private final IdeationSessionRepository ideationSessionRepository;
    private final ChallengeRepository challengeRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public IdeationSessionController(IdeatorRepository ideatorRepository, IdeationSessionRepository ideationSessionRepository, ChallengeRepository challengeRepository, JwtTokenUtil jwtTokenUtil) {
        this.ideatorRepository = ideatorRepository;
        this.ideationSessionRepository = ideationSessionRepository;
        this.challengeRepository = challengeRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //TODO write a test
    @RequestMapping(value = "${jwt.route.anonymous}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CreateSessionRequest createSessionRequest) throws AuthenticationException {
        Optional<Challenge> byId = challengeRepository.findById(createSessionRequest.getChallengeId());
        if (!byId.isPresent()) {
            throw new IllegalStateException("Tried to create session for nonexisting challenge");
        } else {
            final Challenge challenge = byId.get();
            challenge.setNumberSessions(challenge.getNumberSessions() + 1);
            //Create Ideator
            final Ideator anonymousIdeator = ideatorRepository.save(new Ideator("<Anonymous>"));


            //Create Session
            IdeationSession entity = new IdeationSession();
            entity.setIdeatorId(anonymousIdeator.getId());
            entity.setChallengeId(createSessionRequest.getChallengeId());
            entity.setStartTime(LocalDateTime.now());
            entity = ideationSessionRepository.save(entity);

            // Reload password post-security so we can generate the token
            final String token = jwtTokenUtil.generateToken(entity);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }

    }

    //TODO refresh

    //TODO mturk based session-creation

}
