package uniandes.cupi2.videotienda.mundo;


/**
 * Esta clase representa una copia de la película que se encuentra en la videotienda 
 */ 
public class Copia {
	
	 	//-----------------------------------------------------------------
    	// Atributos
    	//-----------------------------------------------------------------

    /**
     * Código de la copia
     */
	private int codigo;
	
	/**
	 * Titulo de la copia de la película
	 */
	private String tituloPelicula;
	
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una nueva copia de una película.
     * @param elCodigo Código único de la copia. elCodigo > 0.
     * @param laPelicula Título de la película. laPelicula != null.
     */
	public Copia(int elCodigo, String laPelicula) {
	    if (elCodigo <= 0 || laPelicula == null) {
	        throw new IllegalArgumentException("Código debe ser positivo y título no puede ser null");
	    }
	    codigo = elCodigo;
	    tituloPelicula = laPelicula;
	}
	
	/**
	 * Retorna el código de la copia.
	 * @return Código de la copia
	 */
	public int darCodigo() {
		return codigo;
	}
	
	/**
	 * Retorna el título de la película.
	 * @return Título de la película.
	 */
	public String darTituloPelicula() {
		
		return tituloPelicula;
	}

    /**
     * Compara esta copia con otra para saber si son iguales (mismo código y misma película).
     * @param otra La otra copia a comparar. otra != null.
     * @return true si ambas copias son iguales, false en caso contrario.
     */
	public boolean esIgualA(Copia otra) {
		return otra != null && codigo == otra.darCodigo() &&
				tituloPelicula.equals(otra.darTituloPelicula());
	}
	
}
