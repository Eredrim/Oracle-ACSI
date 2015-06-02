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
public class VisiteManager {
    private static VisiteManager instance = null;
    protected VisiteManager() {}    //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public VisiteManager getInstance(){
        if(instance == null){
            instance = new VisiteManager();
        }
        return instance;
    }
}
