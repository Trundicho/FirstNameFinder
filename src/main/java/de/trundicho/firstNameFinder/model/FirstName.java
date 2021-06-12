package de.trundicho.firstNameFinder.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public final class FirstName {

    private final String firstName;
    private final String gender;
}