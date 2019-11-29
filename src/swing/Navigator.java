/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

/**
 *
 * @author Paulo
 */
public class Navigator {
        
    public static enum screens{
        HOME,
        LISTAGEM,
        REGISTRO
    }
    
    private static screens activeScreen = screens.HOME;
    private static Home home = new Home();
    private static Listagem listagem = new Listagem();
    private static Registro registro = new Registro();
    
    private static Navigator instance = new Navigator();
    
    public static Navigator getInstance(){
        return instance;
    }
    
    public static void init(){
        home.setVisible(true);
    }
    
    private static void closeScreen(screens screen){
        switch(screen){
            case HOME:
                home.setVisible(false);
                break;
            case LISTAGEM:
                listagem.setVisible(false);
                break;
            case REGISTRO:
                registro.setVisible(false);
                break;
            default: return;
        }                
    }
    
    public static void navigate(screens screen){
        switch(screen){
            case HOME:
                if(activeScreen != screens.HOME){
                    closeScreen(activeScreen);
                    home.setVisible(true);
                    activeScreen = screens.HOME;
                }
                break;
            case LISTAGEM:
                if(activeScreen != screens.LISTAGEM){
                    closeScreen(activeScreen);
                    listagem.setVisible(true);
                    activeScreen = screens.LISTAGEM;
                }
                break;
            case REGISTRO:
                if(activeScreen != screens.REGISTRO){
                    closeScreen(activeScreen);
                    registro.setVisible(true);
                    activeScreen = screens.REGISTRO;
                }
                break;
            default: return;
        }        
    }       
}
