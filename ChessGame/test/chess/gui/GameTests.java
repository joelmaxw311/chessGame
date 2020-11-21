/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.gui;

import chess.Chess;
import chess.Server;
import chess.game.Coordinate;
import chess.gui.state.MainLobbyState;
import chess.gui.state.PlayState;
import chess.gui.state.RegisterState;
import chess.gui.state.SessionLobbyState;
import chess.gui.state.TitleState;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author joelm
 */
public class GameTests {
    
    public GameTests() {
    }
    
    public static void main(String[] args) throws IOException, TimeoutException {
        
        int port = GameProperties.serverPort(); 
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ServerWindow().setVisible(true); // show a window so the server can be terminated
                Server server = new Server();
                server.start(port, 10);
            }
        }).start();
        
        new GameTests().play();
        System.out.println("TEST COMPLETE");
        
        System.exit(0);
    }
    
    private void sleep(long ms) {
        try {
            Thread.sleep(ms); // wait for the server to start
        } catch (InterruptedException ex) {
            Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void interact(GameWindow window, GUIAction action, Object... parameters) {
        window.interact(action, parameters);
        sleep(100);
    }
    
    private File saveScreenshot(GameWindow window, String label) throws IOException {
        Container form = window.getContentPane();
        BufferedImage img = new BufferedImage(form.getWidth(), form.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = img.createGraphics();
        form.printAll(g2d);
        g2d.dispose();
        
        File outfile = new File(String.format("screenshot_%s_%s.png", label, window.guiState().getClass().getSimpleName()));
        ImageIO.write(img, "png", outfile);
        
        return outfile;
    }
    
    private void waitForState(GameWindow window, Class<? extends GUIState> state, long timeout) throws TimeoutException {
        final int freq = 10;
        System.out.println("Waiting for " + state.getName());
        int i;
        for (i = 0; !state.isInstance(window.guiState()) && i < timeout; i += freq) {
            sleep(freq);
        }
        if (i >= timeout)
            throw new TimeoutException("Time out while waiting");
        System.out.println("Done waiting: " + window.guiState().getClass().getName());
    }
    
    public void play() throws IOException, TimeoutException {
        sleep(500);
        
        int cc = 0;
        
        // Start client 1
        GameWindow window1 = new GameWindow ();
        window1.setVisible(true);
        window1.setTitle("Chess ");
        GameWindow window2 = new GameWindow ();
        window2.setVisible(true);
        window2.setTitle("Chess  ");
        System.out.println("Created window1");
        sleep(2000);
        
        // Title
        saveScreenshot(window1, (cc++) + "-1");
        interact(window1, GUIAction.CONTINUE);
        waitForState(window1, RegisterState.class, 100);
        
        // Register
        interact(window1, GUIAction.ENTER_TEXT, "Player1");
        saveScreenshot(window1, (cc++) + "-1");
        interact(window1, GUIAction.SUBMIT_TEXT);
        waitForState(window1, MainLobbyState.class, 4000);
        
        // Main Lobby
        saveScreenshot(window1, (cc++) + "-1");
        interact(window1, GUIAction.CREATE);
        saveScreenshot(window1, (cc++) + "-1");
        waitForState(window1, SessionLobbyState.class, 2000);
        
        // Session Lobby
        saveScreenshot(window1, (cc++) + "-1");
        interact(window1, GUIAction.CONTINUE);
        saveScreenshot(window1, (cc++) + "-1");
        
        // Title
        saveScreenshot(window2, (cc++) + "-2");
        interact(window2, GUIAction.CONTINUE);
        waitForState(window2, RegisterState.class, 100);
        
        // Register
        interact(window2, GUIAction.ENTER_TEXT, "Player2");
        saveScreenshot(window2, (cc++) + "-2");
        interact(window2, GUIAction.CONTINUE);
        waitForState(window2, MainLobbyState.class, 4000);
        
        // Main Lobby
        sleep(300);
        saveScreenshot(window1, (cc++) + "-1");
        saveScreenshot(window2, (cc++) + "-2");
        interact(window2, GUIAction.SELECT, "Player1");
        waitForState(window2, SessionLobbyState.class, 2000);
        
        // Session Lobby
        saveScreenshot(window1, (cc++) + "-1");
        saveScreenshot(window2, (cc++) + "-2");
        interact(window2, GUIAction.CONTINUE);
        
        waitForState(window1, PlayState.class, 15000);
        waitForState(window2, PlayState.class, 15000);
        
        // Play
        saveScreenshot(window1, (cc++) + "-1");
        saveScreenshot(window2, (cc++) + "-2");
        
        String[][] moves = new String[][] {
            {"1", "A2", "A3"},
            {"2", "A7", "A5"},
            {"1", "B2", "B4"},
            {"2", "B7", "B6"},
            {"1", "C2", "C3"},
            {"2", "C7", "C5"},
            {"1", "D2", "D4"},
            {"2", "D7", "D6"},
            {"1", "E2", "E3"},
            {"2", "E7", "E5"},
            {"1", "F2", "F4"},
            {"2", "F7", "F6"},
            {"1", "G2", "G3"},
            {"2", "G7", "G5"},
            {"1", "H2", "H4"},
            {"2", "H7", "H6"},
            
            {"1", "G1", "H3"},
            {"2", "C8", "H3"},
            
            {"1", "H1", "H3"},
            {"2", "E8", "C6"},
            
            {"1", "D1", "D2"},
            {"2", "C6", "H1"}
        };
        
        int m = 0;
        while (m < moves.length) {
            interact(moves[m][0].equals("1") ? window1 : window2, GUIAction.SELECT, new Coordinate(moves[m][1]), new Coordinate(moves[m][2]));
            sleep(1000);
            saveScreenshot(moves[m][0].equals("1") ? window1 : window2, (cc++) + "-" + moves[m][0]);
            m++;
        }
        saveScreenshot(window1, (cc++) + "-1");
        saveScreenshot(window2, (cc++) + "-2");
        
        sleep(4000);
        while (window1.guiState().getClass() != TitleState.class) {
            interact(window1, GUIAction.EXIT);
            sleep(60);
            saveScreenshot(window1, (cc++) + "-1");
            saveScreenshot(window2, (cc++) + "-2");
        }
        while (window2.guiState().getClass() != TitleState.class) {
            interact(window2, GUIAction.EXIT);
            sleep(60);
            saveScreenshot(window1, (cc++) + "-1");
            saveScreenshot(window2, (cc++) + "-2");
        }
        saveScreenshot(window1, (cc++) + "-1");
        saveScreenshot(window2, (cc++) + "-2");
        sleep(10);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
