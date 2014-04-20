/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess;

import javax.swing.JLabel;

/**
 *
 * @author phucdk
 */
public class DisplayPiece {

    private int player;
    private int number;
    private boolean isCaptured;
    private JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;

    public DisplayPiece(int player, int number, int rowPosition, int columnPosition, JLabel pieceLabel) {
        this.player = player;
        this.number = number;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.pieceLabel = pieceLabel;
        this.isCaptured = false;
    }

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

    public JLabel getPieceLabel() {
        return pieceLabel;
    }

    public void setPieceLabel(JLabel pieceLabel) {
        this.pieceLabel = pieceLabel;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }
}
