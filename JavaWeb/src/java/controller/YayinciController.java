/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.YayinciDAO;
import entity.Yayinci;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class YayinciController implements Serializable {

    private List<Yayinci> yayinciList;
    private YayinciDAO yayinciDao;

    private Yayinci yayinci;
    
    private int sayfa = 1;
    private int sayfaBoyutu = 10;
    private int sayfaSayısı;
    
    private String terimAra;
    
    public void ara(){
        this.setSayfa(1);    
    }
    
    public void  aramaTemizle(){
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
        this.getYayinciDao().sil(this.yayinci);
        temizle();
        return "/panel/yayinci/yayinci";
    }
    
    public String onaylaSil(Yayinci yayinci){
        this.yayinci=yayinci;
        return "/panel/onayla/yayinciOnayla";
    }

    public void olustur() {
        this.yayinciDao.ekle(this.yayinci);
        temizle();
    }

    public void formuGuncelle(Yayinci yayinci) {
        this.yayinci = yayinci;
    }

    public void guncelle() {
        this.getYayinciDao().guncelle(this.yayinci);
        temizle();
    }

    public void temizle() {
        this.yayinci = new Yayinci();
    }

    public List<Yayinci> getYayinciList() {
        this.yayinciList = this.getYayinciDao().getYayinci(sayfa, sayfaBoyutu,this.getTerimAra());
        return yayinciList;
    }

    public void setYayinciList(List<Yayinci> yayinciList) {
        this.yayinciList = yayinciList;
    }

    public YayinciDAO getYayinciDao() {
        if (this.yayinciDao == null) {
            this.yayinciDao = new YayinciDAO();
        }
        return yayinciDao;
    }

    public void setYayinciDao(YayinciDAO yayinciDao) {
        this.yayinciDao = yayinciDao;
    }

    public Yayinci getYayinci() {
        if (this.yayinci == null) {
            this.yayinci = new Yayinci();
        }
        return yayinci;
    }

    public void setYayinci(Yayinci yayinci) {
        this.yayinci = yayinci;
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
        this.sayfaSayısı = (int) Math.ceil(this.getYayinciDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
