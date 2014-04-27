/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.algorithm;

/**
 *
 * @author phucdk
 */
public class SearchResult {

    private int[][] chessTable;
    private int value;

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

}
