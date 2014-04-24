/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.common;

import mathchess.MoveBorder;
import mathchess.TableCell;

/**
 *
 * for each cell on table, determine MoveBorder of cell
 *
 * @author phucdk
 */
public class MathChessUtils {

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
        while (currentRow >= Constants.TABLE.MIN_ROW) {
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
        aMoveBorder.setTopCell(new TableCell(currentRow, currentColumn));

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
        aMoveBorder.setBottomLeftCell(new TableCell(currentRow, currentColumn));

        currentColumn = tableCell.getColumn() - 1;
        while (currentRow >= Constants.TABLE.MIN_ROW) {
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
        while (currentRow >= Constants.TABLE.MIN_ROW) {
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
}
