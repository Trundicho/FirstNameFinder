# First name finder
If you try to find a name for your unborn for example - here you have an easy tool.
It gives you plenty of filter possibilities.

Just use minus "-" to negate.

Use blank " " to filter multiple.

![Screenshot](FirstNameFinderExample.png)

## Name data base is missing in the repo
FirstNameFinder is based on the prename dictionary `nam_dict.txt` of the `gender.c`-project.

Download link for the original `gender`.c: http://www.heise.de/ct/ftp/07/17/182/

Link to the (german) article introducing `gender.c`: https://www.heise.de/artikel-archiv/ct/2007/17/182

I was not yet able to get the permission to publish the name database from heise.de.

But there is a project on github that already published it:
https://github.com/cstuder/genderReader/blob/master/gender.c/nam_dict.txt

Just put it in the resources folder.

You can also use your own and adjust FirstNameModelBuilder to read your own name database.
Then you would also have to adjust the file name in the application.properties.
## Getting Started
What things you need to install the software and how to install them

### Run the application
Start FirstNameFinderApplication
Open in your browser: localhost:8080/ui

### Run with docker
Go to the root directory of firstNameFinder

Build the docker image:

sudo docker build -t first-name-finder:1.0 .

Run the docker image:

sudo docker run -d -p 8080:8080 -t first-name-finder:1.0

### Deployment

Put the generated war files in your application server (Tomcat, Jetty) or run the spring-boot vaadin version with internal tomcat.

### Prerequisites

- Java 8 JDK
- A JAVA IDE

## Built With

* [Spring-Boot](http://spring.io/projects/spring-boot) - Spring-Boot
* [Vaadin](https://vaadin.com/docs/v8/framework/tutorial.html) - The vaadin-8 web framework used
* [GWT](http://www.gwtproject.org/) - The GWT 2.8 framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
Angelo Romito - Initial work - Trundicho

## License

This project is licensed under Apache License Version 2.0 - see the LICENSE file for details

## Background
First name finder was implemented between christmas and new year 2017/2018 to help to
find a name for our child.
