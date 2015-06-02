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
public class ArticleManager {
    private static ArticleManager instance = null;
    protected ArticleManager() {}   //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public ArticleManager getInstance(){
        if(instance == null){
            instance = new ArticleManager();
        }
        return instance;
    }
    
    public void enregistrer(Article art){
        //TO DO
    }
    
    public void supprimer(String refArticle){
        //TO DO
    }
    
    public void modifier(Article art1, Article art2){
        //TO DO
    }
    
    public int getNbCrees(){
        //TO DO
        return 0;
    }
    
    public int getNbModifies(){
        //TO DO
        return 0;
    }
    
    public int getNbSupprimes(){
        //TO DO
        return 0;
    }
}
