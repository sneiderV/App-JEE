/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CandidatoTest.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Carlos Andrés Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.elecciones.test;

import uniandes.cupi2.elecciones.mundo.Candidato;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Candidato estén correctamente implementados
 */
public class CandidatoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Candidato candidato;

    // -----------------------------------------------------------------
    // Métodos
    // -------------------------

    /**
     * Construye un nuevo Candidato
     */
    private void setupEscenario1( )
    {
        candidato = new Candidato( );
        candidato.inicializar( "Felipe", "Pitti", "Independiente", 27 );
    }

    /**
     * Prueba 1 - Inicializar los datos del candidato correctamente
     */
    public void testInicializar( )
    {
        setupEscenario1( );

        assertTrue( "No se crea con el nombre dado por parámetro", candidato.darNombre( ).equals( "Felipe" ) );
        assertTrue( "No se crea con el apellido dado por parámetro", candidato.darApellido( ).equals( "Pitti" ) );
        assertTrue( "No se crea con el partido político dado por parámetro", candidato.darPartidoPolitico( ).equals( "Independiente" ) );
        assertTrue( "No se crea con la edad dada por parámetro", candidato.darEdad( ) == 27 );
        assertTrue( "No se crea con el costo de campaña en 0", candidato.darCostoCampanha( ) == 0 );
        assertTrue( "No se crea con el número de votos en 0", candidato.darVotos( ) == 0 );
    }

    /**
     * Prueba 2 - Agregar un voto al candidato influenciado por la televisión correctamente
     */
    public void testAgregarVotoTelevision( )
    {
        setupEscenario1( );

        candidato.agregarVotoTelevision( );
        assertTrue( "No aumenta el número de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campaña en 1000", candidato.darCostoCampanha( ) == 1000 );
    }

    /**
     * Prueba 3 - Agregar un voto al candidato influenciado por la radio correctamente
     */
    public void testAgregarVotoRadio( )
    {
        setupEscenario1( );

        candidato.agregarVotoRadio( );
        assertTrue( "No aumenta el número de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campaña en 500", candidato.darCostoCampanha( ) == 500 );
    }

    /**
     * Prueba 4 - Agregar un voto al candidato influenciado por Internet correctamente
     */
    public void testAgregarVotoInternet( )
    {
        setupEscenario1( );

        candidato.agregarVotoInternet( );
        assertTrue( "No aumenta el número de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campaña en 100", candidato.darCostoCampanha( ) == 100 );
    }

    /**
     * Prueba 5 - Reiniciar los votos del candidato correctamente
     */
    public void testReiniciarConteoVotos( )
    {
        setupEscenario1( );

        candidato.agregarVotoTelevision( );
        candidato.agregarVotoRadio( );
        candidato.agregarVotoInternet( );
        candidato.reiniciarConteoVotos( );

        assertTrue( "No inicializa el número de votos a 0", candidato.darVotos( ) == 0 );
    }

    /**
     * Prueba 6 - Reiniciar el costo de campaña del candidato correctamente
     */
    public void testReiniciarCostoCampanha( )
    {
        setupEscenario1( );

        candidato.agregarVotoTelevision( );
        candidato.agregarVotoRadio( );
        candidato.agregarVotoInternet( );
        candidato.reiniciarCostoCampanha( );

        assertTrue( "No inicializa el costo de campaña a 0", candidato.darCostoCampanha( ) == 0 );
    }
}
