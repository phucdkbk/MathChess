/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.display;

import mathchess.display.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author phucdk
 */
public class MenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.resetGame();
    }
}
