package it.uniroma3.diadia;

import java.io.*;
import java.util.Properties;

public class Proprietà {

    private static final String DIADIA_PROPERTIES = "diadia.properties";
    private static final String CFU = "cfu";
    private static final String PESO_MAX = "pesoMax";
    private static final String NUMERO_MAX_DIREZIONI = "numeroMaxDirezioni";
    private static final String LIMITE_PESO_ATTREZZI = "limitePesoAttrezzi";
    private static final String SOGLIA_MAGICA = "sogliaMagica";
    private static final int DEFAULT_CFU = 10;
    private static final int DEFAULT_PESO_MAX = 20;
    private static final int DEFAULT_NUMERO_MAX_DIREZIONI = 4;
    private static final int DEFAULT_LIMITE_PESO_ATTREZZI = 10;
    private static final int DEFAULT_SOGLIA_MAGICA = 3;

    private static Proprietà instance = null;
    private static Properties prop;

    private Proprietà() throws IOException {
        prop = new Properties();
        try (FileInputStream input = new FileInputStream(DIADIA_PROPERTIES)) {
            prop.load(input);
        } catch (IOException e) {
            // Consider logging or throwing a specific exception
            e.printStackTrace();
            // Set default values if loading fails
            prop.setProperty(CFU, String.valueOf(DEFAULT_CFU));
            prop.setProperty(PESO_MAX, String.valueOf(DEFAULT_PESO_MAX));
            prop.setProperty(NUMERO_MAX_DIREZIONI, String.valueOf(DEFAULT_NUMERO_MAX_DIREZIONI));
            prop.setProperty(LIMITE_PESO_ATTREZZI, String.valueOf(DEFAULT_LIMITE_PESO_ATTREZZI));
            prop.setProperty(SOGLIA_MAGICA, String.valueOf(DEFAULT_SOGLIA_MAGICA));
        }
    }

    public static Proprietà getInstance() throws IOException {
        if (instance == null) {
            instance = new Proprietà();
        }
        return instance;
    }

    public int getCFU() {
        return Integer.parseInt(prop.getProperty(CFU, String.valueOf(DEFAULT_CFU)));
    }

    public int getPesoMax() {
        return Integer.parseInt(prop.getProperty(PESO_MAX, String.valueOf(DEFAULT_PESO_MAX)));
    }
    
    public int getNumeroMaxDirezioni() {
        return Integer.parseInt(prop.getProperty(NUMERO_MAX_DIREZIONI, String.valueOf(DEFAULT_NUMERO_MAX_DIREZIONI)));
    }

    public int getLimitePesoAttrezzi() {
        return Integer.parseInt(prop.getProperty(LIMITE_PESO_ATTREZZI, String.valueOf(DEFAULT_LIMITE_PESO_ATTREZZI)));
    }
    
    public int getSogliaMagica() {
    	return Integer.parseInt(prop.getProperty(SOGLIA_MAGICA, String.valueOf(DEFAULT_SOGLIA_MAGICA)));
    }
}
