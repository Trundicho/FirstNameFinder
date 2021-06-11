package de.trundicho.firstNameFinder.view;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.Gender;
import de.trundicho.firstNameFinder.controller.NameFilter;

import com.vaadin.ui.Grid;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

class GridUiUpdater {

    private final TextField containsFilter;
    private final TextField notContainsFilter;
    private final TextField startsWithFilter;
    private final TextField endsWithFilter;
    private final Collection<FirstName> all;
    private final Comparator<FirstName> byFirstName;
    private final NameFilter nameFilter;
    private final Grid<FirstName> grid;
    private final TextField numberOfNames;
    private final Slider minLengthSlider;
    private final Slider maxLengthSlider;
    private final RadioButtonGroup<String> gender;

    public GridUiUpdater(TextField containsFilter, TextField notContainsFilter, TextField startsWithFilter, TextField endsWithFilter,
            Collection<FirstName> all, Comparator<FirstName> byFirstName, NameFilter nameFilter, Grid<FirstName> grid,
            TextField numberOfNames, Slider minLengthSlider, Slider maxLengthSlider, RadioButtonGroup<String> gender) {
        this.containsFilter = containsFilter;
        this.notContainsFilter = notContainsFilter;
        this.startsWithFilter = startsWithFilter;
        this.endsWithFilter = endsWithFilter;
        this.all = all;
        this.byFirstName = byFirstName;
        this.nameFilter = nameFilter;
        this.grid = grid;
        this.numberOfNames = numberOfNames;
        this.minLengthSlider = minLengthSlider;
        this.maxLengthSlider = maxLengthSlider;
        this.gender = gender;
    }

    public void updateGrid() {
        String containsValues = containsFilter.getValue();
        String notContainsValues = notContainsFilter.getValue();
        String startsWithValues = startsWithFilter.getValue();
        String endsWithValues = endsWithFilter.getValue();
        Stream<FirstName> sorted = all.parallelStream().sorted(byFirstName);
        Double minLength = minLengthSlider.getValue();
        Double maxLength = maxLengthSlider.getValue();
        Gender gender = getGender(this.gender);
        final List<FirstName> filtered = sorted.filter(
                name -> nameFilter.filterNames(name, getFilterValues(containsValues), getFilterValues(notContainsValues),
                        getFilterValues(startsWithValues), getFilterValues(endsWithValues), minLength.intValue(), maxLength.intValue(),
                        gender)).collect(Collectors.toList());
        numberOfNames.setValue(filtered.size() + "");
        grid.setItems(filtered);
    }

    private String[] getFilterValues(String value) {
        final String[] split = value.split(" ");
        if (split.length == 1) {
            if (split[0].equals("")) {
                return new String[] {};
            }
        }
        return split;
    }

    private Gender getGender(RadioButtonGroup<String> genderGroup) {
        Optional<String> selectedItem = genderGroup.getSelectedItem();
        Gender gender = null;
        if (selectedItem.isPresent()) {
            String s = selectedItem.get();
            final Gender[] genders = Gender.values();
            for (Gender gen : genders) {
                if (gen.getGender().equals(s)) {
                    return gen;
                }
            }
        }
        return gender;
    }

}
