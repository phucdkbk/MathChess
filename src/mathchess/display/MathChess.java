/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathchess.display;

/**
 *
 * @author phucdk
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import mathchess.algorithm.Minimax;
import mathchess.algorithm.SearchResult;
import mathchess.common.Constants;
import mathchess.common.MathChessUtils;

/**
 *
 * Display math chess table
 * <br />
 * - chess table 9*11
 * <br />
 * - chess piece
 * <br />
 * ChessTable is a JFrame (mathChess class extend from JFrame)
 * <br/>
 * Chess pieces are displayed on a JLayeredPane <br />
 * layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
 *
 *
 *
 * @author phucdk
 */
public class MathChess extends JFrame implements MouseListener, MouseMotionListener {

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private List<DisplayPiece> listDisplayPiece;
    private int currentMovePlayer;

    public MathChess() {
        Dimension boardSize = new Dimension(540, 660);
        // Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(11, 9));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 99; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);
            square.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
        //Add a few pieces to the board
        listDisplayPiece = new ArrayList<>();
        initGamePiece();
        currentMovePlayer = Constants.PLAYER.PLAYER_2;
        createMenu();
    }

    /**
     * create menu game
     */
    private void createMenu() {
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        //Create the menu bar.
        menuBar = new JMenuBar();
        //Build the first menu.
        menu = new JMenu("Menu");
        menuBar.add(menu);
        //a group of JMenuItems
        menuItem = new JMenuItem("New game");
        menu.add(menuItem);
        menuItem = new JMenuItem("Exit");
        menu.add(menuItem);
        setJMenuBar(menuBar);
    }

