/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DilDAO;
import dao.DosyaDAO;
import dao.FilmDAO;
import entity.Dil;
import entity.Dosya;
import entity.Film;
import entity.Kategori;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class FilmController implements Serializable {

    private Film film;
    private List<Film> filmList;
    private FilmDAO filmDao;

    @Inject
    private KategoriController kategoriController;

    private List<Dil> dilList;
    private DilDAO dilDao;
    
    private List<Dosya> dosyaList;
    private DosyaDAO dosyaDao;
    
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

    public void formuTemizle() {
        this.film = new Film();
    }

    public void formuGuncelle(Film f) {
        this.film = f;
    }

    public void guncelle() {
        this.getFilmDao().duzelt(this.film);
        this.formuTemizle();
    }

    public String sil() {
        this.getFilmDao().yoket(this.film);
        this.film = new Film();
        return "/panel/film/film";
    }
    
    public String onaylaSil(){
        return "/panel/onayla/filmOnayla";
    }

    public void olustur() {
        this.getFilmDao().ekle(this.film);
        this.film = new Film();
    }

    public Film getFilm() {
        if (this.film == null) {
            this.film = new Film();
        }
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Film> getFilmList() {
        this.filmList = this.getFilmDao().findAll(sayfa,sayfaBoyutu,this.getTerimAra());
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    public FilmDAO getFilmDao() {
        if (this.filmDao == null) {
            this.filmDao = new FilmDAO();
        }
        return filmDao;
    }

    public void setFilmDao(FilmDAO filmDao) {
        this.filmDao = filmDao;
    }

    public DilDAO getDilDao() {
        if (this.dilDao == null) {
            this.dilDao = new DilDAO();
        }
        return dilDao;
    }

    public List<Dil> getDilList() {
        this.dilList = this.getDilDao().getDil();
        return dilList;
    }

    public void setDilList(List<Dil> dilList) {
        this.dilList = dilList;
    }


    public KategoriController getKategoriController() {
        return kategoriController;
    }

    public void setKategoriController(KategoriController kategoriController) {
        this.kategoriController = kategoriController;
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
        this.sayfaSayısı = (int) Math.ceil(this.getFilmDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
        return sayfaSayısı;
    }

    public void setSayfaSayısı(int sayfaSayısı) {
        this.sayfaSayısı = sayfaSayısı;
    }

    public List<Dosya> getDosyaList() {
        this.dosyaList=this.getDosyaDao().findAll();
        return dosyaList;
    }
    

    public void setDosyaList(List<Dosya> dosyaList) {
        this.dosyaList = dosyaList;
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

    public void setDosyaDao(DosyaDAO dosyaDao) {
        this.dosyaDao = dosyaDao;
    }

    public String getTerimAra() {
        return terimAra;
    }

    public void setTerimAra(String terimAra) {
        this.terimAra = terimAra;
    }
    
}
