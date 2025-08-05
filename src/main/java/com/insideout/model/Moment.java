package com.insideout.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * It represents a 'single moment' with its properties.
 * This is part of the 'Model layer' in the MVC architecture.
 */
public class Moment {
    // Attibutes (also named fields)
    private final UUID id;
    private final String title;
    private final String description;
    private final Emotion emotion;
    private final LocalDate momentDate;
    private final LocalDate creationDate;
    private LocalDate modificationDate;

    /**
     * 'Parameterized Constructor' for creating a 'new Moment'.
     * 
     * @param title       - The title of the moment.
     * @param description - A brief description of the moment.
     * @param emotion     - The emotion associated with the moment.
     * @param momentDate  - The date the moment occurred.
     */
    public Moment(String title, String description, Emotion emotion, LocalDate momentDate) {
        // Initializes 'instance variables'to custom values.
        this.id = UUID.randomUUID();

        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;

        this.creationDate = LocalDate.now();
        this.modificationDate = LocalDate.now();
    }

    // 'Getters' for all properties.
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public LocalDate getMomentDate() {
        return momentDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    // 'Setter Method' for modificatonDate property
    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    // Override methods from the "java.lang.Object" class
    // Crucial for how this custom class behave when using
    // collections like 'HashMap' or 'HashSet'.

    // Use to determine if two objects are 'equal' in a logical sense.
    // If the two object references point to the exact same object in memory,
    // they are by definition, equal.
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Moment moment = (Moment) o;
        return Objects.equals(id, moment.id);
    }

    /**
     * It support the efficient storage of objects
     * in 'hash-based' date structures like
     * HashMap, HashSet, and HashTable.
     * 
     * @return {int} an integer 'hash code'
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", emotion=" + emotion +
                ", momentDate=" + momentDate +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
} // End class Moment