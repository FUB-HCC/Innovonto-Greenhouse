package de.fuberlin.innovonto.brainstormingapp.backend.export.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class MTURK {

    public static final String WORKER_PREFIX = "http://purl.org/innovonto/mturk/worker/";
    public static final String HIT_PREFIX = "http://purl.org/innovonto/mturk/hit/";
    public static final String ASSIGNMENT_PREFIX = "http://purl.org/innovonto/mturk/assignment/";

    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri = "http://purl.org/innovonto/mturk/";

    private static final Model m = ModelFactory.createDefaultModel();

    public static final Resource HIT = m.createResource(uri + "HIT");


    public static final Property assignmentId = m.createProperty(uri, "assignmentId");
    public static final Property hitId = m.createProperty(uri, "hitId");
    public static final Property workerId = m.createProperty(uri, "workerId");

}
