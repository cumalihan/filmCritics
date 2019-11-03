/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.YazarDAO;
import entity.Yazar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class YazarController implements Serializable {

    private List<Yazar> yazarList;
    private YazarDAO yazarDao;

    private Yazar yazar;

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
        this.getYazarDao().sil(this.yazar);
        temizle();
        return "/panel/yazar/yazar";
    }

    public String onaylaSil(Yazar yazar) {
        this.yazar = yazar;
        return "/panel/onayla/yazarOnayla";
    }

    public void olustur() {
        this.yazarDao.ekle(this.yazar);
        temizle();
    }

    public void formuGuncelle(Yazar yazar) {
        this.yazar = yazar;
    }

    public void guncelle() {
        this.getYazarDao().guncelle(this.yazar);
        temizle();
    }

    public void temizle() {
        this.yazar = new Yazar();
    }

    public List<Yazar> getYazarList() {
        this.yazarList = this.getYazarDao().getYazar(sayfa, sayfaBoyutu, this.getTerimAra());
        return yazarList;
    }

    public void setYazarList(List<Yazar> yazarList) {
        this.yazarList = yazarList;
    }

    public YazarDAO getYazarDao() {
        if (this.yazarDao == null) {
            this.yazarDao = new YazarDAO();
        }
        return yazarDao;
    }

    public void setYazarDao(YazarDAO yazarDao) {
        this.yazarDao = yazarDao;
    }

    public Yazar getYazar() {
        if (this.yazar == null) {
            this.yazar = new Yazar();
        }
        return yazar;
    }

    public void setYazar(Yazar yazar) {
        this.yazar = yazar;
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
        this.sayfaSayısı = (int) Math.ceil(this.getYazarDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
