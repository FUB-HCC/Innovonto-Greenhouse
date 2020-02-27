package org.innovonto.greenhouse.api.backend.common.idea;

import org.innovonto.greenhouse.api.backend.common.session.IdeationSession;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Ideator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public Ideator() {
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<IdeationSession> sessions;


}
