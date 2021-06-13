package de.trundicho.firstNameFinder.controller;

import java.util.ArrayList;
import java.util.List;

class FilterModel {

    private final List<String> filters = new ArrayList<>();
    private final List<String> filtersNegate = new ArrayList<>();

    FilterModel(String[] filter) {
        for (String filterString : filter) {
            if (isNegate(filterString)) {
                filtersNegate.add(filterString.substring(1).toLowerCase());
            } else {
                filters.add(filterString.toLowerCase());
            }
        }
    }

    public List<String> getFilters() {
        return filters;
    }

    public List<String> getFiltersNegate() {
        return filtersNegate;
    }

    private boolean isNegate(String string) {
        return string.startsWith("-") && string.length() > 1;
    }
}
