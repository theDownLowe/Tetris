/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * The Panel displaying tetris pieces on the game board.
 * 
 * @author Trevor N. Lowe
 * @version December 1, 2015 - 7:50 PM
 */
public class GameBoard extends BoardPanel {

    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 2765311302499349136L;
    
    /** Empty String. **/
    private static final String EMPTY_BLOCK = "EMPTY";

    
    // Instance Fields
    
    /** The game board. **/
    private final Board myBoard;
    
    /** Ticks to wait for before sending update to ScorePanel. **/
    private int myTicks;
    
    
    // Constructors
    
    /** Creates a new GameBoard object.
     * 
     * @param theGameBoard the Board being used by the GameBoard
     */
    public GameBoard(final Board theGameBoard) {
        
        super(theGameBoard);
        myBoard = theGameBoard;
        myTicks = 0;
    }
    
    
    // Instance Methods
    
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        
        super.update(theObservable, theObject);
        
        if (myTicks > 0) {
            myTicks--;
        } else {
            myTicks = 0;
        }
        
        boolean isClear = true;
        int newLines = 0;
        
        for (final Block[] b : myBoard.getFrozenBlocks()) {
            for (int i = 0; i < b.length; i++) {
                if (EMPTY_BLOCK.equals(b[i].name())) {
                    isClear = false;
                }
            }
            if (isClear) {
                newLines++;
            }
            isClear = true;
        }
        if (newLines != 0 && myTicks == 0) {
            firePropertyChange("ScoreUpdate", null, newLines);
            myTicks = newLines;
        }
        
        repaint();
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        
        // Prints Frozen Blocks
        int row = 1;
        for (final Block[] b : myBoard.getFrozenBlocks()) {
            for (int i = 0; i < b.length; i++) { 
                if (!EMPTY_BLOCK.equals(b[i].name()) && row - 1 < myBoard.getHeight()) {
                    final double width = (this.getWidth() - (2 * GameBoard.BORDER_OFFSET) 
                                    - 2 - (myBoard.getWidth() - 1)) / myBoard.getWidth();
                    final double height = (this.getHeight() - (2 * GameBoard.BORDER_OFFSET) 
                                    - 2 - (myBoard.getHeight() - 1)) / myBoard.getHeight();
                    final double x = (GameBoard.BORDER_OFFSET + 2) + (i * width) + i;
                    final double y = (this.getHeight() - GameBoard.BORDER_OFFSET)
                                - (row * height) - row;
                    final Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 
                                                                           width, height);
                    g2d.fill(rect);
                }
            }
            row++;
        }
        
        this.printCurrentPiece(g2d);
    }
    
    /**
     * Prints the current piece to the game board.
     * 
     * @param theGraphics the graphics being printed to
     */
    private void printCurrentPiece(final Graphics2D theGraphics) {
        
        // Print Current Piece
        final Piece curPiece = myBoard.getCurrentPiece();
        final int[][] p = ((AbstractPiece) curPiece).getBoardCoordinates();
        for (int i = 0; i < p.length; i++) {
            final int xPosition = p[i][0];
            final int yPosition = p[i][1];
            if (yPosition + 1 <= myBoard.getHeight()) {
                final double width = (this.getWidth() - (2 * GameBoard.BORDER_OFFSET) 
                                - 2 - (myBoard.getWidth() - 1)) / myBoard.getWidth();
                final double height = (this.getHeight() - (2 * GameBoard.BORDER_OFFSET) 
                                - 2 - (myBoard.getHeight() - 1)) / myBoard.getHeight();
                final double x = (GameBoard.BORDER_OFFSET + 2)
                                 + (xPosition * width) + xPosition;
                final double y = (this.getHeight() - GameBoard.BORDER_OFFSET)
                                  - ((yPosition + 1) * height) - yPosition - 1;
                final Rectangle2D.Double rect = new Rectangle2D.Double(x, y, 
                                                                       width, height);
                theGraphics.fill(rect);
            }
        }
    }
}
