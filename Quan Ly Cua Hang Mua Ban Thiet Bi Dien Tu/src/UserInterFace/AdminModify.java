/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterFace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh
 */
public class AdminModify {

//    public static List<Administrators> find(String TenKH) {
//        List<Administrators> aclist = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=qltourdulich", "sa", "123456");
//
//            String sql = "Select [MaKH],[TenKH],[SdtKH],[Email],[DiachiKH], CONVERT(varchar, [NgaySinh], 103) as NgaySinh from[dbo].[TTKhachHang] where TenKH like ?";
//            statement = connection.prepareCall(sql);
//            statement.setString(1, "%" + TenKH + "%");
//
//            ResultSet resulSet = statement.executeQuery();
//
//            while (resulSet.next()) {
//                Administrators acc = new Administrators(resulSet.getString("MaKH"),
//                        resulSet.getString("TenKH"),
//                        resulSet.getString("SdtKH"),
//                        resulSet.getString("Email"),
//                        resulSet.getString("DiachiKH"),
//                        resulSet.getString("NgaySinh"), 1);
//
//                aclist.add(acc);
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                }
//            }
//        }
//
//        //end
//        return aclist;
//    }
//
//    public static List<Administrators> findAll() {
//        List<Administrators> aclist = new ArrayList<>();
//
//        Connection connection = null;
//        Statement statement = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=qltourdulich", "sa", "123456");
//
//            String sql = "Select [MaKH],[TenKH],[SdtKH],[Email],[DiachiKH], CONVERT(varchar, [NgaySinh], 103) as NgaySinh from[dbo].[TTKhachHang]";
//            statement = connection.createStatement();
//
//            ResultSet resulSet = statement.executeQuery(sql);
//
//            while (resulSet.next()) {
//
//                Administrators acc = new Administrators(resulSet.getString("MaKH"),
//                        resulSet.getString("TenKH"),
//                        resulSet.getString("SdtKH"),
//                        resulSet.getString("Email"),
//                        resulSet.getString("DiachiKH"),
//                        resulSet.getString("NgaySinh"), 1);
//
//                aclist.add(acc);
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                }
//            }
//        }
//
//        //end
//        return aclist;
//    }
    public static void insert(Administrators acc) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MilkTeaShopManagement", "sa", "123456");

            String pattern = "dd/MM/yyyy";

            DateFormat df = new SimpleDateFormat(pattern);

            Date today = Calendar.getInstance().getTime();

            String todayAsString = df.format(today);

            String sql = "insert into Administrators(Username, Password, Phone, Name, Email, RegistrationDate) values(?, ?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, acc.getUsername());
            statement.setString(2, acc.getPassword());
            statement.setString(3, acc.getPhone());
            statement.setString(4, acc.getName());
            statement.setString(5, acc.getEmail());
            statement.setString(6, todayAsString);
            statement.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("");
                }
            }
        }

        //end
    }

    public static void delete(String Username) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=qltourdulich", "sa", "123456");

            String sql = "delete from Administrators where Username = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1, Username);

            statement.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        //end
    }
}
