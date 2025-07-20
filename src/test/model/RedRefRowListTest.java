package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedRefRowListTest extends RowListTest {
    protected RedRefRowList testRedRef;
    protected RedRefRowList identityRedRef;
    protected RedRefRowList zeroRedRef;

    /*
     * Use `extends` to ensure that all the test run for RowList class
     * are also run for RedRefRowList class.
     * 
     */

    @BeforeEach
    public void setUpRedRef() {
        testRedRef = new RedRefRowList(typicalCaseRedRef);
        identityRedRef = new RedRefRowList(identity);
        zeroRedRef = new RedRefRowList(zero);
    }

    @Test
    public void testConstructor() {
        assertFalse(identityRedRef.size() == 0);
        RedRefRowList identityRedRef = new RedRefRowList();
        assertTrue(identityRedRef.size() == 0);
    }

    @Test
    public void testComputeRedRefTypical() {
        assertNotEquals(typicalCaseRedRef, typicalCase);
        testRedRef.computeRedRef(testRedRef.getWidth(), testRedRef.size());
        assertNotEquals(typicalCaseRedRef, typicalCase);
        assertEquals(testRedRef, typicalCaseRedRef);
    }

    @Test
    public void testComputeRedRefIdentity() {
        assertEquals(identityRedRef, identity);
        identityRedRef.computeRedRef(identityRedRef.getWidth(), identityRedRef.size());
        assertEquals(identityRedRef, identity);
    }

    @Test
    public void testCheckInvertIdentity() {
        assertTrue(identityRedRef.checkInvert());
        identityRedRef.computeRedRef(identityRedRef.getWidth(), identityRedRef.size());
        assertTrue(identityRedRef.checkInvert());
    }

    @Test
    public void testCheckInvertZero() {
        assertFalse(zeroRedRef.checkInvert());
        zeroRedRef.computeRedRef(zeroRedRef.getWidth(), zeroRedRef.size());
        assertFalse(zeroRedRef.checkInvert());
    }

    @Test
    public void testCheckInvertFalse() {
        assertTrue(identityRedRef.checkInvert());
        identityRedRef.scaleRow(0.0f, 2);
        identityRedRef.computeRedRef(identityRedRef.getWidth(), identityRedRef.size());
        assertFalse(identityRedRef.checkInvert());
    }

    @Test
    public void testCheckInvertRectangle() {
        assertTrue(identityRedRef.checkInvert());
        identityRedRef.remove(2);
        identityRedRef.computeRedRef(identityRedRef.getWidth(), identityRedRef.size());
        assertFalse(identityRedRef.checkInvert());
    }

}
