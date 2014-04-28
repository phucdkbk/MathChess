/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.algorithm;

import mathchess.chess.object.MovePiece;

/**
 *
 * @author phucdk
 */
public class SearchResult {

    private int[][] chessTable;
    private int value;
    private MovePiece movePiece;

    public int[][] getChessTable() {
        return chessTable;
    }

    public void setChessTable(int[][] chessTable) {
        this.chessTable = chessTable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MovePiece getMovePiece() {
        return movePiece;
    }

    public void setMovePiece(MovePiece movePiece) {
        this.movePiece = movePiece;
    }

}
