/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.chess.object;

/**
 * farthest cell on 8 direction (MoveBorder)
 * <br /> go up
 * <br /> go down
 * <br /> go left
 * <br /> go right
 * <br /> go cross up left
 * <br /> go cross up right
 * <br /> go cross down left
 * <br /> go cross down right
 * 
 * @author phucdk
 */
public class MoveBorder {

    private TableCell leftCell;
    private TableCell rightCell;
    private TableCell topCell;
    private TableCell bottomCell;
    private TableCell topLeftCell;
    private TableCell topRightCell;
    private TableCell bottomLeftCell;
    private TableCell bottomRightCell;

    public MoveBorder() {
        this.leftCell = new TableCell();
        this.rightCell = new TableCell();
        this.topCell = new TableCell();
        this.bottomCell = new TableCell();
        this.topLeftCell = new TableCell();
        this.topRightCell = new TableCell();
        this.bottomLeftCell = new TableCell();
        this.bottomRightCell = new TableCell();
    }

    public TableCell getLeftCell() {
        return leftCell;
    }

    public void setLeftCell(TableCell leftCell) {
        this.leftCell = leftCell;
    }

    public TableCell getRightCell() {
        return rightCell;
    }

    public void setRightCell(TableCell rightCell) {
        this.rightCell = rightCell;
    }

    public TableCell getTopCell() {
        return topCell;
    }

    public void setTopCell(TableCell topCell) {
        this.topCell = topCell;
    }

    public TableCell getBottomCell() {
        return bottomCell;
    }

    public void setBottomCell(TableCell bottomCell) {
        this.bottomCell = bottomCell;
    }

    public TableCell getTopLeftCell() {
        return topLeftCell;
    }

    public void setTopLeftCell(TableCell topLeftCell) {
        this.topLeftCell = topLeftCell;
    }

    public TableCell getTopRightCell() {
        return topRightCell;
    }

    public void setTopRightCell(TableCell topRightCell) {
        this.topRightCell = topRightCell;
    }

    public TableCell getBottomLeftCell() {
        return bottomLeftCell;
    }

    public void setBottomLeftCell(TableCell bottomLeftCell) {
        this.bottomLeftCell = bottomLeftCell;
    }

    public TableCell getBottomRightCell() {
        return bottomRightCell;
    }

    public void setBottomRightCell(TableCell bottomRightCell) {
        this.bottomRightCell = bottomRightCell;
    }

}
