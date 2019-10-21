package de.fuberlin.innovonto.brainstormingapp.backend.session;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Projection(name = "expanded",types = IdeationSession.class)
public interface IdeationSessionExpanded {
    UUID getIdeatorId();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    IdeationSessionState getState();
    Set<TrackingEvent> getEvents();
    //TODO this doesn't work. Its not inlined
    Set<Idea> getIdeas();
}
