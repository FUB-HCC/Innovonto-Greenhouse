package de.fuberlin.innovonto.brainstormingapp.backend.icv;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IdeaAnnotationResultRepository extends CrudRepository<IdeaAnnotation, UUID> {

}
