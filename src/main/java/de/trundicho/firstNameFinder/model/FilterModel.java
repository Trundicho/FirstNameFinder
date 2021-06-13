package de.trundicho.firstNameFinder.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class FilterModel {

    private List<String> filters;
    private List<String> filtersNegate;

    public FilterModel(List<String> filter) {
        filtersNegate = filter.stream().filter(this::isNegate).map(f -> f.substring(1).toLowerCase()).collect(Collectors.toList());
        filters = filter.stream()
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
