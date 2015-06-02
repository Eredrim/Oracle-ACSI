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
}
