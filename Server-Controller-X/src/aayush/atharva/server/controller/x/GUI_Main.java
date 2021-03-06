package aayush.atharva.server.controller.x;

import static aayush.atharva.server.controller.x.GetServerInfo.*;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hyper
 */
public class GUI_Main extends javax.swing.JFrame {

    List<Player> players;
    GetServerInfo s;


    /**
     * Creates new form GUI_Main
     */
    public GUI_Main() {
        initComponents();
        s = new GetServerInfo();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        server_name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        players_count = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        map_name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        map_time = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        server_password = new javax.swing.JLabel();
        start_stop_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IGI-2 Server Console");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Server Name: ");

        server_name.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        server_name.setText("IGI2 Server");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setText("Players:");

        players_count.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        players_count.setText("0/0");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setText("Map Name:");

        map_name.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        map_name.setText("MAP");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setText("Map Time:");

        map_time.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        map_time.setText("0:0");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setText("Password: ");

        server_password.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        server_password.setText("No");

        start_stop_btn.setText("Start Server");
        start_stop_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_stop_btnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(start_stop_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(server_name, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(players_count, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(map_name, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 148, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(map_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(server_password, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(server_name)
                    .addComponent(jLabel3)
                    .addComponent(map_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(players_count)
                    .addComponent(jLabel5)
                    .addComponent(map_time)
                    .addComponent(jLabel7)
                    .addComponent(server_password))
                .addGap(32, 32, 32)
                .addComponent(start_stop_btn)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    String BTN = "No";
    String stop = "No";

    private void start_stop_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_stop_btnActionPerformed

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                UpdateServerInfo();
            }
        };

        if (BTN.equals("Yes")) {
            try {
                stop = "Yes";
                Thread.sleep(3000);
                start_stop_btn.setText("Start Server");
                stop = "No";
                BTN = "No";
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            thread1.start();
        }

        UpdatePlayers u = new UpdatePlayers(this);
        u.start();


    }//GEN-LAST:event_start_stop_btnActionPerformed

    private void UpdateServerInfo() {

        BTN = "Yes";
        start_stop_btn.setText("Stop Server");

        while (true) {
            if (stop.equals("Yes")) {
                return;
            }

            try {
                String Data = sendStatus();
                GetServerInfo getInfo = new GetServerInfo();
                getInfo.setServer(Data);

                ServerName = name;
                ServerMap = map;
                Players = numpl + "/" + maxpl;
                ServerUptime = uptime;
                ServerMapTime = time;
                ServerMapStat = mapstat;
                ServerPassword = password;

                server_name.setText(ServerName);
                players_count.setText(Players);
                map_name.setText(ServerMap);
                map_time.setText(ServerMapTime);
                if (ServerPassword.equals("No")) {
                    server_password.setForeground(Color.green);
                } else {
                    server_password.setForeground(Color.red);
                }
                server_password.setText(ServerPassword);

                Thread.sleep(2000);

            } catch (InterruptedException ex) {
                Logger.getLogger(GUI_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTable() {
        this.players = this.s.getPlayers();
        int igi = 0;
        int cons = 0;
        for (int i = 0; i < 12; i++) {
            this.jTable1.setValueAt(null, i, 0);
            this.jTable1.setValueAt(null, i, 1);
            this.jTable1.setValueAt(null, i, 2);
            this.jTable1.setValueAt(null, i, 3);
            this.jTable2.setValueAt(null, i, 0);
            this.jTable2.setValueAt(null, i, 1);
            this.jTable2.setValueAt(null, i, 2);
            this.jTable2.setValueAt(null, i, 3);
        }

        if (this.players != null) {
            for (Player zm : this.players) {
                if (zm != null) {
                    if (zm.getTeam().equals("0")) {
                        this.jTable1.setValueAt(zm.getId(), igi, 0);
                        this.jTable1.setValueAt(zm.getNick(), igi, 1);
                        this.jTable1.setValueAt(zm.getStats(), igi, 2);
                        this.jTable1.setValueAt(zm.getPing(), igi, 3);
                        igi++;
                    } else {
                        this.jTable2.setValueAt(zm.getId(), cons, 0);
                        this.jTable2.setValueAt(zm.getNick(), cons, 1);
                        this.jTable2.setValueAt(zm.getStats(), cons, 2);
                        this.jTable2.setValueAt(zm.getPing(), cons, 3);
                        cons++;
                    }
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JLabel map_name;
    public static javax.swing.JLabel map_time;
    public static javax.swing.JLabel players_count;
    public static javax.swing.JLabel server_name;
    public static javax.swing.JLabel server_password;
    private javax.swing.JButton start_stop_btn;
    // End of variables declaration//GEN-END:variables

}
