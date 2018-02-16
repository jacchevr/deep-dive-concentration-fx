package edu.cnm.deepdive.util;

/**
 * Resource-related constants used by multiple classes.
 */
public class Resources {

  private static final String BASE_DIRECTORY = "resources/";

  /** Directory containing images. */
  public static final String IMAGE_DIRECTORY = BASE_DIRECTORY + "images/";
  /** Directory containing FXML layout files. */
  public static final String LAYOUT_DIRECTORY = BASE_DIRECTORY + "layouts/";
  /** Directory containing {@link java.util.ResourceBundle} properties files. */
  public static final String STRING_DIRECTORY = BASE_DIRECTORY + "strings/";
  /** Default properties file containing strings used in the user interface. */
  public static final String UI_STRINGS_BASENAME = STRING_DIRECTORY + "ui";

}
