/**
 * @file This is the 'test class' for verify the logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */

package com.insideout;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.insideout.model.Emotion;
import com.insideout.model.Moment;
import com.insideout.model.MomentService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Unit Tests for the MomentService class.
 * This class ensures that 'the business logic'
 * for managing moments works as expected
 */
public class MomentServiceTest {
    // ATTRIBUTES (or fields)
    // Instantiate the class I am testing.
    // It's a good practice to do this once for all test.
    // Use of 'private' ensures that the tested class is
    // 'properly encapsulated' throughout the test lifecycle.
    private MomentService momentService;

    @BeforeEach
    void setUp() {
        momentService = new MomentService();    
    }

    /**
     * This test checks...
     */
    @Test
    @DisplayName("1️⃣ Should successfully add a new moment!")
    void testAddMoment() {
        // --- Given ---
        // A 'single moment' with its properties,
        Moment moment = new Moment("My test moment", "This is my first test!", Emotion.JOY, LocalDate.now());

        // --- When ---
        // I call the 'addMoment' method of my 'Service class' with the argument 'moment',
        momentService.addMoment(moment);
        
        // --- Then ---
        // I assert that 'my list of moments' has a size of 1.
        List<Moment> moments = momentService.getAllMoments();
        assertThat(moments, hasSize(1));
    }

    
    
}
