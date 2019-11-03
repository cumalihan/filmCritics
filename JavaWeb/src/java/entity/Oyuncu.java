/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Oyuncu {
    private Long oyuncu_id;
    private String ad;
    private String soyad;
    private String cinsiyet;

    public Oyuncu() {
    }

    public Oyuncu(Long oyuncu_id, String ad, String soyad, String cinsiyet) {
        this.oyuncu_id = oyuncu_id;
        this.ad = ad;
        this.soyad = soyad;
        this.cinsiyet = cinsiyet;
    }

    public Long getOyuncu_id() {
        return oyuncu_id;
    }

    public void setOyuncu_id(Long oyuncu_id) {
        this.oyuncu_id = oyuncu_id;
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

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    @Override
    public String toString() {
        return "Oyuncu{" + "oyuncu_id=" + oyuncu_id + ", ad=" + ad + ", soyad=" + soyad + ", cinsiyet=" + cinsiyet + '}';
    }
    
    
}
