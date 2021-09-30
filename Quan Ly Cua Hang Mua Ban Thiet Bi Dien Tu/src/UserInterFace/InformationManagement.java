package UserInterFace;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class InformationManagement extends javax.swing.JFrame {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private Detail detail;
    private boolean Add = false, Change = false;

    String sql1 = "SELECT * FROM MaterialType";
    String sql2 = "SELECT * FROM Material";
    String sql3 = "SELECT * FROM MaterialSupplier";

    public InformationManagement(Detail d) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        detail = new Detail(d);
        lblStatus.setForeground(Color.red);
        lblStatus1.setForeground(Color.red);
        lblStatus2.setForeground(Color.red);
        connectionSQLServer();
        loadMaterial_type(sql1);
        loadMaterial(sql2);
        loadSupplier(sql3);
        disabledMaterial_type();
        disabledMaterialSupplier();
        disabledMaterial();
    }

    private void connectionSQLServer() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                    + "databaseName=MilkTeaShopManagement;user=sa;password=123456");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadMaterialTypeID() {
        txbMaterial_Type_ID.removeAllItems();
        String sql = "SELECT * FROM MaterialType";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                this.txbMaterial_Type_ID.addItem(rs.getString("Material_TypeID").trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadMaterial_type(String sql) {
        tableMaterial_Type.removeAll();
        try {
            String[] arr = {"ID", "Material Type Name"};
            DefaultTableModel modle = new DefaultTableModel(arr, 0);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("Material_TypeID").trim());
                vector.add(rs.getString("Material_Type_Name").trim());
                modle.addRow(vector);
            }
            tableMaterial_Type.setModel(modle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadMaterial(String sql) {
        tableMaterial.removeAll();
        try {
            String[] arr = {"ID", "Material Name", "Unit", "Material Type ID"};
            DefaultTableModel modle = new DefaultTableModel(arr, 0);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("MaterialID").trim());
                vector.add(rs.getString("MaterialName").trim());
                vector.add(rs.getString("Unit").trim());
                vector.add(rs.getString("Material_TypeID").trim());
                modle.addRow(vector);
            }
            tableMaterial.setModel(modle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadSupplier(String sql) {
        tableSupplier.removeAll();
        try {
            String[] arr = {"ID", "Supplier Name", "Address", "Phone", "Email"};
            DefaultTableModel modle = new DefaultTableModel(arr, 0);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("Material_SupplierID").trim());
                vector.add(rs.getString("Material_Supplier_Name").trim());
                vector.add(rs.getString("Address").trim());
                vector.add(rs.getString("Phone").trim());
                vector.add(rs.getString("Email").trim());
                modle.addRow(vector);
            }
            tableSupplier.setModel(modle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void backHome() {
        HomeAdmin home = new HomeAdmin(detail);
        this.setVisible(false);
        home.setVisible(true);
    }

    private void enabledMaterial_type() {
        txtMaterial_type_id.setEnabled(true);
        txtMaterial_type_name.setEnabled(true);
        lblStatus.setText("Status!");
    }

    private void enabledMaterial() {
        txtMaterial_ID.setEnabled(true);
        txtMaterialName.setEnabled(true);
        txtUnit.setEnabled(true);
        lblStatus1.setText("Status!");
    }

    private void enabledMaterialSupplier() {
        txtSupplierID.setEnabled(true);
        txtSupplierName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        lblStatus2.setText("Status!");
    }

    private void disabledMaterial_type() {
        txtMaterial_type_id.setEnabled(false);
        txtMaterial_type_name.setEnabled(false);

    }

    private void disabledMaterial() {
        txtMaterial_ID.setEnabled(false);
        txtMaterialName.setEnabled(false);
        txtUnit.setEnabled(false);
    }

    private void disabledMaterialSupplier() {
        txtSupplierID.setEnabled(false);
        txtSupplierName.setEnabled(false);
        txtAddress.setEnabled(false);
        txtPhone.setEnabled(false);
        txtEmail.setEnabled(false);
    }

    private void refresh() {
        Change = false;
        Add = false;
        txtMaterial_type_id.setText("");
        txtMaterial_type_name.setText("");
        txtMaterial_ID.setText("");
        txtMaterialName.setText("");
        txtUnit.setText("");
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        btnAdd_Material.setEnabled(true);
        btnChangeMaterial.setEnabled(false);
        btnDelete_Material.setEnabled(false);
        btnSave_Material.setEnabled(false);
        btnAddSupplier.setEnabled(true);
        btnChangeSupplier.setEnabled(false);
        btnDeleteSupplier.setEnabled(false);
        btnSaveSupplier.setEnabled(false);
        btnAddMaterial_type.setEnabled(true);
        btnChangeMaterial_type.setEnabled(false);
        btnDeleteMaterial_type.setEnabled(false);
        btnSaveMaterial_type.setEnabled(false);
        disabledMaterialSupplier();
        disabledMaterial_type();
        disabledMaterial();
    }

    private boolean checkMaterial_type() {
        boolean kq = true;
        String sqlCheck = "SELECT * FROM MaterialType";
        try {
            pst = conn.prepareStatement(sqlCheck);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (this.txtMaterial_type_id.getText().equals(rs.getString("Material_TypeID").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;
    }

    private boolean checkMaterial() {
        boolean kq = true;
        String sqlCheck = "SELECT * FROM Material";
        try {
            pst = conn.prepareStatement(sqlCheck);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (this.txtMaterial_ID.getText().equals(rs.getString("MaterialID").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;
    }

    private boolean checkSupplier() {
        boolean kq = true;
        String sqlCheck = "SELECT * FROM MaterialSupplier";
        try {
            pst = conn.prepareStatement(sqlCheck);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (this.txtSupplierID.getText().equals(rs.getString("Material_SupplierID").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;
    }

    private boolean checkNullMaterial_type() {
        boolean kq = true;
        if (String.valueOf(this.txtMaterial_type_id.getText()).length() == 0) {
            lblStatus.setText("Material Type ID cannot be blank. Pls re-enter!!");
            return false;
        }
        if (String.valueOf(this.txtMaterial_type_name.getText()).length() == 0) {
            lblStatus.setText("Material Type Name cannot be blank. Pls re-enter!!");
            return false;
        }

        return kq;
    }

    private boolean checkNullMaterial() {
        boolean kq = true;
        if (String.valueOf(this.txtMaterial_ID.getText()).length() == 0) {
            lblStatus1.setText("Material ID cannot be blank. Pls re-enter!");
            return false;
        }
        if (String.valueOf(this.txtMaterialName.getText()).length() == 0) {
            lblStatus1.setText("Material Name cannot be blank. Pls re-enter!!");
            return false;
        }
        if (String.valueOf(this.txtUnit.getText()).length() == 0) {
            lblStatus1.setText("Unit cannot be blank. Pls re-enter!");
            return false;
        }
        return kq;
    }

    private boolean checkNullSupplier() {
        boolean kq = true;
        if (String.valueOf(this.txtSupplierID.getText()).length() == 0) {
            lblStatus2.setText("Supplier ID cannot be blank. Pls re-enter!");
            return false;
        }
        if (String.valueOf(this.txtSupplierName.getText()).length() == 0) {
            lblStatus2.setText("Supplier Name cannot be blank. Pls re-enter!");
            return false;
        }
        if (String.valueOf(this.txtAddress.getText()).length() == 0) {
            lblStatus2.setText("Address Name cannot be blank. Pls re-enter!");
            return false;
        }
        if (String.valueOf(this.txtPhone.getText()).length() == 0) {
            lblStatus2.setText("Phone Name cannot be blank. Pls re-enter!");
            return false;
        }
        if (String.valueOf(this.txtEmail.getText()).length() == 0) {
            lblStatus2.setText("Email Name cannot be blank. Pls re-enter!");
            return false;
        }
        return kq;
    }

    private void insertMaterial_type() {
        if (checkNullMaterial_type()) {
            String sqlInsert = "INSERT INTO [dbo].[MaterialType] ([Material_TypeID],[Material_Type_Name]) VALUES (?,?);";
            try {
                pst = conn.prepareStatement(sqlInsert);
                pst.setString(1, txtMaterial_type_id.getText());
                pst.setString(2, txtMaterial_type_name.getText());
                pst.executeUpdate();
                lblStatus.setText("Added successfully!");
                disabledMaterial_type();
                refresh();
                loadMaterial_type(sql1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void insertMaterial() {
        if (checkNullMaterial()) {
            String sqlInsert = "INSERT INTO [dbo].[Material] ([MaterialID],[MaterialName],[Unit],[Material_TypeID]) VALUES(?,?,?,?);";
            try {
                pst = conn.prepareStatement(sqlInsert);
                pst.setString(1, txtMaterial_ID.getText());
                pst.setString(2, txtMaterialName.getText());
                pst.setString(3, txtUnit.getText());
                pst.setString(4, String.valueOf(this.txbMaterial_Type_ID.getSelectedItem()));
                pst.executeUpdate();
                lblStatus1.setText("Added successfully!");
                disabledMaterial();
                refresh();
                loadMaterial(sql2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void addSupplier() {
        if (checkNullSupplier()) {
            String sqlInsert = "INSERT INTO [dbo].[MaterialSupplier] ([Material_SupplierID],[Material_Supplier_Name],[Address],[Phone],[Email])\n"
                    + "VALUES (?,?,?,?,?);";
            try {
                pst = conn.prepareStatement(sqlInsert);
                pst.setString(1, txtSupplierID.getText());
                pst.setString(2, txtSupplierName.getText());
                pst.setString(3, txtAddress.getText());
                pst.setString(4, txtPhone.getText());
                pst.setString(5, txtEmail.getText());
                pst.executeUpdate();
                lblStatus2.setText("Added successfully!");
                disabledMaterialSupplier();
                refresh();
                loadSupplier(sql3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateMaterial_type() {
        int Click = tableMaterial_Type.getSelectedRow();
        TableModel model = tableMaterial_Type.getModel();
        if (checkNullMaterial_type()) {
            String sqlChange = "UPDATE [dbo].[MaterialType] SET [Material_TypeID]=?,[Material_Type_Name]=?\n"
                    + "WHERE [Material_TypeID]='" + model.getValueAt(Click, 0).toString().trim() + "'";
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txtMaterial_type_id.getText());
                pst.setString(2, txtMaterial_type_name.getText());
                pst.executeUpdate();
                lblStatus.setText("Updated successfully!");
                disabledMaterial_type();
                refresh();
                loadMaterial_type(sql1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateSupplier() {
        int Click = tableSupplier.getSelectedRow();
        TableModel model = tableSupplier.getModel();
        if (checkNullSupplier()) {
            String sqlChange = "UPDATE [dbo].[MaterialSupplier] SET [Material_SupplierID]=?, [Material_Supplier_Name]=?, [Address]=?,[Phone]=?, [Email]=?\n"
                    + "WHERE [Material_SupplierID]='" + model.getValueAt(Click, 0).toString().trim() + "'";;
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txtSupplierID.getText());
                pst.setString(2, txtSupplierName.getText());
                pst.setString(3, txtAddress.getText());
                pst.setString(4, txtPhone.getText());
                pst.setString(5, txtEmail.getText());
                pst.executeUpdate();
                lblStatus2.setText("Updated successfully!");
                disabledMaterialSupplier();
                refresh();
                loadSupplier(sql3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateMaterial() {
        int Click = tableMaterial.getSelectedRow();
        TableModel model = tableMaterial.getModel();

        if (checkNullMaterial()) {
            String sqlChange = "UPDATE [dbo].[Material] SET [MaterialID]=?, [MaterialName]=?, [Unit]=?, [Material_TypeID]=?\n"
                    + "WHERE [MaterialID]='" + model.getValueAt(Click, 0).toString().trim() + "'";;
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txtMaterial_ID.getText());
                pst.setString(2, txtMaterialName.getText());
                pst.setString(3, txtUnit.getText());
                pst.setString(4, txbMaterial_Type_ID.getItemAt(Click));
                pst.executeUpdate();
                lblStatus1.setText("Updated successfully!");
                disabledMaterial();
                refresh();
                loadMaterial(sql2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private double convertedToNumbers(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }

    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMaterial_Type = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jMaterial_type_ID = new javax.swing.JLabel();
        txtMaterial_type_id = new javax.swing.JTextField();
        jMaterial_type_name = new javax.swing.JLabel();
        txtMaterial_type_name = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        btnRefresh_Material_type = new javax.swing.JButton();
        btnAddMaterial_type = new javax.swing.JButton();
        btnChangeMaterial_type = new javax.swing.JButton();
        btnDeleteMaterial_type = new javax.swing.JButton();
        btnSaveMaterial_type = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearchMaterial_type = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMaterial = new javax.swing.JTable();
        txtUnit = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jMaterialUnit = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        txtMaterialName = new javax.swing.JTextField();
        jMaterialName = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txtMaterial_ID = new javax.swing.JTextField();
        jMAaterialID = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnRefresh_Material = new javax.swing.JButton();
        btnAdd_Material = new javax.swing.JButton();
        btnChangeMaterial = new javax.swing.JButton();
        btnDelete_Material = new javax.swing.JButton();
        btnSave_Material = new javax.swing.JButton();
        btnSearch_Material = new javax.swing.JButton();
        txtSearch_Material = new javax.swing.JTextField();
        lblStatus1 = new javax.swing.JLabel();
        jMaterial_type_id = new javax.swing.JLabel();
        txbMaterial_Type_ID = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnRefreshSupplier = new javax.swing.JButton();
        btnAddSupplier = new javax.swing.JButton();
        btnChangeSupplier = new javax.swing.JButton();
        btnDeleteSupplier = new javax.swing.JButton();
        btnSaveSupplier = new javax.swing.JButton();
        btnSearchSupplier = new javax.swing.JButton();
        txtSearchSupplier = new javax.swing.JTextField();
        lblStatus2 = new javax.swing.JLabel();
        jSupplierID = new javax.swing.JLabel();
        txtSupplierID = new javax.swing.JTextField();
        jSupplierName = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnBackHome = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InformationManagement");
        setBackground(new java.awt.Color(80, 180, 155));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(80, 180, 155));

        jPanel3.setBackground(new java.awt.Color(82, 180, 155));

        tableMaterial_Type.setBackground(new java.awt.Color(80, 180, 155));
        tableMaterial_Type.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableMaterial_Type.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tableMaterial_Type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMaterial_TypeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableMaterial_Type);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(82, 180, 155));

        jMaterial_type_ID.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMaterial_type_ID.setForeground(new java.awt.Color(255, 255, 255));
        jMaterial_type_ID.setText("Material Type ID:");

        jMaterial_type_name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMaterial_type_name.setForeground(new java.awt.Color(255, 255, 255));
        jMaterial_type_name.setText("Material Type Name:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jMaterial_type_ID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaterial_type_id, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jMaterial_type_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaterial_type_name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMaterial_type_ID)
                    .addComponent(txtMaterial_type_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaterial_type_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMaterial_type_name))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(82, 180, 155));

        btnRefresh_Material_type.setBackground(new java.awt.Color(255, 255, 255));
        btnRefresh_Material_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefresh_Material_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefresh_Material_typeMouseClicked(evt);
            }
        });

        btnAddMaterial_type.setBackground(new java.awt.Color(255, 255, 255));
        btnAddMaterial_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAddMaterial_type.setText("Create");
        btnAddMaterial_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaterial_typeActionPerformed(evt);
            }
        });

        btnChangeMaterial_type.setBackground(new java.awt.Color(255, 255, 255));
        btnChangeMaterial_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangeMaterial_type.setText("Update");
        btnChangeMaterial_type.setEnabled(false);
        btnChangeMaterial_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeMaterial_typeActionPerformed(evt);
            }
        });

        btnDeleteMaterial_type.setBackground(new java.awt.Color(255, 255, 255));
        btnDeleteMaterial_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDeleteMaterial_type.setText("Delete");
        btnDeleteMaterial_type.setEnabled(false);
        btnDeleteMaterial_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMaterial_typeActionPerformed(evt);
            }
        });

        btnSaveMaterial_type.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveMaterial_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSaveMaterial_type.setText("Save");
        btnSaveMaterial_type.setEnabled(false);
        btnSaveMaterial_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMaterial_typeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRefresh_Material_type, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnAddMaterial_type, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChangeMaterial_type, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteMaterial_type, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveMaterial_type, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh_Material_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddMaterial_type)
                        .addComponent(btnChangeMaterial_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteMaterial_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSaveMaterial_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSearchMaterial_type.setBackground(new java.awt.Color(255, 255, 255));
        btnSearchMaterial_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Find.png"))); // NOI18N

        lblStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Status");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(492, 492, 492)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchMaterial_type)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchMaterial_type, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Material Type", jPanel3);

        jPanel1.setBackground(new java.awt.Color(82, 180, 155));

        tableMaterial.setBackground(new java.awt.Color(80, 180, 155));
        tableMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMaterialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMaterial);

        txtUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnitKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMaterialUnit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMaterialUnit.setForeground(new java.awt.Color(255, 255, 255));
        jMaterialUnit.setText("Unit:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMaterialName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMaterialName.setForeground(new java.awt.Color(255, 255, 255));
        jMaterialName.setText("Material Name:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMAaterialID.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMAaterialID.setForeground(new java.awt.Color(255, 255, 255));
        jMAaterialID.setText("Material ID:");

        jPanel17.setBackground(new java.awt.Color(80, 180, 155));

        btnRefresh_Material.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefresh_Material.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefresh_MaterialMouseClicked(evt);
            }
        });
        btnRefresh_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh_MaterialActionPerformed(evt);
            }
        });

        btnAdd_Material.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAdd_Material.setText("Create");
        btnAdd_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_MaterialActionPerformed(evt);
            }
        });

        btnChangeMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangeMaterial.setText("Update");
        btnChangeMaterial.setEnabled(false);
        btnChangeMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeMaterialActionPerformed(evt);
            }
        });

        btnDelete_Material.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDelete_Material.setText("Delete");
        btnDelete_Material.setEnabled(false);
        btnDelete_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_MaterialActionPerformed(evt);
            }
        });

        btnSave_Material.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSave_Material.setText("Save");
        btnSave_Material.setEnabled(false);
        btnSave_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave_MaterialActionPerformed(evt);
            }
        });

        btnSearch_Material.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Find.png"))); // NOI18N

        lblStatus1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus1.setText("Status");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnRefresh_Material)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnAdd_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtSearch_Material)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnChangeMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(btnDelete_Material)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave_Material, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(lblStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChangeMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSave_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch_Material)
                    .addComponent(btnSearch_Material))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMaterial_type_id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMaterial_type_id.setForeground(new java.awt.Color(255, 255, 255));
        jMaterial_type_id.setText("Material Type ID:");

        txbMaterial_Type_ID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jMAaterialID)
                            .addComponent(jMaterialName)
                            .addComponent(jMaterialUnit)
                            .addComponent(jMaterial_type_id))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUnit)
                            .addComponent(txtMaterialName, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(txtMaterial_ID)
                            .addComponent(txbMaterial_Type_ID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaterial_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jMAaterialID)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaterialName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMaterialName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jMaterialUnit)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMaterial_type_id)
                    .addComponent(txbMaterial_Type_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Material", jPanel1);

        jPanel2.setBackground(new java.awt.Color(80, 180, 155));
        jPanel2.setPreferredSize(new java.awt.Dimension(457, 457));

        tableSupplier.setBackground(new java.awt.Color(80, 180, 155));
        tableSupplier.setForeground(new java.awt.Color(255, 255, 255));
        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableSupplier.setGridColor(new java.awt.Color(204, 255, 153));
        tableSupplier.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tableSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSupplierMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSupplier);

        jPanel8.setBackground(new java.awt.Color(80, 180, 155));

        btnRefreshSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnRefreshSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefreshSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshSupplierMouseClicked(evt);
            }
        });

        btnAddSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnAddSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAddSupplier.setText("Create");
        btnAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplierActionPerformed(evt);
            }
        });

        btnChangeSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnChangeSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangeSupplier.setText("Update");
        btnChangeSupplier.setEnabled(false);
        btnChangeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSupplierActionPerformed(evt);
            }
        });

        btnDeleteSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnDeleteSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDeleteSupplier.setText("Delete");
        btnDeleteSupplier.setEnabled(false);
        btnDeleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSupplierActionPerformed(evt);
            }
        });

        btnSaveSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSaveSupplier.setText("Save");
        btnSaveSupplier.setEnabled(false);
        btnSaveSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSupplierActionPerformed(evt);
            }
        });

        btnSearchSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnSearchSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Find.png"))); // NOI18N

        lblStatus2.setBackground(new java.awt.Color(80, 180, 155));
        lblStatus2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus2.setText("Status");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefreshSupplier)
                .addGap(18, 18, 18)
                .addComponent(btnAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchSupplier))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnChangeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(448, 448, 448))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefreshSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddSupplier)
                        .addComponent(btnChangeSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchSupplier)
                    .addComponent(btnSearchSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus2)
                .addContainerGap())
        );

        jSupplierID.setBackground(new java.awt.Color(255, 255, 255));
        jSupplierID.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jSupplierID.setForeground(new java.awt.Color(255, 255, 255));
        jSupplierID.setText("ID:");

        jSupplierName.setBackground(new java.awt.Color(255, 255, 255));
        jSupplierName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jSupplierName.setForeground(new java.awt.Color(255, 255, 255));
        jSupplierName.setText("Supplier Name:");

        jAddress.setBackground(new java.awt.Color(255, 255, 255));
        jAddress.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jAddress.setForeground(new java.awt.Color(255, 255, 255));
        jAddress.setText("Address:");

        jPhone.setBackground(new java.awt.Color(255, 255, 255));
        jPhone.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPhone.setForeground(new java.awt.Color(255, 255, 255));
        jPhone.setText("Phone:");

        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneKeyReleased(evt);
            }
        });

        jEmail.setBackground(new java.awt.Color(255, 255, 255));
        jEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jEmail.setForeground(new java.awt.Color(255, 255, 255));
        jEmail.setText("Email:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSupplierName)
                                .addComponent(jSupplierID)
                                .addComponent(jAddress))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSupplierID, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addComponent(txtSupplierName)
                                .addComponent(txtAddress))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jPhone)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jEmail)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPhone)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSupplierID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSupplierName)
                    .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Supplier", jPanel2);

        jLabel14.setBackground(new java.awt.Color(80, 180, 155));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DLT-MILK TEA");

        btnBackHome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Home.png"))); // NOI18N
        btnBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackHomeMouseClicked(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/english.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBackHome, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBackHome, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMaterial_TypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMaterial_TypeMouseClicked
        int Click = tableMaterial_Type.getSelectedRow();
        TableModel model = tableMaterial_Type.getModel();

        txtMaterial_type_id.setText(model.getValueAt(Click, 0).toString());
        txtMaterial_type_name.setText(model.getValueAt(Click, 1).toString());

        btnChangeMaterial_type.setEnabled(true);
        btnDeleteMaterial_type.setEnabled(true);
    }//GEN-LAST:event_tableMaterial_TypeMouseClicked

    private void btnBackHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackHomeMouseClicked
        backHome();
    }//GEN-LAST:event_btnBackHomeMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int lick = JOptionPane.showConfirmDialog(null, "Do you want to exit the application?", "Notification", 2);
        if (lick == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            if (lick == JOptionPane.CANCEL_OPTION) {
                this.setVisible(true);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnRefresh_Material_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefresh_Material_typeMouseClicked
        refresh();
    }//GEN-LAST:event_btnRefresh_Material_typeMouseClicked

    private void btnAddMaterial_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterial_typeActionPerformed
        refresh();
        Add = true;
        btnAddMaterial_type.setEnabled(false);
        btnSaveMaterial_type.setEnabled(true);
        enabledMaterial_type();
    }//GEN-LAST:event_btnAddMaterial_typeActionPerformed

    private void btnChangeMaterial_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeMaterial_typeActionPerformed
        Add = false;
        Change = true;
        btnAddMaterial_type.setEnabled(false);
        btnChangeMaterial_type.setEnabled(false);
        btnDeleteMaterial_type.setEnabled(false);
        btnSaveMaterial_type.setEnabled(true);
        loadMaterialTypeID();
        enabledMaterial_type();

    }//GEN-LAST:event_btnChangeMaterial_typeActionPerformed

    private void btnDeleteMaterial_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMaterial_typeActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Do you want to delete it or not??", "Notification", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM MaterialType WHERE Material_TypeID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txtMaterial_type_id.getText());
                pst.executeUpdate();
                lblStatus.setText("Deleted successfully!");
                disabledMaterial_type();
                refresh();
                loadMaterial_type(sql1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteMaterial_typeActionPerformed

    private void btnSaveMaterial_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMaterial_typeActionPerformed
        if (Add == true) {
            if (checkMaterial_type()) {
                insertMaterial_type();
            } else {
                lblStatus.setText("The Material Type ID you entered already exists!");
            }
        } else {
            if (Change == true) {
                updateMaterial_type();
            }
        }
    }//GEN-LAST:event_btnSaveMaterial_typeActionPerformed

    private void txtPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyReleased
        txtPhone.setText(cutChar(txtPhone.getText()));
    }//GEN-LAST:event_txtPhoneKeyReleased

    private void btnSaveSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSupplierActionPerformed
        if (Add == true) {
            if (checkSupplier()) {
                addSupplier();
            } else {
                lblStatus2.setText("The Supplier ID you entered already exists!");
            }
        } else {
            if (Change == true) {
                updateSupplier();
            }
        }
    }//GEN-LAST:event_btnSaveSupplierActionPerformed

    private void btnDeleteSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSupplierActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Do you want to delete the supplier??", "Notification", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM SupplierMaterial WHERE Material_SupplierID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txtSupplierID.getText());
                pst.executeUpdate();
                lblStatus2.setText("Delete supplier successfully!");
                disabledMaterialSupplier();
                refresh();
                loadSupplier(sql3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteSupplierActionPerformed

    private void btnChangeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSupplierActionPerformed
        Add = false;
        Change = true;
        btnAddSupplier.setEnabled(false);
        btnChangeSupplier.setEnabled(false);
        btnDeleteSupplier.setEnabled(false);
        btnSaveSupplier.setEnabled(true);
        enabledMaterialSupplier();
    }//GEN-LAST:event_btnChangeSupplierActionPerformed

    private void btnAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplierActionPerformed
        refresh();
        Add = true;
        btnAddSupplier.setEnabled(false);
        btnSaveSupplier.setEnabled(true);
        enabledMaterialSupplier();
    }//GEN-LAST:event_btnAddSupplierActionPerformed

    private void btnRefreshSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshSupplierMouseClicked
        refresh();
        loadSupplier(sql3);
    }//GEN-LAST:event_btnRefreshSupplierMouseClicked

    private void tableSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupplierMouseClicked
        int Click = tableSupplier.getSelectedRow();
        TableModel model = tableSupplier.getModel();

        txtSupplierID.setText(model.getValueAt(Click, 0).toString());
        txtSupplierName.setText(model.getValueAt(Click, 1).toString());
        txtAddress.setText(model.getValueAt(Click, 2).toString());
        txtPhone.setText(model.getValueAt(Click, 3).toString());
        txtEmail.setText(model.getValueAt(Click, 4).toString());

        btnChangeSupplier.setEnabled(true);
        btnDeleteSupplier.setEnabled(true);
    }//GEN-LAST:event_tableSupplierMouseClicked

    private void btnSave_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave_MaterialActionPerformed
        if (Add == true) {
            if (checkMaterial()) {
                insertMaterial();
            } else {
                lblStatus1.setText("The Material ID you entered already exists!");
            }
        } else {
            if (Change == true) {
                updateMaterial();
            }
        }
    }//GEN-LAST:event_btnSave_MaterialActionPerformed

    private void btnDelete_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_MaterialActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Do you want to delete the material or not?", "Notification", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM Material WHERE MaterialID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txtMaterial_ID.getText());
                pst.executeUpdate();
                lblStatus1.setText("Deleted successfully!");
                disabledMaterial();
                refresh();
                loadMaterial(sql2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDelete_MaterialActionPerformed

    private void btnChangeMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeMaterialActionPerformed
        Add = false;
        Change = true;
        loadMaterialTypeID();
        btnAdd_Material.setEnabled(false);
        btnChangeMaterial.setEnabled(false);
        btnDelete_Material.setEnabled(false);
        btnSave_Material.setEnabled(true);
        enabledMaterial();
    }//GEN-LAST:event_btnChangeMaterialActionPerformed

    private void btnAdd_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_MaterialActionPerformed
        refresh();
        Add = true;
        loadMaterialTypeID();
        btnAdd_Material.setEnabled(false);
        btnSave_Material.setEnabled(true);
        enabledMaterial();
    }//GEN-LAST:event_btnAdd_MaterialActionPerformed

    private void btnRefresh_MaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh_MaterialActionPerformed
        // TODO add your handling codre here:
        refresh();
    }//GEN-LAST:event_btnRefresh_MaterialActionPerformed

    private void btnRefresh_MaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefresh_MaterialMouseClicked
        refresh();
    }//GEN-LAST:event_btnRefresh_MaterialMouseClicked

    private void txtUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitKeyReleased
        /* DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtUnit.setText(cutChar(txtUnit.getText()));
        if (txtUnit.getText().equals("")) {
            return;
        } else {
            txtUnit.setText(formatter.format(convertedToNumbers(txtUnit.getText())));
        }*/
    }//GEN-LAST:event_txtUnitKeyReleased

    private void tableMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMaterialMouseClicked
        int Click = tableMaterial.getSelectedRow();
        TableModel model = tableMaterial.getModel();

        txtMaterial_ID.setText(model.getValueAt(Click, 0).toString());
        txtMaterialName.setText(model.getValueAt(Click, 1).toString());
        txtUnit.setText(model.getValueAt(Click, 2).toString());
        txbMaterial_Type_ID.addItem(model.getValueAt(Click, 3).toString());
        btnChangeMaterial.setEnabled(true);
        btnDelete_Material.setEnabled(true);
    }//GEN-LAST:event_tableMaterialMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InformationManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformationManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformationManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformationManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail = new Detail();
                new InformationManagement(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaterial_type;
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnAdd_Material;
    private javax.swing.JButton btnBackHome;
    private javax.swing.JButton btnChangeMaterial;
    private javax.swing.JButton btnChangeMaterial_type;
    private javax.swing.JButton btnChangeSupplier;
    private javax.swing.JButton btnDeleteMaterial_type;
    private javax.swing.JButton btnDeleteSupplier;
    private javax.swing.JButton btnDelete_Material;
    private javax.swing.JButton btnRefreshSupplier;
    private javax.swing.JButton btnRefresh_Material;
    private javax.swing.JButton btnRefresh_Material_type;
    private javax.swing.JButton btnSaveMaterial_type;
    private javax.swing.JButton btnSaveSupplier;
    private javax.swing.JButton btnSave_Material;
    private javax.swing.JButton btnSearchMaterial_type;
    private javax.swing.JButton btnSearchSupplier;
    private javax.swing.JButton btnSearch_Material;
    private javax.swing.JLabel jAddress;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jEmail;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jMAaterialID;
    private javax.swing.JLabel jMaterialName;
    private javax.swing.JLabel jMaterialUnit;
    private javax.swing.JLabel jMaterial_type_ID;
    private javax.swing.JLabel jMaterial_type_id;
    private javax.swing.JLabel jMaterial_type_name;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jPhone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jSupplierID;
    private javax.swing.JLabel jSupplierName;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatus1;
    private javax.swing.JLabel lblStatus2;
    private javax.swing.JTable tableMaterial;
    private javax.swing.JTable tableMaterial_Type;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JComboBox<String> txbMaterial_Type_ID;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaterialName;
    private javax.swing.JTextField txtMaterial_ID;
    private javax.swing.JTextField txtMaterial_type_id;
    private javax.swing.JTextField txtMaterial_type_name;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JTextField txtSearch_Material;
    private javax.swing.JTextField txtSupplierID;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
