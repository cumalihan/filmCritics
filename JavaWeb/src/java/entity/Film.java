/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;


public class Film {

    private int film_id;
    private String baslik;
    private float puan;
    private String aciklama;
    private int vizyon_tarihi;

    private Dil Dil;
    private Dosya Dosya;
    
    private List<Kategori> filmKategorileri;

    public Film() {
    }

    public Film(int film_id, String baslik, float puan, String aciklama, int vizyon_tarihi) {
        this.film_id = film_id;
        this.baslik = baslik;
        this.puan = puan;
        this.aciklama = aciklama;
        this.vizyon_tarihi = vizyon_tarihi;

    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public float getPuan() {
        return puan;
    }

    public void setPuan(float puan) {
        this.puan = puan;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getVizyon_tarihi() {
        return vizyon_tarihi;
    }

    public void setVizyon_tarihi(int vizyon_tarihi) {
        this.vizyon_tarihi = vizyon_tarihi;
    }

    public Dil getDil() {
        return Dil;
    }

    public void setDil(Dil Dil) {
        this.Dil = Dil;
    }

    public List<Kategori> getFilmKategorileri() {
        return filmKategorileri;
    }

    public void setFilmKategorileri(List<Kategori> filmKategorileri) {
        this.filmKategorileri = filmKategorileri;
    }

    public Dosya getDosya() {
        return Dosya;
    }

    public void setDosya(Dosya Dosya) {
        this.Dosya = Dosya;
    }
    
    

}
