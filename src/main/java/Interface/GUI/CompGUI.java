package Interface.GUI;

import Interface.AbstractGUI;
import Utility.JsonFile.JsonUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.*;

/** Menu de sélection de challenge.
 *
 * @author Marc
 * @author Louri */
public class CompGUI extends AbstractGUI {
    public static final String FXML = "/FXML/Comp.fxml";
    private static final String HTML_HELP = "help/index.html";

    private static final String JSONPath = "W4/W4Competences.json";
    private static final int GAP = 30;
    public CheckBox basicBox;

    private JsonUtility JU = new JsonUtility();

    //FXML VARIABLES
    public ListView compList;
    public TextArea nameTA;
    public TextArea caracTA;
    public TextArea descTA;


    @Override
    public void reset(Object[] args)
    {
        refresh();
    }

    @Override
    public void init(Object[] args) {
        assert args.length == 1;
        loadFilesInfos();
        loadCompetences();
    }

    @Override
    public void dispose(){}

    /** Recharge les informations de tous les challenges une fois de plus.
     * Appelé lors du clic sur le bouton Rafraîchir. */
    @FXML
    private void refresh()
    {
        loadFilesInfos();
        loadCompetences();
    }

    /** Charge les informations de tous les challenges et les met sous forme de tiles.
     * N'est appelé que lorsque la grille est vide / a été vidée. */
    private void loadFilesInfos(){
    }

    /** Affiche les options.
     * Appelé lors de l'appui sur le bouton Option . */
    @FXML
    private void selectOption() throws IOException {
        framework.showOption();
    }

    /** Revient au menu principal.
     * Appelé lors de l'appui sur le bouton Menu Principal . */
    @FXML
    private void selectReturn(){

            framework.previous(); // retourne au GUI précédent (on était dans la fenêtre principale)
    }

    //INTERRACTION FUNCTIONS
    public void addComp(ActionEvent actionEvent)
    {
        JU.addComp("W4/W4Competences.json", nameTA.getText(),caracTA.getText(),descTA.getText(), basicBox.isSelected());
        refresh();
    }

    //FUNCTIONS

    private void loadCompetences()
    {
        //Purge graphic Object
        compList.getItems().clear();

        //Create list
        List<String> names = JU.getAllNames(JSONPath);

        //Order list
        names.sort( Comparator.comparing( String::toString ) );
        for (String compName : names)
        {
            compList.getItems().add(compName);
        }

    }


    /** Compare les coordonnées selon l'ordre lexicographique sur (Y,X) .
     *
     * @param x1 numéro de colonne du 1er couple
     * @param y1 numéro de ligne du 1er couple
     * @param x2 numéro de colonne du 2nd couple
     * @param y2 numéro de ligne du 2nd couple
     * @return selon l'ordre lexicographique :
     *         -1 si (y1,x1) < (y2,x2)
     *          0 si (y1,x1) = (y2,x2)
     *         +1 si (y1,x1) > (y2,x2) */
    private static int compareCoords(Integer x1, Integer y1, Integer x2, Integer y2){
        assert x1 != null && y1 != null && x2 != null && y2 != null;

        if(y1 < y2 || (y1.equals(y2) && x1 < x2))
            return -1;
        else if(y1.equals(y2) && x1.equals(x2))
            return 0;
        else
            return +1;
    }

    /** Décrémente les coordonnées (x,y) de 1.
     *
     * Équivalent à :
     * <code>
     *     x' = x-1;
     *     if(x'<0){
     *         y' = y-1;
     *         x' = n-1;
     *     }
     *     return (x',y')
     * </code>
     *
     * @param x numéro de colonne
     * @param y numéro de ligne
     * @param n nombre total de colonnes
     * @return le couple (x', y') résultant de la décrémentation */
    private static Map.Entry<Integer, Integer> decrementCoords(int x, int y, int n){
        // attention à l'ordre, donc il vaut mieux les laisser en séquentiel comme ça
        y -= (x == 0) ? 1 : 0;
        x = (x-1+n) % n;

        return new AbstractMap.SimpleEntry<>(x, y);
    }


}
