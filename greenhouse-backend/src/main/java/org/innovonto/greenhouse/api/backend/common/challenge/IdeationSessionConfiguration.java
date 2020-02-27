package org.innovonto.greenhouse.api.backend.common.challenge;

import org.innovonto.greenhouse.api.backend.common.inspiration.Inspiration;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class IdeationSessionConfiguration {
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


    public IdeationSessionConfiguration() {
    }

    public UUID getId() {
        return id;
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

    @Override
    public String toString() {
        return "IdeationSessionConfiguration{" +
                "id=" + id +
                ", timePerSessionInMs=" + timePerSessionInMs +
                ", taskDescription='" + taskDescription + '\'' +
                ", challengeDescription='" + challengeDescription + '\'' +
                '}';
    }
}
