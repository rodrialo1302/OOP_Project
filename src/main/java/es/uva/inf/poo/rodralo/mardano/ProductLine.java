package es.uva.inf.poo.rodralo.mardano;


/**
 * Implementación de ProductLine para la segunda entrega de Programación Orientada a Obejtos.
 * @author rodralo
 * @author mardano
 */
public class ProductLine {
	private Vendible product;
	private String id;
	private int stock;
	
	/**
	 * Construye un ProductLine formado por la lista de vendibles pasados por argumento
	 * @param product lista de vendibles
	 * @param id identificador del ProductLine
	 * @param stock valor del stock del ProductLine
	 * @assert.pre{@code vendible != null}
	 * @assert.pre{@code id != null}
	 * @assert.pre{@code !id.trim().equals("")}
	 */
	public ProductLine(Vendible product, String id, int stock) {
		// Check nulls
		assert(product != null);
		assert(id != null);
		// Check white-spaces
		assert(!id.trim().equals(""));
		
		this.product = product;
		this.id = id;
		setStock(stock);
	}

	/**
	 * Consulta el stock del ProductLine
	 * @return stock del ProductLine
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * Actualiza el stock del ProductLine
	 * @param stock valor del stock
	 * @assert.pre{@code stock >= 0}
	 */
	public void setStock(int stock) {
		assert(stock >= 0);
		this.stock = stock;
	}
	
	/**
	 * Obtiene el producto gestionado por el ProductLine
	 * @return product
	 */
	public Vendible getProduct() {
		return product;
	}
	
	/**
	 * Obtiene el id del ProductLine
	 * @return identificador del ProductLine
	 */
	public String getId() {
		return id;
	}
}
