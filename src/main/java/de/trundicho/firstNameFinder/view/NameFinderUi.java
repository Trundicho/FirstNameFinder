package de.trundicho.firstNameFinder.view;

import java.util.Collection;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import de.trundicho.firstNameFinder.controller.FirstNameModelParser;
import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.FirstNameModel;
import de.trundicho.firstNameFinder.model.Gender;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/")
@Theme("valo")
public class NameFinderUi extends UI {

    @Value("${name.dictionary.file.in.resources.folder}")
    private String nameDictionaryFileName;

    @Autowired
    private FirstNameModelParser firstNameModelParser;
    @Autowired
    private UiListenerBuilder uiListenerBuilder;
    private FirstNameModel firstNameModel;

    @Override
    protected void init(VaadinRequest request) {
        if (firstNameModel == null) {
            firstNameModel = firstNameModelParser.parse(nameDictionaryFileName);
        }
        Grid<FirstName> grid = new Grid<>();
        final Collection<FirstName> all = firstNameModel.getFirstNames();
        Comparator<FirstName> byFirstName = Comparator.comparing(FirstName::getFirstName);

        grid.addColumn(FirstName::getFirstName).setCaption("First name");

        Layout verticalLayout = new VerticalLayout();
        Layout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.addComponent(new Label("FirstNameFinder - Filter separated by blank. Negation prefix: '-'"));
        Layout horizontalLayout2 = new HorizontalLayout();
        Layout horizontalLayout3 = new HorizontalLayout();
        RadioButtonGroup<String> gender = createGenderRadioGroup();
        TextField containsFilter = new TextField("Contains");
        TextField endsWithFilter = new TextField("Ends with");
        TextField startsWithFilter = new TextField("Starts with");

        horizontalLayout2.addComponent(startsWithFilter);
        horizontalLayout2.addComponent(containsFilter);
        horizontalLayout2.addComponent(endsWithFilter);
        TextField numberOfNames = new TextField("#");
        numberOfNames.setEnabled(false);
        Slider minLengthSlider = createSlider(1, "Min length");
        Slider maxLengthSlider = createSlider(20, "Max length");
        horizontalLayout3.addComponent(gender);
        horizontalLayout3.addComponent(minLengthSlider);
        horizontalLayout3.addComponent(maxLengthSlider);
        horizontalLayout3.addComponent(numberOfNames);
        verticalLayout.addComponent(horizontalLayout1);
        verticalLayout.addComponent(horizontalLayout2);
        verticalLayout.addComponent(horizontalLayout3);
        verticalLayout.addComponent(grid);
        Responsive.makeResponsive(horizontalLayout2);
        Responsive.makeResponsive(horizontalLayout3);
        Responsive.makeResponsive(verticalLayout);
        setContent(verticalLayout);
        numberOfNames.setValue(all.size() + "");

        uiListenerBuilder.registerListeners(containsFilter, startsWithFilter, endsWithFilter, all, byFirstName, grid, numberOfNames,
                minLengthSlider, maxLengthSlider, gender);
    }

    private RadioButtonGroup<String> createGenderRadioGroup() {
        RadioButtonGroup<String> gender = new RadioButtonGroup<>();
        gender.setItems(Gender.MALE.getGender(), Gender.FEMALE.getGender());
        gender.setSelectedItem(Gender.MALE.getGender());
        return gender;
    }

    private Slider createSlider(double initialValue, String description) {
        Slider maxLengthSlider = new Slider(description);
        maxLengthSlider.setMin(1);
        maxLengthSlider.setMax(20);
        maxLengthSlider.setOrientation(SliderOrientation.HORIZONTAL);
        maxLengthSlider.setResolution(0);
        maxLengthSlider.setValue(initialValue);
        return maxLengthSlider;
    }

}
