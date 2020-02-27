package org.innovonto.greenhouse.api.backend.management.export.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * This data model (Modeling an Ontology Namespace as a bunch of static properties is taken from:
 * org.apache.jena.vocabulary.*
 */
public class GI2MO {
    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri = "http://purl.org/gi2mo/ns#";

    private static final Model m = ModelFactory.createDefaultModel();

    public static final Resource Idea = m.createResource(uri + "Idea");

    public static final Property content = m.createProperty(uri, "content");
    public static final Property hasCreator = m.createProperty(uri, "hasCreator");
    public static final Property hasIdeaContest = m.createProperty(uri, "hasIdeaContest");
    public static final Property hasSubmissionMethod = m.createProperty(uri, "hasSubmissionMethod");
    public static final Property hasContributor = m.createProperty(uri, "hasContributor");
    public static final Property hasCategory = m.createProperty(uri, "hasCategory");
    public static final Property hasStatus = m.createProperty(uri, "hasStatus");

    //Review
    //TODO textualReview/MinMaxRating as property is incorrect: It should be gi2mo:hasReview with a typed entity below that
    public static final Property textualReview = m.createProperty(uri, "textualReview");
    public static final Property minMaxRating = m.createProperty(uri, "minMaxRating");
    public static final Property ratingValue = m.createProperty(uri, "ratingValue");
    public static final Property maxRatingValue = m.createProperty(uri, "maxRatingValue");
    public static final Property minRatingValue = m.createProperty(uri, "minRatingValue");

}
