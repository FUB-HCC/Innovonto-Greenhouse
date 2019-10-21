package de.fuberlin.innovonto.brainstormingapp.backend.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class IdeationSessionResourceProcessor implements ResourceProcessor<Resource<IdeationSession>> {
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public IdeationSessionResourceProcessor(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<IdeationSession> process(Resource<IdeationSession> resource) {
        final IdeationSession session = resource.getContent();
        //TODO this is disabled, because of the @OneToMany field "ideas" in IdeationSession.java
        //resource.add(entityLinks.linkToSearchResource(Idea.class, "findByIdeationSessionId").expand(session.getId()));
        return resource;
    }
}
