package Utility.JsonFile;

import JDR.W4.W4Competence;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtility
{
    public void defaultJSON(String filePath)
    {
        JSONObject compObject0 = new JSONObject();
        JSONObject compObject1 = new JSONObject();
        JSONObject compObject2 = new JSONObject();

        JSONObject compDetails0 = new JSONObject();
        JSONObject compDetails1 = new JSONObject();
        JSONObject compDetails2 = new JSONObject();

        compDetails0.put("Type", "type0");
        compDetails0.put("Description", "desc0");
        compDetails0.put("Basique", "true");
        compDetails1.put("Type", "type1");
        compDetails1.put("Description", "desc1");
        compDetails1.put("Basique", "true");
        compDetails2.put("Type", "type2");
        compDetails2.put("Description", "desc2");
        compDetails2.put("Basique", "false");

        compObject0.put("Compétence0", compDetails0);
        compObject1.put("Compétence1", compDetails1);
        compObject2.put("Compétence2", compDetails2);
        //


        //Add to list
        JSONArray compList = new JSONArray();
        compList.add(compObject0);
        compList.add(compObject1);
        compList.add(compObject2);

        //REWrite JSON file
        try (
                FileWriter file = new FileWriter(filePath)) {

            file.write(compList.toJSONString());
            file.flush();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public String searchFiche(String filePath, String part, String name)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        String infoValue = "";

        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray listJSON = (JSONArray) obj;

            //Iterate over array
            System.out.println("Recherche de " + name + " dans " + part);
            for (Object JO : listJSON)
            {
                if (JO instanceof JSONObject)
                {

                        JSONObject compObject = (JSONObject) ((JSONObject) JO).get(part);
                        System.out.println("YESSSSSSSSSSSSSSSSSSSSSSSS");
                        if (name.equals("CC"))
                        {
                            System.out.println("rzgegn");
                        }
                        infoValue =(String) compObject.get(name);
                        System.out.println("info is " + infoValue);


                }
            }

        } catch (Exception ignored) {}

        System.out.println(infoValue);
        return infoValue;

    }

    public void searchComp(String filePath, String name)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath))
        {

            W4Competence W4C = new W4Competence();

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray listJSON = (JSONArray) obj;

            //Iterate over employee array
            listJSON.forEach( emp -> parseComp( (JSONObject) emp, name, W4C ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static void parseComp(JSONObject comp, String name, W4Competence W4C)
    {
        try {

            //Get competence
            JSONObject compObject = (JSONObject) comp.get(name);

            //Get competence type
            String type = (String) compObject.get("Type");
            //Get competence description
            String desc = (String) compObject.get("Description");

            //Get if basic
            Boolean basic = Boolean.parseBoolean((String) compObject.get("Basique")) ;

            //Fill W4Competence object
            W4C.setName(name);
            W4C.setType(type);
            W4C.setDesc(desc);
            W4C.setBasic(basic);

            System.out.println(W4C.getName());
            System.out.println(W4C.getType());
            System.out.println(W4C.getDesc());
            System.out.println(W4C.getBasic());
        }catch (Exception e){}

    }

    public void addComp(String filePath, String name, String type, String desc, Boolean basic)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(filePath);
            Object obj = jsonParser.parse(reader);
            JSONArray JA = (JSONArray) obj;

            JSONObject compObject = new JSONObject();

            JSONObject compDetails = new JSONObject();
            compDetails.put("Type", type);
            compDetails.put("Description", desc);
            compDetails.put("Basique", basic);

            compObject.put(name, compDetails);

            JSONObject comptObject = new JSONObject();
            comptObject.put(type, compDetails);
            //

            //Add to list
            JA.add(compObject);

            //REWrite JSON file
            FileWriter file = new FileWriter(filePath);
            file.write(JA.toJSONString());
            file.flush();

        } catch (Exception ignored) {}

    }
    public List<String> getAllNames(String jsonPath)
    {
        //List for names
        List<String> names = new ArrayList<String>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(jsonPath))
        {
            W4Competence W4C = new W4Competence();

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;

            //Iterate over employee array
            employeeList.forEach( emp -> listAllnames( (JSONObject) emp, names ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return names;
    }
    private void listAllnames(JSONObject comp, List<String> names)
    {
        try {
            //Get competence
            JSONObject compObject = comp;
            ArrayList<String> test = new ArrayList<>(comp.keySet());
            names.addAll(test);

        }catch (Exception e){System.out.println("ERROR");}
    }

    public void updateCharacter(String filePath,ArrayList<String> descArray, ArrayList<String> caracArray, ArrayList<String> pointArray)
    {

        JSONObject compObject0 = new JSONObject();
        JSONObject compObject1 = new JSONObject();
        JSONObject compObject2 = new JSONObject();

        JSONObject characDesc = new JSONObject();
        JSONObject characCarac = new JSONObject();
        JSONObject characPoints = new JSONObject();

        characDesc.put("Nom", descArray.get(0));
        characDesc.put("Race",descArray.get(1));
        characDesc.put("Classe",descArray.get(2));
        characDesc.put("Statut",descArray.get(3));
        characDesc.put("Carrière",descArray.get(4));
        characDesc.put("Échelon",descArray.get(5));
        characDesc.put("Schéma",descArray.get(6));
        characDesc.put("Age",descArray.get(7));
        characDesc.put("Taille",descArray.get(8));
        characDesc.put("Cheveux",descArray.get(9));
        characDesc.put("Yeux",descArray.get(10));
        compObject0.put("Description", characDesc);

        characCarac.put("CC",caracArray.get(0));
        characCarac.put("CT",caracArray.get(1));
        characCarac.put("F",caracArray.get(2));
        characCarac.put("E",caracArray.get(3));
        characCarac.put("I",caracArray.get(4));
        characCarac.put("Ag",caracArray.get(5));
        characCarac.put("Dex",caracArray.get(6));
        characCarac.put("Int",caracArray.get(7));
        characCarac.put("FM",caracArray.get(8));
        characCarac.put("Soc",caracArray.get(9));
        compObject1.put("Caractéristiques", characCarac);

        characPoints.put("Destin",pointArray.get(0));
        characPoints.put("Chance",pointArray.get(1));
        characPoints.put("Résilience",pointArray.get(2));
        characPoints.put("Détermination",pointArray.get(3));
        characPoints.put("Motivation",pointArray.get(4));
        characPoints.put("Mouvement",pointArray.get(5));
        characPoints.put("Expérience",pointArray.get(6));
        //


        //Add to list
        JSONArray compList = new JSONArray();
        compList.add(compObject0);
        compList.add(compObject1);

        //REWrite JSON file
        try (
                FileWriter file = new FileWriter(filePath)) {

            file.write(compList.toJSONString());
            file.flush();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
