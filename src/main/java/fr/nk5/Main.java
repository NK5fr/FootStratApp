package fr.nk5;

import fr.nk5.model.TeamManager;
import fr.nk5.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new MainView(new TeamManager());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
