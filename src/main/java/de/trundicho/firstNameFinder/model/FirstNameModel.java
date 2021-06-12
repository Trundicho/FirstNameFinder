package de.trundicho.firstNameFinder.model;

import java.util.Collection;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public final class FirstNameModel {

    private final Collection<FirstName> firstNames = new HashSet<>();

}