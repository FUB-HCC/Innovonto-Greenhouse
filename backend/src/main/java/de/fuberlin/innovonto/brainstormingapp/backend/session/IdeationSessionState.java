package de.fuberlin.innovonto.brainstormingapp.backend.session;

/**
 * Created: User has looked at the
 * preview page and the faq page
 * Running: User has generated at least
 * one Idea or TrackingEvent
 * Abandoned: Set manually. Implies rejected?
 * Submitted: User has submitted survey
 * Approved: Reviewer approved whole session
 * Rejected: Reviewer rejected whole session
 * Partially Approved: Reviewer approved at leas one idea in the session
 */
public enum IdeationSessionState {
    created,
    running,
    abandoned,
    submitted,
    approved,
    rejected,
    partiallyApproved
}