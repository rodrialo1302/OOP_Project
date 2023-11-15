package es.uva.inf.poo.rodralo.mardano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fabricante.externo.tarjetas.TarjetaMonedero;


/**
 * Implementación de VendingMachine para la segunda entrega de Programación Orientada a Obejtos.
 * @author rodralo
 * @author mardano
 */
public class VendingMachine {

	private boolean enabled;
	private String id;
	private Map<String, ProductLine> productLines;
	private final String CARD_DISCOUNT_CREDENTIAL = "6Z1y00Nm31aA-571";  

	
	/**
	 * Construye un VendingSystem vacío (sin vendingMachines)
	 * @param enabled estado
	 * @param id identificador de la VendingMachine
	 * @assert.pre{@code id != null}
	 * @assert.pre{@code id} no puede ser un texto vacío
	 */ 
	public VendingMachine(boolean enabled, String id) {
		// Check nulls
		assert(id != null);

		this.setEnabled(enabled);

		// Check white-spaces
		assert(!id.trim().equals(""));

		this.id = id;

		// Initialize lines
		productLines = new HashMap<>();
	}
	
	/**
	 * Construye una VendingMachine formado por la lista de productLines
	 * @param enabled estado
	 * @param id identificador de la VendingMachine
	 * @param lines lista de productLines
	 * @assert.pre{@code id != null}
	 * @assert.pre{@code id} no puede ser un texto vacío
	 */
	public VendingMachine(boolean enabled, String id, List<ProductLine> lines) {
		this(enabled, id);
		// self-check in addLines method
		addNewProductLines(lines);
	}

	/**
	 * Añade varios ProductLine a la vendingMachine
	 * @param lines lista de productLine
	 * @assert.pre{@code lines != null}
	 * @assert.pre Cada ProductLine contenido en la lista {@code lines} debe ser distinto de null
	 * @assert.pre Cada ProductLine contenido en la lista {@code lines} no debe existir en la vendingMachine
	 */
	public void addNewProductLines(List<ProductLine> lines) {
		assert(lines != null);
		
		for(ProductLine line : lines ) {
			// require machines arent null
			assert(line != null);
			// require machines not included
			assert(!this.productLines.containsKey(line.getId()));
		}

		// fail if line is already included
		for (ProductLine line : lines)
			addNewProductLine(line);
	}
	
	/**
	 * Añade un productLine a la vendingMachine
	 * @param line productLine a añadir
	 * @assert.pre{@code line != null}
	 * @assert.pre La linea ({@code line}) no debe existir en la vendingMachine
	 */
	public void addNewProductLine(ProductLine line) {
		assert(line != null);
		// fail if line is already included
		assert(!productLines.containsKey(line.getId()));
		productLines.put(line.getId(), line);

	}
	
	/**
	 * Elimina varios productLines de la vendingMachine
	 * @param linesIds lista de productLine
	 * @assert.pre{@code linesIds != null}
	 * @assert.pre Cada ProductLine contenido en la lista {@code linesIds} debe ser distinto de null
	 * @assert.pre Cada ProductLine contenido en la lista {@code linesIds} debe existir en la vendingMachine
	 */
	public void deleteProductLines(List<String> linesIds) {
		assert(linesIds != null);
		
		for(String line : linesIds ) {
			// require machines arent null
			assert(line != null);
			// require machines not included
			assert(this.productLines.containsKey(line));
		}
		
		// fail if line is not included
		for (String line : linesIds)
			deleteProductLine(line);

	}

	/**
	 * Elimina un productLine de la vendingMachine
	 * @param line productLine a eliminar
	 * @assert.pre{@code line != null}
	 * @assert.pre La linea ({@code line}) debe existir en la vendingMachine
	 */
	public void deleteProductLine(String line) {
		assert(line != null);
		// fail if line is not included
		assert(productLines.containsKey(line));
		productLines.remove(line);

	}

	/**
	 * Obtiene un productLine a partir de su identificador
	 * @param lineIdentifier identificador del productLine
	 * @return productLine
	 * @assert.pre{@code lineIdentifier != null}
	 * @assert.pre El id ({@code lineIdentifier}) debe existir en la vendingMachine
	 */
	public ProductLine getProductLine(String lineIdentifier) {
		// line is contained
		assert(lineIdentifier != null);
		assert(productLines.containsKey(lineIdentifier));
		return productLines.get(lineIdentifier);
	}

	/**
	 * Obtiene una lista de todos los productLine dentro de esta VendingMachine
	 * @return lista de todos los productLine dentro de esta VendingMachine
	 */
	public List<ProductLine> getProductLines() {
		return new ArrayList<>(productLines.values());
	}

	/**
	 * Comprueba si alguna máquina tiene alguna línea vacía
	 * @return true si existe una máquina con línea vacía, false si no existe
	 */
	public boolean hasEmptyProductLines() {
		for (ProductLine line: getProductLines()) {
			if (line.getStock() == 0)
				return true;
		}
		return false;
	}

	/**
	 * Actualiza el stock de un producto
	 * @param lineIdentifier id del productLine
	 * @param stock valor del stock
	 * @assert.pre{@code lineIdentifier != null}
	 * @assert.pre El id ({@code lineIdentifier}) debe existir en la vendingMachine
	 * @assert.pre{@code stock >= 0}
	 */
	public void setStockProductLine(String lineIdentifier, int stock) {
		// line is contained
		assert(lineIdentifier != null);
		assert(productLines.containsKey(lineIdentifier));
		assert(stock >= 0);
		productLines.get(lineIdentifier).setStock(stock);
	}

	/**
	 * Obtiene el precio de un producto
	 * @param lineIdentifier id del productLine donde está el producto
	 * @return precio del producto
	 * @assert.pre{@code lineIdentifier != null}
	 * @assert.pre El id ({@code lineIdentifier}) debe existir en la vendingMachine
	 */
	public double getProductPrice(String lineIdentifier) {
		// line is contained
		assert(lineIdentifier != null);
		assert(productLines.containsKey(lineIdentifier));
		return productLines.get(lineIdentifier).getProduct().getPrice();
	}

	/**
	 * Comprar un producto con una tarjetaMonedero
	 * @param card tarjetaMonededro
	 * @param lineIdentifier id del productLine donde está el producto
	 * @assert.pre{@code card != null}
	 * @assert.pre{@code lineIdentifier != null}
	 * @assert.pre El id ({@code lineIdentifier}) debe existir en la vendingMachine
	 * @assert.pre El stock de la línea debe ser mayor o igual que 1
	 * @assert.pre el saldo de la tarjeta ({@code card}) debe ser mayor o igual que el precio del producto
	 */
	public void buyProduct(TarjetaMonedero card, String lineIdentifier) {
		assert(card != null);
		assert(lineIdentifier != null);
		
		assert(productLines.containsKey(lineIdentifier));

		ProductLine line = productLines.get(lineIdentifier);
		assert(line.getStock() >= 1); // 1+ product is needed
		assert(card.getSaldoActual() >= line.getProduct().getPrice()); // check balance

		// descontar saldo
		card.descontarDelSaldo(CARD_DISCOUNT_CREDENTIAL, line.getProduct().getPrice());

		// remove stock
		line.setStock(line.getStock() - 1);
	}

	/**
	 * Consulta el estado de la VendingMachine
	 * @return el estado de la máquina
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Consulta el identificador de la VendingMachine
	 * @return id de la VendingMachine
	 */
	public String getId() {
		return id;
	}

	/**
	 * Actualiza el estado de la VendingMachine
	 * @param enabled valor del estado
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
