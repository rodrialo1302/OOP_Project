package es.uva.inf.poo.rodralo.mardano;

import java.time.LocalDate;

/**
 * Implementación de Product para la segunda entrega de Programación Orientada a
 * Obejtos.
 * 
 * @author rodralo
 * @author mardano
 */
public class Product extends Vendible {
	private double price;
	private LocalDate bestBeforeDate;

	/**
	 * Construye un Producto formado por los datos pasados por argumento
	 * 
	 * @param nombre                 nombre del Product
	 * @param UPC                    UPC del Product
	 * @param precio                 precio del Product
	 * @param fechaConsumoPreferente Valor de la BestBeforeDate
	 */
	public Product(String nombre, String UPC, double precio, LocalDate fechaConsumoPreferente) {
		super(nombre, UPC);
		setPrice(precio);
		setUPC(UPC);
		setBestBeforeDate(fechaConsumoPreferente);
	}

	/**
	 * Consulta el UPC del Product
	 * 
	 * @return UPC
	 */
	public String getUPC() {
		return super.getId();
	}

	private void setUPC(String UPC) {
		assert (UPC != null);
		assert (!UPC.trim().equals(""));
		// m = suma de digitos multiplo de 10
		// s = suma de digitos
		// Se resta 48 para obtener el valor del carácter

		int cont, s = 0, m;

		for (cont = 0; cont < 11; cont++) {
			if (cont % 2 == 0) {
				s += (UPC.charAt(cont) - '0') * 3;
			} else {
				s += (UPC.charAt(cont) - '0');
			}
		}

		m = s;

		if (s % 10 != 0)// Si la suma no es multiplo de 10, se aproxima
			m = (m / 10) * 10 + 10;

		assert (Math.abs(m - s) == (UPC.charAt(11) - '0'));

		super.setId(UPC.toString());
	}

	/**
	 * Consulta el precio del Product
	 * 
	 * @return precio del product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Actualiza el precio del Product
	 * 
	 * @param precio valor del precio del Product
	 */
	public void setPrice(double precio) {
		assert (precio > 0);
		this.price = precio;
	}

	/**
	 * Obtiene la fecha de consumo preferente en formato LocalDate
	 * 
	 * @return fecha de consumo preferente
	 */
	public LocalDate getBestBeforeDate() {
		return bestBeforeDate;
	}

	/**
	 * Actualiza la fecha de consumo preferente
	 * 
	 * @param fechaConsumoPreferente valor de la nueva fecha
	 */
	public void setBestBeforeDate(LocalDate fechaConsumoPreferente) {
		assert (fechaConsumoPreferente != null);
		this.bestBeforeDate = fechaConsumoPreferente;
	}

}