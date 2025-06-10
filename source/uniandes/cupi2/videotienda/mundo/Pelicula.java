/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Pelicula.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
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

import uniandes.cupi2.videotienda.mundo.Copia


/**
 * Esta clase representa una película que se encuentra en la videotienda y
 * de la cual puede haber copias disponibles o prestadas.
 */ 
public class Pelicula
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Título de la película
     */
    private String titulo;

    /**
     * Lista de copias disponibles
     */
    private ArrayList<Copia> disponibles;

    /**
     * Lista de copias prestadas
     */
    private ArrayList<Copia> prestadas;

    /**
     * Número de la siguiente copia a adicionar
     */
    private int codigoSiguienteCopia;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una pel�cula de la videotienda con el t�tulo dado. <br>
     * <b>post: </b> La pel�cula se crea sin copias disponibles ni prestadas.
     * @param unTitulo T�tulo de la pel�cula. unTitulo != null.
     */
    public Pelicula( String unTitulo )
    {
    	//TODO implementar inicializando los atributos
    	titulo = unTitulo;
    	disponibles = new ArrayList();
    	prestadas = new ArrayList();
    	codigoSiguienteCopia = 1;
    	
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Adiciona una nueva copia de la pel�cula. <br>
     * <b>post: </b>La lista de pel�culas disponibles tiene una nueva copia.
     * @return c�digo de la copia creada. c�digo >= 1;
     */
    //TODO implementar. Recuerde retornar lo indicado en la documentación.
    public int agregarCopia( )
    {
    	Copia nuevaCopia = new Copia(codigoSiguienteCopia, titulo);
    	disponibles.add(nuevaCopia);
    	return codigoSiguienteCopia++;
    }

    /**
     * Retorna una copia de pel�cula para alquilar si hay disponibles. <br>
     * <b>post: </b> la copia queda en la lista de prestadas.
     * @return Copia que ha sido alquilada o null si no hay disponibles.
     */
    public Copia alquilarCopia( )
    {
    	//TODO implementar. Recuerde retornar lo indicado en la documentación.
    	if (disponibles.isEmpty()) {
    		return null;
    	}
    	Copia copia = disponibles.remove(0);
    	prestadas.add(copia);
    	return copia;
    }

    /**
     * Devuelve una copia de la pel�cula y la coloca como disponible. <br>
     * <b>post: </b> regresa la copia a la lista de disponibles, s�lo si est� prestada.
     * @param codigoCopia C�digo de la copia que se quiere devolver.
     * @throws Exception Si la copia a devolver no est� prestada.
     */
    
    //TODO Definir la signatura del método de acuerdo a la documentaci�n e implementarlo.
    public void devolverCopia (int codigoCopia) throws Exception {
    	Copia copiaADevolver = null;
    	for ( Copia copia : prestadas) {
    		if ( copia.darCodigo() == codigoCopia) {
    			copiaADevolver = copia;
    			break;
    		}
    	}
    	if (copiaADevolver == null) {
    		throw new Exception("La copia con código " + codigoCopia + "no está prestada");
    	}
    	prestadas.remove(copiaADevolver);
    	disponibles.add(copiaADevolver);
    }


    /**
     * Retorna el t�tulo de la pel�cula.
     * @return t�tulo de la pel�cula.
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna la cantidad total de copias que existen de la pel�cula en la videotienda
     * @return entero con la cantidad de copias que existen en la tienda
     */
    //TODO Definir la signatura del m�todo de acuerdo a la documentaci�n e implementarlo.
    public int darTotalCopias() {
    	return disponibles.size() + prestadas.size();
    }
    
    /**
     * Retorna el n�mero de copias disponibles
     * @return n�mero de copias disponibles
     */
    //TODO Definir la signatura del m�todo de acuerdo a la documentaci�n e implementarlo.
    public int darNumeroDisponibles() {
    	return disponibles.size();
    }
}