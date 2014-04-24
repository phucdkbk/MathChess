/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess;

import java.util.ArrayList;
import java.util.List;
import mathchess.common.Constants;
import mathchess.common.MathChessUtils;

/**
 *
 * @author phucdk
 */
public class Evaluator {

    /**
     * Math chess table will be denoted by 2 dimensions array 9*11
     *
     * Piece of player 1: from 0 to 9, where 0 is the player 1's 0 piece Piece
     * of player 2: from 10 to 19, where 10 is the player 2's 0 piece
     *
     * @param chessTable
     * @return
     */
    private int evalueTableValue(int[][] chessTable, int player) {
        int value = 0;
        int valueByCountPiece = evaluateByCountPiece(chessTable, player);
        value = 1 * valueByCountPiece;
        return value;
    }

    /**
     *
     * Evaluate table value by addition all value of piece.
     *
     * if the piece is current player piece piece 1: 1 point piece 2: 2 point
     * ... piece 9: 9 point
     *
     * if the piece is opponent's piece piece 1: -1 point piece 2: -2 point ...
     * piece 9: -9 point
     *
     *
     * @param chessTable
     * @param player
     * @return
     */
    private int evaluateByCountPiece(int[][] chessTable, int player) {
        int value = 0;
        for (int[] row : chessTable) {
            for (int j = 0; j < row.length; j++) {
                if (Constants.PLAYER.PLAYER_1 == player) {
                    if (row[j] > 0) {
                        if (row[j] <= 9) {
                            value += row[j];
                        }
                        if (row[j] > 11) {
                            value -= row[j] % 10;
                        }
                    }
                } else if (Constants.PLAYER.PLAYER_2 == player) {
                    if (row[j] > 0) {
                        if (row[j] <= 9) {
                            value -= row[j];
                        }
                        if (row[j] > 11) {
                            value += row[j] % 10;
                        }
                    }
                }
            }
        }
        return value;
    }

    /**
     *
     * check if cannonPiece and borePiece pair can capture given cell
     *
     * @param cannonPiece
     * @param borePiece
     * @param cell
     * @param chessTable
     * @param player
     * @return
     */
    private boolean canCapturePiece(LogicPiece cannonPiece, LogicPiece borePiece, TableCell cell, int[][] chessTable, int player) {
        boolean canCapture = false;
        List<TableCell> listCanCaptureCell = getListCanCaputureCell(cannonPiece, borePiece, chessTable, false, player, CaptureType.SUB_CROSS_TOP);
        if (listCanCaptureCell.contains(cell)) {
            canCapture = true;
        }
        return canCapture;
    }

