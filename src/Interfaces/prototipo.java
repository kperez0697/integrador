/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class prototipo extends javax.swing.JFrame {

    /**
     * Creates new form prototipo
     */
    String Usuario = "ADMIN";
    int IdCartera = 0;

    public prototipo() {
        initComponents();
        Cargar_Moneda();
        setLocationRelativeTo(null);
//        Cargar_Datos();
    }

    public void Cargar_Moneda() {

        Connection con = Conexion.getConexion();
        cbmonedas.removeAllItems();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MONEDA WHERE CODMONEDA<>'USDT'");

            while (rs.next()) {
                cbmonedas.addItem(rs.getString("CODMONEDA"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void Cargar_Datos() {
//        Connection con = Conexion.getConexion();
//        try {
//            Statement stmt = con.createStatement();
//            String codmoneda = cbmonedas.getSelectedItem().toString();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE WHERE CODMONEDA='" + codmoneda + "'");
//
//            while (rs.next()) {
//                txtMonto.setText(rs.getString("FONDOS"));
//            }
//
//            rs.close();
//            stmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    void Tipo_Cambio() {
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            String codmoneda = cbmonedas.getSelectedItem().toString();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MONEDA WHERE CODMONEDA='" + codmoneda + "'");

            while (rs.next()) {
                txttcambio.setText(rs.getString("TIPOCAMBIO"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void calcular_usd() {
        double usd = 0;
        double fondos = 0;
        double tcambio = 0;
        if (txtMonto != null) {
            fondos = Double.parseDouble(txtMonto.getText());
        }
        if (txttcambio != null) {
            tcambio = Double.parseDouble(txttcambio.getText());
        }
        double monto = fondos * tcambio;
        txtusd.setText("" + monto);
    }

    void obtenerMontoCuentaUSDT() {
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cast(round(SUM(FONDOS*TIPOCAMBIO),2) as numeric(36,2))MONTO_USDT FROM CLIENTE CL\n"
                    + "INNER JOIN MONEDA MON ON CL.CODMONEDA=MON.CODMONEDA WHERE USUARIO='" + Usuario + "'");

            while (rs.next()) {
                txtfondos.setText(rs.getString("MONTO_USDT"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void obtenerMontosoloUSDT() {
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cast(round(FONDOS,2) as numeric(36,2))MONTO_USDT FROM CLIENTE WHERE USUARIO='" + Usuario + "' AND CODMONEDA='USDT' ");

            while (rs.next()) {
                txtfondousdt.setText(rs.getString("MONTO_USDT"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    double exisfondoscompra = 0;

    void ExisteFondosCompra() {
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            String codmoneda = cbmonedas.getSelectedItem().toString();
            ResultSet rs = stmt.executeQuery("SELECT FONDOS FROM CLIENTE WHERE USUARIO='" + Usuario + "' AND CODMONEDA='USDT'");

            while (rs.next()) {
                exisfondoscompra = (rs.getDouble("FONDOS"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double exisfondosventa = 0;

    void ExisteFondosVenta() {
        Connection con = Conexion.getConexion();
        try {
            Statement stmt = con.createStatement();
            String codmoneda = cbmonedas.getSelectedItem().toString();
            ResultSet rs = stmt.executeQuery("SELECT FONDOS FROM CLIENTE WHERE USUARIO='" + Usuario + "' AND CODMONEDA='" + codmoneda + "'");

            while (rs.next()) {
                exisfondosventa = (rs.getDouble("FONDOS"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbmonedas = new javax.swing.JComboBox<>();
        txtMonto = new javax.swing.JTextField();
        txtusd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btcomprar = new javax.swing.JButton();
        btvender = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txttcambio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfondos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfondousdt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de Moneda");

        cbmonedas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BitCoin" }));
        cbmonedas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmonedasItemStateChanged(evt);
            }
        });

        txtMonto.setText("0");
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        txtusd.setText("0");
        txtusd.setEnabled(false);

        jLabel2.setText("USD");

        jLabel3.setText("Monto");

        btcomprar.setText("comprar");
        btcomprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcomprarActionPerformed(evt);
            }
        });

        btvender.setText("Vender");
        btvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btvenderActionPerformed(evt);
            }
        });

        jButton3.setText("Enviar");

        jLabel4.setText("T.Cambio:");

        txttcambio.setText("0");
        txttcambio.setEnabled(false);

        jLabel5.setText("Fondos de la cuenta (USDT)");

        jLabel6.setText("Fondos en USDT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMonto)
                                        .addGap(41, 41, 41)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbmonedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jButton3))))
                            .addComponent(txtusd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txttcambio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(txtfondos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btcomprar)
                        .addGap(30, 30, 30)
                        .addComponent(btvender)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfondousdt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbmonedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txttcambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtusd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtfondos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfondousdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btcomprar)
                    .addComponent(btvender)
                    .addComponent(jButton3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        calcular_usd();

    }//GEN-LAST:event_txtMontoActionPerformed

    private void cbmonedasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmonedasItemStateChanged
//        Cargar_Datos();
        Tipo_Cambio();
//        obtenerIDcartera();
        calcular_usd();
        obtenerMontoCuentaUSDT();
        obtenerMontosoloUSDT();
    }//GEN-LAST:event_cbmonedasItemStateChanged

    private void btcomprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcomprarActionPerformed
        ExisteFondosCompra();
        if (exisfondoscompra > 0) {
            Connection con = Conexion.getConexion();
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                String codmoneda = cbmonedas.getSelectedItem().toString();
                double usdt = Double.parseDouble(txtusd.getText());
                String sql = "UPDATE CLIENTE SET FONDOS=" + monto + "+FONDOS WHERE USUARIO='" + Usuario + "' AND CODMONEDA='" + codmoneda + "' \n"
                        + "UPDATE CLIENTE SET FONDOS=FONDOS-" + usdt + " WHERE USUARIO='" + Usuario + "' AND CODMONEDA='USDT'";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.executeUpdate();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Compra exitosa");
                obtenerMontoCuentaUSDT();
                obtenerMontosoloUSDT();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existen Fondos en tu cartera para comprar");
        }

    }//GEN-LAST:event_btcomprarActionPerformed

    private void btvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btvenderActionPerformed
        ExisteFondosVenta();
        if (exisfondosventa > 0) {
            Connection con = Conexion.getConexion();
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                String codmoneda = cbmonedas.getSelectedItem().toString();
                double usdt = Double.parseDouble(txtusd.getText());
                String sql = "UPDATE CLIENTE SET FONDOS=FONDOS-" + monto + " WHERE USUARIO='" + Usuario + "' AND CODMONEDA='" + codmoneda + "' \n"
                        + "UPDATE CLIENTE SET FONDOS=FONDOS+" + usdt + " WHERE USUARIO='" + Usuario + "' AND CODMONEDA='USDT'";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.executeUpdate();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Venta exitosa");
                obtenerMontoCuentaUSDT();
                obtenerMontosoloUSDT();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existen Fondos en tu cartera para vender");

        }

    }//GEN-LAST:event_btvenderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(prototipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prototipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prototipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prototipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prototipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcomprar;
    private javax.swing.JButton btvender;
    private javax.swing.JComboBox<String> cbmonedas;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtfondos;
    private javax.swing.JTextField txtfondousdt;
    private javax.swing.JTextField txttcambio;
    private javax.swing.JTextField txtusd;
    // End of variables declaration//GEN-END:variables
}
