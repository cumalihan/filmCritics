/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Yonetmen {
    private Long yonetmen_id;
    private String ad;
    private String soyad;

    public Yonetmen() {
    }

    public Yonetmen(Long yonetmen_id, String ad, String soyad) {
        this.yonetmen_id = yonetmen_id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public Long getYonetmen_id() {
        return yonetmen_id;
    }

    public void setYonetmen_id(Long yonetmen_id) {
        this.yonetmen_id = yonetmen_id;
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
        return "Yonetmen{" + "yonetmen_id=" + yonetmen_id + ", ad=" + ad + ", soyad=" + soyad + '}';
    }
    
    
    
}
