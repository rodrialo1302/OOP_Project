package es.uva.inf.poo.rodralo.mardano;


/**
 * Clase abstracta Vendible para la segunda entrega de Programación Orientada a
 * Obejtos.
 * 
 * @author rodralo
 * @author mardano
 */
public abstract class Vendible {

	private String id;
	private String name;
	
	/**
	 * Construye un Vendible formado por los datos pasados por argumento
	 * 
	 * @param name                   Nombre del Vendible
	 * @param id                     ID del Vendible
	 * @assert.pre{@code name != null}
	 * @assert.pre{@code name} no puede ser un String vacío
	 * @assert.pre{@code id != null}
	 * @assert.pre{@code id} no puede ser un String vacío
	 */
	public Vendible(String name, String id) {
		assert(name != null);
		assert(!name.trim().equals(""));
		assert(id != null);
		assert(!id.trim().equals(""));
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Consulta el precio del Vendible
	 * @return precio el vendible
	 */
	public abstract double getPrice();
	
	/**
	 * Obtiene el nombre del Vendible
	 * @return nombre del Vendible
	 */
	public final String getName() {
		return name;
	}
	
	
	/**
	 * Obtiene el identifiacdor del Vendible
	 * @return identificador del Vendible
	 */
	public final String getId() {
		return id;
	}
	
	
	/**
	 * Actualiza el nombre del Vendible
	 * @param nombre nombre del Vendible
	 * @assert.pre{@code nombre != null}
	 * @assert.pre{@code nombre} no puede ser un String vacío
	 */
	public void setName(String nombre) {
		assert(nombre!=null);
		assert(!nombre.trim().equals(""));
		this.name = nombre;
	}

	/**
	 * Actualiza el identificador del Vendible
	 * @param id identificador nuevo del Vendible
	 * @assert.pre{@code id != null}
	 * @assert.pre{@code id} no puede ser un String vacío
	 */
	public void setId(String id) {
		assert(id!=null);
		assert(!id.trim().equals(""));
		this.id = id;
	}
	
}
