package fr.nk5.view;

import fr.nk5.model.TeamManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPlayerView extends Stage {
    private final TeamManager teamManager;
    private final TextField playerName = new TextField();
    private final Button addPlayer = new Button("Add Player");
    private static final int WIDTH = 500;
    private static final int HEIGHT = 50;
    private final String color;

    public AddPlayerView(TeamManager teamManager, String color) {
        this.teamManager = teamManager;
        this.color = color;
        this.setAddPlayerScene();
        this.setActions();
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Add Player");
        this.setResizable(false);
        this.show();
    }

    private void setActions(){
        addPlayer.setOnAction(event -> addPlayerAction());
        playerName.setOnAction(event -> addPlayerAction());
    }

    private void addPlayerAction() {
        if (playerName.getText().equals("")){
            new AlertView(Alert.AlertType.ERROR, "Error", "Error", "Player name cannot be empty");
        } else {
            teamManager.addPlayer(playerName.getText(), color);
            this.close();
        }
    }

    public void setAddPlayerScene(){
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(new Label("Player Name : "));
        root.getChildren().add(playerName);
        Region spacer = new Region();
        spacer.setPrefWidth(10);
        root.getChildren().add(spacer);
        root.getChildren().add(addPlayer);
        this.setScene(new Scene(root, WIDTH, HEIGHT));
    }
}
