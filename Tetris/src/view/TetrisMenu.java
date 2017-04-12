/*
 * TCSS 305
 * Assignment 6 - Tetris Project
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * A menu bar for the Tetris GUI.
 * 
 * @author Trevor N. Lowe
 * @version December 10, 2015 - 9:10 PM
 */
public class TetrisMenu extends JMenuBar {

    // Class Constants
    
    /** A generated serial ID. **/
    private static final long serialVersionUID = 6523545150132007076L;
    
    /** Dialog appearing from about menu. **/
    private static final String ABOUT_DIALOG = "GUI designed by: Trevor Lowe\n" 
                                             + "Back end designed by: Alan Fowler\n"
                                             + "University of Washington Tacoma TCSS 305\n\n"
                                             + "Scoring: 10 * level * lines cleared ^ 2\n\n"
                                             + "Dev Highscore: 17330\n"
                                             + "Can you beat it?\n";
    
    
    // Instance Fields
    
    
    
    // Constructors
    
    /**
     * Creates a new Tetris Menu bar.
     * 
     * @param theFrame the frame for the GUI
     */
    public TetrisMenu(final TetrisGUI theFrame) {
        
        super();
        setupGameMenu(theFrame);
        setupAboutMenu(theFrame);
    }
    
    
    // Instance Methods
    
    /**
     * Sets up the menu bar for the GUI.
     * 
     * @param theFrame the frame for the GUI
     */
    private void setupGameMenu(final TetrisGUI theFrame) {
        
        final JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);
        final JMenuItem endGame = new JMenuItem("End Game");
        final JMenuItem newGame = new JMenuItem("New Game");
        endGame.setEnabled(!theFrame.isFirst());
        endGame.setMnemonic(KeyEvent.VK_E);
        endGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                BoardPanel.endGame();
                newGame.setEnabled(true);
                endGame.setEnabled(false);
            }
        });
        if (theFrame.isFirst()) {
            BoardPanel.endGame();
        }

        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.setEnabled(theFrame.isFirst());
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.newGame();
                
            }
        });
        final JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
        
        game.add(endGame);
        game.add(newGame);
        game.addSeparator();
        game.add(exit);
        this.add(game);
        
    }
    
    /**
     * Sets up the about menu.
     *
     * @param theFrame the frame for the GUI
     */
    private void setupAboutMenu(final TetrisGUI theFrame) {
        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        final JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (BoardPanel.gameInPlay()) {
                    BoardPanel.paused();
                }
                JOptionPane.showMessageDialog(theFrame, ABOUT_DIALOG);
                if (!BoardPanel.gameInPlay()) {
                    BoardPanel.paused();
                }
                
            }
        });
        help.add(about);
        this.add(help);
    }

}
