/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: VideoTienda.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * clase representa un cliente de la videotienda.
 */
public class Cliente {

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	
    /**
     * Cédula del cliente
     */
    private String cedula;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Dirección del cliente
     */
    private String direccion;

    /**
     * Saldo disponible del cliente
     */
    private int saldo;

    /**
     * Copias alquiladas por el cliente
     */
    private ArrayList<Copia> copiasAlquiladas;
    
  //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo cliente con la información dada.
     * @param cedula Cédula del cliente. cedula != null.
     * @param nombre Nombre del cliente. nombre != null.
     * @param direccion Dirección del cliente. direccion != null.
     */
    public Cliente(String cedula, String nombre, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.saldo = 0;
        this.copiasAlquiladas = new ArrayList<Copia>();
    }
    
  //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna la cédula del cliente.
     * @return cédula del cliente.
     */
    public String darCedula() {
        return cedula;
    }

    /**
     * Retorna el nombre del cliente.
     * @return nombre del cliente.
     */
    public String darNombre() {
        return nombre;
    }
    
    /**
     * Descarga o descuenta un valor del saldo actual del cliente.
     *
     * @param valor Monto que se debe descontar del saldo. Debe ser mayor a 0.
     */
    public void descargarSaldo(double valor) {
        saldo -= valor;
    }

    /**
     * Retorna la dirección del cliente.
     * @return dirección del cliente.
     */
    public String darDireccion() {
        return direccion;
    }

    /**
     * Retorna el saldo disponible del cliente.
     * @return saldo del cliente.
     */
    public int darSaldo() {
        return saldo;
    }
    
    /**
     * Aumenta el saldo disponible del cliente en la cantidad especificada.
     * 
     * @param monto Monto a cargar al saldo. monto > 0.
     */
    public void cargarSaldo(int monto) {
        saldo += monto;
    }


    /**
     * Retorna las copias alquiladas por el cliente.
     * @return lista de copias alquiladas.
     */
    public ArrayList<Copia> darAlquiladas() {
        return copiasAlquiladas;
    }

    /**
     * Recarga el saldo del cliente.
     * @param monto Monto a recargar. monto > 0.
     */
    public void recargarSaldo(int monto) {
        saldo += monto;
    }

    /**
     * Descuenta el saldo del cliente.
     * @param monto Monto a descontar. monto > 0.
     */
    public void descontarSaldo(int monto) {
        saldo -= monto;
    }
    
    /**
     * Busca una copia alquilada por el cliente que coincida con el título de la película y el código de la copia.
     * 
     * @param pelicula Título de la película que se desea buscar. pelicula != null.
     * @param codigo Código de la copia que se desea buscar.
     * @return La copia que coincide con el título y el código, o null si no se encuentra.
     */
    public Copia buscarPeliculaAlquilada(String pelicula, int codigo) {
        for (Copia c : copiasAlquiladas) {
            if (c.darTituloPelicula().equals(pelicula) && c.darCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }


    /**
     * Alquila una copia para el cliente.
     * @param copia Copia a alquilar. copia != null.
     */
    public void alquilarCopia(Copia copia) {
        copiasAlquiladas.add(copia);
    }

    /**
     * Devuelve una copia alquilada por el cliente.
     * @param titulo Título de la película.
     * @param numeroCopia Número de la copia.
     * @return la copia devuelta.
     * @throws Exception si el cliente no tiene alquilada esa copia.
     */
    public Copia devolverCopia(String titulo, int numeroCopia) throws Exception {
    	for (int i = 0; i < copiasAlquiladas.size(); i++) {
            Copia c = copiasAlquiladas.get(i);
            if (c.darCodigo() == numeroCopia && c.darTituloPelicula().equals(titulo)) {
                copiasAlquiladas.remove(i);
                return c;
            }
        }
        throw new Exception("El cliente no tiene alquilada esa copia.");
    }
    
}
