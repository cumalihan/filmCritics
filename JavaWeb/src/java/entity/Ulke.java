/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Ulke {
    private Long ulke_id;
    private String ulke;

    public Ulke() {
    }

    public Ulke(Long ulke_id, String ulke) {
        this.ulke_id = ulke_id;
        this.ulke = ulke;
    }

    public Long getUlke_id() {
        return ulke_id;
    }

    public void setUlke_id(Long ulke_id) {
        this.ulke_id = ulke_id;
    }

    public String getUlke() {
        return ulke;
    }

    public void setUlke(String ulke) {
        this.ulke = ulke;
    }

    @Override
    public String toString() {
        return "Ulke{" + "ulke_id=" + ulke_id + ", ulke=" + ulke + '}';
    }
    
}
