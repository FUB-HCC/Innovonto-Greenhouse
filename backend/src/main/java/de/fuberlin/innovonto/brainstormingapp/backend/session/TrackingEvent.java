package de.fuberlin.innovonto.brainstormingapp.backend.session;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TrackingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID ideationSessionId;

    private LocalDateTime created;
    private long timeInSession;

    private String type;
    private String payload;
    //TODO DTO payload?


    public TrackingEvent() {
    }

    public UUID getIdeationSessionId() {
        return ideationSessionId;
    }

    public void setIdeationSessionId(UUID ideationSessionId) {
        this.ideationSessionId = ideationSessionId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getTimeInSession() {
        return timeInSession;
    }

    public void setTimeInSession(long timeInSession) {
        this.timeInSession = timeInSession;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
