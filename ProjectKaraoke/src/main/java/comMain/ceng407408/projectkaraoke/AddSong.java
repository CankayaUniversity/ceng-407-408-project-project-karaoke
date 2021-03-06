/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comMain.ceng407408.projectkaraoke;

/**
 *
 * @author sevtap
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import comMain.ceng407408.projectkaraoke.*;
import static comMain.ceng407408.projectkaraoke.CreateAccount.stage;
import static comMain.ceng407408.projectkaraoke.MainApp.stage;
//import static comMain.ceng407408.projectkaraoke.MainApp.stage;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.ChangeListener;
import sun.applet.Main;
import javafx.scene.control.TextArea;

public class AddSong implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static Stage stage = new Stage();
    @FXML
    public TextField songNameGUI = new TextField();
    @FXML
    public TextArea lyricsGUI = new TextArea();
    @FXML
    public Button addSongGUI = new Button();
    @FXML
    public Button ppageGUI = new Button();
    @FXML
    public Label messageGUI = new Label();
    @FXML
    public Button musicBtn = new Button();
    @FXML
    public Label pathmessageGUI = new Label();
    @FXML
    public TextField timeTxt = new TextField();
     @FXML
    public Label nameMessage = new Label();
      @FXML
    public Label lyricMessage = new Label();
       @FXML
    public Label timeMessage = new Label();
    
    String path = "";

    @FXML
    public void ppageBtn() {
        try {
            replaceSceneContent("/fxml/AdminMainPage.fxml", 631, 316);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pathBtn() {

        FileChooser fc = new FileChooser();
        // fc.setInitialDirectory(); bu kodda path verirsek direk ordan açılıyor 
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("WAV files", "*.wav"));

        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            path = selectedfile.getAbsolutePath();
        } else {
            pathmessageGUI.setText("File Cannot Selected!!");
            pathmessageGUI.setVisible(true);
        }
    }

    @FXML
    public void addSongBtn() {
        Karaoke db = new Karaoke();

        String SongName = "";
        String Lyric = "";
        String Time="";
        int intTime = 0;

        SongName = songNameGUI.getText();
        Lyric = lyricsGUI.getText();
        Time = timeTxt.getText();
        intTime= Integer.parseInt(Time);
        
         if (songNameGUI.getText().isEmpty() == true) {
            nameMessage.setVisible(true);
            nameMessage.setText("Please Enter Song Name!");
        } else {
            nameMessage.setVisible(false);

        }

        if (lyricsGUI.getText().isEmpty() == true) {

            lyricMessage.setVisible(true);
            lyricMessage.setText("*");

        } else {
            lyricMessage.setVisible(false);

        }
        if (timeTxt.getText().isEmpty() == true) {

            timeMessage.setVisible(true);
            timeMessage.setText("Please Song Time!");

        } else {
            timeMessage.setVisible(false);
        }
      if (!songNameGUI.getText().isEmpty() && !lyricsGUI.getText().isEmpty() && !timeTxt.getText().isEmpty() && !path.isEmpty()) {

        int result = db.AddSong(SongName, Lyric, path,intTime);

        if (result == 1) {
            messageGUI.setText("Song Inserted Successfully!");
            messageGUI.setVisible(true);
        } else if (result == 0) {
            messageGUI.setText("Song Cannot Inserted!");
            messageGUI.setVisible(true);

        }
      }

    }

   private Parent replaceSceneContent(String fxml, int numX, int numY) throws Exception {
        Parent page;
        page = (Parent) FXMLLoader.load(getClass().getResource(fxml));

        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, numX, numY);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }

        //stage.getScene().setRoot(page);
        //stage.setScene(page.getScene());
        stage.setMinHeight(numY);
        stage.setMinWidth(numX);
        stage.setMaxHeight(numY);
        stage.setMaxWidth(numX);
        stage.setResizable(false);
        stage.setTitle("");
        stage.show();
        return page;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
