/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;


public class Dosya {
    private Long dosya_id;
    private String dosya_konumu;
    private String dosya_ismi;
    private String dosya_tipi;
    
    private Film film;

    public Long getDosya_id() {
        return dosya_id;
    }

    public void setDosya_id(Long dosya_id) {
        this.dosya_id = dosya_id;
    }

    public String getDosya_konumu() {
        return dosya_konumu;
    }

    public void setDosya_konumu(String dosya_konumu) {
        this.dosya_konumu = dosya_konumu;
    }

    public String getDosya_ismi() {
        return dosya_ismi;
    }

    public void setDosya_ismi(String dosya_ismi) {
        this.dosya_ismi = dosya_ismi;
    }

    public String getDosya_tipi() {
        return dosya_tipi;
    }

    public void setDosya_tipi(String dosya_tipi) {
        this.dosya_tipi = dosya_tipi;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.dosya_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dosya other = (Dosya) obj;
        if (!Objects.equals(this.dosya_id, other.dosya_id)) {
            return false;
        }
        return true;
    }
    
    

    
}
