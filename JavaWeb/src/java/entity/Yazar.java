/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Yazar {
    private Long yazar_id;
    private String ad;
    private String soyad;

    public Yazar() {
    }

    public Yazar(Long yazar_id, String ad, String soyad) {
        this.yazar_id = yazar_id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public Long getYazar_id() {
        return yazar_id;
    }

    public void setYazar_id(Long yazar_id) {
        this.yazar_id = yazar_id;
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

    @Override
    public String toString() {
        return "Yazar{" + "yazar_id=" + yazar_id + ", ad=" + ad + ", soyad=" + soyad + '}';
    }
    
    
}
