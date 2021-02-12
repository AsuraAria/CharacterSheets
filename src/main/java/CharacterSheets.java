import Interface.Framework;
import Interface.AbstractGUI;
import Interface.GUI.MainGUI;
import Utility.ConfigFile.CharacterSheetsConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/** L'Application qui a été développée.
 *
 * L'application est composée de plusieurs fenêtres : une fenêtre principale dans laquelle se déroule la majorité des
 * interactions, et des fenêtres secondaires (appellées dialogs) réservées à des traitements spécifiques.
 *
 * La fenêtre principale est composée de plusieurs {@link AbstractGUI}s (Graphical User Interface)
 * et la navigation entre ceux-cis est assurée par le {@link Framework}.
 *
 * @author Louri
 * @author Marc
 * */
public class CharacterSheets extends Application {
    /** Chemin de l'icône de l'application dans le jar. */
    private static final String ICON = "/Images/icon.png";

    /** Titre de la fenêtre */
    public static final String TITLE = "Escape the Shell";

    /** Si la fenêtre principale peut être redimensionnée. */
    private static final boolean RESIZEABLE = true;

    /** Couleur de fond des fenêtres de l'application. */
    private static final Paint BACKGROUND = Color.BEIGE;

    /** Crée la Scene et ses composants, puis montre le Stage principal (ie. affiche la fenêtre principale).
     * Initialise aussi le reste de l'application.
     *
     * @param primaryStage le Stage correspondant à la fenêtre */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Chargement de la configuration de l'application
        CharacterSheetsConfig config = new CharacterSheetsConfig();
        config.load(CharacterSheetsConfig.CONFIG);

        boolean fullscreen = (boolean) config.getValue(CharacterSheetsConfig.FULLSCREEN).get();
        int width = (int) config.getValue(CharacterSheetsConfig.WIDTH).get();
        int height = (int) config.getValue(CharacterSheetsConfig.HEIGHT).get();

        // Gestionnaire de navigation des GUIs
        Framework framework = new Framework(primaryStage, ICON, TITLE, fullscreen, width, height, RESIZEABLE, BACKGROUND, config);

        // Charge le menu principal
        framework.next(MainGUI.FXML);

        // Traitements à faire avant de quitter l'application (croix rouge, alt+F4, ...)
        // Libération des ressources, des fichiers temporaires créés ...
        Platform.setImplicitExit(false); // enlève le comportement par défaut de fermeture
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0); // quitte l'application toute entière
        });

        // Affiche la fenêtre (et ainsi le menu principal de l'application)
        primaryStage.show();
    }

    /** Lance l'application. Ce n'est pas le vrai point d'entrée du programme,
     * à cause de Maven & JavaFX qui demandent à ce qu'il soit dans une autre classe.
     *
     * La méthode <code>start</code> est appelée automatiquement après l'appel à <code>launch</code>. */
    public static void main(String[] args) {
        launch(args);
    }
}
