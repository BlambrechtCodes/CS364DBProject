package com.example;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DatabaseViewController {

    @FXML
    private TabPane tabPane;
    @FXML
    private TextField searchField;

    private final Map<Integer, String> teamNameMap = new HashMap<>();

    public void initialize() {
        try {
            ObservableList<Team> teams = CompanyDBCrud.getAllTeams();
            // Load team names into the map for quick lookup
            for (Team team : teams) {
                teamNameMap.put(team.getTeamId(), team.getName());
            }

            createTableTab("Team", teams, Team.class);
            createTableTab("Player", CompanyDBCrud.getAllPlayers(), Player.class);
            createTableTab("Player Stats", CompanyDBCrud.getAllPlayerGameStats(), PlayerGameStat.class);
            createTableTab("Game", CompanyDBCrud.getAllGames(), Game.class);
            createTableTab("Bracket", CompanyDBCrud.getAllBrackets(), Bracket.class);
            createTableTab("Schedule", CompanyDBCrud.getAllSchedules(), Schedule.class);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load data from the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private <T> void createTableTab(String tableName, ObservableList<T> data, Class<T> clazz) {
        TableView<T> table = new TableView<>();

        // Create columns dynamically based on the class structure
        if (!data.isEmpty()) {
            T firstItem = data.get(0);
            Field[] fields = firstItem.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                TableColumn<T, String> column = new TableColumn<>(fieldName);

                // Convert field name to capitalized format
                String capitalizedFieldName = Arrays.stream(fieldName.split("(?=[A-Z])"))
                        .map(s -> {
                            if (s.equalsIgnoreCase("Id")) {
                                return "ID"; // Keep "ID" as is
                            } else if (s.equalsIgnoreCase("I")) {
                                return "I";
                            } else {
                                return s.substring(0, 1).toUpperCase() + s.substring(1);
                            }
                        })
                        .reduce("", (a, b) -> a + " " + b)
                        .trim()
                        .replace(" I D", " ID"); //Special case
                column.setText(capitalizedFieldName); // Set column header text

                if (tableName.equals("Player Stats")) {
                    // Special handling for TeamID column in PlayerGameStats table
                    if (fieldName.equals("teamID")) {
                        column.setCellValueFactory(cellData -> {
                            try {
                                PlayerGameStat stat = (PlayerGameStat) cellData.getValue();
                                int teamId = stat.getTeamID();
                                String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                                return new SimpleStringProperty(teamId + " (" + teamName + ")");
                            } catch (Exception e) {
                                return new SimpleStringProperty("Error");
                            }
                        });
                    } else {
                        // Default cell value factory
                        column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                    }
                }
                else{
                     column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                }
                  if (tableName.equals("Bracket") && fieldName.equals("champion")) {
                    column.setCellValueFactory(cellData -> {
                        try {
                            Bracket bracket = (Bracket) cellData.getValue(); // Cast to Bracket
                            int championTeamId = bracket.getChampion();
                            String teamName = teamNameMap.getOrDefault(championTeamId, "Unknown Team");
                            return new SimpleStringProperty(championTeamId + " (" + teamName + ")");
                        } catch (Exception e) {
                            return new SimpleStringProperty("Error");
                        }
                    });
                }
                  // Special handling for TeamID column in Player table
                   else if (tableName.equals("Player") && fieldName.equals("teamId")) {
                    column.setCellValueFactory(cellData -> {
                        try {
                            Player player = (Player) cellData.getValue(); // Cast to Player
                            int teamId = player.getTeamId();
                            String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                            return new SimpleStringProperty(teamId + " (" + teamName + ")");
                        } catch (Exception e) {
                            return new SimpleStringProperty("Error");
                        }
                    });
                }
                 // Special handling for TeamID column in Game table
                  else if (tableName.equals("Game") && (fieldName.equals("team1Id") || fieldName.equals("team2Id"))) {
                    column.setCellValueFactory(cellData -> {
                        try {
                            Game game = (Game) cellData.getValue(); // Cast to Game
                            int teamId = (fieldName.equals("team1Id")) ? game.getTeam1Id() : game.getTeam2Id();
                            String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                            return new SimpleStringProperty(teamId + " (" + teamName + ")");
                        } catch (Exception e) {
                            return new SimpleStringProperty("Error");
                        }
                    });
                }
                 else{
                     column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                }
                table.getColumns().add(column);
            }

            // Add filtering
            FilteredList<T> filteredData = new FilteredList<>(data, p -> true);
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(item -> {
                    // If search text is empty, display all data
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare search text with data in each column
                    String lowerCaseFilter = newValue.toLowerCase();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        try {
                            Object value = field.get(item);
                             String stringValue = null;

                            if (tableName.equals("Player Stats") && field.getName().equals("teamID")) {
                                // For Player Stats table, use the formatted "teamID (TeamName)" for searching
                                int teamId = (int) value;
                                String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                                stringValue = (teamId + " (" + teamName + ")").toLowerCase();
                            }
                            else if (tableName.equals("Bracket") && field.getName().equals("champion")) {
                                // For Bracket table, use the formatted "teamID (TeamName)" for searching
                                int teamId = (int) value;
                                String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                                stringValue = (teamId + " (" + teamName + ")").toLowerCase();
                            }
                             else if (tableName.equals("Player") && field.getName().equals("teamId")) {
                                // For Player table, use the formatted "teamID (TeamName)" for searching
                                int teamId = (int) value;
                                String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                                stringValue = (teamId + " (" + teamName + ")").toLowerCase();
                            }
                             else if (tableName.equals("Game") && (field.getName().equals("team1Id") || field.getName().equals("team2Id"))) {
                                // For Game table, use the formatted "teamID (TeamName)" for searching
                                int teamId = (int) value;
                                String teamName = teamNameMap.getOrDefault(teamId, "Unknown Team");
                                stringValue = (teamId + " (" + teamName + ")").toLowerCase();
                            }
                            else if (value != null){
                                 stringValue = value.toString().toLowerCase();
                            }
                            if (stringValue != null && stringValue.contains(lowerCaseFilter)) {
                                return true; // Match found in any column
                            }
                        } catch (IllegalAccessException e) {
                        }
                    }
                    return false; // No match found
                });
            });

            // Wrap the FilteredList in a SortedList
            SortedList<T> sortedData = new SortedList<>(filteredData);

            // Bind the SortedList comparator to the TableView comparator
            sortedData.comparatorProperty().bind(table.comparatorProperty());

            // Set the sorted and filtered data to the table
            table.setItems(sortedData);
        }

        // Wrap the TableView in an AnchorPane to make it resizable within the Tab
        AnchorPane tableContainer = new AnchorPane(table);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);

        Tab tab = new Tab(tableName);
        tab.setContent(tableContainer);
        tabPane.getTabs().add(tab);
    }
}
