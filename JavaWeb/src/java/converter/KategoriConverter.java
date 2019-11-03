/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.KategoriDAO;
import entity.Kategori;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(value = "kategoriConverter")
public class KategoriConverter implements Converter{

    private KategoriDAO kategoriDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKategoriDao().bul(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Kategori k = (Kategori) arg2;
        return k.getKategori_id().toString();
    }
    
    public KategoriDAO getKategoriDao() {
        if(this.kategoriDao == null)
            this.kategoriDao = new KategoriDAO();
        return kategoriDao;
    } 
    
}
