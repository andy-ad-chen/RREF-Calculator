package persistence;

import org.json.JSONObject;


    /*
     * 
     * This class is inspired by code provided by the UBC Department of Computer
     * Science, in the course material of CPSC 210.
     * Found in edX, CPSC 210 2025S2, Personal Project, Phase 2.
     * JsonSerializationDemo application, persistence package.
     * 
     */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
