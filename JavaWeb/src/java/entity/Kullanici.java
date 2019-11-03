/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Kullanici {
    private Long kullanici_id;
    private String kullanici_adi;
    private String sifre;
    private String ad;
    private String soyad;
    private String e_posta;
    private Yetki yetki;

    public Kullanici() {
    }

    public Kullanici(Long kullanici_id, String kullanici_adi, String sifre, String ad, String soyad, String e_posta) {
        this.kullanici_id = kullanici_id;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.e_posta = e_posta;
    }

    public Long getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(Long kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getE_posta() {
        return e_posta;
    }

    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }

    public Yetki getYetki() {
        if(this.yetki==null){
            this.yetki= new Yetki();
            
        }
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }


    @Override
    public String toString() {
        return "Kullanici{" + "kullanici_id=" + kullanici_id + ", kullanici_adi=" + kullanici_adi + ", sifre=" + sifre + ", ad=" + ad + ", soyad=" + soyad + ", e_posta=" + e_posta + '}';
    }
    
    
    
}
