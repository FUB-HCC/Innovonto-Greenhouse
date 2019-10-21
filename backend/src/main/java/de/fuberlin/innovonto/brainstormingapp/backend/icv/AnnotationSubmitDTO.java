package de.fuberlin.innovonto.brainstormingapp.backend.icv;

import de.fuberlin.innovonto.brainstormingapp.backend.session.Idea;

public class AnnotationSubmitDTO {
    private Idea idea;
    private ICVResult icvResult;

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public ICVResult getIcvResult() {
        return icvResult;
    }

    public void setIcvResult(ICVResult icvResult) {
        this.icvResult = icvResult;
    }
}
