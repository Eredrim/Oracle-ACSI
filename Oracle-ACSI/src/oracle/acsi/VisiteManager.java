/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.Time;

/**
 *
 * @author clem-62
 */
public class VisiteManager {
    private static VisiteManager instance = null;
    protected VisiteManager() {}    //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public VisiteManager getInstance(){
        if(instance == null){
            instance = new VisiteManager();
        }
        return instance;
    }
    
    public void enregistrerVisite(Visite myVisite){
        //TO DO
    }
    
    public Time getTempsMoyen(){
        //TO DO
        return null;
    }
    
    public int getNbVuesTotal(){
        //TO DO
        return 0;
    }
    
    public int getNbVues(String refArticle){
        //TO DO
        return 0;
    }
    
    public void getHitParage(int place1, int place2){
        //TO DO
        //Changer le type de retour
    }
}
