package rs.raf.demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Baza {

    private static Baza instance;

    private ArrayList<String> ids;

    private Map<String, ArrayList<String>> menu;

    private Map<String, Map<String, Integer>> chosenForClient;

    private Map<String, ArrayList<String>> chosenForPerson;

    private String password = "";

    private Baza(){
        ids = new ArrayList<>();
        menu = new HashMap<>();
        chosenForClient = new HashMap<>();
        chosenForPerson = new HashMap<>();
    }

    public static Baza getInstance() {
        if(instance == null){
            instance = new Baza();
        }
        return instance;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public Map<String, ArrayList<String>> getMenu() {
        return menu;
    }

    public Map<String, Map<String, Integer>> getChosenForClient() {
        return chosenForClient;
    }

    public Map<String, ArrayList<String>> getChosenForPerson() {
        return chosenForPerson;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
