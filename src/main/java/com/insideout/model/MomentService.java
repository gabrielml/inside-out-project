/**
 * @file This is the Model class containing the business logic
 * for adding moments.
 * @author gml <kickAssDeveloper@hugsForMyBugs.mock>
 */

package com.insideout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to 'manage the business logic' for Moments.
 * This is a part of the 'Model layer' in the 'MVC architecture'.
 */
public class MomentService {
    // Attribute (or field)
    private final List<Moment> moments = new ArrayList<>();

    /**
     * Adds a new moment to the list.
     * 
     * @param moment - The Moment object to add.
     */
    public void addMoment(Moment moment) {
        if (moment != null) {
            moments.add(moment);
        }
    }

    /**
     * Retrieves an unmodifiable list of all moments.
     * 
     * <p>
     * This method 'returns a reference (!)' to the internal list of moments.
     * This implementation returns the raw list for direct access.
     * </p>
     * 
     * @return An unmodifiable {@link List} of all {@link Moment} objects.
     */
    public List<Moment> getAllMoments() {
        return moments;
    }
}