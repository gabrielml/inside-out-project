/**
 * @file This is the View for handling user I/O (input and output).
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */
package com.insideout.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.insideout.model.Emotion;

public class MomentView {
    // --- ATTRIBUTES (aka fields) ---
    private final Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // --- CONSTRUCTOR ---
    public MomentView(Scanner scanner) {
        this.scanner = scanner;
    }

    // --- METHODS ---
    public String[] getMomentDetails() {
        System.out.println("--- ADD MOMENT ---");
        System.out.println("Enter the title: ");
        String title = scanner.nextLine();

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        // Get emotions
        System.out.println("Select an emotion: ");
        for (int i = 0; i < Emotion.values().length; i++) {
            System.out.printf("%d. %s%n", i + 1, Emotion.values()[i]);
        }
        System.out.println("Enter your option: ");
        int emotionIndex = -1;
        try {
            emotionIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }

        Emotion emotion = null;
        if (emotionIndex > 0 && emotionIndex <= Emotion.values().length) {
            emotion = Emotion.values()[emotionIndex -1];
        } else {
            System.out.println("Invalid emotion selected.");
            return null;
        }

        // Get & Validate Date
        LocalDate date = null;
        while (date == null) {
            System.out.println("Enter the date (dd/mm/yyyyy): ");
            String dateString = scanner.nextLine();
            try {
                date = LocalDate.parse(dateString, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd/mm/yyyy.");
            }
        }

        return new String[]{title, description, emotion.name(), date.format(DATE_FORMATTER)};
    }

    public void displayMomentAddedSuccess() {
        System.out.println("Moment added successfully.");
    }
}
