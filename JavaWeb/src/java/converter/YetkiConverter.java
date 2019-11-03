/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.YetkiDAO;
import entity.Yetki;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "yetkiConverter")
public class YetkiConverter implements Converter{

    private YetkiDAO yetkiDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getYetkiDao().bul(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Yetki yet = (Yetki) arg2;
        return yet.getYetki_id().toString();
    }

    public YetkiDAO getYetkiDao() {
        if(this.yetkiDao == null)
            this.yetkiDao = new YetkiDAO();
        return yetkiDao;
    }
    
    
    
}
