/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.chess.object;

/**
 *
 * @author phucdk
 */
public class LogicPiece {

    private int player;
    private int number;
    private boolean isCaptured;
    private TableCell currentCell;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIsCaptured() {
        return isCaptured;
    }

    public void setIsCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public TableCell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(TableCell currentCell) {
        this.currentCell = currentCell;
    }

}
