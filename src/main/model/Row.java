package model;

import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


// Row extending arraylist of floats.
public class Row extends ArrayList<Float> implements Writable {

    // EFFECTS: constructs row
    public Row() {
        super();
    }

    // EFFECTS: constructs row with a collection and correct column num
    public Row(ArrayList<Float> collection) {
        super(collection);
    }

    // REQUIERS: nonzero row
    // EFFECTS: constructs a copy of a row
    public Row(Row toBeCopied) {
        super();
        for (float c: toBeCopied) {
            super.add(c);
        }
    }

    // for some freaking reason, this is the only way to get a good deep clone.

    // REQUIRES: two rows are of same length
    // MODIFIES: this
    // EFFECTS: sums the values of the row passed as an argument and super
    public void sumRow(Row secondRow) {
        for (int i = 0; i < super.size(); i++) {
            float firstRowVal = super.get(i);
            float secondRowVal = secondRow.get(i);
            float a = firstRowVal + secondRowVal;
            super.set(i, a);
        }
    }

    // MODIFIES: this
    // EFFECTS: scales the values of this row
    public void scaleRow(float scale) {
        for (int i = 0; i < super.size(); i++) {
            float val = scale * super.get(i);
            super.set(i, val);
        }
    }

    // EFFECTS: produce true if Row is all 0
    public boolean zeroRow() {
        for (float f: this) {
            if (f != 0.0f) {
                return false;
            }
        }
        return true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("row", this.rowToJson());
        return json;
    }

    // EFFECTS: returns Rows in this matrix as a JSON array
    private JSONArray rowToJson() {
        JSONArray jsonArray = new JSONArray();
        for (float f : this) {
            jsonArray.put(f);
        }
        return jsonArray;
    }

}



