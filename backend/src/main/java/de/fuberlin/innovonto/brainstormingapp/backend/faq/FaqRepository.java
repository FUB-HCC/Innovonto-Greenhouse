package de.fuberlin.innovonto.brainstormingapp.backend.faq;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FaqRepository extends CrudRepository<Faq, UUID> {
}
