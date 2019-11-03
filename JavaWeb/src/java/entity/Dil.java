/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;


public class Dil {
    private Long dil_id;
    private String dil;

    public Dil() {
    }

    public Dil(Long dil_id, String dil) {
        this.dil_id = dil_id;
        this.dil = dil;
    }

    public Long getDil_id() {
        return dil_id;
    }

    public void setDil_id(Long dil_id) {
        this.dil_id = dil_id;
    }

    public String getDil() {
        return dil;
    }

    public void setDil(String dil) {
        this.dil = dil;
    }

    @Override
    public String toString() {
        return "Dil{" + "dil_id=" + dil_id + ", dil=" + dil + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.dil_id);
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
        final Dil other = (Dil) obj;
        if (!Objects.equals(this.dil_id, other.dil_id)) {
            return false;
        }
        return true;
    }
    
    
}
