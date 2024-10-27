e-Sports Tournament Management Project

Introduction

    This project was developed to create an application for organizing and managing e-sports tournaments. It aims to offer a comprehensive solution for handling players, teams, and tournaments using modern technologies such as Spring, JPA/Hibernate, and H2, adhering to specific technical requirements.

Project Objectives
The application provides functionalities for:

    Player Management: Registration, modification, deletion, and display of players.
    Team Management: Creation, modification, addition/removal of players, and display of teams.
    Tournament Management: Creation, modification, addition/removal of teams, display, and estimation of tournament duration.
    Game Management:  Creation, modification, deletion, and display of games.

Key Technologies and Concepts
This project leverages several important technologies and design principles:

    Spring Framework for Inversion of Control (IoC) and Dependency Injection (DI).
    JPA/Hibernate for data access, using an H2 database.
    Design patterns like Repository and Singleton.
    Entity Validation using annotations such as @NotNull, @Size, etc.
    Extension of DAO functionality to calculate tournament duration, following the Open/Closed SOLID principle.

Project Structure
1. Model Layer

This layer includes JPA entities representing different elements of the application:

    Player: Contains attributes such as pseudonym, age, and team.
    Team: Includes the team name, players, tournaments, and ranking.
    Tournament: Contains details like title, game, start date, end date, spectator count, teams, estimated duration, break times, ceremony times, and status (PLANNED, IN_PROGRESS, COMPLETED, CANCELED).
    Game: Includes attributes like name, difficulty, and average match duration.

2. DAO Layer (Data Access Object)

The DAO layer handles data access using interfaces and JPA/Hibernate implementations:

    TournamentDao: Defines the calculateEstimatedDuration(Long tournamentId) method for calculating the estimated duration of a tournament.
    TournamentDaoImpl: Implements the basic calculation for the estimated tournament duration.
    TournamentDaoExtension: Extends DAO to incorporate additional factors in duration estimation, such as game difficulty and ceremonies.

3. Service Layer

This layer contains the business logic for the application:

    TournamentService: Exposes the getEstimatedTournamentDuration(Long tournamentId) method to obtain the total estimated duration of a tournament using the DAO.

4. Presentation Layer (Console)

The application features a console-based user interface that allows users to:

    Register new players and teams.
    Create and manage tournaments and games.
    Calculate and display the estimated duration of tournaments.

5. Spring Configuration

        The application uses Spring Core for IoC and DI via an XML configuration file (applicationContext.xml). Beans are managed through this configuration file instead of annotations.
6. H2 Database

        An embedded H2 database is used to store data. Tables are generated automatically via JPA/Hibernate, and data is persisted in memory during execution.

Technical Implementation
1. Estimation of Tournament Duration

Two versions of the duration calculation are implemented:

    Basic Calculation:
    Estimated Duration=(Number of Teams×Average Match Duration)+Break Time Between Matches
    Estimated Duration=(Number of Teams×Average Match Duration)+Break Time Between Matches

    Implemented in TournamentDaoImpl.

    Advanced Calculation: A more complex version considers game difficulty and ceremony durations:
    Estimated Duration=(Number of Teams×Average Match Duration×Game Difficulty)+Break Time Between Matches+Ceremony Time
    Estimated Duration=(Number of Teams×Average Match Duration×Game Difficulty)+Break Time Between Matches+Ceremony Time

    Implemented in TournamentDaoExtension.

2. Entity Validation

        Entity validation is managed through Hibernate Validator annotations like @NotNull, ensuring data consistency.
3. Design Patterns

        Repository Pattern: Encapsulates data access in the DAO layer.
        Singleton Pattern: Ensures a unique instance for certain services.

4. Logging

        SLF4J is used to set up a logging system, monitoring critical events in the application.
Testing
Unit Tests

    JUnit and Mockito are used for dependency simulation in tests.
    Validates the logic for calculating tournament duration.

Integration Tests

    Integration testing is set up to validate interactions between the DAO and Service layers, using JPA and H2 to persist and retrieve data.
Code Coverage

    JaCoCo measures code coverage. After executing tests, a coverage report is generated in target/site/jacoco.
Installation and Execution
Prerequisites

    Java 8 installed.
    Maven for dependency management.

Steps

     1- Clone the Repository
     2- Install Maven Dependencies
     3- Run the Application: Execute the main method from the main class
     4- Run Tests
     
Author and Contact Information

      Termoussi Lamiaa 
      Email: lamiaa3105@gmail.com