    /**
     *
     * @param cannonPiece
     * @param borePiece
     * @param chessTable
     * @param isGetAllPiece true: get all capture cell, don't care about other
     * piece, false: just get real capture cell (regard current table state)
     *
     * @return
     */
    private List<TableCell> getListCanCaputureCell(LogicPiece cannonPiece, LogicPiece borePiece, int[][] chessTable, boolean isGetAllCell, int player, CaptureType captureType) {
        List<TableCell> listTableCells = new ArrayList<>();
        int[] arrCaptureNumber = getCaptureNumber(cannonPiece.getNumber(), borePiece.getNumber());
        int borePieceRow = borePiece.getCurrentCell().getRow();
        int borePieceColumn = borePiece.getCurrentCell().getColumn();
        if (isGetAllCell) {
            for (int i = 0; i < arrCaptureNumber.length; i++) {
                switch (captureType) {
                    case HORIZON_TO_LEFT:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceColumn - arrCaptureNumber[j] >= 0) {
                                TableCell aTableCell = new TableCell(borePieceRow, borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case HORIZON_TO_RIGHT:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceColumn + arrCaptureNumber[j] <= 8) {
                                TableCell aTableCell = new TableCell(borePieceRow, borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case VERTICAL_TO_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= 10) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case VERTICAL_TO_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= 0) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case MAIN_CROSS_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= 10 && borePieceColumn + arrCaptureNumber[j] <= 8) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case MAIN_CROSS_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= 0 && borePieceColumn - arrCaptureNumber[j] >= 0) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case SUB_CROSS_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= 10 && borePieceColumn - arrCaptureNumber[j] >= 0) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case SUB_CROSS_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= 0 && borePieceColumn + arrCaptureNumber[j] <= 8) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                }
            }
        } else {
            MoveBorder moveBorder = MathChessUtils.getMoveBorder(chessTable, player);
            for (int i = 0; i < arrCaptureNumber.length; i++) {
                switch (captureType) {
                    case HORIZON_TO_LEFT:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceColumn - arrCaptureNumber[j] >= moveBorder.getLeftCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow, borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            } else {
                                break;
                            }
                        }
                        break;
                    case HORIZON_TO_RIGHT:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceColumn + arrCaptureNumber[j] <= moveBorder.getRightCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow, borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case VERTICAL_TO_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= moveBorder.getBottomCell().getRow()) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case VERTICAL_TO_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= moveBorder.getTopCell().getRow()) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case MAIN_CROSS_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= moveBorder.getBottomRightCell().getRow() && borePieceColumn + arrCaptureNumber[j] <= moveBorder.getBottomRightCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case MAIN_CROSS_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= moveBorder.getTopLeftCell().getRow() && borePieceColumn - arrCaptureNumber[j] >= moveBorder.getTopLeftCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case SUB_CROSS_DOWN:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow + arrCaptureNumber[j] <= moveBorder.getBottomLeftCell().getRow() && borePieceColumn - arrCaptureNumber[j] >= moveBorder.getBottomLeftCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow + arrCaptureNumber[j], borePieceColumn - arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                    case SUB_CROSS_TOP:
                        for (int j = 0; j < arrCaptureNumber.length; j++) {
                            if (borePieceRow - arrCaptureNumber[j] >= moveBorder.getTopRightCell().getRow() && borePieceColumn + arrCaptureNumber[j] <= moveBorder.getRightCell().getColumn()) {
                                TableCell aTableCell = new TableCell(borePieceRow - arrCaptureNumber[j], borePieceColumn + arrCaptureNumber[j]);
                                listTableCells.add(aTableCell);
                            }
                        }
                        break;
                }
            }
        }
        return listTableCells;
    }

    /**
     *
     * get capture number by cannonNumber and boreNumber by operation: addition,
     * sub, multiplication, div
     *
     * @param cannonNumber
     * @param boreNumber
     * @return
     */
    private int[] getCaptureNumber(int cannonNumber, int boreNumber) {
        List<Integer> listCaptureNumber = new ArrayList<>();
        int captureNumber = (cannonNumber + boreNumber) % 10;
        if (captureNumber > 0) {
            if (!listCaptureNumber.contains(new Integer(captureNumber))) {
                listCaptureNumber.add(new Integer(captureNumber));
            }
        }
        captureNumber = cannonNumber - boreNumber;
        if (captureNumber > 0) {
            if (!listCaptureNumber.contains(new Integer(captureNumber))) {
                listCaptureNumber.add(new Integer(captureNumber));
            }
        }
        captureNumber = cannonNumber / boreNumber;
        if (captureNumber > 0) {
            if (!listCaptureNumber.contains(new Integer(captureNumber))) {
                listCaptureNumber.add(new Integer(captureNumber));
            }
        }
        captureNumber = cannonNumber % boreNumber;
        if (captureNumber > 0) {
            if (!listCaptureNumber.contains(new Integer(captureNumber))) {
                listCaptureNumber.add(new Integer(captureNumber));
            }
        }

        captureNumber = (cannonNumber * boreNumber) % 10;
        if (captureNumber > 0) {
            if (!listCaptureNumber.contains(new Integer(captureNumber))) {
                listCaptureNumber.add(new Integer(captureNumber));
            }
        }
        int[] arrcapture = new int[listCaptureNumber.size()];
        for (int i = 0; i < listCaptureNumber.size(); i++) {
            arrcapture[i] = Integer.valueOf(listCaptureNumber.get(i).toString()).intValue();
        }
        return arrcapture;
    }

    /**
     * main cross: from top left - bottom right sub cross: from top right -
     * bottom left
     */
    enum CaptureType {

        HORIZON_TO_LEFT, HORIZON_TO_RIGHT, VERTICAL_TO_TOP, VERTICAL_TO_DOWN,
        MAIN_CROSS_DOWN, MAIN_CROSS_TOP, SUB_CROSS_DOWN, SUB_CROSS_TOP
    }
}
