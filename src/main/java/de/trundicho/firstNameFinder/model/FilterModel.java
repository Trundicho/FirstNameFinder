package de.trundicho.firstNameFinder.model;

import java.util.ArrayList;
import java.util.List;

public class FilterModel {

    private final List<String> filters = new ArrayList<>();
    private final List<String> filtersNegate = new ArrayList<>();

    public FilterModel(String[] filter) {
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
