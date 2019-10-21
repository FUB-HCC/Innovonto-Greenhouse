package de.fuberlin.innovonto.brainstormingapp.backend.inspiration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Inspiration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String methodicalDescription;
    private String image;
    private String text;

    public Inspiration() {
    }

    public Inspiration(String methodicalDescription, String image, String text) {
        this.methodicalDescription = methodicalDescription;
        this.image = image;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getMethodicalDescription() {
        return methodicalDescription;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Inspiration{" +
                "id=" + id +
                ", methodicalDescription='" + methodicalDescription + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
