/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Dosya;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class DosyaDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Dosya> findAll(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Dosya> dosyaList = new ArrayList<>();
         int baslangic=(sayfa-1)*sayfaBoyutu;
        
        try {
            String query = "select * from dosya";
            
            if(arananTerim!= null){
                query += " where dosya_ismi like ? ";
            }
            
            query+= " order by dosya_id asc limit ? offset ?";
            
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
            
            while(rs.next()){
                Dosya d = new Dosya();
                d.setDosya_id(rs.getLong("dosya_id"));
                d.setDosya_ismi(rs.getString("dosya_ismi"));
                d.setDosya_konumu(rs.getString("dosya_konumu"));
                d.setDosya_tipi(rs.getString("dosya_tipi"));
                
                dosyaList.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return dosyaList;
    }
    
    public List<Dosya> findAll() {
        List<Dosya> dosyaList = new ArrayList<>();
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("select * from dosya");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Dosya d = new Dosya();
                d.setDosya_id(rs.getLong("dosya_id"));
                d.setDosya_ismi(rs.getString("dosya_ismi"));
                d.setDosya_konumu(rs.getString("dosya_konumu"));
                d.setDosya_tipi(rs.getString("dosya_tipi"));
                
                dosyaList.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return dosyaList;
    }
    
     public int sayi(String arananTerim) {
        int sayi=0;
        try {
            String query = "select count(dosya_id) as dosya_sayisi from dosya ";
            if(arananTerim!=null){
                query += "where dosya_ismi like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);
            
            if(arananTerim !=null){
                st.setString(1, "%"+arananTerim+"%");
            }
            
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi=rs.getInt("dosya_sayisi");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }
    
    public Dosya bul(Long id) {
        Dosya ds = null;
        try {

            PreparedStatement st = this.getConnection().prepareStatement("select * from dosya where dosya_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            ds = new Dosya();
            ds.setDosya_id(rs.getLong("dosya_id"));
            ds.setDosya_ismi(rs.getString("dosya_ismi"));
            ds.setDosya_konumu(rs.getString("dosya_konumu"));
            ds.setDosya_tipi(rs.getString("dosya_tipi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ds;
    }
    
    
    public void ekle(Dosya d){
        String query = "insert into dosya (dosya_ismi, dosya_konumu, dosya_tipi) values(?,?,?)";
        try{
            PreparedStatement pst = this.getConnection().prepareStatement(query);
            pst.setString(1, d.getDosya_ismi());
            pst.setString(2, d.getDosya_konumu());
            pst.setString(3, d.getDosya_tipi());
            pst.executeUpdate();
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
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
