package com.insideout;

import java.util.Scanner;

import com.insideout.controller.MomentController;
import com.insideout.model.MomentService;
import com.insideout.view.MomentView;

// TODO: (optimize) Refactor: Create `View` & `HomeView` (after implementing all the options)

/**
 * The main application class, acting as the primary controller.
 *
 * <p>
 * It contains the main application loop and manages the interaction between
 * the user and the other components.
 * </p>
 */
public final class App {
    // --- ATTRIBUTES (aka fields) ---
    /**
     * The controller instance that handles moment-related operations.
     */
    private final MomentController momentController;

    /**
     * The scanner for reading user input.
     */
    private final Scanner scanner;

    // --- CONSTRUCTOR ---

    /**
     * Constructs a new App instance, initializing the Model, View, and Controller
     * components.
     */
    private App() {
        this.scanner = new Scanner(System.in);
        MomentService momentService = new MomentService();
        MomentView momentView = new MomentView(scanner);
        this.momentController = new MomentController(momentService, momentView);
    }

    // --- METHODS ---

    /**
     * Starts the main application loop.
     */
    public void run() {
        System.out.println("--- MY DIARY ---");
        while (true) {
            displayMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    momentController.addMoment();
                    break;
                case "2":
                    momentController.viewAllMoments();
                    break;
                case "3":
                    momentController.deleteMoment();
                    break;
                case "4":
                    System.out.println("Filter moments functionality is not yet implemented.");
                    break;
                case "5":
                    System.out.println("Exiting the application!");
                    System.out.print("See you next time!");
                    return; // Exit the application.
                default:
                    System.out.println("Invalid option! Please try again!");
            }
        }
    }

    /**
     * Displays the main menu options to the user.
     */
    private void displayMenu() {
        System.out.println("\n1. Add moment");
        System.out.println("2. View all moments");
        System.out.println("3. Delete a moment");
        System.out.println("4. Filter moments");
        System.out.println("5. Exit");

        System.out.println("\nSelect an option: ");
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new App().run();
    }
}
