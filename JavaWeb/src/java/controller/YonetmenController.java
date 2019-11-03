/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.YonetmenDAO;
import entity.Yonetmen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class YonetmenController implements Serializable {

    private List<Yonetmen> yonetmenList;
    private YonetmenDAO yonetmenDao;

    private Yonetmen yonetmen;

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
        this.getYonetmenDao().sil(this.yonetmen);
        temizle();
        return "/panel/yonetmen/yonetmen";
    }

    public String onaylaSil(Yonetmen yonetmen) {
        this.yonetmen = yonetmen;
        return "/panel/onayla/yonetmenOnayla";

    }

    public void olustur() {
        this.yonetmenDao.ekle(this.yonetmen);
        temizle();
    }

    public void formuGuncelle(Yonetmen yonetmen) {
        this.yonetmen = yonetmen;
    }

    public void guncelle() {
        this.getYonetmenDao().guncelle(this.yonetmen);
        temizle();
    }

    public void temizle() {
        this.yonetmen = new Yonetmen();
    }

    public List<Yonetmen> getYonetmenList() {
        this.yonetmenList = this.getYonetmenDao().getYonetmen(sayfa, sayfaBoyutu,this.getTerimAra());
        return yonetmenList;
    }

    public void setYonetmenList(List<Yonetmen> yonetmenList) {
        this.yonetmenList = yonetmenList;
    }

    public YonetmenDAO getYonetmenDao() {
        if (this.yonetmenDao == null) {
            this.yonetmenDao = new YonetmenDAO();
        }
        return yonetmenDao;
    }

    public void setYonetmenDao(YonetmenDAO yonetmenDao) {
        this.yonetmenDao = yonetmenDao;
    }

    public Yonetmen getYonetmen() {
        if (this.yonetmen == null) {
            this.yonetmen = new Yonetmen();
        }
        return yonetmen;
    }

    public void setYonetmen(Yonetmen yonetmen) {
        this.yonetmen = yonetmen;
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
        this.sayfaSayısı = (int) Math.ceil(this.getYonetmenDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
