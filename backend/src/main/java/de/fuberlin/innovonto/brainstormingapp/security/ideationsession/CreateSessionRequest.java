package de.fuberlin.innovonto.brainstormingapp.security.ideationsession;

import java.util.UUID;

public class CreateSessionRequest {
    private UUID challengeId;
    //private String name;

    public CreateSessionRequest() {
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }
}
