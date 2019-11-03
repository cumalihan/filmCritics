/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;



public class Kategori {
    private Long kategori_id;
    private String kategori;

    public Kategori() {
    }

    public Kategori(Long kategori_id, String kategori) {
        this.kategori_id = kategori_id;
        this.kategori = kategori;
    }

    public Long getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(Long kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return "Kategori{" + "kategori_id=" + kategori_id + ", kategori=" + kategori + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.kategori_id);
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
        final Kategori other = (Kategori) obj;
        if (!Objects.equals(this.kategori_id, other.kategori_id)) {
            return false;
        }
        return true;
    }
  
    
    
    
}
