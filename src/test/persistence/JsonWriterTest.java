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

public class JsonWriterTest extends MethodsForTest {
    private MatrixList ml;
    private Matrix matrix;

    @BeforeEach
    void setUp() {
        ml = new MatrixList();
        matrix = new Matrix(typicalCase, 3, "name", "desc");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Matrix m = new Matrix(typicalCase, 3, "name", "desc");
            ml.addMatrix(m);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testEmptyList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyList.json");
            ml = reader.read();
            ml.addMatrix(matrix);
            assertEquals(1, ml.getMatrices().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGeneralList() {
        try {
            ml.addMatrix(matrix);
            ml.addMatrix(matrix);
            JsonWriter writer = new JsonWriter("./data/testGenList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGenList.json");
            ml = reader.read();
            assertEquals(2, ml.getMatrices().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
