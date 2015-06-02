/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

/**
 *
 * @author clem-62
 */
public class InscriptionManager {
    private static InscriptionManager instance = null;
    protected InscriptionManager() {}   //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public InscriptionManager getInstance(){
        if(instance == null){
            instance = new InscriptionManager();
        }
        return instance;
    }
}
