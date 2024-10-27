package com.esport.presentation;

import com.esport.model.*;
import com.esport.service.GameService;
import com.esport.service.PlayerService;
import com.esport.service.TeamService;
import com.esport.service.TournamentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {


    private final TeamService teamService;
    private final PlayerService playerService;
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final Scanner scanner;

//    public ConsoleMenu(ApplicationContext context) {
//
//        this.teamService = context.getBean(TeamService.class);
//        this.playerService = context.getBean(PlayerService.class);
//        this.tournamentService = context.getBean(TournamentService.class);
//        this.gameService=context.getBean(GameService.class);
//        this.scanner = new Scanner(System.in);
//    }
public ConsoleMenu(TeamService teamService, PlayerService playerService,
                   TournamentService tournamentService, GameService gameService) {
    this.teamService = teamService;
    this.playerService = playerService;
    this.tournamentService = tournamentService;
    this.gameService = gameService;
    this.scanner = new Scanner(System.in);
}
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Manage Players");
            System.out.println("2. Manage Teams");
            System.out.println("3. Manage Tournaments");
            System.out.println("4. Manage Games");
            System.out.println("5. Manage Games");
            System.out.println("6. Exit");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    managePlayers();
                    break;
                case 2:
                    manageTeams();
                    break;
                case 3:
                    manageTournaments();
                    break;
                case 4:
                    manageGames();
                    break;
                case 5:
                    calculateEstimatedDuration();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    private void calculateEstimatedDuration() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the tournament ID: ");
        Long tournamentId = scanner.nextLong();

        long estimatedDuration = tournamentService.calculateEstimatedDuration(tournamentId);
        if (estimatedDuration > 0) {
            System.out.println("Estimated duration of tournament with ID " + tournamentId + ": " + estimatedDuration + " minutes.");
        } else {
            System.out.println("Could not calculate duration. Please check the tournament ID.");
        }
    }

    private void managePlayers() {
        while (true) {
            System.out.println("\n=== Manage Players ===");
            System.out.println("1. Add Player");
            System.out.println("2. Update Player");
            System.out.println("3. Delete Player");
            System.out.println("4. View Players");
            System.out.println("5. Back to Menu");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    updatePlayer();
                    break;
                case 3:
                    deletePlayer();
                    break;
                case 4:
                    viewPlayers();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPlayer() {
        System.out.print("Enter player username: ");
        String username = scanner.next();
        System.out.print("Enter player age: ");
        int age = scanner.nextInt();
        System.out.print("Enter team ID : ");
        Long teamId = scanner.nextLong();
        Optional<Team> optionalTeam = teamService.findTeamById(teamId);
        Team team = null;

        if (optionalTeam.isPresent()) {
            team = optionalTeam.get();
        } else {
            System.out.println("Team not found. The player will be added without a team.");
        }
        Player player = new Player(username, age, team);
        playerService.addPlayer(player);
        if (team != null) {
            team.getPlayers().add(player);
            teamService.modifyTeam(team);
        }
        System.out.println("Player added successfully.");
    }

    private void updatePlayer() {
        System.out.print("Enter player ID to update: ");
        Long playerId = scanner.nextLong();

        Optional<Player> optionalPlayer = playerService.findPlayerById(playerId);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            System.out.println("Player found: " + player);

            System.out.print("Enter new username (leave blank to keep current): ");
            String newUsername = scanner.next();
            if (!newUsername.isEmpty()) {
                player.setUsername(newUsername);
            }

            System.out.print("Enter new age (leave blank to keep current): ");
            String ageInput = scanner.next();
            if (!ageInput.isEmpty()) {
                int newAge = Integer.parseInt(ageInput);
                player.setAge(newAge);
            }
            // Update team
            System.out.print("Enter new team ID (leave blank to keep current): ");
            String teamIdInput = scanner.nextLine();
            if (!teamIdInput.isEmpty()) {
                Long newTeamId = Long.parseLong(teamIdInput);
                Optional<Team> optionalTeam = teamService.findTeamById(newTeamId);
                if (optionalTeam.isPresent()) {
                    player.setTeam(optionalTeam.get());
                } else {
                    System.out.println("Team with ID " + newTeamId + " not found. Keeping current team.");
                }
            }

            playerService.modifyPlayer(player);
            System.out.println("Player updated successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }
    private void viewPlayers() {
        List<Player> players = playerService.getAllPlayers();

        if (players.isEmpty()) {
            System.out.println("No players found.");
        } else {
            System.out.println("List of players:");
            for (Player player : players) {
                System.out.println(player);
            }
        }
    }
    private void deletePlayer() {
        System.out.print("Enter player ID to delete: ");
        Long playerId = scanner.nextLong();
        Optional<Player> optionalPlayer = playerService.findPlayerById(playerId);

        if (optionalPlayer.isPresent()) {
            playerService.removePlayer(playerId);
            System.out.println("Player deleted successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }
    private void manageTeams() {
        while (true) {
            System.out.println("\n=== Manage Teams ===");
            System.out.println("1. Add Team");
            System.out.println("2. Update Team");
            System.out.println("3. Delete Team");
            System.out.println("4. View Teams");
            System.out.println("5. Back to Menu");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addTeam();
                    break;
                case 2:
                    updateTeam();
                    break;
                case 3:
                    deleteTeam();
                    break;
                case 4:
                    viewTeams();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTeam() {
        System.out.print("Enter team name: ");
        String name = scanner.next();

        System.out.print("Enter team ranking: ");
        int ranking = scanner.nextInt();

        Team team = new Team();
        team.setName(name);
        team.setRanking(ranking);

        teamService.addTeam(team);
        System.out.println("Team added successfully without tournament associations.");
    }

    private void updateTeam() {
        System.out.print("Enter team ID to update: ");
        Long teamId = scanner.nextLong();
        Optional<Team> optionalTeam = teamService.findTeamById(teamId);

        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            System.out.println("Current team details: " + team);
            System.out.print("Enter new team name (leave blank to keep current): ");
            scanner.nextLine();
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                team.setName(newName);
            }
            System.out.print("Enter new team ranking (leave blank to keep current): ");
            String rankingInput = scanner.next();
            if (!rankingInput.isEmpty()) {
                int newRanking = Integer.parseInt(rankingInput);
                team.setRanking(newRanking);
            }
            teamService.modifyTeam(team);
            System.out.println("Team updated successfully.");
        } else {
            System.out.println("Team not found.");
        }
    }

    private void deleteTeam() {
        System.out.print("Enter team ID to delete: ");
        Long teamId = scanner.nextLong();
        Optional<Team> optionalTeam = teamService.findTeamById(teamId);

        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            System.out.println("Team found: " + team);
            System.out.print("Are you sure you want to delete this team? (yes/no): ");
            String confirmation = scanner.next();
            if (confirmation.equalsIgnoreCase("yes")) {
                teamService.removeTeam(teamId);
                System.out.println("Team deleted successfully.");
            } else {
                System.out.println("Team deletion canceled.");
            }
        } else {
            System.out.println("Team not found.");
        }
    }
    private void viewTeams() {
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            System.out.println("List of Teams:");
            for (Team team : teams) {
                System.out.println(team);
            }
        }
    }

    private void manageTournaments() {
        while (true) {
            System.out.println("\n=== Manage Tournaments ===");
            System.out.println("1. Add Tournament");
            System.out.println("2. Update Tournament");
            System.out.println("3. Delete Tournament");
            System.out.println("4. View Tournaments");
            System.out.println("5. Add Team to Tournament");
            System.out.println("6. Remove Team from Tournament");
            System.out.println("7. Back to Menu");


            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addTournament();
                    break;
                case 2:
                    updateTournament();
                    break;
                case 3:
                    deleteTournament();
                    break;
                case 4:
                    viewTournaments();
                    break;
                case 5:
                    addTeamToTournamentOption();
                    break;
                case 6:
                    removeTeamFromTournamentOption();
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void removeTeamFromTournamentOption() {
        System.out.print("Enter Tournament ID: ");
        Long tournamentId = scanner.nextLong();
        Optional<Tournament> tournamentOpt = tournamentService.findTournamentById(tournamentId);
        if (!tournamentOpt.isPresent()) {
            System.out.println("Tournament not found with ID: " + tournamentId);
            return;
        }
        System.out.print("Enter Team ID: ");
        Long teamId = scanner.nextLong();
        Optional<Team> teamOpt = teamService.findTeamById(teamId);
        if (!teamOpt.isPresent()) {
            System.out.println("Team not found with ID: " + teamId);
            return;
        }

        tournamentService.removeTeamFromTournament(tournamentId, teamId);
    }
    private void addTeamToTournamentOption() {
        System.out.print("Enter Tournament ID: ");
        Long tournamentId = scanner.nextLong();

        System.out.print("Enter Team ID: ");
        Long teamId = scanner.nextLong();
        Optional<Tournament> tournamentOpt = tournamentService.findTournamentById(tournamentId);
        if (!tournamentOpt.isPresent()) {
            System.out.println("Tournament not found with ID: " + tournamentId);
            return;
        }
        Optional<Team> teamOpt = teamService.findTeamById(teamId);
        if (!teamOpt.isPresent()) {
            System.out.println("Team not found with ID: " + teamId);
            return;
        }
        tournamentService.addTeamToTournament(tournamentId, teamId);
    }
    private void addTournament() {
        scanner.nextLine();
        System.out.print("Enter tournament title: ");
        String title = scanner.nextLine();
        System.out.print("Enter game ID: ");
        Long gameId = scanner.nextLong();
        scanner.nextLine();
        Optional<Game> optionalGame = gameService.findGameById(gameId);
        if (!optionalGame.isPresent()) {
            System.out.println("Game with ID " + gameId + " not found. Tournament creation canceled.");
            return;
        }
        Game game = optionalGame.get();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter expected spectator count: ");
        int spectatorCount = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter estimated duration (in minutes): ");
        int estimatedDuration = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter break time between matches (in minutes): ");
        int breakTime = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter ceremony time (in minutes): ");
        int ceremonyTime = Integer.parseInt(scanner.nextLine());
        TournamentStatus status = getTournamentStatusFromUser();
        Tournament tournament = new Tournament(title, game, startDate, endDate, spectatorCount, new ArrayList<>(), estimatedDuration, breakTime, ceremonyTime, status);
        tournamentService.addTournament(tournament);

        System.out.println("Tournament added successfully without team associations.");
    }

    private TournamentStatus getTournamentStatusFromUser() {
        System.out.println("Select tournament status:");
        for (TournamentStatus status : TournamentStatus.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        int choice = getUserChoice();
        return TournamentStatus.values()[choice - 1];
    }
    private void deleteTournament() {
        System.out.print("Enter tournament ID to delete: ");
        Long tournamentId = scanner.nextLong();
        Optional<Tournament> optionalTournament = tournamentService.findTournamentById(tournamentId);

        if (optionalTournament.isPresent()) {
            Tournament tournament = optionalTournament.get();
            System.out.println("Tournament found: " + tournament.getTitle());
            tournamentService.removeTournament(tournamentId);
            System.out.println("Tournament deleted successfully.");
        } else {
            System.out.println("Tournament not found.");
        }
    }

    private void updateTournament() {
        System.out.print("Enter tournament ID to update: ");
        Long tournamentId = scanner.nextLong();
        scanner.nextLine();

        Optional<Tournament> optionalTournament = tournamentService.findTournamentById(tournamentId);
        if (optionalTournament.isPresent()) {
            Tournament tournamentToUpdate = optionalTournament.get();
            System.out.println("Updating tournament: " + tournamentToUpdate.getTitle());

            System.out.print("Enter new title (leave blank to keep current): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                tournamentToUpdate.setTitle(newTitle);
            }

            System.out.print("Enter new start date (YYYY-MM-DD) or leave blank to keep current: ");
            String startDateInput = scanner.nextLine();
            if (!startDateInput.isEmpty()) {
                tournamentToUpdate.setStartDate(LocalDate.parse(startDateInput));
            }

            System.out.print("Enter new end date (YYYY-MM-DD) or leave blank to keep current: ");
            String endDateInput = scanner.nextLine();
            if (!endDateInput.isEmpty()) {
                tournamentToUpdate.setEndDate(LocalDate.parse(endDateInput));
            }

            System.out.print("Enter new spectator count (leave blank to keep current): ");
            String spectatorCountInput = scanner.nextLine();
            if (!spectatorCountInput.isEmpty()) {
                tournamentToUpdate.setSpectatorCount(Integer.parseInt(spectatorCountInput));
            }

            System.out.print("Enter new estimated duration (in minutes) or leave blank to keep current: ");
            String estimatedDurationInput = scanner.nextLine();
            if (!estimatedDurationInput.isEmpty()) {
                tournamentToUpdate.setEstimatedDuration(Integer.parseInt(estimatedDurationInput));
            }

            System.out.print("Enter new break time (in minutes) or leave blank to keep current: ");
            String breakTimeInput = scanner.nextLine();
            if (!breakTimeInput.isEmpty()) {
                tournamentToUpdate.setBreakTime(Integer.parseInt(breakTimeInput));
            }

            System.out.print("Enter new ceremony time (in minutes) or leave blank to keep current: ");
            String ceremonyTimeInput = scanner.nextLine();
            if (!ceremonyTimeInput.isEmpty()) {
                tournamentToUpdate.setCeremonyTime(Integer.parseInt(ceremonyTimeInput));
            }

            System.out.print("Would you like to update the tournament status? (yes/no): ");
            String updateStatusChoice = scanner.nextLine();
            if (updateStatusChoice.equalsIgnoreCase("yes")) {
                TournamentStatus newStatus = getTournamentStatusFromUser();
                tournamentToUpdate.setStatus(newStatus);
            }
            tournamentService.modifyTournament(tournamentToUpdate);
            System.out.println("Tournament updated successfully.");
        } else {
            System.out.println("Tournament not found with ID " + tournamentId);
        }
    }
    private void viewTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();

        if (tournaments.isEmpty()) {
            System.out.println("No tournaments available.");
            return;
        }
        System.out.println("\n=== List of Tournaments ===");
        for (Tournament tournament : tournaments) {
            System.out.println("ID: " + tournament.getId());
            System.out.println("Title: " + tournament.getTitle());
            System.out.println("Game: " + tournament.getGame());
            System.out.println("Start Date: " + tournament.getStartDate());
            System.out.println("End Date: " + tournament.getEndDate());
            System.out.println("Spectator Count: " + tournament.getSpectatorCount());
            System.out.println("Estimated Duration: " + tournament.getEstimatedDuration() + " minutes");
            System.out.println("Break Time: " + tournament.getBreakTime() + " minutes");
            System.out.println("Ceremony Time: " + tournament.getCeremonyTime() + " minutes");
            System.out.println("Status: " + tournament.getStatus());
            System.out.println("--------------------------");
        }
    }
    private void manageGames() {
        while (true) {
            System.out.println("\n=== Manage Games ===");
            System.out.println("1. Add Game");
            System.out.println("2. Update Game");
            System.out.println("3. Delete Game");
            System.out.println("4. View Games");
            System.out.println("5. Back to Menu");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addGame();
                    break;
                case 2:
                    updateGame();
                    break;
                case 3:
                    deleteGame();
                    break;
                case 4:
                    viewGames();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addGame() {
        scanner.nextLine();
        System.out.print("Enter game name: ");
        String name = scanner.nextLine();

        System.out.print("Enter difficulty (1-10): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter average duration (in minutes): ");
        int averageDuration = scanner.nextInt();
        scanner.nextLine();

        Game newGame = new Game(name, difficulty, averageDuration);
        gameService.addGame(newGame);

        System.out.println("Game added successfully.");
    }
    private void updateGame() {
        System.out.print("Enter game ID to update: ");
        Long gameId = scanner.nextLong();
        Optional<Game> optionalGame = gameService.findGameById(gameId);

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            System.out.println("Updating game: " + game);

            System.out.print("Enter new name (leave blank to keep current): ");
            scanner.nextLine();
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                game.setName(newName);
            }

            System.out.print("Enter new difficulty (1-10, leave blank to keep current): ");
            String difficultyInput = scanner.nextLine();
            if (!difficultyInput.isEmpty()) {
                game.setDifficulty(Integer.parseInt(difficultyInput));
            }

            System.out.print("Enter new average duration (in minutes, leave blank to keep current): ");
            String durationInput = scanner.nextLine();
            if (!durationInput.isEmpty()) {
                game.setAverageDuration(Integer.parseInt(durationInput));
            }

            gameService.modifyGame(game);
            System.out.println("Game updated successfully.");
        } else {
            System.out.println("Game not found.");
        }
    }
    private void deleteGame() {
        System.out.print("Enter game ID to delete: ");
        Long gameId = scanner.nextLong();
        Optional<Game> optionalGame = gameService.findGameById(gameId);

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            System.out.println("Game found: " + game);
            gameService.removeGame(gameId);
            System.out.println("Game deleted successfully.");
        } else {
            System.out.println("Game not found.");
        }
    }
    private void viewGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            System.out.println("No games available.");
            return;
        }
        System.out.println("\n=== List of Games ===");
        for (Game game : games) {
            System.out.println(game.toString());
        }
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsoleMenu consoleMenu = context.getBean(ConsoleMenu.class);
        consoleMenu.showMenu();
    }


}
