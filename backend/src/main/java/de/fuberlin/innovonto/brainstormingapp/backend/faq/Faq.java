package de.fuberlin.innovonto.brainstormingapp.backend.faq;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    Set<FaqEntry> entries;

    public Faq() {
        entries = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public Set<FaqEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<FaqEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(FaqEntry faqEntry) {
        this.entries.add(faqEntry);
    }

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", entries=" + entries +
                '}';
    }
}
