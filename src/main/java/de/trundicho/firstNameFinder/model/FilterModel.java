package de.trundicho.firstNameFinder.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class FilterModel {

    private final List<String> filters = new ArrayList<>();
    private final List<String> filtersNegate = new ArrayList<>();
    private boolean isEmpty = true;

    public FilterModel(String[] filter) {
        for (String filterString : filter) {
            if (isNegate(filterString)) {
                isEmpty = false;
                filtersNegate.add(filterString.substring(1).toLowerCase());
            } else if(!StringUtils.pathEquals("-", filterString)) {
                isEmpty = false;
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

    public boolean isEmpty() {
        return isEmpty;
    }
}
