package edu.cnm.deepdive.controller;

import edu.cnm.deepdive.util.Resources;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This is the controller class for the view embodied by the
 * <code>main.fxml</code> layout file. To complete this class, the
 * implementations of the {@link #handle(ActionEvent)} and {@link
 * #newGame(ActionEvent)} methods must be completed. These methods (along with
 * any additional helper methods needed) will embody both the model of the
 * Concentration game, and the controller logic that connects the model to the
 * view.
 * <p>
 * A method must also be written to handle the Exit menu command.
 *
 * This is the controller with the {@link #handle(ActionEvent)} containing
 * the model for the game. This class also embodied the <code>main.fxml</code>
 * layout file. The , and {@link #newGame(ActionEvent)} starts with a random shuffle.
 */

public class Main implements EventHandler<ActionEvent> {

  /** Location of FXML layout file supported by this controller class.
   *
   * Additional field (ToggleButton one and two, int counter, string tileOneUrl and tileTwo)
   * is for the handle method.
   * */
  public static final String LAYOUT_PATH = Resources.LAYOUT_DIRECTORY + "main.fxml";

  @FXML
  private FlowPane tileTable;
  @FXML
  private Text totalStatus;
  @FXML
  private Text currentStatus;

  private List<ToggleButton> tiles;
  private String totalStatusFormat;
  private String currentStatusFormat;
  private int counter = 1;
  private String tileOneUrl;
  private String tileTwoUrl;
  private ToggleButton one;
  private ToggleButton two;
  private MediaPlayer win;
  private MediaPlayer welcome;
  private int guess;
  private int allGuess;
  private int match;
  private int gameSet = 0;

  /**
   * Initialize the controller for the main view (layout). This includes loading
   * the set of tiles, each of which is a {@link ToggleButton} instance.
   *
   * @throws URISyntaxException if the path to the tile images is invalid.
   * @throws IOException        if a tile image file can't be read.
   */
  public void initialize() throws URISyntaxException, IOException {
    tiles = Tile.getTiles();
    for (ToggleButton tile : tiles) {
      tile.setOnAction(this);
    }
    currentStatusFormat = currentStatus.getText();
    totalStatusFormat = totalStatus.getText();
    newGame(null);
  }

  private void updateDisplay() {
    double avgGuess = (gameSet > 0) ? (double) allGuess / match : 0;
    currentStatus.setText(String.format(currentStatusFormat, guess, match));
    totalStatus.setText(String.format(totalStatusFormat, gameSet, avgGuess));

  }

  /**
   * This handle method contains the model for the game where the clicked
   * ToggleButton gets disable and stay disable if the two image string url matches.
   * The matching image is stays flipped back when its a match. When two image string
   * is not a match then the toggleButton is re-enable and the image disable.
   * @param event            This is the main event of the main
   *
   */
  public void handle(ActionEvent event) {
    ToggleButton temp = ((ToggleButton) event.getSource());
    if (counter == 1) {
      if (one != null) {
        one.setSelected(!one.isSelected());
        one = null;
      }
      if (two != null) {
        two.setSelected(!two.isSelected());
        two = null;
      }
      temp.setDisable(true);
      tileOneUrl = ((ImageView) temp.getGraphic()).getImage().getUrl();
      one = temp;
      counter = 2;
    } else {
      tileTwoUrl = ((ImageView) temp.getGraphic()).getImage().getUrl();
      two = temp;
      guess++;
      if (tileOneUrl.equals(tileTwoUrl)) {
        two.setDisable(true);
        winSound();
        match++;
        if (match >= tiles.size() / 2) {
          gameSet++;
          allGuess += guess;
          // Increment number of games
        }
      } else {
        one.setDisable(false);
      }
//      two.setSelected(false);
//      one.setSelected(false);
      counter = 1;
      updateDisplay();
    }

    // TODO Add logic for handling clicks on tiles.
  }

  /**
   *newGame runs when the application starts
   *along with a shuffle from the Collections.
   * @param event
   */
  @FXML
  private void newGame(ActionEvent event) {
    tileTable.getChildren().clear();
    Collections.shuffle(tiles);
    welcomeSound();
    for (ToggleButton tile : tiles) {
      tile.setSelected(false);
      tile.setDisable(false);
      tileTable.getChildren().add(tile);
    }
    one = null;
    two = null;
    counter = 1;
    updateDisplay();
  }

  public List<ToggleButton> getTiles() {
    return tiles;
  }

  public void winSound() {
      // Open an audio input stream.
      File soundFile = new File("src/resources/win-effect.mp3");
      // Get a sound clip resource.
      win = new MediaPlayer(new Media(soundFile.toURI().toString()));
      // Open audio clip and load samples from the audio input stream.
      win.play();
  }

  public void welcomeSound() {
    // Open an audio input stream.
    File soundFile = new File("src/resources/welcome.mp3");
    // Get a sound clip resource.
    welcome = new MediaPlayer(new Media(soundFile.toURI().toString()));
    // Open audio clip and load samples from the audio input stream.
    welcome.play();
  }

  /**
   * This quit method is to exit the application.
   */
  public void quit() {
    Platform.exit();
    System.exit(0);
  }
}
