/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Urna.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Carlos Andrés Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.elecciones.mundo;

/**
 * Es la una urna de votación con tres candidatos.
 */
public class Urna
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El candidato número 1 de la elecciones
     */
    private Candidato candidato1;

    /**
     * El candidato número 2 de la elecciones
     */
    private Candidato candidato2;

    /**
     * El candidato número 3 de la elecciones
     */
    private Candidato candidato3;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Inicializa los tres candidatos<br>
     * <b>post: </b> Se inicializaron los tres candidatos con los valores por parámetro nombre, apellido, partido político y edad.
     */
    public void inicializar( )
    {

        // Inicializa el candidato 1
        candidato1 = new Candidato( );
        candidato1.inicializar( "Felipe", "Pitti", "Independiente", 27 );

        // Inicializa el candidato 2
        candidato2 = new Candidato( );
        candidato2.inicializar( "Susanita", "Chirusi", "Revolucionario", 26 );

        // Inicializa el candidato 3
        candidato3 = new Candidato( );
        candidato3.inicializar( "Manolito", "Goreiro", "Tradicional", 26 );
    }

    /**
     * Devuelve el candidato 1
     * @return Candidato 1
     */
    public Candidato darCandidato1( )
    {
        return candidato1;
    }

    /**
     * Devuelve el candidato 2
     * @return Candidato 2
     */
    public Candidato darCandidato2( )
    {
        return candidato2;
    }

    /**
     * Devuelve el candidato 3
     * @return Candidato 3
     */
    public Candidato darCandidato3( )
    {
        return candidato3;
    }

    /**
     * Ingresa un voto influenciado por la televisión al candidato 1
     */
    public void ingresarVotoTelevisionCandidato1( )
    {
        candidato1.agregarVotoTelevision( );
    }

    /**
     * Ingresa un voto influenciado por radio al candidato 1
     */
    public void ingresarVotoRadioCandidato1( )
    {
        candidato1.agregarVotoRadio( );
    }

    /**
     * Ingresa un voto influenciado por Internet al candidato 1
     */
    public void ingresarVotoInternetCandidato1( )
    {
        candidato1.agregarVotoInternet( );
    }

    /**
     * Ingresa un voto influenciado por la televisión al candidato 2
     */
    public void ingresarVotoTelevisionCandidato2( )
    {
        candidato2.agregarVotoTelevision( );
    }

    /**
     * Ingresa un voto influenciado por radio al candidato 2
     */
    public void ingresarVotoRadioCandidato2( )
    {
        candidato2.agregarVotoRadio( );
    }

    /**
     * Ingresa un voto influenciado por Internet al candidato 2
     */
    public void ingresarVotoInternetCandidato2( )
    {
        candidato2.agregarVotoInternet( );
    }

    /**
     * Ingresa un voto influenciado por la televisión al candidato 3
     */
    public void ingresarVotoTelevisionCandidato3( )
    {
        candidato3.agregarVotoTelevision( );
    }

    /**
     * Ingresa un voto influenciado por radio al candidato 3
     */
    public void ingresarVotoRadioCandidato3( )
    {
        candidato3.agregarVotoRadio( );
    }

    /**
     * Ingresa un voto influenciado por Internet al candidato 3
     */
    public void ingresarVotoInternetCandidato3( )
    {
        candidato3.agregarVotoInternet( );
    }

    /**
     * Devuelve el total de votos en la urna
     * @return La sumatoria de los votos de los tres candidatos
     */
    public int calcularTotalVotos( )
    {
        return candidato1.darVotos( ) + candidato2.darVotos( ) + candidato3.darVotos( );
    }

    /**
     * Devuelve el costo promedio de campaña de los candidatos
     * @return La razón de la sumatoria de los costos de campaña de los candidatos y el número total de candidatos.
     */
    public double calcularCostoPromedioCampanha( )
    {

        double total;
        double promedio;

        total = candidato1.darCostoCampanha( ) + candidato2.darCostoCampanha( ) + candidato3.darCostoCampanha( );
        promedio = total / 3;

        return promedio;

    }

    /**
     * Devuelve el porcentaje de votos obtenidos por el candidato 1
     * @return porcentaje de votos obtenidos por el candidato 1
     */
    public double calcularPorcentajeVotosCandidato1( )
    {

        double numVotosCandidato1;
        double votosTotales;

        double porcentaje;

        numVotosCandidato1 = candidato1.darVotos( );
        votosTotales = calcularTotalVotos( );

        porcentaje = numVotosCandidato1 / votosTotales * 100;

        return porcentaje;
    }

    /**
     * Devuelve el porcentaje de votos obtenidos por el candidato 2
     * @return porcentaje de votos obtenidos por el candidato 2
     */
    public double calcularPorcentajeVotosCandidato2( )
    {

        double numVotosCandidato2;
        double votosTotales;

        double porcentaje;

        numVotosCandidato2 = candidato2.darVotos( );
        votosTotales = calcularTotalVotos( );

        porcentaje = numVotosCandidato2 / votosTotales * 100;

        return porcentaje;
    }

    /**
     * Devuelve el porcentaje de votos obtenidos por el candidato 3
     * @return porcentaje de votos obtenidos por el candidato 3
     */
    public double calcularPorcentajeVotosCandidato3( )
    {

        double numVotosCandidato3;
        double votosTotales;

        double porcentaje;

        numVotosCandidato3 = candidato3.darVotos( );
        votosTotales = calcularTotalVotos( );

        porcentaje = numVotosCandidato3 / votosTotales * 100;

        return porcentaje;
    }

    /**
     * Restaura la urna al estado inicial, todos los candidatos quedan sin votos y costo de campaña en 0
     */
    public void vaciarUrna( )
    {

        // Reiniciar candidato 1
        candidato1.reiniciarConteoVotos( );
        candidato1.reiniciarCostoCampanha( );

        // Reiniciar candidato 2
        candidato2.reiniciarConteoVotos( );
        candidato2.reiniciarCostoCampanha( );

        // Reiniciar candidato 3
        candidato3.reiniciarConteoVotos( );
        candidato3.reiniciarCostoCampanha( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}