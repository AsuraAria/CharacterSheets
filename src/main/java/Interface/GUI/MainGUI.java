package Interface.GUI;


import Interface.AbstractGUI;
import Utility.JsonFile.JsonUtility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


/** Menu principal.
 * Propose de naviguer vers la création ou la modification de ficher perso.
 *
 * @author Marc
 * @author Louri */
public class MainGUI extends AbstractGUI {

    //VARIABLE
    public static final String FXML = "/FXML/Main.fxml";
    private static final String HTML_HELP = "help/index.html";

    //OVERRIDE
    @Override
    public void reset(Object[] args) {
        // aucun traitement particulier lors d'un retour
    }

    @Override
    public void init(Object[] args) {
        framework.loadSettings();
        // Après avoir affiché la fenêtre, affiche par dessus le dialog de login.
        test();
    }

    @Override
    public void dispose(){}


    //FXML FUNCTION
    /** Affiche l'aide de l'application.
     * Appelé lors de l'appui sur le bouton Information . */
    @FXML
    private void selectInfo(){
/*        framework.showInfo(HTML_HELP);*/
    }

    /** Affiche les options.
     * Appelé lors de l'appui sur le bouton Option . */
    @FXML
    private void selectOption() throws IOException {
        framework.showOption();
    }

    /** Quitte l'application.
     * Appelé lors de l'appui sur le bouton Quitter . */
    @FXML
    private void selectExit(){
        Platform.exit();
    }

    /** Navigue vers le choix d'un nouvelle fiche perso'.
     * Appelé lors de l'appui sur le bouton New . */
    public void selectB0(ActionEvent actionEvent) throws IOException {
        framework.next(CompGUI.FXML, null);
    }

    public void selectB1(ActionEvent actionEvent) throws IOException
    {
        framework.next(PersoGUI.FXML, null);
    }



    public void test()
    {
        JsonUtility JJ = new JsonUtility();
        //JJ.defaultJSON("W4/W4Competences.json");
    }
}
