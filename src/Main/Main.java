/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import swing.Navigator;

public class Main {
    private static Navigator navigator = Navigator.getInstance();
    public static void main(String[] args){
        navigator.init();
    }
}
