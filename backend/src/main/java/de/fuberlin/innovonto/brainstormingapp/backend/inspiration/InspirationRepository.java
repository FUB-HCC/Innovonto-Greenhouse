package de.fuberlin.innovonto.brainstormingapp.backend.inspiration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InspirationRepository extends CrudRepository<Inspiration, UUID> {
}
