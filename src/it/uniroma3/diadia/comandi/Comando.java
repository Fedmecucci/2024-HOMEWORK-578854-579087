package it.uniroma3.diadia.comandi;

import java.io.IOException;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public interface Comando {
    /**
    * esecuzione del comando
     * @throws IOException 
    */
    public void esegui(Partita partita) throws IOException;
    /**
    * set parametro del comando
    */
    public void setParametro(String parametro);
    
    // CORREZIONE IO CONSOLE
    public void setIo(IO ioConsole);
    
    public String getNome();
    public String getParametro();
}