package de.fuberlin.innovonto.brainstormingapp.backend.icv;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class IdeaAnnotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private UUID ideaId;
    @Column(length = 5_000)
    private String text;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SemanticAnnotation> annotations;

    //hibernate
    public IdeaAnnotation() {
    }

    public IdeaAnnotation(UUID ideaId, String text, List<SemanticAnnotation> annotations) {
        this.ideaId = ideaId;
        this.text = text;
        this.annotations = annotations;
    }

    public UUID getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(UUID ideaId) {
        this.ideaId = ideaId;
    }

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
