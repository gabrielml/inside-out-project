/**
 * @file This is the Controller class to manage the flow for adding moments.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package com.insideout.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    // --- METHOD ---

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

    public void viewAllMoments(){
        List<Moment> moments = momentService.getAllMoments();

        momentView.displayAllMoments(moments);
    }
}
