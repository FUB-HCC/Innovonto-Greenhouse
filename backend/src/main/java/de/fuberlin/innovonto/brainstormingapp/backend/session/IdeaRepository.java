package de.fuberlin.innovonto.brainstormingapp.backend.session;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface IdeaRepository extends PagingAndSortingRepository<Idea, UUID> {
    Set<Idea> findByIdeationSessionId(@Param("ideationSessionId") UUID ideationSessionId);
    Set<Idea> findByChallengeId(@Param("challengeId") UUID challengeId);
}
