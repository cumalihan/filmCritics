/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Kullanici;
import entity.Yetki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class KullaniciDAO {

    private DBConnection connector;
    private Connection connection;

    private YetkiDAO yetkiDao;

    public List<Kullanici> getKullanici(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Kullanici> kullaniciList = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from kullanici";

            if (arananTerim != null) {
                query += " where kullanici_adi like ? ";
            }

            query += " order by kullanici_id asc limit ? offset ?";
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
                Kullanici tmp = new Kullanici(rs.getLong("kullanici_id"), rs.getString("kullanici_adi"),
                        rs.getString("sifre"), rs.getString("ad"), rs.getString("soyad"), rs.getString("e_posta"));
                kullaniciList.add(tmp);

                tmp.setYetki(this.getYetkiDao().bul(rs.getLong("yetki_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kullaniciList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(kullanici_id) as kullanici_sayisi from kullanici ";
            if (arananTerim != null) {
                query += "where kullanici_adi like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("kullanici_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public Kullanici giris(String kullanici_adi, String sifre) {
        Kullanici kul = null;
        try {

            String q = "select * from kullanici where kullanici_adi=? and sifre=?";
            PreparedStatement pst = this.getConnection().prepareStatement(q);
            pst.setString(1, kullanici_adi);
            pst.setString(2, sifre);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                kul = new Kullanici();
                kul.setKullanici_id(rs.getLong("kullanici_id"));
                kul.setKullanici_adi(rs.getString("kullanici_adi"));
                kul.setSifre(rs.getString("sifre"));
                kul.setAd(rs.getString("ad"));
                kul.setSoyad(rs.getString("soyad"));
                kul.setE_posta(rs.getString("e_posta"));
                kul.setYetki(this.yetkiBul(rs.getLong("yetki_id")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kul;

    }

    public Yetki yetkiBul(Long id) {
        Yetki yet = null;
        try {
            String q = "select * from yetki where yetki_id=?";
            PreparedStatement pst = this.getConnection().prepareStatement(q);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            yet = new Yetki();
            yet.setYetki_id(rs.getLong("yetki_id"));
            yet.setGrup(rs.getString("grup"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return yet;
    }

    public void ekle(Kullanici kullanici) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("insert into kullanici (kullanici_adi,sifre,ad,soyad,e_posta) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kullanici.getKullanici_adi());
            pst.setString(2, kullanici.getSifre());
            pst.setString(3, kullanici.getAd());
            pst.setString(4, kullanici.getSoyad());
            pst.setString(5, kullanici.getE_posta());
            

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Kullanici kullanici) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("delete from kullanici where kullanici_id=?");
            pst.setLong(1, kullanici.getKullanici_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Kullanici kullanici) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("update kullanici set kullanici_adi=?,sifre=?,ad=?,soyad=?,e_posta=?,yetki_id=? where kullanici_id=?");
            pst.setString(1, kullanici.getKullanici_adi());
            pst.setString(2, kullanici.getSifre());
            pst.setString(3, kullanici.getAd());
            pst.setString(4, kullanici.getSoyad());
            pst.setString(5, kullanici.getE_posta());
            pst.setLong(6, kullanici.getYetki().getYetki_id());
            pst.setLong(7, kullanici.getKullanici_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public YetkiDAO getYetkiDao() {
        if (this.yetkiDao == null) {
            this.yetkiDao = new YetkiDAO();
        }
        return yetkiDao;
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
