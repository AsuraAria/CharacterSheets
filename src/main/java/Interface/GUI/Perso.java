package Interface.GUI;

import Interface.AbstractGUI;
import Utility.JsonFile.JsonUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Menu de sélection de challenge.
 *
 * @author Marc
 * @author Louri
 */
public class Perso extends AbstractGUI {
    public static final String FXML = "/FXML/Perso.fxml";
    private static final String HTML_HELP = "help/index.html";

    private static final String JSONPath = "W4/Fiches/Siegfried.json";


    //FXML IMPORTS
    public GridPane tabCarac; //carac
    public GridPane tabComp1; //basique 1
    public GridPane tabComp2; //basique 2
    public GridPane tabComp3; //avancée

    public Text ccC; //CC courante
    public Text ctC; //CT courante
    public Text fC; //F courante
    public Text eC; //E courante
    public Text iC; //I courante
    public Text agC; //Ag courante
    public Text dexC; //Dex courante
    public Text intC; //Int courante
    public Text fmC; //FM courante
    public Text socC; //Soc courante

    public TextField nameTA;
    public TextField speciesTA;
    public TextField classTA;
    public TextField statusTA;
    public TextField carreerTA;
    public TextField levelTA;
    public TextField pathTA;
    public TextField ageTA;
    public TextField heightTA;
    public TextField hairTA;
    public TextField eyesTA;

    public TextArea dpTXT;
    public TextArea luckpTXT;
    public TextArea respTXT;
    public TextArea detPTXT;
    public TextArea motivTXT;
    public TextArea mvtTXT;
    public TextArea walkTXT;
    public TextArea runTXT;

    private JsonUtility JU = new JsonUtility();
    public Node[][] caracNodeArray = new Node[][]{};
    public ArrayList<String> descArray = new ArrayList<>();
    public ArrayList<String> pointArray = new ArrayList<>();

    //FXML VARIABLES

    @Override
    public void reset(Object[] args) {
        refresh();
    }

    @Override
    public void init(Object[] args) {
        assert args.length == 1;
        caracNodeArray =  gridPaneToArray(tabCarac, 4,11);
    }

    private Node[][] gridPaneToArray(GridPane GP, int row, int col) {
        Node[][] array = new Node[row][col];
        for (Node node : GP.getChildren()) {
            int rowID = 0;
            int colID = 0;
            try
            {
                rowID = GP.getRowIndex(node);
            } catch (Exception ignored){}
            try
            {
                colID = GP.getColumnIndex(node);
            } catch (Exception ignored){}

            array[rowID][colID] = node;
        }
        return array;
    }

    private void loadCarac()
    {
        for (int i=1;i<11;i++)
        {
            int newValue = Integer.parseInt(((TextArea) caracNodeArray[1][i]).getText());
            try{
            newValue += Integer.parseInt(((Text) caracNodeArray[2][i]).getText());}catch (Exception ignored){}
            ((Text) caracNodeArray[3][i]).setText(String.valueOf(newValue));
        }
    }

    private void writeCompt()
    {
        Node[][] compArr1 = gridPaneToArray(tabComp1,14,5);
        Node[][] compArr2 = gridPaneToArray(tabComp2,14,5);
        Node[][] compArr3 = gridPaneToArray(tabComp3,14,5);
        loadCompValue(compArr1);
        loadCompValue(compArr2);
        //loadCompValue(compArr3);


    }

