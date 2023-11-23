package fr.nk5.view;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertView extends Alert {
        private static final int HEIGHT = 300;
        public AlertView(AlertType alertType, String title, String header, String content) {
            super(alertType);
            this.setTitle(title);
            this.setResizable(false);
            this.setHeight(HEIGHT);
            this.setHeaderText(header);
            this.setContentText(content);
            this.initModality(Modality.APPLICATION_MODAL);
            this.show();
        }
}
