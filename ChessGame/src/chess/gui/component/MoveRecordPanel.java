/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.gui.component;

import chess.game.Alignment;
import chess.game.ChessPieceClass;
import chess.game.Coordinate;

/**
 *
 * @author joelm
 */
public class MoveRecordPanel extends javax.swing.JPanel {

    public MoveRecordPanel() {
        this(Alignment.WHITE, "TEST");
    }
    
    /**
     * Creates new form MoveRecordPanel
     */
    private MoveRecordPanel(Alignment color, String message) {
        initComponents();
        colorLabel.setText(color.toString());
        actionLabel.setText(message);
    }
    
    public static MoveRecordPanel movement(Alignment color, ChessPieceClass moving, Coordinate from, Coordinate to) {
        return new MoveRecordPanel(color, String.format("%s %s → %s", moving, from, to));
    }
    
    public static MoveRecordPanel capture(Alignment color, ChessPieceClass captured, Coordinate at) {
        System.out.println("Logging capture of " + captured);
        return new MoveRecordPanel(color, String.format("Captured %s at %s", captured, at));
    }
    
    public static MoveRecordPanel promote(Alignment color, Coordinate at) {
        return new MoveRecordPanel(color, String.format("Promoted at %s", at));
    }
    
    public static MoveRecordPanel check(Alignment color) {
        return new MoveRecordPanel(color, "Check");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorLabel = new javax.swing.JLabel();
        actionLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(32767, 30));

        colorLabel.setText("WHITE");
        colorLabel.setMaximumSize(new java.awt.Dimension(33, 22));

        actionLabel.setText("A1 → B1");
        actionLabel.setMaximumSize(new java.awt.Dimension(42, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(actionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionLabel;
    private javax.swing.JLabel colorLabel;
    // End of variables declaration//GEN-END:variables
}
