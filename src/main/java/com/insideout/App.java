package com.insideout;

import java.util.Scanner;

import com.insideout.controller.MomentController;
import com.insideout.model.MomentService;
import com.insideout.view.MomentView;

/**
 * Hello world!
 */
public final class App {
    // --- ATTRIBUTES (aka fields) ---
    private final MomentController momentController;
    private final Scanner scanner;

    // --- CONSTRUCTOR ---
    private App() {
        this.scanner = new Scanner(System.in);
        MomentService momentService = new MomentService();
        MomentView momentView = new MomentView(scanner);
        this.momentController = new MomentController(momentService, momentView);
    }

    // --- METHODS ---
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

    private void displayMenu() {
        System.out.println("\n1. Add moment");
        System.out.println("2. View all moments");
        System.out.println("3. Delete a moment");
        System.out.println("4. Filter moments");
        System.out.println("5. Exit");

        System.out.println("\nSelect an option: ");
    }

    public static void main(String[] args) {
        new App().run();
    }
}
