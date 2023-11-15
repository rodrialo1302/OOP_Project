package es.uva.inf.poo.rodralo.mardano;

/**
 * Implementación de Pack para la segunda entrega de Programación Orientada a
 * Obejtos.
 * 
 * @author rodralo
 * @author mardano
 */

public class Pack extends Vendible {
	private Product[] pack;

	/**
	 * Construye un Pack formado por los datos pasados por argumento
	 * 
	 * @param nuevoPack     Pack a añadir
	 * @param identificador ID del Pack
	 * @param nombre        Nombre del Pack
	 * @assert.pre{@code nuevoPack != null}
	 * @assert.pre{@code nuevoPack} tiene que tener 2 elementos o más.
	 * @assert.pre{@code nombre != null}
	 * @assert.pre{@code nombre} no puede ser un String vacío
	 * @assert.pre{@code identificador != null}
	 * @assert.pre{@code identificador} no puede ser un String vacío
	 */
	public Pack(Product[] nuevoPack, String identificador, String nombre) {
		super(nombre, identificador);
		assert (nuevoPack != null);
		assert (nuevoPack.length >= 2);
		setFirstPack(nuevoPack);

	}

	private void setFirstPack(Product[] nuevoPack) {
		boolean repeatedElement = false;
		for (int i = 0; i < nuevoPack.length; i++) {
			for (int j = i + 1; j < nuevoPack.length; j++) {
				if (nuevoPack[i] == nuevoPack[j])
					repeatedElement = true;
				if (nuevoPack[i].getUPC() == nuevoPack[j].getUPC())
					repeatedElement = true;
			}
		}

		assert (repeatedElement == false);

		this.pack = nuevoPack;
	}

	/**
	 * Consulta los productos dentro del Pack
	 * @return array de productos
	 */
	public Product[] getPack() {
		return pack;
	}

	/**
	 * Consulta el precio del Pack
	 * 
	 * @return precio del Pack
	 */
	public double getPrice() {
		double price = 0.0;
		for (Product x : this.pack) {
			price += x.getPrice();
		}
		price *= 0.8;
		return price;
	}

	/**
	 * Consulta el tamaño del Pack
	 * 
	 * @return tamaño del Pack
	 */
	public int getPackSize() {
		int size = this.pack.length;
		return size;
	}

	/**
	 * Comprueba si un producto dado está dentro del Pack
	 * 
	 * @param upc UPC del producto a comprobar
	 * @return true si está dentro del pack, false si no está
	 */
	public boolean isInPack(String upc) {
		assert (upc != null);
		assert (!upc.trim().equals(""));
		boolean found = false;
		int index = findProduct(upc);
		if (index != -1)
			found = true;
		return found;
	}

	/**
	 * Busca la posición de un producto dentro del Pack
	 * 
	 * @param upc UPC del producto a buscar
	 * @return index Índice del producto dentro del pack, -1 si no está dentro
	 */
	public int findProduct(String upc) {
		assert (upc != null);
		assert (!upc.trim().equals(""));
		int index = -1;
		for (int i = 0; i < pack.length; i++) {
			if (pack[i].getUPC() == upc)
				index = i;
		}

		return index;

	}

	/**
	 * Añade un producto al Pack
	 * 
	 * @param producto Producto a añadir
	 * @assert.pre{@code producto != null}
	 * @assert.pre{@code producto} no puede estar dentro del pack
	 */
	public void addProduct(Product producto) {
		assert (producto != null);
		boolean found = false;
		for (Product x : pack) {
			if (x == producto)
				found = true;
		}
		assert (found == false);
		Product[] newPack = new Product[this.pack.length + 1];

		for (int i = 0; i < this.pack.length; i++) {
			newPack[i] = this.pack[i];
		}

		newPack[newPack.length - 1] = producto;
		this.pack = newPack;
	}

	/**
	 * Elimina un producto del Pack si hay más de 2 dentro del mismo.
	 * 
	 * @param upc UPC del producto a eliminar
	 * @assert.pre{@code upc} no puede estar dentro del pack
	 */
	public void deleteProduct(String upc) {
		assert (pack.length > 2);
		assert (upc != null);
		assert (!upc.trim().equals(""));
		Product[] newPack = new Product[this.pack.length - 1];
		int index = findProduct(upc);
		assert (index != -1);
		pack[index] = null;
		int newPackIndex = 0;
		for (int i = 0; i < pack.length; i++) {
			if (pack[i] != null) {
				newPack[newPackIndex] = pack[i];
				newPackIndex++;
			}
		}

		this.pack = newPack;

	}

}
