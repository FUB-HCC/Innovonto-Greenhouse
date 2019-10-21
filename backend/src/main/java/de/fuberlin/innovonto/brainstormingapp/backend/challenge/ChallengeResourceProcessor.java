package de.fuberlin.innovonto.brainstormingapp.backend.challenge;

import de.fuberlin.innovonto.brainstormingapp.backend.session.Idea;
import de.fuberlin.innovonto.brainstormingapp.backend.session.IdeationSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class ChallengeResourceProcessor implements ResourceProcessor<Resource<Challenge>> {
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public ChallengeResourceProcessor(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<Challenge> process(Resource<Challenge> resource) {
        final Challenge challenge = resource.getContent();
        resource.add(entityLinks.linkToSearchResource(Idea.class, "findByChallengeId").expand(challenge.getId()));
        resource.add(entityLinks.linkToSearchResource(IdeationSession.class, "findByChallengeId").expand(challenge.getId()));
        return resource;
    }
}
