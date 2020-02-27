package org.innovonto.greenhouse.api.backend.client.icv;

import java.util.List;

public class ICVResult {
    private String text;
    private List<SemanticAnnotation> annotations;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SemanticAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<SemanticAnnotation> annotations) {
        this.annotations = annotations;
    }

}
