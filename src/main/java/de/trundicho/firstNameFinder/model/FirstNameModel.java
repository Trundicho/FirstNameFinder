package de.trundicho.firstNameFinder.model;

import java.util.Collection;
import java.util.HashSet;

public final class FirstNameModel {

    private final Collection<FirstName> firstNames = new HashSet<FirstName>();

    public Collection<FirstName> getAll() {
        return firstNames;
    }

    public void addFirstName(FirstName firstName) {
        firstNames.add(firstName);
    }

}