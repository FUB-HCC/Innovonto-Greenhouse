package de.fuberlin.innovonto.brainstormingapp.backend.challenge;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/*
Notes:
 - There is no Creator for a challenge.

 The ChallengeResourceProcessor adds the following links:
  - getIdeasForChallengeId
  - getSessionsForChallengeId
 */
@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private ChallengeState state;
    private String title;

    private LocalDateTime created;
    private LocalDateTime lastModified;
    private LocalDateTime published;
    private LocalDateTime closed;

    //TODO is the challenge config required?
    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    private ChallengeConfig config;

    //TODO remove?
    private long numberIdeas;
    private long numberSessions;

    public Challenge() {
    }

    public UUID getId() {
        return id;
    }

    public ChallengeState getState() {
        return state;
    }

    public void setState(ChallengeState state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }

    public ChallengeConfig getConfig() {
        return config;
    }

    public void setConfig(ChallengeConfig config) {
        this.config = config;
    }

    public long getNumberIdeas() {
        return numberIdeas;
    }

    public void setNumberIdeas(long numberIdeas) {
        this.numberIdeas = numberIdeas;
    }

    public long getNumberSessions() {
        return numberSessions;
    }

    public void setNumberSessions(long numberSessions) {
        this.numberSessions = numberSessions;
    }

    public String getSessionLink() {
        if (published != null) {
            return "/session/" + this.id + "/start";
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", state=" + state +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", published=" + published +
                ", closed=" + closed +
                ", config=" + config +
                ", numberIdeas=" + numberIdeas +
                ", numberSessions=" + numberSessions +
                ", sessionLink='" + getSessionLink() + '\'' +
                '}';
    }
}
