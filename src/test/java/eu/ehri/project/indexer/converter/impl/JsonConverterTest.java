package eu.ehri.project.indexer.converter.impl;

import com.google.common.collect.Iterables;
import eu.ehri.project.indexer.source.Source;
import eu.ehri.project.indexer.source.impl.InputStreamSource;
import org.codehaus.jackson.JsonNode;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertFalse;

/**
 * @author Mike Bryant (http://github.com/mikesname)
 */
public class JsonConverterTest {

    private static String testResource = "inputdoc.json";

    private JsonNode inputNode;

    @Before
    public void setUp() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(testResource);
        Source<JsonNode> source = new InputStreamSource(stream);
        try {
            inputNode = Iterables.get(source, 0);
        } finally {
            source.finish();
            stream.close();
        }
    }

    @Test
    public void testCorrectNumberOfOutputNodes() throws Exception {
        assertEquals(1, Iterables.size(new JsonConverter().convert(inputNode)));
    }

    @Test
    public void testOutputDocIsDifferent() throws Exception {
        JsonNode out = Iterables.get(new JsonConverter().convert(inputNode), 0);
        assertNotSame(out, inputNode);
    }

    @Test
    public void testOutputDocContainsRightValues() throws Exception {
        JsonNode out = Iterables.get(new JsonConverter().convert(inputNode), 0);
        assertFalse(out.path("id").isMissingNode());
        assertFalse(out.path("type").isMissingNode());
        assertFalse(out.path("accessibleTo").isMissingNode());
    }
}