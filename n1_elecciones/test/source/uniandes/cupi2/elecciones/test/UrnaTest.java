/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: UrnaTest.java 445 2007-06-25 16:30:28Z camil-ji $
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

import junit.framework.TestCase;
import uniandes.cupi2.elecciones.mundo.Urna;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Urna estén correctamente implementados
 */
public class UrnaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Urna urna;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Urna vacía
     * 
     */
    private void setupEscenario1( )
    {
        urna = new Urna( );
        urna.inicializar( );
    }

    /**
     * Construye una urna, donde cada candidato tiene un voto de cada tipo
     */
    private void setupEscenario2( )
    {
        urna = new Urna( );
        urna.inicializar( );

        urna.ingresarVotoTelevisionCandidato1( );
        urna.ingresarVotoTelevisionCandidato2( );
        urna.ingresarVotoTelevisionCandidato3( );

        urna.ingresarVotoRadioCandidato1( );
        urna.ingresarVotoRadioCandidato2( );
        urna.ingresarVotoRadioCandidato3( );

        urna.ingresarVotoInternetCandidato1( );
        urna.ingresarVotoInternetCandidato2( );
        urna.ingresarVotoInternetCandidato3( );
    }

    /**
     * Prueba 1 - Inicialización de los candidatos
     */
    public void testInicializacion( )
    {
        setupEscenario1( );
        assertNotNull( "El candidato 1 debe estar inicializado", urna.darCandidato1( ) );
        assertNotNull( "El candidato 2 debe estar inicializado", urna.darCandidato2( ) );
        assertNotNull( "El candidato 3 debe estar inicializado", urna.darCandidato3( ) );
    }

    /**
     * Prueba 2 - Ingresar voto influenciado por la televisión al candidato 1
     */
    public void testIngresarVotoTelevisionCandidato1( )
    {
        setupEscenario1( );
        urna.ingresarVotoTelevisionCandidato1( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato1( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 1000", urna.darCandidato1( ).darCostoCampanha( ) == 1000 );
    }

    /**
     * Prueba 3 - Ingresar voto influenciado por la radio al candidato 1
     */
    public void testIngresarVotoRadioCandidato1( )
    {
        setupEscenario1( );
        urna.ingresarVotoRadioCandidato1( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato1( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 500", urna.darCandidato1( ).darCostoCampanha( ) == 500 );
    }

    /**
     * Prueba 4 - Ingresar voto influenciado por Internet al candidato 1
     */
    public void testIngresarVotoInternetCandidato1( )
    {
        setupEscenario1( );
        urna.ingresarVotoInternetCandidato1( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato1( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 100", urna.darCandidato1( ).darCostoCampanha( ) == 100 );
    }

    /**
     * Prueba 5 - Ingresar voto influenciado por la televisión al candidato 2
     */
    public void testIngresarVotoTelevisionCandidato2( )
    {
        setupEscenario1( );
        urna.ingresarVotoTelevisionCandidato2( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato2( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 1000", urna.darCandidato2( ).darCostoCampanha( ) == 1000 );
    }

    /**
     * Prueba 6 - Ingresar voto influenciado por la radio al candidato 2
     */
    public void testIngresarVotoRadioCandidato2( )
    {
        setupEscenario1( );
        urna.ingresarVotoRadioCandidato2( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato2( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 500", urna.darCandidato2( ).darCostoCampanha( ) == 500 );
    }

    /**
     * Prueba 7 - Ingresar voto influenciado por Internet al candidato 2
     */
    public void testIngresarVotoInternetCandidato2( )
    {
        setupEscenario1( );
        urna.ingresarVotoInternetCandidato2( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato2( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 100", urna.darCandidato2( ).darCostoCampanha( ) == 100 );
    }

    /**
     * Prueba 8 - Ingresar voto influenciado por la televisión al candidato 3
     */
    public void testIngresarVotoTelevisionCandidato3( )
    {
        setupEscenario1( );
        urna.ingresarVotoTelevisionCandidato3( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato3( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 1000", urna.darCandidato3( ).darCostoCampanha( ) == 1000 );
    }

    /**
     * Prueba 9 - Ingresar voto influenciado por la radio al candidato 3
     */
    public void testIngresarVotoRadioCandidato3( )
    {
        setupEscenario1( );
        urna.ingresarVotoRadioCandidato3( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato3( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 500", urna.darCandidato3( ).darCostoCampanha( ) == 500 );
    }

    /**
     * Prueba 10 - Ingresar voto influenciado por Internet al candidato 3
     */
    public void testIngresarVotoInternetCandidato3( )
    {
        setupEscenario1( );
        urna.ingresarVotoInternetCandidato3( );
        assertTrue( "Debería aumentar el número de votos en 1", urna.darCandidato3( ).darVotos( ) == 1 );
        assertTrue( "Debería aumentar el costo de campaña en 100", urna.darCandidato3( ).darCostoCampanha( ) == 100 );
    }

    /**
     * Prueba 11 - Calcular el total de votos que posee la urna
     */
    public void testCalcularTotalVotos( )
    {
        setupEscenario2( );

        assertTrue( "No calcula el total de votos correctamente", urna.calcularTotalVotos( ) == 9 );

    }

    /**
     * Prueba 12 - Calcular el costo promedio de campaña de los candidatos
     */
    public void testCalcularCostoPromedioCampanha( )
    {
        setupEscenario2( );

        assertTrue( "No calcula el costo promedio de campaña correctamente", urna.calcularCostoPromedioCampanha( ) == 1600 );
    }

    /**
     * Prueba 13 - Calcular el porcentaje de los votos obtenidos por el candidato 1
     */
    public void testCalcularPorcentajeVotosCandidato1( )
    {
        setupEscenario2( );

        double porcentaje = ( double )3 / ( double )9 * ( double )100;

        assertTrue( "No calcula el costo promedio de campaña correctamente", urna.calcularPorcentajeVotosCandidato1( ) == porcentaje );
    }

    /**
     * Prueba 14 - Calcular el porcentaje de los votos obtenidos por el candidato 2
     */
    public void testCalcularPorcentajeVotosCandidato2( )
    {
        setupEscenario2( );

        double porcentaje = ( double )3 / ( double )9 * ( double )100;

        assertTrue( "No calcula el costo promedio de campaña correctamente", urna.calcularPorcentajeVotosCandidato2( ) == porcentaje );
    }

    /**
     * Prueba 15 - Calcular el porcentaje de los votos obtenidos por el candidato 3
     */
    public void testCalcularPorcentajeVotosCandidato3( )
    {
        setupEscenario2( );

        double porcentaje = ( double )3 / ( double )9 * ( double )100;

        assertTrue( "No calcula el costo promedio de campaña correctamente", urna.calcularPorcentajeVotosCandidato3( ) == porcentaje );
    }

    /**
     * Prueba 16 - Vaciar la urna de votación
     */
    public void testVaciarUrna( )
    {
        setupEscenario2( );

        urna.vaciarUrna( );

        assertTrue( "No inicializa el número de votos a 0 del candidato 1", urna.darCandidato1( ).darVotos( ) == 0 );
        assertTrue( "No inicializa el costo de campaña a 0 del candidato 1", urna.darCandidato1( ).darCostoCampanha( ) == 0 );

        assertTrue( "No inicializa el número de votos a 0 del candidato 2", urna.darCandidato2( ).darVotos( ) == 0 );
        assertTrue( "No inicializa el costo de campaña a 0 del candidato 2", urna.darCandidato2( ).darCostoCampanha( ) == 0 );

        assertTrue( "No inicializa el número de votos a 0 del candidato 3", urna.darCandidato3( ).darVotos( ) == 0 );
        assertTrue( "No inicializa el costo de campaña a 0 del candidato 3", urna.darCandidato3( ).darCostoCampanha( ) == 0 );

    }

}