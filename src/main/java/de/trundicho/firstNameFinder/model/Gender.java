package de.trundicho.firstNameFinder.model;

public enum Gender {
    MALE("Männlich"),
    FEMALE("Weiblich");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
