package org.innovonto.greenhouse.api.backend.management.export.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class INOV {

    public static final String IDEA_PREFIX = "http://purl.org/innovonto/ideas/";
    public static final String CONCEPT_PREFIX = "http://purl.org/innovonto/concepts/";
    public static final String IDEA_CONTEST_PREFIX = "http://purl.org/innovonto/ideaContests/";
    public static final String SESSION_PREFIX = "http://purl.org/innovonto/sessions/";
    public static final String SUBMISSION_METHOD_PREFIX = "http://purl.org/innovonto/submissionMethods/";
    public static final String CHALLENGE_PREFIX_LEGACY = "http://purl.org/innovonto/legacy/";

    //MTURK-Specific Things
    public static final String WORKER_PREFIX = "http://purl.org/innovonto/mturk/worker/";
    public static final String HIT_PREFIX = "http://purl.org/innovonto/mturk/hit/";
    public static final String ASSIGNMENT_PREFIX = "http://purl.org/innovonto/mturk/assignment/";


    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri = "http://purl.org/innovonto/types/";

    private static final Model m = ModelFactory.createDefaultModel();

    //MTURK SPECIFIC THINGS
    public static final Resource HIT = m.createResource(uri + "HIT");
    public static final Property assignmentId = m.createProperty(uri, "assignmentId");
    public static final Property hitId = m.createProperty(uri, "hitId");
    public static final Property workerId = m.createProperty(uri, "workerId");

    public static final Property icon = m.createProperty(uri, "icon");
    public static final Property challenge = m.createProperty(uri, "challenge");
    public static final Property assignedTo = m.createProperty(uri, "assignedTo");
    public static final Property textualRefinement = m.createProperty(uri, "textualRefinement");
    public static final Property ideaStatus = m.createProperty(uri,"ideaStatus");

    //Categories
    public static final Property category = m.createProperty(uri, "category");
    public static final Property categoryLabel = m.createProperty(uri, "categoryLabel");
    public static final Property superCategory = m.createProperty(uri, "superCategory");

    //Concepts
    public static final Property concept = m.createProperty(uri, "concept");
    //TODO hasConcept?
    public static final Property conceptSurface = m.createProperty(uri, "conceptSurface");
    public static final Property linkedConcept = m.createProperty(uri, "linkedConcept");
    public static final Property startIndex = m.createProperty(uri, "startIndex");
    public static final Property endIndex = m.createProperty(uri, "endIndex");
    //TODO offset

    //Session
    public static final Property createdInSession = m.createProperty(uri, "createdInSession");
    public static final Property numberInSession = m.createProperty(uri, "numberInSession");
    public static final Property timeSinceLastIdea = m.createProperty(uri, "timeSinceLastIdea");
    public static final Property timerValueInSession = m.createProperty(uri, "timerValueInSession");
    //TODO remove
    public static final Property formattedTimeSinceLastIdea = m.createProperty(uri, "formattedTimeSinceLastIdea");

}
