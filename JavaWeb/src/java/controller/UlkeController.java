/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UlkeDAO;
import entity.Ulke;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UlkeController implements Serializable {

    private List<Ulke> ulkeList;
    private UlkeDAO ulkeDao;

    private Ulke ulke;

    private int sayfa = 1;
    private int sayfaBoyutu = 10;
    private int sayfaSayısı;

    private String terimAra;

    public void ara() {
        this.setSayfa(1);
    }

    public void aramaTemizle() {
        this.setTerimAra(null);
    }

    public void ileri() {
        if (this.sayfa == this.getSayfaSayısı()) {
            this.sayfa = 1;
        } else {
            this.sayfa++;
        }

    }

    public void geri() {
        if (this.sayfa == 1) {
            this.sayfa = this.getSayfaSayısı();
        } else {
            this.sayfa--;
        }

    }

    public String Sil() {
        this.getUlkeDao().sil(this.ulke);
        temizle();
        return "/panel/ulke/ulke";
    }

    public String onaylaSil(Ulke ulke) {
        this.ulke = ulke;
        return "/panel/onayla/ulkeOnayla";
    }

    public void olustur() {
        this.ulkeDao.ekle(this.ulke);
        temizle();
    }

    public void formuGuncelle(Ulke ulke) {
        this.ulke = ulke;
    }

    public void guncelle() {
        this.getUlkeDao().guncelle(this.ulke);
        temizle();
    }

    public void temizle() {
        this.ulke = new Ulke();
    }

    public List<Ulke> getUlkeList() {
        this.ulkeList = this.getUlkeDao().getUlke(sayfa, sayfaBoyutu,this.getTerimAra());
        return ulkeList;
    }

    public void setUlkeList(List<Ulke> ulkeList) {
        this.ulkeList = ulkeList;
    }

    public UlkeDAO getUlkeDao() {
        if (this.ulkeDao == null) {
            this.ulkeDao = new UlkeDAO();
        }
        return ulkeDao;
    }

    public void setUlkeDao(UlkeDAO ulkeDao) {
        this.ulkeDao = ulkeDao;
    }

    public Ulke getUlke() {
        if (this.ulke == null) {
            this.ulke = new Ulke();
        }
        return ulke;
    }

    public void setUlke(Ulke ulke) {
        this.ulke = ulke;
    }

    public int getSayfa() {
        return sayfa;
    }

    public void setSayfa(int sayfa) {
        this.sayfa = sayfa;
    }

    public int getSayfaBoyutu() {
        return sayfaBoyutu;
    }

    public void setSayfaBoyutu(int sayfaBoyutu) {
        this.sayfaBoyutu = sayfaBoyutu;
    }

    public int getSayfaSayısı() {
        this.sayfaSayısı = (int) Math.ceil(this.getUlkeDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
        return sayfaSayısı;
    }

    public void setSayfaSayısı(int sayfaSayısı) {
        this.sayfaSayısı = sayfaSayısı;
    }
    
    public String getTerimAra() {
        return terimAra;
    }

    public void setTerimAra(String terimAra) {
        this.terimAra = terimAra;
    }

}
