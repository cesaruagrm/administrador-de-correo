/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;

/**
 *
 * @author Lisbeth
 */
public interface AccionCorreo {

    public byte[] getModel();

    public String getHtml(List<String> datos);// aqui enviar los datos ABME

    public String getExample();
}
