/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class KategoriDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Kategori> getKategori(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Kategori> clist = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from kategori";

            if (arananTerim != null) {
                query += " where kategori like ? ";
            }

            query += " order by kategori_id asc limit ? offset ?";

            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
                st.setInt(2, sayfaBoyutu);
                st.setInt(3, baslangic);
            } else {
                st.setInt(1, sayfaBoyutu);
                st.setInt(2, baslangic);
            }

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Kategori tmp = new Kategori(rs.getLong("kategori_id"), rs.getString("kategori"));
                clist.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clist;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(kategori_id) as kategori_sayisi from kategori ";
            if (arananTerim != null) {
                query += "where kategori like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }

            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("kategori_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Kategori kategori) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("insert into kategori (kategori) values(?)");
            pst.setString(1, kategori.getKategori());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Kategori kategori) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("delete from kategori where kategori_id=?");
            pst.setLong(1, kategori.getKategori_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Kategori kategori) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("update kategori set kategori=? where kategori_id=?");
            pst.setString(1, kategori.getKategori());
            pst.setLong(2, kategori.getKategori_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Kategori> getFilmKategorileri(int film_id) {
        List<Kategori> filmKategorileri = new ArrayList<>();

        try {
            PreparedStatement st = this.getConnection().prepareStatement("select * from film_kategori where film_id=?");
            st.setInt(1, film_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                filmKategorileri.add(this.bul((int) rs.getLong("kategori_id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return filmKategorileri;
    }

    public Kategori bul(int id) {
        Kategori k = null;
        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from kategori where kategori_id=" + id);
            rs.next();

            k = new Kategori();
            k.setKategori_id(rs.getLong("kategori_id"));
            k.setKategori(rs.getString("kategori"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
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
