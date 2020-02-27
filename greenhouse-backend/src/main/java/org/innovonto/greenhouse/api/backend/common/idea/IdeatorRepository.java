package org.innovonto.greenhouse.api.backend.common.idea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdeatorRepository extends CrudRepository<Ideator, UUID> {
}
