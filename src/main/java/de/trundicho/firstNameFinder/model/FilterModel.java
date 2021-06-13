package de.trundicho.firstNameFinder.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class FilterModel {

    private List<String> filters;
    private List<String> filtersNegate;

    public FilterModel(String[] filter) {
        filtersNegate = Arrays.stream(filter).filter(this::isNegate).map(f -> f.substring(1).toLowerCase()).collect(Collectors.toList());
        filters = Arrays.stream(filter)
                        .filter(f -> !isNegate(f))
                        .filter(f -> !StringUtils.pathEquals("-", f))
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
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
