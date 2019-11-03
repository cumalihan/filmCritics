/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Film;
import entity.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class FilmDAO {

    private DBConnection connector;
    private Connection connection;

    private DilDAO dilDao;
    private DosyaDAO dosyaDao;
    private KategoriDAO kategoriDao;

    public List<Film> findAll(int sayfa, int sayfaBoyutu,String arananTerim) {
        List<Film> filmList = new ArrayList<>();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from film";

            if (arananTerim != null) {
                query += " where baslik like ? ";
            }

            query += " order by film_id asc limit ? offset ?";

            PreparedStatement pst = this.getConnection().prepareStatement(query);

            if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
                pst.setInt(2, sayfaBoyutu);
                pst.setInt(3, baslangic);
            } else {
                pst.setInt(1, sayfaBoyutu);
                pst.setInt(2, baslangic);
            }
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Film tmp = new Film(rs.getInt("film_id"), rs.getString("baslik"),
                        rs.getFloat("puan"), rs.getString("aciklama"),
                        rs.getInt("vizyon_tarihi"));
                filmList.add(tmp);

                tmp.setDil(this.getDilDao().bul(rs.getLong("dil_id")));
                tmp.setDosya(this.getDosyaDao().bul(rs.getLong("dosya_id")));
                tmp.setFilmKategorileri(this.getKategoriDao().getFilmKategorileri(tmp.getFilm_id()));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return filmList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(film_id) as film_sayisi from film ";
            if (arananTerim != null) {
                query += "where baslik like ?";
            }
            PreparedStatement pst = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                pst.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = pst.executeQuery();
            rs.next();
            sayi = rs.getInt("film_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void duzelt(Film film) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update  film set baslik=?,puan=?,aciklama=?,vizyon_tarihi=?,dil_id=?,dosya_id=? where film_id=?");
            pst.setString(1, film.getBaslik());
            pst.setFloat(2, film.getPuan());
            pst.setString(3, film.getAciklama());
            pst.setInt(4, film.getVizyon_tarihi());
            pst.setLong(5, film.getDil().getDil_id());
            pst.setLong(6, film.getDosya().getDosya_id());
            pst.setInt(7, film.getFilm_id());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from film_kategori where film_id=?");
            pst.setInt(1, film.getFilm_id());
            pst.executeUpdate();

            for (Kategori k : film.getFilmKategorileri()) {
                pst = this.getConnection().prepareStatement("insert into film_kategori(film_id,kategori_id) values(?,?)");
                pst.setInt(1, film.getFilm_id());
                pst.setLong(2, k.getKategori_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ekle(Film film) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into film (baslik,puan,aciklama,vizyon_tarihi,dil_id,dosya_id) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, film.getBaslik());
            pst.setFloat(2, film.getPuan());
            pst.setString(3, film.getAciklama());
            pst.setInt(4, film.getVizyon_tarihi());
            pst.setLong(5, film.getDil().getDil_id());
            pst.setLong(6, film.getDosya().getDosya_id());

            pst.executeUpdate();

            ResultSet gk = pst.getGeneratedKeys();
            Long film_id = null;
            if (gk.next()) {
                film_id = gk.getLong(1);
            }

            for (Kategori k : film.getFilmKategorileri()) {
                pst = this.getConnection().prepareStatement("insert into film_kategori(film_id,kategori_id) values(?,?)");
                pst.setLong(1, film_id);
                pst.setLong(2, k.getKategori_id());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void yoket(Film film) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from film where film_id=?");
            pst.setInt(1, film.getFilm_id());
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

    public DilDAO getDilDao() {
        if (this.dilDao == null) {
            this.dilDao = new DilDAO();
        }
        return dilDao;
    }

    public KategoriDAO getKategoriDao() {
        if (kategoriDao == null) {
            this.kategoriDao = new KategoriDAO();
        }
        return kategoriDao;
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

}
