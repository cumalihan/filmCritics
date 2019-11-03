/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DosyaDAO;
import entity.Dosya;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;


@Named
@SessionScoped
public class DosyaController implements Serializable {

    private Dosya dosya;
    private List<Dosya> dosyaList;
    private DosyaDAO dosyaDao;
    
    private Part dos;
    
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
    
    private final String uploadTo = "/Users/Burak/Documents/NetBeansProjects/JavaWeb/yukleme/";
    
    public void yukle(){
        try{
            InputStream veriGirisi  = dos.getInputStream();
            File f = new File(uploadTo+dos.getSubmittedFileName());
            Files.copy(veriGirisi, f.toPath());
            
            dosya = this.getDosya();
            dosya.setDosya_konumu(f.getParent());
            dosya.setDosya_ismi(f.getName());
            dosya.setDosya_tipi(dos.getContentType());
            
            this.getDosyaDao().ekle(dosya);
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public Dosya getDosya() {
        if (this.dosya == null) {
            this.dosya = new Dosya();
        }
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public List<Dosya> getDosyaList() {
        this.dosyaList = this.getDosyaDao().findAll(sayfa, sayfaBoyutu,this.getTerimAra());
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

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
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
        this.sayfaSayısı = (int) Math.ceil(this.getDosyaDao().sayi(this.getTerimAra()) / (double) sayfaBoyutu);
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