    private void loadCompValue(Node[][] compArr)
    {
        for(int i=1;i<14;i++)
        {

            String carac = ((Text)compArr[i][1]).getText();
            switch (carac)
            {
                case "CC":
                    ((Text)compArr[i][2]).setText(String.valueOf(ccC.getText()));
                    break;
                case "CT":
                    ((Text)compArr[i][2]).setText(String.valueOf(ctC.getText()));
                    break;
                case "F":
                    ((Text)compArr[i][2]).setText(String.valueOf(fC.getText()));
                    break;
                case "E":
                    ((Text)compArr[i][2]).setText(String.valueOf(eC.getText()));
                    break;
                case "I":
                    ((Text)compArr[i][2]).setText(String.valueOf(iC.getText()));
                    break;
                case "Ag":
                    ((Text)compArr[i][2]).setText(String.valueOf(agC.getText()));
                    break;
                case "Dex":
                    ((Text)compArr[i][2]).setText(String.valueOf(dexC.getText()));
                    break;
                case "Int":
                    ((Text)compArr[i][2]).setText(String.valueOf(intC.getText()));
                    break;
                case "FM":
                    ((Text)compArr[i][2]).setText(String.valueOf(fmC.getText()));
                    break;
                case "Soc":
                    ((Text)compArr[i][2]).setText(String.valueOf(socC.getText()));
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            int newValue = Integer.parseInt(((Text) compArr[i][2]).getText());
            try {
                newValue += Integer.parseInt(((Text) compArr[i][3]).getText());
            }catch (Exception ignored){}
            ((Text)compArr[i][4]).setText(String.valueOf(newValue));

        }
    }

    @Override
    public void dispose() {
    }

    /**
     * Recharge les informations de tous les challenges une fois de plus.
     * Appelé lors du clic sur le bouton Rafraîchir.
     */
    @FXML
    private void refresh()
    {
        descArray = loadDescArray();
        pointArray = loadPointArray();
        caracNodeArray =  gridPaneToArray(tabCarac, 4,11);
        loadFilesInfos();
        loadCarac();
        writeCompt();
    }

    private ArrayList<String> loadPointArray()
    {
        ArrayList<String> Array = new ArrayList<String>();
        Array.add(dpTXT.getText());
        Array.add(luckpTXT.getText());
        Array.add(respTXT.getText());
        Array.add(detPTXT.getText());
        Array.add(motivTXT.getText());
        Array.add(mvtTXT.getText());
        Array.add(walkTXT.getText());
        Array.add(runTXT.getText());
        return Array;
    }

    private ArrayList<String> loadDescArray()
    {
        ArrayList<String> Array = new ArrayList<String>();

        Array.add(nameTA.getText());
        Array.add(speciesTA.getText());
        Array.add(classTA.getText());
        Array.add(statusTA.getText());
        Array.add(carreerTA.getText());
        Array.add(levelTA.getText());
        Array.add(pathTA.getText());
        Array.add(ageTA.getText());
        Array.add(heightTA.getText());
        Array.add(hairTA.getText());
        Array.add(eyesTA.getText());
        return Array;
    }

    /**
     * Charge les informations de tous les challenges et les met sous forme de tiles.
     * N'est appelé que lorsque la grille est vide / a été vidée.
     */
    private void loadFilesInfos() {
    }

    /**
     * Affiche les options.
     * Appelé lors de l'appui sur le bouton Option .
     */
    @FXML
    private void selectOption() throws IOException {
        framework.showOption();
    }

    /**
     * Revient au menu principal.
     * Appelé lors de l'appui sur le bouton Menu Principal .
     */
    @FXML
    private void selectReturn() {

        framework.previous(); // retourne au GUI précédent (on était dans la fenêtre principale)
    }

    //INTERRACTION FUNCTIONS

    //FUNCTIONS


    /**
     * Compare les coordonnées selon l'ordre lexicographique sur (Y,X) .
     *
     * @param x1 numéro de colonne du 1er couple
     * @param y1 numéro de ligne du 1er couple
     * @param x2 numéro de colonne du 2nd couple
     * @param y2 numéro de ligne du 2nd couple
     * @return selon l'ordre lexicographique :
     * -1 si (y1,x1) < (y2,x2)
     * 0 si (y1,x1) = (y2,x2)
     * +1 si (y1,x1) > (y2,x2)
     */
    private static int compareCoords(Integer x1, Integer y1, Integer x2, Integer y2) {
        assert x1 != null && y1 != null && x2 != null && y2 != null;

        if (y1 < y2 || (y1.equals(y2) && x1 < x2))
            return -1;
        else if (y1.equals(y2) && x1.equals(x2))
            return 0;
        else
            return +1;
    }

    /**
     * Décrémente les coordonnées (x,y) de 1.
     * <p>
     * Équivalent à :
     * <code>
     * x' = x-1;
     * if(x'<0){
     * y' = y-1;
     * x' = n-1;
     * }
     * return (x',y')
     * </code>
     *
     * @param x numéro de colonne
     * @param y numéro de ligne
     * @param n nombre total de colonnes
     * @return le couple (x', y') résultant de la décrémentation
     */
    private static Map.Entry<Integer, Integer> decrementCoords(int x, int y, int n) {
        // attention à l'ordre, donc il vaut mieux les laisser en séquentiel comme ça
        y -= (x == 0) ? 1 : 0;
        x = (x - 1 + n) % n;

        return new AbstractMap.SimpleEntry<>(x, y);
    }

    public void loadClicked(ActionEvent actionEvent)
    {

        //DESC
        nameTA.setText(JU.searchFiche(JSONPath, "Description", "Nom"));
        speciesTA.setText(JU.searchFiche(JSONPath,"Description", "Race"));
        classTA.setText(JU.searchFiche(JSONPath,"Description", "Classe"));
        statusTA.setText(JU.searchFiche(JSONPath,"Description", "Statut"));
        carreerTA.setText(JU.searchFiche(JSONPath,"Description", "Carrière"));
        levelTA.setText(JU.searchFiche(JSONPath,"Description", "Échelon"));
        pathTA.setText(JU.searchFiche(JSONPath,"Description", "Schéma"));
        ageTA.setText(JU.searchFiche(JSONPath,"Description", "Age"));
        heightTA.setText(JU.searchFiche(JSONPath,"Description", "Taille"));
        hairTA.setText(JU.searchFiche(JSONPath,"Description", "Cheveux"));
        eyesTA.setText(JU.searchFiche(JSONPath,"Description", "Yeux"));

        //CARAC décalage de 1, le 0 est du texte
        ((TextArea)caracNodeArray[1][1]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "CC"));
        ((TextArea)caracNodeArray[1][2]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "CT"));
        ((TextArea)caracNodeArray[1][3]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "F"));
        ((TextArea)caracNodeArray[1][4]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "E"));
        ((TextArea)caracNodeArray[1][5]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "I"));
        ((TextArea)caracNodeArray[1][6]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "Ag"));
        ((TextArea)caracNodeArray[1][7]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "Dex"));
        ((TextArea)caracNodeArray[1][8]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "Int"));
        ((TextArea)caracNodeArray[1][9]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "FM"));
        ((TextArea)caracNodeArray[1][10]).setText(JU.searchFiche(JSONPath,"Caractéristiques", "Soc"));

        //POINT


    }

    public void saveClicked(ActionEvent actionEvent)
    {
        refresh();
        //carac array
        ArrayList<String> caracArray = new ArrayList<>();
        for (int i=1;i<11;i++)
        {
            caracArray.add(((TextArea) this.caracNodeArray[1][i]).getText());
        }

        //update
        JU.updateCharacter(JSONPath, descArray, caracArray, pointArray);
    }
}
