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
public class Autenticador {

    private boolean isAlfabeto(String dato) {// 65=A;Z=90;a=97;z=122;ñ=241;Ñ=209        
        for (char caracter : dato.toCharArray()) {
            if (caracter < 65 || (caracter > 90 && caracter < 97) || (caracter > 122 && caracter < 209)
                    || (caracter > 209 && caracter < 241) || caracter > 241) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumero(String dato) {//0 = 48;9= 57;
        for (char caracter : dato.toCharArray()) {
            if (caracter < 48 || caracter > 57) {
                return false;
            }
        }
        return true;
    }

    private boolean isDouble(String dato) {//0 = 48;9= 57; . = 46
        boolean punto = false;
        for (char caracter : dato.toCharArray()) {
            if (caracter < 48 || caracter > 57) {//no es numero
                if (caracter == 46 && !punto) {
                    punto = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean Tipo(String dato, byte tipo) {
        switch (tipo) {
            case Tipo.ALFABETO:
                return isAlfabeto(dato);
            case Tipo.NUMERO:
                return isNumero(dato);
            case Tipo.DOUBLE:
                return isDouble(dato);
        }
        return true;
    }

    public boolean Verificar(List<String> datos, byte[] modelo) {
        if (datos.size() < modelo.length + 1)
        {
            return false;
        }
        return true;
    }
}
