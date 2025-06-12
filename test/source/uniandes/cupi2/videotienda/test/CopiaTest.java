/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: CopiaTest.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.test;

import junit.framework.TestCase;
import uniandes.cupi2.videotienda.mundo.Copia;

/**
 * Clase para probar la clase Copia
 */
public class CopiaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * La copia 1 de prueba
     */
    private Copia copia1;

    /**
     * La copia 2 de prueba
     */
    private Copia copia2;

    /**
     * La pel�cula 1 de prueba
     */
    private String tituloPelicula1;

    /**
     * La pel�cula 2 de prueba
     */
    private String tituloPelicula2;
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Prepara el escenario de prueba
     */
    private void setupEscenario1( )
    {
        tituloPelicula1 = "Retroceder nunca, rendirse jamás XVII";
        copia1 = new Copia(1, tituloPelicula1);
        tituloPelicula2 = "El Gran Pez";
        copia2 = new Copia(2, tituloPelicula2);
    }
    /**
     * Verificar el m�todo darConsecutivo
     */
    public void testDarCodigo( )
    {
        setupEscenario1( );

        assertEquals( "El consecutivo est� equivocado", 1, copia1.darCodigo( ) );
        assertEquals( "El consecutivo est� equivocado", 2, copia2.darCodigo( ) );
    }

    /**
     * Valida el m�todo que retorna el t�tulo de la pel�cula de una copia
     */
    public void testDarTituloPelicula( )
    {
        setupEscenario1( );

        String t = copia1.darTituloPelicula( );
        assertEquals( "El t�tulo de la pel�cula 1 es incorrecto", tituloPelicula1, t );
        String t2 = copia2.darTituloPelicula( );
        assertEquals( "El t�tulo de la pel�cula 2 es incorrecto", tituloPelicula2, t2 );
    }

    /**
     * Valida el m�todo de comparaci�n entre copias
     */
    public void testEsIgualA( )
    {
        setupEscenario1( );

        assertFalse( copia1.esIgualA( copia2 ) );
        Copia otra = new Copia( copia1.darCodigo(), copia1.darTituloPelicula() );
        assertTrue( copia1.esIgualA( otra ) );
    }
}
