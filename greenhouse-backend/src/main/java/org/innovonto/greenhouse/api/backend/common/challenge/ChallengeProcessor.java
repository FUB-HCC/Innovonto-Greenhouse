package org.innovonto.greenhouse.api.backend.common.challenge;

import org.innovonto.greenhouse.api.backend.common.idea.Idea;
import org.innovonto.greenhouse.api.backend.common.session.IdeationSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ChallengeProcessor implements RepresentationModelProcessor<EntityModel<Challenge>> {
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public ChallengeProcessor(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public EntityModel<Challenge> process(EntityModel<Challenge> resource) {
        final Challenge challenge = resource.getContent();
        resource.add(entityLinks.linkToSearchResource(Idea.class, LinkRelation.of("findByChallengeId")).expand(challenge.getId()));
        resource.add(entityLinks.linkToSearchResource(IdeationSession.class, LinkRelation.of("findByChallengeId")).expand(challenge.getId()));
        return resource;
    }
}
