package org.innovonto.greenhouse.api.backend.common.challenge;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    //TODO is IdeationSessionConfiguration as it's own class necessary?
    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    private IdeationSessionConfiguration ideationSessionConfiguration;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChallengeState getState() {
        return state;
    }

    public void setState(ChallengeState state) {
        this.state = state;
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

    public IdeationSessionConfiguration getIdeationSessionConfiguration() {
        return ideationSessionConfiguration;
    }

    public void setIdeationSessionConfiguration(IdeationSessionConfiguration ideationSessionConfiguration) {
        this.ideationSessionConfiguration = ideationSessionConfiguration;
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
                ", ideationSessionConfiguration=" + ideationSessionConfiguration +
                '}';
    }
}
