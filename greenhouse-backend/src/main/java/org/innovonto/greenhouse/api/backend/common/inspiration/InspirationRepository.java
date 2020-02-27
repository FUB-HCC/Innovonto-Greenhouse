package org.innovonto.greenhouse.api.backend.common.inspiration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InspirationRepository extends CrudRepository<Inspiration, UUID> {
}
