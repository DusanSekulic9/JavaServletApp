package rs.raf.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil {




    static String[] fajlovi = {"ponedeljak.txt", "utorak.txt", "sreda.txt", "cetvrtak.txt", "petak.txt", "password.txt"};

    public static void ucitajFajlove() throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        FileReader fr = null;
        BufferedReader br = null;
        for (int i = 0; i < fajlovi.length; i++) {
            ArrayList<String> jela = new ArrayList<>();
            HashMap<String, Integer> mapa = new HashMap<>();
            {
                try {
                    fr = new FileReader(fajlovi[i]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if(fr != null) {
                br = new BufferedReader(fr);
                String line = "";
                while((line = br.readLine()) != null){
                    if(!fajlovi[i].equals("password.txt")){
                        jela.add(line);
                        mapa.put(fajlovi[i].substring(fajlovi[i].indexOf('.')), 0);
                    }
                    else{
                        Baza.getInstance().setPassword(line);
                    }
                }
                switch(fajlovi[i]){
                    case "ponedeljak.txt":{
                        Baza.getInstance().getMenu().put("ponedeljak", jela);
                        Baza.getInstance().getChosenForClient().put("ponedeljak", mapa);
                        break;
                    }
                    case "utorak.txt":{
                        Baza.getInstance().getMenu().put("utorak", jela);
                        Baza.getInstance().getChosenForClient().put("utorak", mapa);
                        break;
                    }
                    case "sreda.txt":{
                        Baza.getInstance().getMenu().put("sreda", jela);
                        Baza.getInstance().getChosenForClient().put("sreda", mapa);
                        break;
                    }
                    case "cetvrtak.txt":{
                        Baza.getInstance().getMenu().put("cetvrtak", jela);
                        Baza.getInstance().getChosenForClient().put("cetvrtak", mapa);
                        break;
                    }
                    case "petak.txt":{
                        Baza.getInstance().getMenu().put("petak", jela);
                        Baza.getInstance().getChosenForClient().put("petak", mapa);
                        break;
                    } default:{

                    }

                }
            }
        }
    }





}
