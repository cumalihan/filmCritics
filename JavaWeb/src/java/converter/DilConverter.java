/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.DilDAO;
import entity.Dil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dilConverter")
public class DilConverter implements Converter{

    private DilDAO dilDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDilDao().bul(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Dil d = (Dil) arg2;
        return d.getDil_id().toString();
    }

    public DilDAO getDilDao() {
        if(this.dilDao==null)
            this.dilDao = new DilDAO();
        return dilDao;
    }
    
    
}
