package de.fuberlin.innovonto.brainstormingapp.backend.challenge;

import de.fuberlin.innovonto.brainstormingapp.backend.inspiration.Inspiration;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class ChallengeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    long timePerSessionInMs;
    @Column(length = 20_000)
    String taskDescription;
    @Column(length = 20_000)
    String challengeDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Inspiration> inspirations;

    public ChallengeConfig() {
    }

    public long getTimePerSessionInMs() {
        return timePerSessionInMs;
    }

    public void setTimePerSessionInMs(long timePerSessionInMs) {
        this.timePerSessionInMs = timePerSessionInMs;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public Set<Inspiration> getInspirations() {
        return inspirations;
    }

    public void setInspirations(Set<Inspiration> inspirations) {
        this.inspirations = inspirations;
    }

    @Override
    public String toString() {
        return "ChallengeConfig{" +
                "id=" + id +
                ", timePerSessionInMs=" + timePerSessionInMs +
                ", taskDescription='" + taskDescription + '\'' +
                ", challengeDescription='" + challengeDescription + '\'' +
                '}';
    }
}
