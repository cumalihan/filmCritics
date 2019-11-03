/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entity.Kullanici;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = {"/*"})
public class OturumFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        Kullanici ku = (Kullanici) req.getSession().getAttribute("kullanici");

        if (ku == null) {

            if (url.contains("panel")||url.contains("cikis")) {
                res.sendRedirect(req.getContextPath() + "/giris.xhtml");
            } else {
                chain.doFilter(request, response);
            }

        } else {
            if (url.contains("giris")|| url.contains("kayit")) {
                res.sendRedirect(req.getContextPath() + "/front/anasayfa.xhtml");
            }else if(url.contains("cikis")){
                 req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            }
            else {
                chain.doFilter(request, response);
            }
        }

    }

}
