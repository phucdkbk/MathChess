/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.algorithm;

import java.util.List;
import mathchess.common.Constants;
import mathchess.common.Evaluator;
import mathchess.common.MathChessUtils;

/**
 *
 * @author phucdk
 */
public class Minimax {

    public static SearchResult minimaxSearch(int[][] chessTable, int player, int length) {
        SearchResult searchResult;
        int value;
        if (length == 0) {
            searchResult = new SearchResult();
            searchResult.setChessTable(chessTable);
            searchResult.setValue(Evaluator.evalueTableValue(chessTable, player));
        } else {
            searchResult = new SearchResult();
            if (isMaximizingPlayer(length)) {
                List<int[][]> listChildTableStatus = MathChessUtils.getListChildTableStatus(chessTable, Constants.PLAYER.PLAYER_1);
                value = -100000000;
                for (int[][] childChessTable : listChildTableStatus) {
                    SearchResult tmpSearchResult = minimaxSearch(childChessTable, player, length - 1);
                    if (tmpSearchResult.getValue() > value) {
                        value = tmpSearchResult.getValue();
                        searchResult.setChessTable(childChessTable);
                        searchResult.setValue(tmpSearchResult.getValue());
                    }
                }
            } else {
                List<int[][]> listChildTableStatus = MathChessUtils.getListChildTableStatus(chessTable, Constants.PLAYER.PLAYER_2);
                value = -100000000;
                for (int[][] childChessTable : listChildTableStatus) {
                    SearchResult tmpSearchResult = minimaxSearch(childChessTable, player, length - 1);
                    if (tmpSearchResult.getValue() < value) {
                        value = tmpSearchResult.getValue();
                        searchResult.setChessTable(childChessTable);
                        searchResult.setValue(tmpSearchResult.getValue());
                    }
                }
            }
        }
        return searchResult;
    }

    /**
     *
     * check if current player is minimum or maximum player
     *
     * @param length
     * @return
     */
    public static boolean isMaximizingPlayer(int length) {
        return length % 2 == 0;
    }
}
