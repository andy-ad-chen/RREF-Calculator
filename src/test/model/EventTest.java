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
    private Event event1;
    private Event event2;
    private Event event3;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    // NOTE: tried fixing with modified testing.

    @BeforeEach
    public void runBefore() {
        event1 = new Event("you just lost THE GAME :3");

        date = new Date(999999);
        event2 = new Event("yee yee ahh event");
        event3 = new Event("yee yee ahh event");
    }

    @Test
    public void testEvent() {
        assertEquals("you just lost THE GAME :3", event1.getDescription());
    }

    @Test
    public void testToString() {
        assertNotEquals(date.toString() + "\n" + "you just lost THE GAME :3", event1.toString());
        assertEquals(event1.getDate() + "\n" + "you just lost THE GAME :3", event1.toString());
    }

    @Test
    public void testEquality() {
        assertTrue(event2.equals(event3));
    }

    @Test
    public void testEqualityNot() {
        assertFalse(event2.equals(event1));
    }

    @Test
    public void testEqualityNull() {
        assertFalse(event2.equals(null));
    }

    @Test
    public void testEqualityWrongClass() {
        assertFalse(event2.equals("wrong ahh object"));
    }

    @Test
    public void testHash() {
        assertEquals(event2.hashCode(), event3.hashCode());
    }

    @Test
    public void testHashNot() {
        assertNotEquals(event2.hashCode(), event1.hashCode());
    }
}
