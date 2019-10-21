package de.fuberlin.innovonto.brainstormingapp.backend.session;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime created;
    private String content;
    private IdeaState state;

    private UUID challengeId;
    private UUID creatorId;
    //TODO how to get an on-demand nested ideator, or link?
    private UUID ideationSessionId;

    public Idea() {
        this.state = IdeaState.unreviewed;
        this.created = LocalDateTime.now();
    }

    public Idea(String content) {
        this.content = content;
        this.state = IdeaState.unreviewed;
        this.created = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IdeaState getState() {
        return state;
    }

    public void setState(IdeaState state) {
        this.state = state;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public UUID getIdeationSessionId() {
        return ideationSessionId;
    }

    public void setIdeationSessionId(UUID ideationSessionId) {
        this.ideationSessionId = ideationSessionId;
    }
}
