package org.innovonto.greenhouse.api.backend.common.session;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface IdeationSessionRepository extends PagingAndSortingRepository<IdeationSession, UUID> {
    Set<IdeationSession> findByChallengeId(@Param("challengeId") UUID challengeId);
}
