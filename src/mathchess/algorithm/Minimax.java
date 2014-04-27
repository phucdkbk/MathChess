/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.algorithm;

import java.util.List;
import mathchess.common.Evaluator;
import mathchess.common.MathChessUtils;

/**
 *
 * @author phucdk
 */
public class Minimax {

    public static SearchResult minimaxSearch(int[][] chessTable, int player, int length) {
        //MovePiece movePiece = new MovePiece();
        SearchResult searchResult;
        if (length == 0) {
            searchResult = new SearchResult();
            searchResult.setChessTable(chessTable);
            searchResult.setValue(Evaluator.evalueTableValue(chessTable, player));
        } else {
            List<int[][]> listChildTableStatus = MathChessUtils.getListChildTableStatus(chessTable, player);
            if (isMaximizingPlayer(length)) {
                value = -100000000;
                for (int[][] childChessTable : listChildTableStatus) {
                    SearchResult tmpSearchResult = minimaxSearch(childChessTable, player, length - 1);
                    if (tmpSearchResult.v > value) {
                        value = tmpValue;
                    }
                }
            } else {
                value = -100000000;
                for (int[][] childChessTable : listChildTableStatus) {
                    int tmpValue = Evaluator.evalueTableValue(childChessTable, player);
                    if (tmpValue < value) {
                        value = tmpValue;
                    }
                }
            }
        }
        return value;
        //return movePiece;
    }

    private static boolean isMaximizingPlayer(int length) {
        return length % 2 == 0;
    }
}