    /**
     *
     * draw chess piece on chess table
     *
     */
    private void initGamePiece() {
        JLabel pieceLabel;
        JPanel panel;

        //Player 1
        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bb.png"));
        panel = (JPanel) chessBoard.getComponent(0);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 1, 0, 0, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bc.png"));
        panel = (JPanel) chessBoard.getComponent(1);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 2, 0, 1, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bd.png"));
        panel = (JPanel) chessBoard.getComponent(2);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 3, 0, 2, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/be.png"));
        panel = (JPanel) chessBoard.getComponent(3);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 4, 0, 3, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bf.png"));
        panel = (JPanel) chessBoard.getComponent(4);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 5, 0, 4, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bg.png"));
        panel = (JPanel) chessBoard.getComponent(5);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 6, 0, 5, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bh.png"));
        panel = (JPanel) chessBoard.getComponent(6);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 7, 0, 6, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bi.png"));
        panel = (JPanel) chessBoard.getComponent(7);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 8, 0, 7, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/bj.png"));
        panel = (JPanel) chessBoard.getComponent(8);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 9, 0, 8, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/ba.png"));
        panel = (JPanel) chessBoard.getComponent(13);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_1, 0, 1, 4, pieceLabel));

        //Player 2
        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/ra.png"));
        panel = (JPanel) chessBoard.getComponent(85);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 0, 9, 5, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rj.png"));
        panel = (JPanel) chessBoard.getComponent(90);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 9, 10, 0, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/ri.png"));
        panel = (JPanel) chessBoard.getComponent(91);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 8, 10, 1, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rh.png"));
        panel = (JPanel) chessBoard.getComponent(92);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 7, 10, 2, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rg.png"));
        panel = (JPanel) chessBoard.getComponent(93);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 6, 10, 3, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rf.png"));
        panel = (JPanel) chessBoard.getComponent(94);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 5, 10, 4, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/re.png"));
        panel = (JPanel) chessBoard.getComponent(95);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 4, 10, 5, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rd.png"));
        panel = (JPanel) chessBoard.getComponent(96);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 3, 10, 6, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rc.png"));
        panel = (JPanel) chessBoard.getComponent(97);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 2, 10, 7, pieceLabel));

        pieceLabel = new JLabel(new ImageIcon("/home/phucdk/MathChessImage/rb.png"));
        panel = (JPanel) chessBoard.getComponent(98);
        panel.add(pieceLabel);
        listDisplayPiece.add(new DisplayPiece(Constants.PLAYER.PLAYER_2, 1, 10, 8, pieceLabel));
    }

    /**
     * mouse press event
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) {
            return;
        }
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        DisplayPiece aPiece = getPieceByPieceLabel(chessPiece);
        if (isCurrentPlayer(aPiece)) {
            chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
            layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        }
    }

    /**
     *
     * Move the chess piece around
     *
     * @param me
     */
    @Override
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        DisplayPiece aPiece = getPieceByPieceLabel(chessPiece);
        if (isCurrentPlayer(aPiece)) {
            chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
        }
    }


    /**
     *
     * Drop the chess piece back onto the chess board
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        DisplayPiece aPiece = getPieceByPieceLabel(chessPiece);
        if (isCurrentPlayer(aPiece)) {
            if (chessPiece == null) {
                return;
            }
            chessPiece.setVisible(false);
            Component c = chessBoard.findComponentAt(e.getX(), e.getY());
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(chessPiece);
            } else {
                Container parent = (Container) c;
                parent.add(chessPiece);
            }
            chessPiece.setVisible(true);
            resetCurrentMovePlayer();
        }
        
        if(Constants.PLAYER.PLAYER_1 == currentMovePlayer){
            int[][] chessTable = MathChessUtils.convertFromDisplayToLogic(this);
            SearchResult searchResult = Minimax.minimaxSearch(chessTable, Constants.PLAYER.PLAYER_1, 3);
            MathChessUtils.calculateMovePiece(searchResult, chessTable);
            movePiece(searchResult.getMovePiece().getFromCell().getRow(), searchResult.getMovePiece().getFromCell().getColumn(), 
                    searchResult.getMovePiece().getToCell().getRow(), searchResult.getMovePiece().getToCell().getColumn());            
        }
    }

    /**
     * move piece from (fRow, fColumn) to (tRow, tColumn)
     *
     * @param fRow
     * @param fColumn
     * @param tRow
     * @param tColumn
     */
    public void movePiece(int fRow, int fColumn, int tRow, int tColumn) {
        int fComponentOrder = getComponentOrderFromPosition(fRow, fColumn);
        int tComponentOrder = getComponentOrderFromPosition(tRow, tColumn);
        chessPiece = null;
        JPanel fromCell = (JPanel) chessBoard.getComponent(fComponentOrder);
        JLabel c = (JLabel) fromCell.getComponent(0);
        JPanel toCell = (JPanel) chessBoard.getComponent(tComponentOrder);
        chessPiece = (JLabel) c;
        chessPiece.setLocation((int) toCell.getLocation().getX(), (int) toCell.getLocation().getY());
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        chessPiece.setVisible(false);
        Container parent = (Container) toCell;
        parent.add(chessPiece);
        chessPiece.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //movePiece(0, 0, 5, 5);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     *
     * @param chessPiece
     * @return
     */
    private DisplayPiece getPieceByPieceLabel(JLabel chessPiece) {
        for (int i = 0; i < listDisplayPiece.size(); i++) {
            if (listDisplayPiece.get(i).getPieceLabel().equals(chessPiece)) {
                return listDisplayPiece.get(i);
            }
        }
        return null;
    }

    private boolean isCurrentPlayer(DisplayPiece aPiece) {
        if (aPiece != null) {
            return aPiece.getPlayer() == currentMovePlayer;
        } else {
            return false;
        }

    }

    private void resetCurrentMovePlayer() {
        if (Constants.PLAYER.PLAYER_1 == currentMovePlayer) {
            currentMovePlayer = Constants.PLAYER.PLAYER_2;
        } else {
            currentMovePlayer = Constants.PLAYER.PLAYER_1;
        }
    }

    private int getComponentOrderFromPosition(int fRow, int fColumn) {
        return fRow * 9 + fColumn;
    }


    public JPanel getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(JPanel chessBoard) {
        this.chessBoard = chessBoard;
    }

    public JLabel getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(JLabel chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getxAdjustment() {
        return xAdjustment;
    }

    public void setxAdjustment(int xAdjustment) {
        this.xAdjustment = xAdjustment;
    }

    public int getyAdjustment() {
        return yAdjustment;
    }

    public void setyAdjustment(int yAdjustment) {
        this.yAdjustment = yAdjustment;
    }

    public int getCurrentMovePlayer() {
        return currentMovePlayer;
    }

    public void setCurrentMovePlayer(int currentMovePlayer) {
        this.currentMovePlayer = currentMovePlayer;
    }

    public List<DisplayPiece> getListDisplayPiece() {
        return listDisplayPiece;
    }

    public void setListDisplayPiece(List<DisplayPiece> listDisplayPiece) {
        this.listDisplayPiece = listDisplayPiece;
    }        

}
