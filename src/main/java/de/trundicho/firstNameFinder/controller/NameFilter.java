package de.trundicho.firstNameFinder.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.Gender;

public final class NameFilter {

    public boolean filterNames(FirstName name, String[] containsFilter, String[] notContainsFilter, String[] startsWithFilter,
            String[] endsWithFilter, Integer minLength, Integer maxLength, Gender gender) {
        boolean female = isFemale(name);
        final boolean maleGender = gender.equals(Gender.MALE);
        if (maleGender && !female || !maleGender && female) {
            String firstName = name.getFirstName().toLowerCase();
            return isLongerThan(minLength, firstName) && isShorterThan(maxLength, firstName) && filterChars(firstName,
                    Arrays.asList(containsFilter)) && filterChars(firstName,
                    Arrays.stream(notContainsFilter).map(n -> "-" + n).collect(Collectors.toList())) && endsWithFilter(firstName,
                    endsWithFilter) && startsWithFilter(firstName, startsWithFilter);
        }
        return false;
    }

    private boolean isLongerThan(Integer length, String firstName) {
        return length == null || firstName.length() >= length;
    }

    private boolean isShorterThan(Integer length, String firstName) {
        return length == null || firstName.length() <= length;
    }

    private boolean filterChars(String firstName, List<String> filter) {
        if (filter.isEmpty()) {
            return true;
        }
        List<String> filters = new ArrayList<>();
        List<String> filtersNegate = new ArrayList<>();
        for (String filterString : filter) {
            if (isNegate(filterString)) {
                filtersNegate.add(filterString.substring(1).toLowerCase());
            } else {
                filters.add(filterString.toLowerCase());
            }
        }

        boolean filterCheck = filters.isEmpty();
        if (!filterCheck) {
            filterCheck = true;
            for (String string : filters) {
                if (!firstName.contains(string)) {
                    filterCheck = false;
                }
            }
        }

        boolean filterCheckNegate = filtersNegate.isEmpty();
        if (!filterCheckNegate) {
            filterCheckNegate = true;
            for (String string : filtersNegate) {
                if (firstName.contains(string)) {
                    filterCheckNegate = false;
                }
            }
        }
        return filterCheck && filterCheckNegate;
    }

    private boolean endsWithFilter(String firstName, String[] filter) {
        if (filter.length == 0) {
            return true;
        }
        List<String> filters = new ArrayList<>();
        List<String> filtersNegate = new ArrayList<>();
        for (String string : filter) {
            if (!isNegate(string)) {
                filters.add(string.toLowerCase());
            } else {
                filtersNegate.add(string.substring(1).toLowerCase());
            }
        }

        boolean filterCheck = filters.isEmpty();
        for (String string : filters) {
            if (firstName.endsWith(string)) {
                filterCheck = true;
            }

        }

        boolean filterCheckNegate = filtersNegate.isEmpty();
        if (!filterCheckNegate) {
            filterCheckNegate = true;
            for (String string : filtersNegate) {
                if (firstName.endsWith(string)) {
                    filterCheckNegate = false;
                }
            }
        }
        return filterCheck && filterCheckNegate;
    }

    private boolean isNegate(String string) {
        return string.startsWith("-") && string.length() > 1;
    }

    private boolean startsWithFilter(String firstName, String[] filter) {
        if (filter.length == 0) {
            return true;
        }
        List<String> filters = new ArrayList<>();
        List<String> filtersNegate = new ArrayList<>();
        for (String string : filter) {
            if (isNegate(string)) {
                filtersNegate.add(string.substring(1).toLowerCase());
            } else {
                filters.add(string.toLowerCase());
            }
        }

        boolean filterCheck = filters.isEmpty();
        for (String string : filters) {
            if (firstName.startsWith(string)) {
                filterCheck = true;
            }

        }

        boolean filterCheckNegate = filtersNegate.isEmpty();
        if (!filterCheckNegate) {
            filterCheckNegate = true;
            for (String string : filtersNegate) {
                if (firstName.startsWith(string)) {
                    filterCheckNegate = false;
                }
            }
        }
        return filterCheck && filterCheckNegate;
    }

    private boolean isFemale(FirstName name) {
        return name.getGender().contains("F");
    }
}