package de.fuberlin.innovonto.brainstormingapp.backend.session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdeatorRepository extends CrudRepository<Ideator, UUID>{
}
