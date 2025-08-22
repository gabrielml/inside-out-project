/**
 * @file This is the Model class containing the business logic
 * for adding moments.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */

package com.insideout.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service class to 'manage the business logic' for Moments.
 * This is a part of the 'Model layer' in the 'MVC architecture'.
 * It handles the storage and manipulation of {@link Moment} objects.
 */
public class MomentService {
    // --- ATTRIBUTE (or field) ---
    /**
     * The internal list to store all moments.
     */
    private final List<Moment> moments = new ArrayList<>();

    // --- METHODS ---

    /**
     * Adds a new moment to the list.
     *
     * @param moment - The Moment object to add. It must not be null.
     */
    public void addMoment(Moment moment) {
        if (moment != null) {
            moments.add(moment);
        }
    }

    /**
     * Retrieves all moments stored in the service.
     *
     * <p>
     * This method now returns a 'Defensive Copy (!)' of the internal list of
     * moments.
     * The returned list can be modified freely by the caller without affecting
     * the internal list of our object.
     * </p>
     *
     * @return An unmodifiable {@link List} of all {@link Moment} objects.
     * This prevents external modification of the internal list.
     */
    public List<Moment> getAllMoments() {
        return new ArrayList<>(moments); // Return a copy to prevent external modification.
    }

    /**
     * Deletes a moment by its unique identifier.
     *
     * @param id The {@link UUID} of the moment to delete.
     * @return {@code true} if the moment was found and deleted,
     * {@code false} otherwise.
     */
    public boolean deleteMoment(UUID id) {
        // Use a stream to find the moment and remove it. This is an efficient approach.
        return moments.removeIf(moment -> moment.getId().equals(id));
    }
}
