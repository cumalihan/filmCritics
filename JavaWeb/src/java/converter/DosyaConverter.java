/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.DosyaDAO;
import entity.Dosya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "dosyaConverter")
public class DosyaConverter implements Converter {

    private DosyaDAO dosyaDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDosyaDao().bul(Long.valueOf(value));
    }

    @Override

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Dosya ds = (Dosya)arg2;
        return ds.getDosya_id().toString();
    }

    public DosyaDAO getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDAO();
        }
        return dosyaDao;
    }

}
