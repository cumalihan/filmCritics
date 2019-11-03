/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Yayinci;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class YayinciDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Yayinci> getYayinci(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Yayinci> yayinciList = new ArrayList();
        int baslangic=(sayfa-1)*sayfaBoyutu;
        
        try {
            String query = "select * from yayinci";
            
            if(arananTerim!= null){
                query += " where ad like ? ";
            }
            
            query+= " order by yayinci_id asc limit ? offset ?";
            
            PreparedStatement st = this.getConnection().prepareStatement(query);
            if(arananTerim!=null){
                st.setString(1, "%"+arananTerim+"%");
                st.setInt(2, sayfaBoyutu);
                st.setInt(3, baslangic);
            }
            else{
                st.setInt(1, sayfaBoyutu);
                st.setInt(2, baslangic);
            }
            
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Yayinci tmp = new Yayinci();
                tmp.setYayinci_id(rs.getLong("yayinci_id"));
                tmp.setAd(rs.getString("ad"));
                yayinciList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yayinciList;
    }
    
    public int sayi(String arananTerim) {
        int sayi=0;
        try {
            String query = "select count(yayinci_id) as yayinci_sayisi from yayinci ";
            if(arananTerim!=null){
                query += "where ad like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);
            
            if(arananTerim !=null){
                st.setString(1, "%"+arananTerim+"%");
            }
            
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi=rs.getInt("yayinci_sayisi");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Yayinci yayinci) {

        try {
            PreparedStatement st = getConnection().prepareStatement("insert into yayinci(ad) values(?)");
            st.setString(1, yayinci.getAd());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yayinci yayinci) {

        try {
            PreparedStatement st = getConnection().prepareStatement("delete from yayinci where yayinci_id=?");
            st.setLong(1, yayinci.getYayinci_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yayinci yayinci) {
        try {

            PreparedStatement st = getConnection().prepareStatement("update yayinci set ad=? where yayinci_id=?");
            st.setString(1, yayinci.getAd());
            st.setLong(2, yayinci.getYayinci_id());
            st.executeUpdate();
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

    public java.sql.Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
}
