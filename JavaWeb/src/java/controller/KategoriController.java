/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.KategoriDAO;
import entity.Kategori;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class KategoriController implements Serializable {

    private List<Kategori> clist;
    private KategoriDAO cdao;

    private Kategori kategori;

    private int sayfa = 1;
    private int sayfaBoyutu = 10;
    private int sayfaSayısı;
    
    private String terimAra;
    
    public void ara(){
        this.setSayfa(1);    
    }
    
    public void aramaTemizle(){
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
        this.getKategoriDao().sil(this.kategori);
         temizle();
         return "/panel/kategori/kategori";
    }
    
    public String onaylaSil(Kategori kategori){
        this.kategori= kategori;
        return "/panel/onayla/kategoriOnayla";
    }

    public void olustur() {
        this.cdao.ekle(this.kategori);
        temizle();
    }

    public void formuGuncelle(Kategori kategori) {
        this.kategori = kategori;
    }

    public void guncelle() {
        this.getKategoriDao().guncelle(this.kategori);
        temizle();
    }

    public void temizle() {
        this.kategori = new Kategori();
    }

    public List<Kategori> getKategoriList() {
        this.clist = this.getKategoriDao().getKategori(sayfa, sayfaBoyutu,this.getTerimAra());
        return clist;
    }

    public void setKategoriList(List<Kategori> clist) {
        this.clist = clist;
    }

    public KategoriDAO getKategoriDao() {
        if (this.cdao == null) {
            this.cdao = new KategoriDAO();
        }
        return cdao;
    }

    public void setKategoriDao(KategoriDAO cdao) {
        this.cdao = cdao;
    }

    public Kategori getKategori() {
        if (this.kategori == null) {
            this.kategori = new Kategori();
        }
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
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
        this.sayfaSayısı = (int) Math.ceil(this.getKategoriDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
