/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class DilDAO {

    private DBConnection connector;
    private Connection connection;

    public Dil bul(Long id) {
        Dil l = null;
        try {

            PreparedStatement st = this.getConnection().prepareStatement("select * from dil where dil_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            l = new Dil();
            l.setDil_id(rs.getLong("dil_id"));
            l.setDil(rs.getString("dil"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public List<Dil> getDil() {
        List<Dil> dilList = new ArrayList();
        
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from dil ");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Dil tmp = new Dil();
                tmp.setDil_id(rs.getLong("dil_id"));
                tmp.setDil(rs.getString("dil"));
                dilList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dilList;
    }
    
    public List<Dil> getDil(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Dil> dilList = new ArrayList();
        
        int baslangic=(sayfa-1)*sayfaBoyutu;
        try {
            String query = "select * from dil";
            
            if(arananTerim!= null){
                query += " where dil like ? ";
            }
            
            query+= " order by dil_id asc limit ? offset ?";
            PreparedStatement pst = this.getConnection().prepareStatement(query);            
            if(arananTerim!=null){
                pst.setString(1, "%"+arananTerim+"%");
                pst.setInt(2, sayfaBoyutu);
                pst.setInt(3, baslangic);
            }
            else{
                pst.setInt(1, sayfaBoyutu);
                pst.setInt(2, baslangic);
            }
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Dil tmp = new Dil();
                tmp.setDil_id(rs.getLong("dil_id"));
                tmp.setDil(rs.getString("dil"));
                dilList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dilList;
    }

    public void ekle(Dil dil) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into dil (dil) values(?)");
            pst.setString(1, dil.getDil());           
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int sayi(String arananTerim) {
        int sayi=0;
        try {
            String query = "select count(dil_id) as dil_sayisi from dil ";
            if(arananTerim!=null){
                query += "where dil like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);
            
            if(arananTerim !=null){
                st.setString(1, "%"+arananTerim+"%");
            }
            
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi=rs.getInt("dil_sayisi");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void sil(Dil dil) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("delete from dil where dil_id=?");
            pst.setLong(1, dil.getDil_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Dil dil) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("update dil set dil=? where dil_id=?");
            pst.setString(1, dil.getDil());
            pst.setLong(2, dil.getDil_id());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

}
