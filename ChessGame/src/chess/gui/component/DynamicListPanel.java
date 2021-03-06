/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.gui.component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

/**
 *
 * @author joelm
 */
public class DynamicListPanel extends javax.swing.JPanel {

    private DefaultListModel<String> listModel;
    
    /**
     * Creates new form PlayersList
     */
    public DynamicListPanel() {
        this("Players Online");
    }
    
    public DynamicListPanel(String title) {
        listModel = new DefaultListModel<String>();
        initComponents();
        playersListField.setModel(listModel);
        setTitle(title);
    }
    
    public final void pushItem(String name) {
        if (!listModel.contains(name))
            listModel.addElement(name);
    }
    
    public final void popItem(String name) {
        listModel.removeElement(name);
    }
    
    public final void setItems(String[] names) {
        for (int i = 0; i < listModel.size() || i < names.length; ++i) {
            if (i < listModel.size() && i < names.length)
                listModel.set(i, names[i]);
            else if (i < listModel.size())
                listModel.remove(i--);
            else if (i < names.length)
                listModel.addElement(names[i]);
        }
    }
    
    public final void setTitle(String title) {
        borderPanel.setBorder(BorderFactory.createTitledBorder(title));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        borderPanel = new javax.swing.JPanel();
        listScrollPane = new javax.swing.JScrollPane();
        playersListField = new javax.swing.JList<>();

        borderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Players Online"));

        playersListField.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listScrollPane.setViewportView(playersListField);

        javax.swing.GroupLayout borderPanelLayout = new javax.swing.GroupLayout(borderPanel);
        borderPanel.setLayout(borderPanelLayout);
        borderPanelLayout.setHorizontalGroup(
            borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );
        borderPanelLayout.setVerticalGroup(
            borderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel borderPanel;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JList<String> playersListField;
    // End of variables declaration//GEN-END:variables
}
