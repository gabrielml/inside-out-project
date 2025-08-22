/**
 * @file This is the 'test class' for verify the logic.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */

package com.insideout;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.insideout.model.Emotion;
import com.insideout.model.Moment;
import com.insideout.model.MomentService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("1️⃣ Should successfully add a new moment!")
    void testAddMoment() {
        // --- Given ---
        // A 'single moment' with its properties,
        Moment moment = new Moment("My test moment", "This is my first test!", Emotion.JOY, LocalDate.now());

        // --- When ---
        // I call the 'addMoment' method of my 'Service class' with the argument
        // 'moment',
        momentService.addMoment(moment);

        // --- Then ---
        // I assert that 'my list of moments' has a size of 1.
        List<Moment> moments = momentService.getAllMoments();
        assertThat(moments, hasSize(1));
    }

    @Test
    @DisplayName("2. It should find the new moment added to the list.")
    void testFindMoment() {
        // --- Given ---
        // A 'single moment' with its properties.
        Moment moment = new Moment("titleTEST", "descriptionTEST", Emotion.SADNESS, LocalDate.now());

        // --- When ---
        // I add the new moment to the list.
        momentService.addMoment(moment);

        // --- Then ---
        // I can find the moment by checking:
        // 1.The list is not empty.
        // 2. The list has the expected length.
        // 3. The moments fields are as expected.
        List<Moment> moments = momentService.getAllMoments();

        assertThat(moments, hasSize(1));
        assertNotNull(moments);
        assertThat(moments.get(0).getTitle(), is("titleTEST"));
        assertThat(moments.get(0).getDescription(), is("descriptionTEST"));
        assertThat(moments.get(0).getEmotion(), is(Emotion.SADNESS));
        assertThat(moments.get(0).getMomentDate(), is(LocalDate.now()));
    }

    @Test
    @DisplayName("3. It should not add a null moment to the list.")
    void testAvoidAddNullMoment(){
        // --- Given ---
        // A declared new moment without assign it an object (Uninitialized Declaration [null]).
        Moment moment = null;

        // --- When ---
        // I add the new moment to the list.
        momentService.addMoment(moment);

        // --- Then ---
        // After recovering the list of moments,
        // The length of the list remains the same because the new moment is not added.
        List<Moment> moments = momentService.getAllMoments();
        assertThat(moments, hasSize(0));
    }

    @Test
    @DisplayName("4. It should retrieve all moments added to the service.")
    void testGetAllMoments(){
        // --- Given (Prepare) ---
        // A group of moments (in this case two) added to the moment list,
        Moment moment1 = new Moment("Title m1", "Description m1", Emotion.ANGER, LocalDate.now());
        Moment moment2 = new Moment("Title m2", "Description m2", Emotion.ANXIETY, LocalDate.now());
        momentService.addMoment(moment1);
        momentService.addMoment(moment2);

        // --- When (acts) ---
        // I retrieve the list of all the moments,
        List<Moment> moments = momentService.getAllMoments();

        // --- Then (assert) ---
        // I confirm that the moments are instantiated (not null),
        // there are two of them, and that their attributes match
        // the expected ones.
        assertNotNull(moments);
        assertThat(moments, hasSize(2));

        assertThat(moments.get(0).getTitle(), is("Title m1"));
        assertThat(moments.get(1).getTitle(), is("Title m2"));
    }

    // TODO: Make test to delete a moment by ID and to not delete a moment if ID doesn't exist!
    @Test
    @DisplayName("5.1 It should successfully delete an existing moment by ID.")
    void testDeleteMomentExisting(){
        // --- Given (prepare) ---
        // A group of moments (in this case two) added to the moment list,
        Moment moment1 = new Moment("To be deleted", "This moment will be removed", Emotion.SADNESS, LocalDate.now());
        Moment moment2 = new Moment("To be kept", "This moment will remain.", Emotion.JOY, LocalDate.now());

        // --- When (act) ---
        // I want to delete the first moment previously added,
        boolean result = momentService.deleteMoment(moment1.getId());
        
        // --- Then (assert) ---
        // I confirm that the first moment was deleted, "then"
        // the size of the moments list is one, "then"
        // the second moment is in the moments list.
        assertTrue(result);
        assertThat(momentService.getAllMoments(), hasSize(1));
        assertThat(momentService.getAllMoments().get(0).getTitle(), is("To be kept"));
    }

    @Test
    @DisplayName("5.2. It should NOT delete a moment if the ID does not exist.")
    void testDeleteMomentNonExistent() {
        // --- Given (prepare) ---
        // A moment added to the moment list,
        // A random ID,
        Moment moment1 = new Moment("To be kept", "This moment will remain", Emotion.JOY, LocalDate.now());
        UUID nonExistentID = UUID.randomUUID();

        // --- When (act) ---
        // I delete a moment with a random ID,
        boolean result = momentService.deleteMoment(nonExistentID);

        // --- Then (assert) ---
        // I confirm that the moment with the random ID could not be deleted, and
        // the list size of moments remains one.
        assertFalse(result);
        assertThat(momentService.getAllMoments(), hasSize(1));
    }

}
