package ui;

import java.util.ArrayList;

import model.Matrix;
import model.Row;
import model.MatrixList;

public class Main {
    public static void main(String[] args) {
        new MatrixReducerApp();

         Matrix testMatrix;
         ArrayList<Float> listFloatTypical1 = new ArrayList<>();
         ArrayList<Float> listFloatTypical2 = new ArrayList<>();
         ArrayList<Float> listFloatTypical3 = new ArrayList<>();
         ArrayList<Row> typicalTestVals = new ArrayList<>();
         listFloatTypical1.add(1.0f);
         listFloatTypical1.add(3.0f);
         listFloatTypical1.add(5.0f);
   

         listFloatTypical2.add(10.0f);
         listFloatTypical2.add(1.0f);
         listFloatTypical2.add(1.0f);
    

         listFloatTypical3.add(1.0f);
         listFloatTypical3.add(1.0f);
         listFloatTypical3.add(9.0f);
 

         Row typicalCaseRow1;
         Row typicalCaseRow2;
         Row typicalCaseRow3;
         typicalCaseRow1 = new Row(3, listFloatTypical1);
         typicalCaseRow2 = new Row(3, listFloatTypical2);
         typicalCaseRow3 = new Row(3, listFloatTypical3);
         typicalTestVals.add(typicalCaseRow1);
         typicalTestVals.add(typicalCaseRow2);
         typicalTestVals.add(typicalCaseRow3);
         testMatrix = new Matrix(typicalTestVals, 3, "myMatrix", "this is a matrix desc");
         testMatrix.computeRedRef();
         for (Row r : testMatrix.getRedRefRows()) {
             System.out.println(r.getFloatArray());
         }
    }
}



