/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathchess.chess.object;

/**
 *
 * @author phucdk
 */
public class MovePiece {
    private TableCell fromCell;
    private TableCell toCell;

    public TableCell getFromCell() {
        return fromCell;
    }

    public void setFromCell(TableCell fromCell) {
        this.fromCell = fromCell;
    }

    public TableCell getToCell() {
        return toCell;
    }

    public void setToCell(TableCell toCell) {
        this.toCell = toCell;
    }
    
    
}
