package de.trundicho.firstNameFinder.controller;

import java.util.List;
import java.util.function.BiFunction;

import de.trundicho.firstNameFinder.model.FilterModel;
import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.Gender;

public final class NameFilter {

    public boolean filterNames(FirstName name, String[] containsFilter, String[] startsWithFilter, String[] endsWithFilter,
            Integer minLength, Integer maxLength, Gender gender) {
        if (checkGender(name, gender)) {
            String firstName = name.getFirstName().toLowerCase();
            return isLongerThan(minLength, firstName) && isShorterThan(maxLength, firstName) && filter(firstName, containsFilter,
                    String::contains) && filter(firstName, endsWithFilter, String::endsWith) && filter(firstName, startsWithFilter,
                    String::startsWith);
        }
        return false;
    }

    private boolean isLongerThan(Integer length, String firstName) {
        return length == null || firstName.length() >= length;
    }

    private boolean isShorterThan(Integer length, String firstName) {
        return length == null || firstName.length() <= length;
    }

    private boolean filter(String firstName, String[] filter, BiFunction<String, String, Boolean> function) {
        FilterModel filterModel = new FilterModel(filter);
        List<String> filters = filterModel.getFilters();
        List<String> filtersNegate = filterModel.getFiltersNegate();
        boolean filterCheck = filters.isEmpty() || checkFilter(firstName, filters, function);
        boolean filterCheckNegate = filtersNegate.isEmpty() || !checkFilter(firstName, filtersNegate, function);
        return filterCheck && filterCheckNegate;
    }

    private boolean checkFilter(String firstName, List<String> filters, BiFunction<String, String, Boolean> function) {
        return filters.stream().anyMatch(string -> function.apply(firstName, string));
    }

    private boolean checkGender(FirstName name, Gender gender) {
        boolean female = isFemale(name);
        boolean maleGender = gender.equals(Gender.MALE);
        return maleGender && !female || !maleGender && female;
    }

    private boolean isFemale(FirstName name) {
        return name.getGender().contains("F");
    }
}