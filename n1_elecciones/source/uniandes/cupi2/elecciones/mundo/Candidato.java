/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Candidato.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Carlos Andr�s Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.elecciones.mundo;

/**
 * Candidato de la elecci�n.
 */

public class Candidato
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del candidato.
     */
    private String nombre;

    /**
     * Apellido del candidato.
     */
    private String apellido;

    /**
     * Partido pol�tico del candidato.
     */
    private String partidoPolitico;

    /**
     * Edad del candidato.
     */
    private int edad;

    /**
     * Costo de campa�a del candidato.
     */
    private double costoCampanha;

    /**
     * N�mero de votos del candidato.
     */
    private int votos;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa los datos del candidato con los valores que vienen por par�metro. <br>
     * <b>post: </b> El costo de campa�a se inicializ� en cero<br>
     * El n�mero de votos se inicializ� en cero. <br>
     * @param nNombre Nombre del candidato
     * @param nApellido Apellido del Candidato
     * @param nPartidoPolitico Partido pol�tico del candidato
     * @param nEdad Edad del candidato
     */

    public void inicializar( String nNombre, String nApellido, String nPartidoPolitico, int nEdad )
    {
        nombre = nNombre;
        apellido = nApellido;
        partidoPolitico = nPartidoPolitico;
        edad = nEdad;
        costoCampanha = 0;
        votos = 0;
    }

    /**
     * Devuelve el nombre del candidato.
     * @return nombre del candidato.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Devuelve el apellido del candidato.
     * @return apellido del candidato.
     */
    public String darApellido( )
    {
        return apellido;
    }

    /**
     * Devuelve el partido pol�tico del candidato.
     * @return partido pol�tico del candidato.
     */
    public String darPartidoPolitico( )
    {
        return partidoPolitico;
    }

    /**
     * Devuelve la edad del candidato.
     * @return edad del candidato.
     */
    public int darEdad( )
    {
        return edad;
    }

    /**
     * Devuelve el costo de campa�a del candidato.
     * @return costo de campa�a del candidato.
     */
    public double darCostoCampanha( )
    {
        return costoCampanha;
    }

    /**
     * Devuelve el n�mero de votos del candidato.
     * @return n�mero de votos obtenidos.
     */
    public int darVotos( )
    {
        return votos;
    }

    /**
     * Ingresa un voto al candidato.<br>
     * <b>post: </b> Se modific� el n�mero de votos aument�ndose el existente en 1.
     */
    private void ingresarUnVoto( )
    {
        votos = votos + 1;
    }

    /**
     * Adiciona un voto influenciado por la televisi�n <br<
     * <b>post: </b> Se adiciona al costo de la campa�a la suma de $1000 y se incrementa el n�mero de votos en 1.
     */
    public void agregarVotoTelevision( )
    {
        costoCampanha = costoCampanha + 1000;
        ingresarUnVoto( );
    }

    /**
     * Adiciona un voto influenciado por la radio <br>
     * <b>post: </b> Se adiciona al costo de la campa�a la suma de $500 y se incrementa el n�mero de votos en 1.
     * 
     */
    public void agregarVotoRadio( )
    {
        costoCampanha = costoCampanha + 500;
        ingresarUnVoto( );
    }

    /**
     * Adiciona un voto influenciado por Internet <br>
     * <b>post: </b> Se adiciona al costo de la campa�a la suma de $100 y se incrementa el n�mero de votos en 1.
     */
    public void agregarVotoInternet( )
    {
        costoCampanha = costoCampanha + 100;
        ingresarUnVoto( );
    }

    /**
     * Se reinicia el conteo de votos <b>post: </b> votos = 0.
     */
    public void reiniciarConteoVotos( )
    {
        votos = 0;
    }

    /**
     * Se reinicia el costo de campa�a <b>post: </b> costoCampanha = 0.
     */
    public void reiniciarCostoCampanha( )
    {
        costoCampanha = 0;
    }
}
