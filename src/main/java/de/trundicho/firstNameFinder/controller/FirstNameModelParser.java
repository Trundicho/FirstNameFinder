package de.trundicho.firstNameFinder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import de.trundicho.firstNameFinder.model.FirstName;
import de.trundicho.firstNameFinder.model.FirstNameModel;

/**
 * This class is adjusted to nam_dict.txt right now.
 * <p>
 * Adjust it to read any file and parse male and female prenames.
 */
@Component
public class FirstNameModelParser {

    @Autowired
    ResourceLoader resourceLoader;

    public FirstNameModel parse(String fileName) {
        FirstNameModel firstNameModel = new FirstNameModel();
        Collection<FirstName> firstNames = firstNameModel.getFirstNames();
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        try (InputStream inputStream = resource.getInputStream(); Scanner sc = new Scanner(inputStream)) {
            while (sc.hasNextLine()) {
                String zeile = sc.nextLine();
                String[] split = zeile.split(" ");
                if (split.length > 3 && (split[0].contains("M") || split[0].contains("F"))) {
                    String gender = split[0];
                    String firstName = split[2];

                    if (!firstName.isEmpty() && !firstName.contains(">")) {
                        firstNames.add(new FirstName(firstName, gender));
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("File not found: " + fileName);
        }
        return firstNameModel;
    }
}