/**
 * @file This is the View for handling user I/O (input and output).
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package com.insideout.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.insideout.model.Emotion;
import com.insideout.model.Moment;

/**
 * 'View class' for handling user interaction related to moments.
 * This is part of the 'View layer' in the 'MVC architecture'.
 * It is responsible for all input and output operations with the
 * user via the console.
 */
public class MomentView {
    // --- ATTRIBUTES (aka fields) ---
    /**
     * The scanner used to read user input from the console.
     */
    private final Scanner scanner;

    /**
     * The date formatter used to parse and format dates.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // --- CONSTRUCTOR ---

    /**
     * Constructs a new MomentView.
     *
     * @param scanner The {@link Scanner} object to use for reading user input.
     */
    public MomentView(Scanner scanner) {
        this.scanner = scanner;
    }

    // --- METHODS ---

    /**
     * Prompts the user for moment details (title, description, emotion, and date)
     * and reads the input from the console.
     *
     * @return A String array containing title, description, emotion, and date,
     * or {@code null} if the input  is invalid
     */
    public String[] getMomentDetails() {
        System.out.println("--- ADD MOMENT ---");
        System.out.println("Enter the title: ");
        String title = scanner.nextLine();

        System.out.println("\nEnter the description: ");
        String description = scanner.nextLine();

        // Get emotions
        System.out.println("\nSelect an emotion: ");
        for (int i = 0; i < Emotion.values().length; i++) {
            System.out.printf("%d. %s%n", i + 1, Emotion.values()[i]);
        }
        System.out.println("\nEnter your option: ");
        int emotionIndex = -1;
        try {
            emotionIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }

        Emotion emotion = null;
        if (emotionIndex > 0 && emotionIndex <= Emotion.values().length) {
            emotion = Emotion.values()[emotionIndex - 1];
        } else {
            System.out.println("Invalid emotion selected.");
            return null;
        }

        // Get & Validate Date
        LocalDate date = null;
        while (date == null) {
            System.out.println("\nEnter the date (dd/mm/yyyy): ");
            String dateString = scanner.nextLine();
            try {
                date = LocalDate.parse(dateString, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd/mm/yyyy.");
            }
        }

        return new String[]{title, description, emotion.name(), date.format(DATE_FORMATTER)};
    }

    /**
     * Displays a success message to the user after a moment has been added.
     */
    public void displayMomentAddedSuccess() {
        System.out.println("\nMoment added successfully!");
    }

    /**
     * Displays a formatted list of moments to the user. Each moment is preceded
     * by a number corresponding to its index in the list, for user selection.
     *
     * @param moments The {@link List} of moments to display.
     */
    public void displayAllMoments(List<Moment> moments) {
        if (moments.isEmpty()) {
            System.out.println("No moments to display. Add a moment first.");
            return;
        }

        System.out.println("\n--- ALL MOMENTS ---");
        // It uses the index of the list to make it easier for the user to delete.
        for (int i = 0; i < moments.size(); i++) {
            Moment moment = moments.get(i);
            System.out.printf("%d. Occurred on: %s. Title: %s. Description: %s. Emotion: %s.%n",
                i + 1, moment.getMomentDate().format(DATE_FORMATTER), moment.getTitle(), moment.getDescription(),
                moment.getEmotion());
        }

        System.out.println("-------------------------------------");
    }

    /**
     * Prompts the user to enter the index of the moment they wish to delete.
     *
     * @return The {@code 1-based} index provided by the user, or {@code -1} if the input
     * is not a valid number.
     */
    public int getMomentIndexToDelete() {
        System.out.println("Enter the number of the moment you wish to delete: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // This indicates an invalid input.
        }
    }

    /**
     * Displays a success message to the user after a moment has been deleted.
     */
    public void displayDeletionSuccess() {
        System.out.println("Moment successfully deleted.");
    }

    /**
     * Displays a message to the user when a moment is not found.
     */
    public void displayMomentNotFound() {
        System.out.println("Moment NOT found! No changes were made!");
    }
}
