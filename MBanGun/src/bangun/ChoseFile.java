/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangun;

import java.io.File;

/**
 *
 * @author Mateusz
 */
public class ChoseFile extends javax.swing.JFrame {

    /**
     * Creates new form Start
     */
    public ChoseFile() {
        initComponents();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setTitle(ChooseWeapons.odkoduj(Strings.chosefiletitle)
        );

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setApproveButtonToolTipText("");
        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Program Files"));
        jFileChooser1.setDialogTitle("Choose file to work");
        jFileChooser1.setToolTipText("");
        jFileChooser1.setAutoscrolls(true);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        setVisible(false);
        File f = jFileChooser1.getSelectedFile();
        BanGun.copy(f.getAbsolutePath(), "out\\weapon.qvm");
        BanGun.wczytajPlik();
    }//GEN-LAST:event_jFileChooser1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}
