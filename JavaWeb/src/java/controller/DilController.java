/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DilDAO;
import entity.Dil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class DilController implements Serializable {

    private List<Dil> dilList;
    private DilDAO dilDao;

    private Dil dil;
    
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
        this.getDilDao().sil(this.dil);
        temizle();
        return "/panel/dil/dil";
    }

    public String onaylaSil(Dil dil){
        this.dil=dil;
        
        return "/panel/onayla/dilOnayla";
    }
            
            
    public void olustur() {
        this.dilDao.ekle(this.dil);
        temizle();
    }

    public void formuGuncelle(Dil dil) {
        this.dil = dil;
    }

    public void guncelle() {
        this.getDilDao().guncelle(this.dil);
        temizle();
    }

    public void temizle() {
        this.dil = new Dil();
    }

    public List<Dil> getDilList() {
        this.dilList = this.getDilDao().getDil(sayfa, sayfaBoyutu,this.getTerimAra());
        return dilList;
    }

    public void setDilList(List<Dil> dilList) {
        this.dilList = dilList;
    }

    public DilDAO getDilDao() {
        if (this.dilDao == null) {
            this.dilDao = new DilDAO();
        }
        return dilDao;
    }

    public void setDilDao(DilDAO dilDao) {
        this.dilDao = dilDao;
    }

    public Dil getDil() {
        if (this.dil == null) {
            this.dil = new Dil();
        }
        return dil;
    }

    public void setDil(Dil dil) {
        this.dil = dil;
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
        this.sayfaSayısı = (int) Math.ceil(this.getDilDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
