package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in edX, CPSC 210 2025S2, Personal Project, Phase 2.
 * JsonSerializationDemo application, test/persistence package.
 * 
 */

public class JsonReadTest extends MethodsForTest {
    private MatrixList ml;
    private Matrix matrix;

    @BeforeEach
    void setUp() {
        ml = new MatrixList();
        matrix = new Matrix(typicalCase, 3, "name", "desc");
    }

    @Test
    void testReadInvalidFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyList() {
        JsonReader reader = new JsonReader("./data/testEmptyList.json");
        try {
            ml = reader.read();
            ml.addMatrix(matrix);
            assertEquals(1, ml.getMatrices().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGeneralList() {
        JsonReader reader = new JsonReader("./data/testGenList.json");
        try {
            ml = reader.read();
            assertEquals(2, ml.getMatrices().size());
            checkMatrix("name", "desc", 3, typicalCase, ml.getMatrices().get(0));
            checkMatrix("name", "desc", 3, typicalCase, ml.getMatrices().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
