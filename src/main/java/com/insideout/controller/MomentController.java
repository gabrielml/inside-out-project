/**
 * @file This is the Controller class to manage the flow for adding moments.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package com.insideout.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.insideout.model.Emotion;
import com.insideout.model.Moment;
import com.insideout.model.MomentService;
import com.insideout.view.MomentView;

/**
 * Controller class to manage the flow for Moment-related actions.
 * This is a part of the Controller layer in the MVC architecture.
 */
public class MomentController {
    // --- ATTRIBUTES (aka fields) ---
    private final MomentService momentService;
    private final MomentView momentView;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // --- CONSTRUCTOR ---
    public MomentController(MomentService momentService, MomentView momentView) {
        this.momentService = momentService;
        this.momentView = momentView;
    }

    // --- METHODS ---

    /**
     * Handles the user's request to add a new moment.
     */
    public void addMoment() {
        String[] details = momentView.getMomentDetails();

        if (details != null) {
            String title = details[0];
            String description = details[1];
            Emotion emotion = Emotion.valueOf(details[2]);
            LocalDate date = LocalDate.parse(details[3], DATE_FORMATTER);

            Moment newMoment = new Moment(title, description, emotion, date);
            momentService.addMoment(newMoment);
            momentView.displayMomentAddedSuccess();
        }
    }

    /**
     * Handles the user's request to view all moments.
     */
    public void viewAllMoments() {
        List<Moment> moments = momentService.getAllMoments();

        momentView.displayAllMoments(moments);
    }

    // --- Method to delete a moment ---
    public void deleteMoment() {
        // Get all moments from the service:
        List<Moment> moments = momentService.getAllMoments();

        // Pass them to the view for display:
        if (moments.isEmpty()) {
            momentView.displayMomentNotFound();
            return;
        }
        momentView.displayAllMoments(moments);

        // Get the user's selected index from the view:
        int indexToDelete = momentView.getMomentIndexToDelete();

        // Adjust index to be 0-based for list access:
        if (indexToDelete >= 0 && indexToDelete <= moments.size()) {
            // Find the UUID that correspond to the ID:
            UUID momentID = moments.get(indexToDelete - 1).getId();
            // Call the service to perform the deletion.
            boolean wasDeleted = momentService.deleteMoment(momentID);
            if (wasDeleted) {
                momentView.displayDeletionSuccess();
            } else {
                momentView.displayMomentNotFound();
            }
        } else {
            momentView.displayMomentNotFound();
        }
    }
}
