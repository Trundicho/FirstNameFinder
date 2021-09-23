# First name finder
If you try to find a name for your unborn for example - here you have an easy tool.
It gives you plenty of filter possibilities.

Just use minus "-" to negate.

Use blank " " to filter multiple.

![Screenshot](FirstNameFinderExample.png)

## Name data base
FirstNameFinder is based on the prename dictionary `nam_dict.txt` of the `gender.c`-project.

Download link for the original `gender`.c: http://www.heise.de/ct/ftp/07/17/182/

Link to the (german) article introducing `gender.c`: https://www.heise.de/artikel-archiv/ct/2007/17/182

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

The prename dictionary was created by Jörg Michael namequality.pro@googlemail.com. Updated and extended dictionaries are available commercially.

## License

This project is licensed under Apache License Version 2.0 - see the LICENSE file for details

The dictionary nam_dict.txt is licensed under the GNU Free Documentation License. (gender.c/gnu_doc.txt)

## Background
First name finder was implemented between christmas and new year 2017/2018 to help to
find a name for our child.

<iframe src="http://trundichohost.dynv6.net:8083" name="iframe" frameborder="0" 
            marginheight="0" 
            marginwidth="0" 
            width="600px" 
            height="400px" 
            scrolling="auto"></iframe>
