/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OyuncuDAO;
import entity.Oyuncu;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class OyuncuController implements Serializable {

    private List<Oyuncu> oyuncuList;
    private OyuncuDAO oyuncuDao;

    private Oyuncu oyuncu;

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
        this.getOyuncuDao().sil(this.oyuncu);
        temizle();
        return "/panel/oyuncu/oyuncu";
    }

    public String onaylaSil(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
        return "/panel/onayla/oyuncuOnayla";
    }

    public void olustur() {
        this.oyuncuDao.ekle(this.oyuncu);
        temizle();
    }

    public void formuGuncelle(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

    public void guncelle() {
        this.getOyuncuDao().guncelle(this.oyuncu);
        temizle();

    }

    public void temizle() {
        this.oyuncu = new Oyuncu();
    }

    public List<Oyuncu> getOyuncuList() {
        this.oyuncuList = this.getOyuncuDao().getOyuncu(sayfa, sayfaBoyutu,this.getTerimAra());
        return oyuncuList;
    }

    public void setOyuncuList(List<Oyuncu> oyuncuList) {
        this.oyuncuList = oyuncuList;
    }

    public OyuncuDAO getOyuncuDao() {
        if (this.oyuncuDao == null) {
            this.oyuncuDao = new OyuncuDAO();
        }
        return oyuncuDao;
    }

    public void setOyuncuDao(OyuncuDAO oyuncuDao) {
        this.oyuncuDao = oyuncuDao;
    }

    public Oyuncu getOyuncu() {
        if (this.oyuncu == null) {
            this.oyuncu = new Oyuncu();
        }
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
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
        this.sayfaSayısı = (int) Math.ceil(this.getOyuncuDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
