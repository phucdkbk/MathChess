/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.display;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author phucdk
 */
public class Game {

    public static MathChess aGamePlay;

    public static void main(String[] args) {
        startNewGame();
    }

    private static void startNewGame() {
        aGamePlay = new MathChess();
        aGamePlay.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        aGamePlay.pack();
        aGamePlay.setResizable(true);
        aGamePlay.setLocationRelativeTo(null);
        aGamePlay.setVisible(true);
    }
    
    
    public static void resetGame(){
        
    }
}
