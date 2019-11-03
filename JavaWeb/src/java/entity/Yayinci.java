/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Yayinci {
    private Long yayinci_id;
    private String ad;

    public Yayinci() {
    }

    public Yayinci(Long yayinci_id, String ad) {
        this.yayinci_id = yayinci_id;
        this.ad = ad;
    }

    public Long getYayinci_id() {
        return yayinci_id;
    }

    public void setYayinci_id(Long yayinci_id) {
        this.yayinci_id = yayinci_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Yayinci{" + "yayinci_id=" + yayinci_id + ", ad=" + ad + '}';
    }
    
    
}
