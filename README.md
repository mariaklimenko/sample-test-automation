# Generic install checklist

*Basic steps:*

- Install Java and supporting tools
    - install Java JDK
    - install Maven
    - check Java and Maven work by running a sample test
- Install IntelliJ Community Edition and plugins
    - check IntelliJ works by running the sample test

---

# Windows Install checklist

## Install Java/Maven Pre-requisites
*  Check what you need to install by typing console commands `javac -version`
   *  If it fails install [Java JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Check Maven `mvn -version`
   *  If it fails install [Maven](http://maven.apache.org/download.cgi#Installation)

## Run test from cmd
*  Run everything using Maven: `mvn test`
*  Run only tagged tests using Maven: `mvn test -Dcucumber.options="--tags @rest"`


## Install and Configure IntelliJ
* Install and run IntelliJ Community Edition, e.g. for [Windows](https://www.jetbrains.com/idea/download/#section=windows)
* Install Idea plugins: [Cucumber for Java](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java), [Markdown support](https://plugins.jetbrains.com/plugin/7793-markdown-support), [Gherkin Support](https://plugins.jetbrains.com/plugin/9164-gherkin)

### Run with Intellij IDEA
Config settings: Run- > Edit Configurations
 - `Main class: cucumber.api.cli.Main`
 - `Glue: com.jeta.sample.test`
 - `Feature or folder path: C:/projects/sample-test-automation/src/test/resources/features`
 - `Program arguments:   --plugin org.jetbrains.plugins.cucumber.java.run.CucumberJvm3SMFormatter`
 
 You can run each feature file independently or execute the whole pack by running RunCukesTest

### Examples of usage
- `-Dwhisper.env=env2` 
- `-Dcucumber.options="--tags @rest"`
- `-Dcucumber.options="--tags @rest" -Denv=env1`

### List of available parameters
- `-Denv` (available options: env1 and env2)
- `-Dcucumber.options`
- `-Dselenide.browser` (available options: chrome, firefox, ie, phantomjs, htmlunit, safari, opera)
- `-Dselenide.headless` (can be true or false, false by default)
- `-Dselenide.timeout`
* In addition to that you can override any configuration parameter from automation.properties file, e.g. `-Drest.baseurl`