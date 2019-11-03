/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.YoneticiDAO;
import entity.Yonetici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class YoneticiController implements Serializable {

    private List<Yonetici> yoneticiList;
    private YoneticiDAO yoneticiDao;

    private Yonetici yonetici;

    public String Sil() {
        this.getYoneticiDao().sil(this.yonetici);
        temizle();
        return "/panel/yonetici/yonetici";
    }

    public String onaylaSil(Yonetici yonetici) {
        this.yonetici = yonetici;
        return "/panel/onayla/yoneticiOnayla";
    }

    public void olustur() {
        this.yoneticiDao.ekle(this.yonetici);
        temizle();
    }

    public void formuGuncelle(Yonetici yonetici) {
        this.yonetici = yonetici;
    }

    public void guncelle() {
        this.getYoneticiDao().guncelle(this.yonetici);
        temizle();
    }

    public void temizle() {
        this.yonetici = new Yonetici();
    }

    public List<Yonetici> getYoneticiList() {
        this.yoneticiList = this.getYoneticiDao().getYonetici();
        return yoneticiList;
    }

    public void setYoneticiList(List<Yonetici> yoneticiList) {
        this.yoneticiList = yoneticiList;
    }

    public YoneticiDAO getYoneticiDao() {
        if (this.yoneticiDao == null) {
            this.yoneticiDao = new YoneticiDAO();
        }
        return yoneticiDao;
    }

    public void setYoneticiDao(YoneticiDAO yoneticiDao) {
        this.yoneticiDao = yoneticiDao;
    }

    public Yonetici getYonetici() {
        if (this.yonetici == null) {
            this.yonetici = new Yonetici();
        }
        return yonetici;
    }

    public void setYonetici(Yonetici yonetici) {
        this.yonetici = yonetici;
    }
}
