package org.innovonto.greenhouse.api.backend.common.session;

import org.innovonto.greenhouse.api.backend.common.idea.Idea;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class IdeationSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime lastActive;

    private UUID ideatorId;
    private UUID challengeId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrackingEvent> events;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Idea> ideas;


    public UUID getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public UUID getIdeatorId() {
        return ideatorId;
    }

    public void setIdeatorId(UUID ideatorId) {
        this.ideatorId = ideatorId;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }

    public List<TrackingEvent> getEvents() {
        return events;
    }

    public void setEvents(List<TrackingEvent> events) {
        this.events = events;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

    @Override
    public String toString() {
        return "IdeationSession{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", lastActive=" + lastActive +
                ", ideatorId=" + ideatorId +
                ", challengeId=" + challengeId +
                ", events=" + events +
                ", ideas=" + ideas +
                '}';
    }
}
