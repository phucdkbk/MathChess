/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import mathchess.algorithm.Minimax;
import mathchess.algorithm.SearchResult;
import mathchess.chess.object.MovePiece;
import mathchess.common.Constants;
import mathchess.common.Evaluator;
import mathchess.common.MathChessUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author phucdk
 */
public class Game {

    public static MathChess aGamePlay;
    private static final Logger logger = Logger.getLogger(Game.class);

    public static void main(String[] args) {
        //startNewGame();
        try {
            int[][] chessTable = readFile();
            SearchResult aSearchResult = Minimax.minimaxSearch(chessTable, Constants.PLAYER.PLAYER_1, 4);
            MathChessUtils.calculateMovePiece(aSearchResult, chessTable);
            MovePiece movePiece = aSearchResult.getMovePiece();
            System.out.println(MathChessUtils.countEvaluaionTime);

            //int value = Evaluator.evalueTableValue(chessTable, Constants.PLAYER.PLAYER_1);
            //System.out.println(value);
            //MathChessUtils.printTableStatus(chessTable);
        } catch (IOException ex) {
            logger.error("error when evaluate game status", ex);
        }
    }

    private static int[][] readFile() throws IOException {
        int[][] chessTable = new int[11][9];
        BufferedReader br = new BufferedReader(new FileReader("E:\\phucdk\\MathChessDocs\\GameStatus\\GameStatus1.txt"));
        try {
            String line;
            for (int i = 0; i < 11; i++) {
                line = br.readLine();
                String[] arrStrPiece = line.split(" ");
                for (int j = 0; j < 9; j++) {
                    chessTable[i][j] = Integer.parseInt(arrStrPiece[j]);
                }
            }
        } catch (IOException ex) {
            logger.error("error when readfile from ", ex);
            throw ex;
        } finally {
            br.close();
        }
        return chessTable;
    }

    private static void startNewGame() {
        aGamePlay = new MathChess();
        aGamePlay.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        aGamePlay.pack();
        aGamePlay.setResizable(true);
        aGamePlay.setLocationRelativeTo(null);
        aGamePlay.setVisible(true);
    }

    public static void resetGame() {

    }
}
