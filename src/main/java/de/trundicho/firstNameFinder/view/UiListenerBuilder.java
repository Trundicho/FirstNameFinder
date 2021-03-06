package de.trundicho.firstNameFinder.view;

import java.util.Collection;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.trundicho.firstNameFinder.controller.NameFilter;
import de.trundicho.firstNameFinder.model.FirstName;

import com.vaadin.data.HasValue;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

@Component
public class UiListenerBuilder {

    private final NameFilter nameFilter;

    @Autowired
    public UiListenerBuilder(NameFilter nameFilter) {
        this.nameFilter = nameFilter;
    }

    public void registerListeners(TextField containsFilter, TextField startsWithFilter, TextField endsWithFilter, Collection<FirstName> all,
            Comparator<FirstName> byFirstName, Grid<FirstName> grid, TextField numberOfNames, Slider minLengthSlider,
            Slider maxLengthSlider, RadioButtonGroup<String> gender) {
        GridUiUpdater gridUiUpdater = new GridUiUpdater(nameFilter, containsFilter, startsWithFilter, endsWithFilter, all, byFirstName,
                grid, numberOfNames, minLengthSlider, maxLengthSlider, gender);
        HasValue.ValueChangeListener<String> listener = valueChangeEvent -> gridUiUpdater.updateGrid();
        containsFilter.addValueChangeListener(listener);
        startsWithFilter.addValueChangeListener(listener);
        endsWithFilter.addValueChangeListener(listener);
        HasValue.ValueChangeListener<Double> valueChangeListener = valueChangeEvent -> gridUiUpdater.updateGrid();
        maxLengthSlider.addValueChangeListener(valueChangeListener);
        minLengthSlider.addValueChangeListener(valueChangeListener);

        gender.addSelectionListener((SingleSelectionListener<String>) singleSelectionEvent -> gridUiUpdater.updateGrid());

        gridUiUpdater.updateGrid();
    }

}
