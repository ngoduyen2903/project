package UserInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HomeAdmin extends javax.swing.JFrame implements Runnable {

    private Detail detail;
    private Thread thread;

    public HomeAdmin(Detail d) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        lblSoftwareName.setForeground(Color.GREEN);
        lblRun.setForeground(Color.GREEN);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        detail = new Detail(d);
       
        //Start();
    }

    private void Start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void Update() {
        lblRun.setForeground(Color.GREEN);
        lblRun.setLocation(lblRun.getX() - 1, lblRun.getY());
        if (lblRun.getX() + lblRun.getWidth() < 0) {
            lblRun.setLocation(this.getWidth(), lblRun.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Image/Background.png");
            public void paintComponent(Graphics g){

                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        btnEmployeesManagement = new javax.swing.JButton();
        btnRevenue = new javax.swing.JButton();
        btnProduct = new javax.swing.JButton();
        btnData = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnAccount = new javax.swing.JButton();
        btnOrders = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giao Di???n H??? Th???ng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(80, 180, 155));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEmployeesManagement.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEmployeesManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Account.png"))); // NOI18N
        btnEmployeesManagement.setText("C???p Nh???t Nh??n Vi??n");
        btnEmployeesManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmployeesManagement.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEmployeesManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmployeesManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeesManagementActionPerformed(evt);
            }
        });
        jPanel1.add(btnEmployeesManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 130, 120));

        btnRevenue.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRevenue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Payroll.png"))); // NOI18N
        btnRevenue.setText("Th???ng K??");
        btnRevenue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRevenue.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnRevenue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevenueActionPerformed(evt);
            }
        });
        jPanel1.add(btnRevenue, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 320, 120, 110));

        btnProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Product.png"))); // NOI18N
        btnProduct.setText("C???p Nh???t S???n Ph???m");
        btnProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProduct.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 130, 120));

        btnData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Database.png"))); // NOI18N
        btnData.setText("C???p Nh???t Th??ng Tin");
        btnData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnData.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataActionPerformed(evt);
            }
        });
        jPanel1.add(btnData, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 132, 120, 120));

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Search.png"))); // NOI18N
        btnFind.setText("T??m Ki???m");
        btnFind.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFind.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnFind.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel1.add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 130, 110));

        btnAccount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change PasssWord.png"))); // NOI18N
        btnAccount.setText("C???p Nh???t T??i Kho???n");
        btnAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccount.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountActionPerformed(evt);
            }
        });
        jPanel1.add(btnAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 320, 120, 110));

        btnOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cart.png"))); // NOI18N
        btnOrders.setText("C???p Nh???t ????n H??ng");
        btnOrders.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrders.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnOrders.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdersActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 120, 120));

        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/LogOut.png"))); // NOI18N
        btnLogOut.setText("????ng Xu???t");
        btnLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogOut.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnLogOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 120, 110));

        btnSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Sale.png"))); // NOI18N
        btnSale.setText("B??n H??ng");
        btnSale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSale.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSale.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });
        jPanel1.add(btnSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 128, 120, 120));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit.png"))); // NOI18N
        btnExit.setText("Tho??t");
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 120, 110));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/MilkTea.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int lick = JOptionPane.showConfirmDialog(null, "B???n C?? Mu???n Tho??t Kh???i Ch????ng Tr??nh Hay Kh??ng?", "Th??ng B??o", 2);
        if (lick == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            if (lick == JOptionPane.CANCEL_OPTION) {
                this.setVisible(true);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int lick = JOptionPane.showConfirmDialog(null, "B???n C?? Mu???n Tho??t Kh???i Ch????ng Tr??nh Hay Kh??ng?", "Th??ng B??o", 2);
        if (lick == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            if (lick == JOptionPane.CANCEL_OPTION) {
                this.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n ????ng xu???t t??i kho???n kh???i h??? th???ng hay kh??ng?", "Th??ng B??o", 2);
        if (Click == JOptionPane.YES_OPTION) {
            Login login = new Login();
            this.setVisible(false);
            login.setVisible(true);
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        Account account = new Account(detail);
        this.setVisible(false);
        account.setVisible(true);
    }//GEN-LAST:event_btnAccountActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        Find find = new Find(detail);
        this.setVisible(false);
        find.setVisible(true);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevenueActionPerformed
        Revenue payroll = new Revenue(detail);
        this.setVisible(false);
        payroll.setVisible(true);
    }//GEN-LAST:event_btnRevenueActionPerformed

    private void btnEmployeesManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeesManagementActionPerformed
        EmployeesManagement account = new EmployeesManagement(detail);
        this.setVisible(false);
        account.setVisible(true);
    }//GEN-LAST:event_btnEmployeesManagementActionPerformed

    private void btnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataActionPerformed
        InformationManagement data = new InformationManagement(detail);
        this.setVisible(false);
        data.setVisible(true);
    }//GEN-LAST:event_btnDataActionPerformed

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        Product product = new Product(detail);
        this.setVisible(false);
        product.setVisible(true);
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersActionPerformed
        OrderForm orderForm = new OrderForm(detail);
        this.setVisible(false);
        orderForm.setVisible(true);
    }//GEN-LAST:event_btnOrdersActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        Sale sale = new Sale(detail);
        this.setVisible(false);
        sale.setVisible(true);
    }//GEN-LAST:event_btnSaleActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail = new Detail();
                new HomeAdmin(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccount;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnEmployeesManagement;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnProduct;
    private javax.swing.JButton btnRevenue;
    private javax.swing.JButton btnSale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        long FPS = 80;
        long period = 1000 * 1000000 / FPS;
        long beginTime, sleepTime;

        beginTime = System.nanoTime();
        while (true) {

            Update();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(period / 2000000);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            beginTime = System.nanoTime();
        }
    }
}
