package persistence;
import model.MatrixList;
import org.json.JSONObject;


import java.io.*;

    /*
     * 
     * This class is inspired by code provided by the UBC Department of Computer
     * Science, in the course material of CPSC 210.
     * Found in edX, CPSC 210 2025S2, Personal Project, Phase 2.
     * JsonSerializationDemo application, persistence package.
     * 
     */

// Represents a writer that writes JSON representation of matrices to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes MatrixList representation of workroom to file
    public void write(MatrixList ml) {
        JSONObject json = ml.toJson();

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
