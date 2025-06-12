/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: VideoTiendaTest.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import java.util.ArrayList;
import junit.framework.TestCase;
import uniandes.cupi2.videotienda.mundo.Cliente;
import uniandes.cupi2.videotienda.mundo.Copia;
import uniandes.cupi2.videotienda.mundo.Pelicula;
import uniandes.cupi2.videotienda.mundo.VideoTienda;

/**
 * Esta clase sirve para probar la clase Videotienda
 */
public class VideoTiendaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	
    /**
     * Videotienda de prueba
     */
    private VideoTienda videotienda;

    /**
     * Cliente 1 de prueba
     */
    private Cliente cliente1;

    /**
     * Cliente 2 de prueba
     */
    private Cliente cliente2;

    /**
     * Pel�cula 1 de prueba
     */
    private Pelicula pelicula1;

    /**
     * Pel�cula 2 de prueba
     */
    private Pelicula pelicula2;

    /**
     * Tarifa diaria de prueba
     */
    private int tarifa;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Establece la tarifa, crea la videotienda y la carga con las pel�culas de prueba
     */
    private void setupEscenario1( )
    {
        tarifa = 6000;
        try
        {
            videotienda = new VideoTienda( tarifa );
            videotienda.cargarPeliculas( "test/data/peliculas_prueba.txt" );
        }
        catch( Exception e )
        {
            fail( "Los datos de prueba se deben cargar sin problemas: " + e.getMessage( ) );
        }
    }

    /**
     * A la videotienda del escenario 1 le afilia 2 clientes
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );

        cliente1 = new Cliente( "Pedro", "123", "dir1" );
        cliente2 = new Cliente( "Jorge", "345", "dir2" );
        try
        {
            videotienda.afiliarCliente( cliente1.darCedula( ), cliente1.darNombre( ), cliente1.darDireccion( ) );
            videotienda.afiliarCliente( cliente2.darCedula( ), cliente2.darNombre( ), cliente2.darDireccion( ) );
        }
        catch( Exception e )
        {
            fail( "Los clientes se debieron afiliar sin problemas" );
        }

    }

    /**
     * En la videotienda del escenario 2, le agrega saldo al cliente1
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );

        try
        {
            videotienda.cargarSaldoCliente( cliente1.darCedula( ), 100000 );
        }
        catch( Exception e )
        {
            fail( "Debi� incrementar el saldo sin problemas" );
        }
    }

    /**
     * A la videotienda del escenario 3, le registra un alquiler del cliente 1
     */
    private void setupEscenario4( )
    {
        setupEscenario3( );

        String titulo = "Buscando a Nemo";
        try
        {
            videotienda.alquilarPelicula( titulo, cliente1.darCedula( ) );
        }
        catch( Exception e )
        {
            fail( "Se debi� alquilar la pel�cula sin problemas" );
        }
    }

    /**
     * Prueba la carga de las pel�culas seg�n el archivo de pruebas
     */
    public void testCargaCatalogo( )
    {
        setupEscenario1( );
        ArrayList<Pelicula> catalogo = videotienda.darCatalogo();
        assertEquals( 10, catalogo.size( ) );
        pelicula1 = ( Pelicula )catalogo.get( 0 );
        assertEquals( "El senor de Los anillos I", pelicula1.darTitulo( ) );
        assertEquals( 3, pelicula1.darNumeroDisponibles( ) );
        assertEquals( 3, pelicula1.darTotalCopias( ) );
    }

    /**
     * Prueba la b�squeda de pel�culas existentes y no existentes
     */
    public void testBuscarPeliculas( )
    {
        setupEscenario1( );
        pelicula1 = videotienda.buscarPelicula( "Matrix" );
        assertNotNull( pelicula1 );
        assertEquals( 3, pelicula1.darNumeroDisponibles( ) );
        assertEquals( 3, pelicula1.darTotalCopias( ) );
        assertEquals( "Matrix", pelicula1.darTitulo( ) );

        pelicula2 = videotienda.buscarPelicula( "PeliculaNoExiste" );
        assertNull( pelicula2 );
    }

    /**
     * Prueba agregar copias a pel�culas que existen y que no existen
     */
    public void testAgregarCopia( )
    {
        setupEscenario1( );

        //Agrega copia a una pel�cula existente
        String titulo = "Buscando a Nemo";
        try
        {
            pelicula1 = videotienda.buscarPelicula( titulo );
            assertEquals( 2, pelicula1.darTotalCopias( ) );
            assertEquals( 2, pelicula1.darNumeroDisponibles( ) );
            videotienda.agregarCopiaPelicula( titulo );
            assertEquals( 3, pelicula1.darTotalCopias( ) );
            assertEquals( 3, pelicula1.darNumeroDisponibles( ) );
        }
        catch( Exception e )
        {
            fail( "La copia se debi� agregar sin problemas" );
        }

        //Agrega copia a una pel�cula no existente
        titulo = "Pel�cula no existe";
        try
        {
            videotienda.agregarCopiaPelicula( titulo );
            fail( "Debi� fallar porque la pel�cula no existe" );
        }
        catch( Exception e )
        {
            assertTrue( "La excepci�n se espera porque la pel�cula no existe", true );
        }
    }

    /**
     * Prueba la afiliaci�n de clientes a la videotienda.
     */
    public void testAfiliarClientes( )
    {
        setupEscenario2( );

        ArrayList<Cliente> clientes = videotienda.darListaClientes();
        assertEquals( 2, clientes.size( ) );
        Cliente c = ( Cliente )clientes.get( 0 );
        assertEquals( cliente1.darCedula( ), c.darCedula( ) );
        assertEquals( cliente1.darDireccion( ), c.darDireccion( ) );
        assertEquals( cliente1.darNombre( ), c.darNombre( ) );
        c = ( Cliente )clientes.get( 1 );
        assertEquals( cliente2.darCedula( ), c.darCedula( ) );
        assertEquals( cliente2.darDireccion( ), c.darDireccion( ) );
        assertEquals( cliente2.darNombre( ), c.darNombre( ) );
    }

    /**
     * Prueba la b�squeda de clientes que existen y que no existen
     */
    public void testBuscarClientes( )
    {
        setupEscenario2( );

        //Busca un cliente que si existe
        Cliente c = videotienda.buscarCliente( cliente2.darCedula( ) );
        assertEquals( cliente2.darCedula( ), c.darCedula( ) );
        assertEquals( cliente2.darNombre( ), c.darNombre( ) );
        assertEquals( cliente2.darDireccion( ), c.darDireccion( ) );
        assertEquals( 0, c.darAlquiladas( ) );
        assertEquals( 0, c.darSaldo( ) );

        //Busca un cliente que no existe
        c = videotienda.buscarCliente( "0000" );
        assertNull( c );
    }

    /**
     * Prueba la carga de saldo del cliente
     */
    public void testCargarSaldocliente( )
    {
        setupEscenario3( );
        Cliente c = videotienda.buscarCliente( cliente1.darCedula( ) );
        assertEquals( 100000, c.darSaldo( ) );
        try
        {
            videotienda.cargarSaldoCliente( cliente1.darCedula( ), -1000 );
            fail( "Debe fallar porque el monto es negativo" );
        }
        catch( Exception e )
        {
            assertTrue( "Se esperaba la excepci�n porque el monto es negativo", true );
        }
    }

    /**
     * Prueba el alquiler de una pel�cula a un cliente con saldo.
     */
    public void testAlquilarPeliculaConSaldo( )
    {
        setupEscenario3( );
        String titulo = "Buscando a Nemo";
        try
        {
            videotienda.alquilarPelicula( titulo, cliente1.darCedula( ) );
            Cliente c = videotienda.buscarCliente( cliente1.darCedula( ) );
            //verifica la copia
            Copia copia = ( Copia )c.darAlquiladas( ).get( 0 );
            assertEquals( titulo, copia.darTituloPelicula( ) );
            //El saldo del cliente se debe disminuir en la tarifa
            assertEquals( 100000 - tarifa, c.darSaldo( ) );
        }
        catch( Exception e )
        {
            fail( "Se debi� alquilar la pel�cula sin problemas" );
        }
    }

    /**
     * Prueba el alquiler de una pel�cula a un cliente sin saldo.
     */
    public void testAlquilarPeliculaSinSaldo( )
    {
        setupEscenario3( );
        String titulo = "Buscando a Nemo";
        try
        {
            videotienda.alquilarPelicula( titulo, cliente2.darCedula( ) );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n es esperada porque no tiene saldo", true );
        }
    }

    /**
     * Prueba la correcta devoluci�n de la copia de un cliente
     */
    public void testDevolverCopiaExistente( )
    {
        setupEscenario4( );
        Cliente c = videotienda.buscarCliente( cliente1.darCedula( ) );
        Copia copia = ( Copia )c.darAlquiladas( ).get( 0 );
        try
        {
            videotienda.devolverCopia( copia.darTituloPelicula( ), copia.darCodigo( ), c.darCedula( ) );
        }
        catch( Exception e )
        {
            fail( "La copia se debi� devolver sin problemas" );
        }
    }

    /**
     * Prueba la incorrecta devoluci�n de la copia de un cliente
     */
    public void testDevolverCopiaNoExistente( )
    {
        setupEscenario4( );
        Cliente c = videotienda.buscarCliente( cliente1.darCedula( ) );
        Copia copia = ( Copia )c.darAlquiladas( ).get( 0 );

        //Si el t�tulo est� mal
        try
        {
            videotienda.devolverCopia( "titulo no existente", copia.darCodigo( ), c.darCedula( ) );
            fail( "Debi� generar error" );
        }
        catch( Exception e )
        {
            assertTrue( "Se esperaba por ser un t�tulo incorrecto", true );
        }

        try
        {
            videotienda.devolverCopia( copia.darTituloPelicula( ), 9999, c.darCedula( ) );
            fail( "Debi� generar error" );
        }
        catch( Exception e )
        {
            assertTrue( "Se esperaba por ser un c�digo incorrecto", true );
        }

        try
        {
            videotienda.devolverCopia( copia.darTituloPelicula( ), copia.darCodigo( ), "00000" );
            fail( "Debi� generar error" );
        }
        catch( Exception e )
        {
            assertTrue( "Se esperaba por ser una c�dula incorrecta", true );
        }
    }
}
