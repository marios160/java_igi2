
package hexdecconverter;

import sun.applet.Main;

/**
 *
 * @author Mateusz
 */
public class Okienko extends javax.swing.JFrame {

    
    public Okienko() {
        initComponents();
    }
    
    public static void main(String[] args) {
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Okienko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Okienko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Okienko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Okienko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Okienko().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fromHex = new javax.swing.JTextField();
        hexToDec = new javax.swing.JButton();
        toDec = new javax.swing.JTextField();
        decToHex = new javax.swing.JButton();
        toHex = new javax.swing.JTextField();
        fromDec = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Koder");

        fromHex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fromHexKeyPressed(evt);
            }
        });

        hexToDec.setText("Convert");
        hexToDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hexToDecActionPerformed(evt);
            }
        });

        toDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toDecActionPerformed(evt);
            }
        });

        decToHex.setText("Convert");
        decToHex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decToHexActionPerformed(evt);
            }
        });

        fromDec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fromDecKeyPressed(evt);
            }
        });

        jLabel1.setText("Hex to Dec");

        jLabel2.setText("Dec to Hex");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromDec)
                    .addComponent(toHex)
                    .addComponent(fromHex)
                    .addComponent(toDec)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(decToHex)
                            .addComponent(hexToDec)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 310, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(fromHex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hexToDec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decToHex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toHex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toDecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toDecActionPerformed

    private void hexToDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hexToDecActionPerformed
        String txt = fromHex.getText();
        int decimal = Integer.parseInt(txt,16);
        toDec.setText(decimal+"");
        
    }//GEN-LAST:event_hexToDecActionPerformed

    private void decToHexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decToHexActionPerformed
        String txt = fromDec.getText();
        String hex = Integer.toHexString(Integer.parseInt(txt));
        toHex.setText(hex);
    }//GEN-LAST:event_decToHexActionPerformed

    private void fromHexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fromHexKeyPressed
        if(evt.getKeyCode() == 10)
            hexToDecActionPerformed(null);
    }//GEN-LAST:event_fromHexKeyPressed

    private void fromDecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fromDecKeyPressed
        if(evt.getKeyCode() == 10)
            decToHexActionPerformed(null);
    }//GEN-LAST:event_fromDecKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton decToHex;
    private javax.swing.JTextField fromDec;
    private javax.swing.JTextField fromHex;
    private javax.swing.JButton hexToDec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField toDec;
    private javax.swing.JTextField toHex;
    // End of variables declaration//GEN-END:variables
}
