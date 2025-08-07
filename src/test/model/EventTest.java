package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in edX, CPSC 210 2025S2, Personal Project, Phase 4.
 * AlarmSystem application, test package.
 * 
 */

// Further tests were added to achieve 100% coverage of the Event class.
public class EventTest {
    private Event e;
    private Event a;
    private Event b;
    private Date d;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    // NOTE: tried fixing with modified testing.

    @BeforeEach
    public void runBefore() {
        e = new Event("you just lost THE GAME :3");

        d = new Date(999999);
        a = new Event("yee yee ahh event");
        b = new Event("yee yee ahh event");
    }

    @Test
    public void testEvent() {
        assertEquals("you just lost THE GAME :3", e.getDescription());
    }

    @Test
    public void testToString() {
        assertNotEquals(d.toString() + "\n" + "you just lost THE GAME :3", e.toString());
        assertEquals(e.getDate() + "\n" + "you just lost THE GAME :3", e.toString());
    }

    @Test
    public void testEquality() {
        assertTrue(a.equals(b));
    }

    @Test
    public void testEqualityNot() {
        assertFalse(a.equals(e));
    }

    @Test
    public void testEqualityNull() {
        assertFalse(a.equals(null));
    }

    @Test
    public void testEqualityWrongClass() {
        assertFalse(a.equals("wrong ahh object"));
    }

    @Test
    public void testHash() {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testHashNot() {
        assertNotEquals(a.hashCode(), e.hashCode());
    }
}
