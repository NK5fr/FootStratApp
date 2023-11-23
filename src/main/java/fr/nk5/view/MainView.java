package fr.nk5.view;

import fr.nk5.model.MessagePlayer;
import fr.nk5.model.TeamManager;
import fr.univlille.iutinfo.utils.Observer;
import fr.univlille.iutinfo.utils.Subject;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView extends Stage implements Observer {

    private final TeamManager teamManager;
    private final Pane terrain = new Pane();
    private final Button addPlayerBlue = new Button("Add Player Blue");
    private final Button addPlayerRed = new Button("Add Player Red");

    private final Button delPlayerRed = new Button("Delete Player Red");
    private final Button delPlayerBlue = new Button("Delete Player Blue");
    private final ListView<String> playerListBlue = new ListView<>();

    private final ListView<String> playerListRed = new ListView<>();
    private static final int WIDTH = 1250;
    private static final int HEIGHT = 500;

    public MainView(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.teamManager.attach(this);
        setActions();
        this.setMainScene();
        this.setTitle("FootStratApp");
        this.setResizable(false);
        this.show();
    }

    public void setPitch() {
        Canvas pitch = new Canvas(1250, 500);
        GraphicsContext gc = pitch.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, 1000, 500);
        gc.setFill(Color.WHITE);
        gc.fillRect(20, 20, 10, 460);
        gc.fillRect(20, 20, 960, 10);
        gc.fillRect(970, 20, 10, 460);
        gc.fillRect(20, 470, 960, 10);
        gc.fillOval(375, 125, 250, 250);
        gc.setFill(Color.GREEN);
        gc.fillOval(385, 135, 230, 230);
        gc.setFill(Color.WHITE);
        gc.fillRect(495, 20, 10, 460);
        gc.fillRect(795, 125, 175, 10);
        gc.fillRect(795, 375, 175, 10);
        gc.fillRect(795, 125, 10, 250);
        gc.fillRect(30, 125, 175, 10);
        gc.fillRect(30, 375, 175, 10);
        gc.fillRect(195, 125, 10, 250);
        terrain.getChildren().add(pitch);
    }

    private VBox setMenu() {
        playerListRed.getItems().add("none");
        playerListBlue.getItems().add("none");
        VBox menu = new VBox();
        HBox teamBlue = new HBox();
        HBox teamRed = new HBox();
        teamBlue.getChildren().addAll(addPlayerBlue, delPlayerBlue);
        teamRed.getChildren().addAll(addPlayerRed, delPlayerRed);
        menu.getChildren().add(teamBlue);
        menu.getChildren().add(playerListBlue);
        menu.getChildren().add(teamRed);
        menu.getChildren().add(playerListRed);
        addPlayerBlue.setMinWidth(125);
        addPlayerRed.setMinWidth(125);
        delPlayerRed.setMinWidth(125);
        delPlayerBlue.setMinWidth(125);
        return menu;
    }

    private void setMainScene() {
        setPitch();
        HBox root = new HBox();
        root.getChildren().add(terrain);
        root.getChildren().add(setMenu());
        this.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    @Override
    public void update(Subject subject) {
        new AlertView(Alert.AlertType.ERROR, "UnknowError", "UnknowError", "Unknown error");
    }

    @Override
    public void update(Subject subject, Object o) {
        if(o instanceof String message){
            new AlertView(Alert.AlertType.ERROR, "Error", "Error", message);
        }else{
            MessagePlayer messagePlayer = (MessagePlayer) o;
            if(messagePlayer.getMessage().equals("add")){
                addPlayer(messagePlayer);
            }else if(messagePlayer.getMessage().equals("remove")){
                removePlayer(messagePlayer);
            }
        }
    }

    private void removePlayer(MessagePlayer messagePlayer) {
        if(messagePlayer.getPlayerTeam().equals("Blue")){
            playerListBlue.getItems().remove(messagePlayer.getPlayerName());
            terrain.getChildren().remove(new PlayerImage(500, 250, 20, Color.BLUE, messagePlayer.getPlayerName()));
            playerListBlue.getSelectionModel().clearSelection();
        }else if(messagePlayer.getPlayerTeam().equals("Red")){
            playerListRed.getItems().remove(messagePlayer.getPlayerName());
            terrain.getChildren().remove(new PlayerImage(500, 250, 20, Color.RED, messagePlayer.getPlayerName()));
            playerListRed.getSelectionModel().clearSelection();
        }
    }

    private void addPlayer(MessagePlayer messagePlayer) {
        if(messagePlayer.getPlayerTeam().equals("Blue")){
            playerListBlue.getItems().add(messagePlayer.getPlayerName());
            PlayerImage playerImage = new PlayerImage(500, 250, 20, Color.BLUE, messagePlayer.getPlayerName());
            terrain.getChildren().add(playerImage);
            setPlayerImageAction(playerImage);
        }else if(messagePlayer.getPlayerTeam().equals("Red")){
            playerListRed.getItems().add(messagePlayer.getPlayerName());
            PlayerImage playerImage = new PlayerImage(500, 250, 20, Color.RED, messagePlayer.getPlayerName());
            terrain.getChildren().add(playerImage);
            setPlayerImageAction(playerImage);
        }
    }



    public void setActions(){
        addPlayerBlue.setOnAction(event -> new AddPlayerView(teamManager, "Blue"));
        addPlayerRed.setOnAction(event -> new AddPlayerView(teamManager, "Red"));
        delPlayerBlue.setOnAction(event -> {
            String pn = playerListBlue.getSelectionModel().getSelectedItem();
            if(pn != null && !pn.equals("none")){
                teamManager.removePlayer(pn, "Blue");
            }
        });
        delPlayerRed.setOnAction(event -> {
            String pn = playerListRed.getSelectionModel().getSelectedItem();
            if(pn != null && !pn.equals("none")){
                teamManager.removePlayer(pn, "Red");
            }
        });
        playerListBlue.getSelectionModel().getSelectedItems().addListener(new BlueListeListener());
        playerListRed.getSelectionModel().getSelectedItems().addListener(new RedListeListener());
    }

    private void setPlayerImageAction(PlayerImage pi){
        pi.setOnMouseDragged(f -> {
            if(f.getX() >= 20 && f.getX() <= 980){
                pi.setCenterX(f.getX());
            }
            if(f.getY() >= 20 && f.getY() <= 480){
                pi.setCenterY(f.getY());
            }
        });
    }

    class BlueListeListener implements ListChangeListener<String> {

        @Override
        public void onChanged(Change<? extends String> change) {
            for(Node n : terrain.getChildren()){
                if(n instanceof PlayerImage pi){
                    if(change.getList().contains(pi.toString())){
                        pi.setFill(Color.LIGHTBLUE);
                    }else if(pi.getFill().equals(Color.LIGHTBLUE)){
                        pi.setFill(Color.BLUE);
                    }
                }
            }
        }
    }

    class RedListeListener implements ListChangeListener<String> {

        @Override
        public void onChanged(Change<? extends String> change) {
            for(Node n : terrain.getChildren()){
                if(n instanceof PlayerImage pi){
                    if(change.getList().contains(pi.toString())){
                        pi.setFill(Color.PINK);
                    }else if(pi.getFill().equals(Color.PINK)){
                        pi.setFill(Color.RED);
                    }
                }
            }
        }
    }
}
