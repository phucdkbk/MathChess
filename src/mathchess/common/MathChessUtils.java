/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.common;

import java.util.ArrayList;
import java.util.List;
import mathchess.algorithm.SearchResult;
import mathchess.chess.object.MoveBorder;
import mathchess.chess.object.MovePiece;
import mathchess.chess.object.TableCell;
import mathchess.display.DisplayPiece;
import mathchess.display.MathChess;
import org.apache.log4j.Logger;

/**
 *
 * for each cell on table, determine MoveBorder of cell
 *
 * @author phucdk
 */
public class MathChessUtils {

    private static final Logger logger = Logger.getLogger(MathChessUtils.class);
    public static int countEvaluaionTime = 0;

    public static MoveBorder getMoveBorder(int[][] chessTable, int player, TableCell tableCell) {
        MoveBorder aMoveBorder = new MoveBorder();
        int currentRow;
        int currentColumn;

        currentRow = tableCell.getRow() - 1;
        currentColumn = tableCell.getColumn();
        while (currentRow >= Constants.TABLE.MIN_ROW) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow++;
                    break;
                } else {
                    break;
                }
            }
            currentRow--;
        }
        aMoveBorder.setTopCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow() - 1;
        currentColumn = tableCell.getColumn() + 1;
        while (currentRow >= Constants.TABLE.MIN_ROW && currentColumn <= Constants.TABLE.MAX_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow++;
                    currentColumn--;
                    break;
                } else {
                    break;
                }
            }
            currentRow--;
            currentColumn++;
        }
        aMoveBorder.setTopRightCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow();
        currentColumn = tableCell.getColumn() + 1;
        while (currentColumn <= Constants.TABLE.MAX_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentColumn--;
                    break;
                } else {
                    break;
                }
            }
            currentColumn++;
        }
        aMoveBorder.setRightCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow() + 1;
        currentColumn = tableCell.getColumn() + 1;
        while (currentRow <= Constants.TABLE.MAX_ROW && currentColumn <= Constants.TABLE.MAX_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow--;
                    currentColumn--;
                    break;
                } else {
                    break;
                }
            }
            currentRow++;
            currentColumn++;
        }
        aMoveBorder.setBottomRightCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow() + 1;
        while (currentRow <= Constants.TABLE.MAX_ROW) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow--;
                    break;
                } else {
                    break;
                }
            }
            currentRow++;
        }
        aMoveBorder.setBottomCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow() + 1;
        currentColumn = tableCell.getColumn() - 1;
        while (currentRow <= Constants.TABLE.MAX_ROW && currentColumn >= Constants.TABLE.MIN_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow--;
                    currentColumn++;
                    break;
                } else {
                    break;
                }
            }
            currentRow++;
            currentColumn--;
        }
        aMoveBorder.setBottomLeftCell(new TableCell(currentRow, currentColumn));

        currentColumn = tableCell.getColumn() - 1;
        while (currentColumn >= Constants.TABLE.MIN_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentColumn++;
                    break;
                } else {
                    break;
                }
            }
            currentColumn--;
        }
        aMoveBorder.setLeftCell(new TableCell(currentRow, currentColumn));

        currentRow = tableCell.getRow() - 1;
        currentColumn = tableCell.getColumn() - 1;
        while (currentRow >= Constants.TABLE.MIN_ROW && currentColumn >= Constants.TABLE.MIN_COLUMN) {
            if (isPiece(chessTable[currentRow][currentColumn])) {
                if (isMyPiece(chessTable[currentRow][currentColumn], player)) {
                    currentRow++;
                    currentColumn++;
                    break;
                } else {
                    break;
                }
            }
            currentRow--;
            currentColumn--;
        }
        aMoveBorder.setTopLeftCell(new TableCell(currentRow, currentColumn));

        return aMoveBorder;
    }

    /**
     *
     * Check if number on chess table is a piece
     *
     * @param numberOnTable
     * @return
     */
    public static boolean isPiece(int numberOnTable) {
        return numberOnTable >= 0;
    }

    /**
     * Check if number on chess table is real piece (not 0 piece)
     *
     * @param numberOnTable
     * @return
     */
    public static boolean canMove(int numberOnTable) {
        return (numberOnTable >= 1 && numberOnTable <= 9) || (numberOnTable >= 11 && numberOnTable <= 19);
    }

    /**
     * check if chess piece number on the table is piece of player 1 or player 2
     *
     * @param pieceValue
     * @param player
     * @return
     */
    public static boolean isMyPiece(int pieceValue, int player) {
        if (Constants.PLAYER.PLAYER_1 == player) {
            if (pieceValue >= 0 && pieceValue <= 9) {
                return true;
            }
        }

        if (Constants.PLAYER.PLAYER_2 == player) {
            if (pieceValue >= 10 && pieceValue <= 19) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * List all child chess table status of given chessTable and current move
     * player
     *
     * @param chessTable
     * @param currentMovePlayer
     * @return
     */
    public static List<int[][]> getListChildTableStatus(int[][] chessTable, int currentMovePlayer) {
        List<int[][]> listChildChessTables = new ArrayList<>();
        List<MovePiece> listMovePieces = getListMovePiece(chessTable, currentMovePlayer);
        for (int i = 0; i < listMovePieces.size(); i++) {
            int[][] tmpChessTable = getChessTableByMove(chessTable, listMovePieces.get(i));
            listChildChessTables.add(tmpChessTable);
        }
        return listChildChessTables;
    }

    /**
     *
     * get list move piece from current chess table with given current move
     * player
     *
     * @param chessTable
     * @param currentMovePlayer
     * @return
     */
    public static List<MovePiece> getListMovePiece(int[][] chessTable, int currentMovePlayer) {
        List<MovePiece> listMovePieces = new ArrayList<>();
        int currentRow = -1;
        int currentColumn = -1;
        try {
            for (int row = 0; row < chessTable.length; row++) {
                for (int column = 0; column < chessTable[row].length; column++) {
                    currentRow = row;
                    currentColumn = column;
                    if (isMyPiece(chessTable[row][column], currentMovePlayer)) {
                        while (currentRow > Constants.TABLE.MIN_ROW) {
                            currentRow--;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentRow > Constants.TABLE.MIN_ROW && currentColumn < Constants.TABLE.MAX_COLUMN) {
                            currentRow--;
                            currentColumn++;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentColumn < Constants.TABLE.MAX_COLUMN) {
                            currentColumn++;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentRow < Constants.TABLE.MAX_ROW && currentColumn < Constants.TABLE.MAX_COLUMN) {
                            currentRow++;
                            currentColumn++;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentRow < Constants.TABLE.MAX_ROW) {
                            currentRow++;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentRow < Constants.TABLE.MAX_ROW && currentColumn > Constants.TABLE.MIN_COLUMN) {
                            currentRow++;
                            currentColumn--;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentColumn > Constants.TABLE.MIN_COLUMN) {
                            currentColumn--;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }

                        currentRow = row;
                        currentColumn = column;
                        while (currentRow > Constants.TABLE.MIN_ROW && currentColumn > Constants.TABLE.MIN_COLUMN) {
                            currentRow--;
                            currentColumn--;
                            if (isPiece(chessTable[currentRow][currentColumn])) {
                                break;
                            }
                            listMovePieces.add(new MovePiece(new TableCell(row, column), new TableCell(currentRow, currentColumn)));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(" " + currentRow + "_" + currentColumn, ex);
        }
        return listMovePieces;
    }

    /**
     *
     * @param chessTable
     * @param movePiece
     * @return
     */
    private static int[][] getChessTableByMove(int[][] chessTable, MovePiece movePiece) {
        int[][] tmpChessTable = chessTable.clone();
        tmpChessTable[movePiece.getToCell().getRow()][movePiece.getToCell().getColumn()] = tmpChessTable[movePiece.getFromCell().getRow()][movePiece.getFromCell().getColumn()];
        tmpChessTable[movePiece.getFromCell().getRow()][movePiece.getFromCell().getColumn()] = Constants.PIECE.EMPTY_PIECE;
        return tmpChessTable;
    }

    /**
     *
     * convert from display math chess to logic math chess table
     *
     * @param displayChessTable
     * @return
     */
    public static int[][] convertFromDisplayToLogic(MathChess displayChessTable) {
        int[][] chessTable = new int[9][11];
        for (int[] row : chessTable) {
            for (int j = 0; j < row.length; j++) {
                row[j] = Constants.PIECE.EMPTY_PIECE;
            }
        }
        for (int i = 0; i < displayChessTable.getListDisplayPiece().size(); i++) {
            DisplayPiece displayPiece = displayChessTable.getListDisplayPiece().get(i);
            if (!displayPiece.isIsCaptured()) {
                chessTable[displayPiece.getRowPosition()][displayPiece.getColumnPosition()] = displayPiece.getNumber() + 10 * displayPiece.getNumber();
            }
        }
        return chessTable;
    }

    /**
     *
     * calculate move piece from algorithm search result and current table
     * status
     *
     * @param searchResult
     * @param chessTable
     */
    public static void calculateMovePiece(SearchResult searchResult, int[][] chessTable) {
        MovePiece movePiece = new MovePiece();
        for (int i = 0; i < chessTable.length; i++) {
            for (int j = 0; j < chessTable[i].length; j++) {
                if (searchResult.getChessTable()[i][j] == chessTable[i][j]) {
                    if (chessTable[i][j] == Constants.PIECE.EMPTY_PIECE) {
                        movePiece.setFromCell(new TableCell(i, j));
                    } else {
                        movePiece.setToCell(new TableCell(i, j));
                    }
                }
            }
        }
        searchResult.setMovePiece(movePiece);
    }

    /**
     * 
     * print chess table status to console for debugging
     * 
     * @param chessTable 
     */
    public static void printTableStatus(int[][] chessTable) {
        for (int[] row : chessTable) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + " ");
            }
            System.out.println("");
        }
    }

}
