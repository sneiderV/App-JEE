/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CandidatoTest.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Carlos Andr�s Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.elecciones.test;

import uniandes.cupi2.elecciones.mundo.Candidato;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Candidato est�n correctamente implementados
 */
public class CandidatoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Candidato candidato;

    // -----------------------------------------------------------------
    // M�todos
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

        assertTrue( "No se crea con el nombre dado por par�metro", candidato.darNombre( ).equals( "Felipe" ) );
        assertTrue( "No se crea con el apellido dado por par�metro", candidato.darApellido( ).equals( "Pitti" ) );
        assertTrue( "No se crea con el partido pol�tico dado por par�metro", candidato.darPartidoPolitico( ).equals( "Independiente" ) );
        assertTrue( "No se crea con la edad dada por par�metro", candidato.darEdad( ) == 27 );
        assertTrue( "No se crea con el costo de campa�a en 0", candidato.darCostoCampanha( ) == 0 );
        assertTrue( "No se crea con el n�mero de votos en 0", candidato.darVotos( ) == 0 );
    }

    /**
     * Prueba 2 - Agregar un voto al candidato influenciado por la televisi�n correctamente
     */
    public void testAgregarVotoTelevision( )
    {
        setupEscenario1( );

        candidato.agregarVotoTelevision( );
        assertTrue( "No aumenta el n�mero de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campa�a en 1000", candidato.darCostoCampanha( ) == 1000 );
    }

    /**
     * Prueba 3 - Agregar un voto al candidato influenciado por la radio correctamente
     */
    public void testAgregarVotoRadio( )
    {
        setupEscenario1( );

        candidato.agregarVotoRadio( );
        assertTrue( "No aumenta el n�mero de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campa�a en 500", candidato.darCostoCampanha( ) == 500 );
    }

    /**
     * Prueba 4 - Agregar un voto al candidato influenciado por Internet correctamente
     */
    public void testAgregarVotoInternet( )
    {
        setupEscenario1( );

        candidato.agregarVotoInternet( );
        assertTrue( "No aumenta el n�mero de votos en 1", candidato.darVotos( ) == 1 );
        assertTrue( "No aumenta el costo de campa�a en 100", candidato.darCostoCampanha( ) == 100 );
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

        assertTrue( "No inicializa el n�mero de votos a 0", candidato.darVotos( ) == 0 );
    }

    /**
     * Prueba 6 - Reiniciar el costo de campa�a del candidato correctamente
     */
    public void testReiniciarCostoCampanha( )
    {
        setupEscenario1( );

        candidato.agregarVotoTelevision( );
        candidato.agregarVotoRadio( );
        candidato.agregarVotoInternet( );
        candidato.reiniciarCostoCampanha( );

        assertTrue( "No inicializa el costo de campa�a a 0", candidato.darCostoCampanha( ) == 0 );
    }
}
