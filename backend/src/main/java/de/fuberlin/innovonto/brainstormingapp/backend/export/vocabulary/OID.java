package de.fuberlin.innovonto.brainstormingapp.backend.export.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

/**
 * This namespace is used for legacy interactions with the ontoidea platform
 */
public class OID {

    public static final  String IDEA_PREFIX = "http://purl.org/innovonto/ontoideaLegacy/ideas/";
    public static final  String CONCEPT_PREFIX = "http://purl.org/innovonto/ontoideaLegacy/concepts/";


    /**
     * The namespace of the vocabulary as a string
     */
    public static final String uri = "http://purl.org/innovonto/legacy/types/";
    private static final Model m = ModelFactory.createDefaultModel();

    public static final Property legacyId = m.createProperty(uri, "legacyId");
    public static final Property legacyApplicationCase = m.createProperty(uri, "applicationCase");
    public static final Property legacyPreselected = m.createProperty(uri, "preselectedFor");
    public static final Property selectedCategory = m.createProperty(uri, "selectedCategory");
    public static final Property legacyStatus  = m.createProperty(uri, "status");
    public static final Property createdByCeRRI = m.createProperty(uri,"createdInternally");

    public static final Property hitId = m.createProperty(uri,"hitId");
    public static final Property workerId = m.createProperty(uri,"workerId");
    public static final Property assignmentId = m.createProperty(uri,"assignmentId");
    public static final Property legacyChallenge = m.createProperty(uri,"challenge");
    public static final Property validationStatus  = m.createProperty(uri,"validationStatus");
}
