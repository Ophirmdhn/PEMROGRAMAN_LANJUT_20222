package com.ophi.database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{

    private static final String  URL = "jdbc:mysql://localhost:3306/aplikasi_mahasiswa";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JPanel panelMain;
    private JTable jTableMahasiswa;
    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtIpk;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txtFilter;
    private JButton btnFilter;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        refreshTable(getMahasiswa());

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String nim = txtNim.getText();
                double ipk = Double.parseDouble(txtIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                clearForm();
                insertMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());
            }
        });

        jTableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = jTableMahasiswa.getSelectedRow();
                System.out.println(row);

                String nim = jTableMahasiswa.getValueAt(row,0).toString();
                String nama = jTableMahasiswa.getValueAt(row,1).toString();
                String ipk = jTableMahasiswa.getValueAt(row,2).toString();

                txtNama.setText(nama);
                txtNim.setText(nim);
                txtIpk.setText(ipk);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String nim = txtNim.getText();
                double ipk = Double.parseDouble(txtIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                clearForm();
                updateMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNim.getText();

                clearForm();
                deleteMahasiswa(nim);
                refreshTable(getMahasiswa());
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtFilter.getText();
                refreshTable(filterMahasiswa(nama));
            }
        });
    }

    private static List<Mahasiswa> filterMahasiswa(String filterNama){
       List<Mahasiswa> arrayListMahasiswa =  new ArrayList<>();

       ResultSet resultSet = executeQuery("select * from mahasiswa where nama like '%" + filterNama + "%'");

       try {
           while (resultSet.next()){
               String nim = resultSet.getString("nim");
               String nama = resultSet.getString("nama");
               double ipk = Double.parseDouble(resultSet.getString("ipk"));

               Mahasiswa mahasiswa = new Mahasiswa();
               mahasiswa.setNim(nim);
               mahasiswa.setNama(nama);
               mahasiswa.setIpk(ipk);

               arrayListMahasiswa.add(mahasiswa);
           }
       }

       catch (Exception e){
          return null;
       }
       return arrayListMahasiswa;
    }

    public void refreshTable(List<Mahasiswa> arrayListMahasiswa){
        Object [][] data =  new Object [arrayListMahasiswa.size()][3];

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            data[i] = new Object[]{
                    arrayListMahasiswa.get(i).getNim(),
                    arrayListMahasiswa.get(i).getNama(),
                    arrayListMahasiswa.get(i).getIpk()
            };
        }

        defaultTableModel = new DefaultTableModel(
                data,
                new String[] {"NIM", "Nama", "IPK"}
        );

        jTableMahasiswa.setModel(defaultTableModel);
    }



    //Connector
    private static ResultSet executeQuery(String query){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);
        }

        catch (Exception e){
            return null;
        }
    }

    public static List<Mahasiswa> getMahasiswa(){
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from mahasiswa");

        try {
            while (resultSet.next()){
                 String nama = resultSet.getString("nama");
                 String nim = resultSet.getString("nim");
                 double ipk = Double.parseDouble(resultSet.getString("ipk"));

                System.out.println(nama);
                System.out.println(nim);
                System.out.println(ipk);
                System.out.println();

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(ipk);
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                arrayListMahasiswa.add(mahasiswa);
            }
        }

        catch (Exception e){

        }
        return arrayListMahasiswa;
    }

    public static void executeSql(String sql){
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
        }

        catch (Exception e){

        }
    }

    private static void insertMahasiswa(Mahasiswa mahasiswa){
        String sql = "insert into mahasiswa values (" +
                "'" + mahasiswa.getNim() + "', " +
                "'" + mahasiswa.getNama() + "', " +
                "'" + mahasiswa.getIpk() + "')";
        executeSql(sql);
     }

     private static void updateMahasiswa(Mahasiswa mahasiswa){
         String sql = "update mahasiswa set " +
                 "nama = '"+ mahasiswa.getNama() +"', " +
                 "ipk = '"+ mahasiswa.getIpk() +"' " +
                 "where nim = '"+ mahasiswa.getNim() +"'";
         executeSql(sql);
     }

     private static void deleteMahasiswa(String nim){
        String sql = "delete from mahasiswa " +
                "where nim = '"+ nim +"'";
        executeSql(sql);
     }

     private void clearForm(){
        txtNama.setText("");
        txtNim.setText("");
        txtIpk.setText("");
     }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }

}
