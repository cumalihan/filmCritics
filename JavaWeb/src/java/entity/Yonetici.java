/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Yonetici {
    private Long yonetici_id;
    private String kullanici_adi;
    private String sifre;

    public Yonetici() {
    }

    public Yonetici(Long yonetici_id, String kullanici_adi, String sifre) {
        this.yonetici_id = yonetici_id;
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
    }

    public Long getYonetici_id() {
        return yonetici_id;
    }

    public void setYonetici_id(Long yonetici_id) {
        this.yonetici_id = yonetici_id;
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

    @Override
    public String toString() {
        return "Yonetici{" + "yonetici_id=" + yonetici_id + ", kullanici_adi=" + kullanici_adi + ", sifre=" + sifre + '}';
    }
    
    
            
}
