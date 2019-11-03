/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

public class Yetki {
    private Long yetki_id;
    private String grup;

    public Yetki() {
    }

    public Yetki(Long yetki_id, String grup) {
        this.yetki_id = yetki_id;
        this.grup = grup;
    }

    public Long getYetki_id() {
        return yetki_id;
    }

    public void setYetki_id(Long yetki_id) {
        this.yetki_id = yetki_id;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    @Override
    public String toString() {
        return "Yetki{" + "yetki_id=" + yetki_id + ", grup=" + grup + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.yetki_id);
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
        final Yetki other = (Yetki) obj;
        if (!Objects.equals(this.yetki_id, other.yetki_id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
