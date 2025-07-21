package persistence;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in edX, CPSC 210 2025S2, Personal Project, Phase 2.
 * JsonSerializationDemo application, persistence package.
 * 
 */

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MatrixList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMatrixList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses matrixList from JSON object and returns it
    private MatrixList parseMatrixList(JSONObject jsonObject) {
        MatrixList ml = new MatrixList();
        addMatrices(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses matrices from JSON object and adds them to matrix list
    private void addMatrices(MatrixList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List of Matrices");
        for (Object json : jsonArray) {
            JSONObject nextMatrix = (JSONObject) json;
            addOneMatrix(ml, nextMatrix);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses 1 matrix from JSON object and adds it to matrixlist
    // Recompute RREF rather than parsing, funciontally the same.
    private void addOneMatrix(MatrixList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String desc = jsonObject.getString("Description");
        int width = jsonObject.getInt("Width");
        JSONArray rowListArray = jsonObject.getJSONArray("Matrix Rows");
        RowList matrixRows = parseRowList(rowListArray, width);
        Matrix matrix = new Matrix(matrixRows, width, name, desc);
        matrix.computeRedRef();
        matrix.checkInvert();
        ml.addMatrix(matrix);
    }

    // EFFECTS: parses rowlist from JSON object
    private RowList parseRowList(JSONArray rowListArray, int width) {
        RowList rowList = new RowList(width);
        for (Object json : rowListArray) {
            JSONArray nextRow = (JSONArray) ((JSONObject) json).getJSONArray("row");
            // EXPLANATION TO SELF
            // case `json` object as JSONOBJECT, use get... to get the row array which
            // used the key "row", then set nextRow to be JSONARRAY by casting.
            rowList.add(parseRow(nextRow));
        }
        return rowList;
    }

    // EFFECTS: parses a row from JSONArray
    private Row parseRow(JSONArray rowArray) {
        Row row = new Row();
        for (int i = 0; i < rowArray.length(); i++) {
            float val = (float) rowArray.getFloat(i);
            row.add(val);
        }
        return row;
    }

}
