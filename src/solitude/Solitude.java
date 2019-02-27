/*
 * @author: Martins Anerua.
 * Martins Anerua © 2018-2019.
 * All rights reserved.
 */
package solitude;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 31-12-2018
 * @author MARTINS
 */
public class Solitude extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SolitudeLayout.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Solitude");
        stage.setResizable(false);
        stage.centerOnScreen();
        
        Image logo = new Image(Solitude.class.getResourceAsStream("/solitude/icns/tac_icn.png"));
        stage.getIcons().add(logo);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
