package de.trundicho.firstNameFinder.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.Gender;

public final class NameFilter {

    public boolean filterNames(FirstName name, String[] containsFilter, String[] notContainsFilter, String[] startsWithFilter,
            String[] endsWithFilter, Integer minLength, Integer maxLength, Gender gender) {
        boolean female = isFemale(name);
        final boolean maleGender = gender.equals(Gender.MALE);
        if (maleGender && !female || !maleGender && female) {
            String firstName = name.getFirstName().toLowerCase();
            return isLongerThan(minLength, firstName) && isShorterThan(maxLength, firstName) && filterChars(firstName, containsFilter)
                    && filterChars(firstName, Arrays.stream(notContainsFilter).map(n -> "-" + n).toArray(String[]::new)) && endsWithFilter(
                    firstName, endsWithFilter) && startsWithFilter(firstName, startsWithFilter);
        }
        return false;
    }

    private boolean isLongerThan(Integer length, String firstName) {
        return length == null || firstName.length() >= length;
    }

    private boolean isShorterThan(Integer length, String firstName) {
        return length == null || firstName.length() <= length;
    }

    private boolean filterChars(String firstName, String[] filter) {
        return filter(firstName, filter, String::contains, (f1, f2) -> !f1.contains(f2));
    }

    private boolean endsWithFilter(String firstName, String[] filter) {
        return filter(firstName, filter, String::endsWith, (f1, f2) -> !f1.endsWith(f2));
    }

    private boolean startsWithFilter(String firstName, String[] filter) {
        return filter(firstName, filter, String::startsWith, (f1, f2) -> !f1.startsWith(f2));
    }

    private boolean isFemale(FirstName name) {
        return name.getGender().contains("F");
    }

    private boolean filter(String firstName, String[] filter, BiFunction<String, String, Boolean> function,
            BiFunction<String, String, Boolean> functionNegate) {
        FilterModel filterModel = new FilterModel(filter);
        boolean filterCheck = checkFilter(firstName, filterModel.getFilters(), function);
        boolean filterCheckNegate = checkFilter(firstName, filterModel.getFiltersNegate(), functionNegate);
        return filterCheck && filterCheckNegate;
    }

    private boolean checkFilter(String firstName, List<String> filters, BiFunction<String, String, Boolean> function) {
        if (!filters.isEmpty()) {
            boolean filterCheck = false;
            for (String string : filters) {
                if (function.apply(firstName, string)) {
                    filterCheck = true;
                }
            }
            return filterCheck;
        }
        return true;
    }
}