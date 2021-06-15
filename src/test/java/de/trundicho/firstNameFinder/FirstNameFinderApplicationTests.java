package de.trundicho.firstNameFinder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.trundicho.firstNameFinder.controller.NameFilter;
import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.Gender;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstNameFinderApplicationTests {

    @Autowired
    private NameFilter nameFilter;

    @Test
    public void whenMultipleFilters_thenNameNotFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("C", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isTrue();
    }

    @Test
    public void whenNotEndsWith_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("a", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenNotEndsWithNegate_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("-t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenNotStartsWith_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("C", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenNotStartsWithNegate_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Collections.singletonList("-a");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenNotContains_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBerr");// lBerr leads to filter
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenNotContainsNegate_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer", "-bert"); // -bert leads to filter
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenWrongGender_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "F"); //wrong gender
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 10, Gender.MALE)).isFalse();
    }

    @Test
    public void whenTooLong_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 6, Gender.MALE)).isTrue();
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 0, 5, Gender.MALE)).isFalse();
    }

    @Test
    public void whenTooShort_thenNameFiltered() {
        FirstName firstName = new FirstName("Albert", "M");
        List<String> startsWithFilter = Arrays.asList("A", "-b");
        List<String> containsFilter = Arrays.asList("-cc", "lBer");
        List<String> endsWithFilter = Arrays.asList("t", "-e");
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 6, 10, Gender.MALE)).isTrue();
        assertThat(nameFilter.filterNames(firstName, containsFilter, startsWithFilter, endsWithFilter, 7, 10, Gender.MALE)).isFalse();
    }
}
