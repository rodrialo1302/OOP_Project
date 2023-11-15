package es.uva.inf.poo.rodralo.mardano;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
/**
 * Implementacion de VendingSystem para la segunda entrega de Programacion Orientada a Objetos.
 * @author mardano
 * @author rodralo
 */
public class VendingSystem {
	
	private ArrayList<VendingCity> vendingSystem;

	/**
	 * Construye un VendingSystem vacio (sin VendingCitys)
	 */
	public VendingSystem() {
		vendingSystem = new ArrayList<>();
	}
	/**Construye un VendingSystem formado por la lista de VendingCities pasadas por argumento
	 * @param vendingCities vendingCiies a añadir
	 * @assert.pre{@code vendingCities != null}
	 */
	public VendingSystem(List<VendingCity> vendingCities) {
		this();
		addNewVendingCities(vendingCities);
	}
	/**
	 * Devuelve una lista que contiene todos los codigos de region de las VendingCities en que estan en un 
	 * momento dado en VendinSystem
	 * @return lista de codigos de las VendingCity actuales
	 */
	
	public ArrayList<Integer> getIdentifiers(){
		ArrayList<Integer> identifiersList = new ArrayList<>();
		for(VendingCity vendingcity : vendingSystem) {
			identifiersList.add(vendingcity.getRegionId());
		}
		return identifiersList;
	}
	/**
	 * Devuelve la lista de VendingCitys sobre la que trabajamos
	 * @return lista de VendingCitys
	 */
	public ArrayList<VendingCity> getVendingSystem(){
		return vendingSystem;
	}
	/**
	 * Devuelve la VendingCity que contiene el codigo de region especifico
	 * @param VendingCityRegionId id de la región a comprobar
	 * @return objeto VendingCity con el codigo de region especifico
	 */
	public VendingCity getVendingCity(Integer VendingCityRegionId) {
		assert(VendingCityRegionId != null);
		assert(VendingCityRegionId > 0);
		assert(getIdentifiers().contains(VendingCityRegionId));
		for(VendingCity vendingcity : vendingSystem) {
			if(vendingcity.getRegionId() == VendingCityRegionId) {
				return vendingcity;
			}
		}
		return null;
	}
	/**
	 * Añade varias VendingCities a la lista de VendingCities gestionadas
	 * @param vendingCities lista de VendingCities
	 * @assert.pre{@code vendingCities  != null}
     * @assert.pre Cada vendingCity contenido en la lista {@code vendingMachines} debe ser distinto de null
	 * @assert.pre Cada vendingCity contenido en la lista {@code vendingMachines} no debe existir en el VendingSystem
	 */
	public void addNewVendingCities(List<VendingCity> vendingCities) {
		assert(vendingCities != null);
		//Fallo si se añaden VendingCities nulas
		for(VendingCity vendingcity : vendingCities) {
			assert(vendingcity != null);
		}
		//Fallo si se añaden VendingCities iguales
		for(VendingCity vendingcity : vendingCities) {
			assert(!getVendingSystem().contains(vendingcity));
		}
		//Fallo si se añaden VendingCities con el mismo identificador
		for(VendingCity vendingcity : vendingCities) {
			assert(!getIdentifiers().contains(vendingcity.getRegionId()));
		}
		for (VendingCity vendingcity :vendingCities) {
			addNewVendingCity(vendingcity);
		}
	}
	/**Añade una VendingCity a la lista de VendingCities gestionadas.
	 * @param newVendingCity VendingCity a añadir
	 * @assert.pre{@code vendingCity != null}
	 * @assert.pre La vendingCity ({@code VendingCity}) no debe existir en el vendingSystem
	 */
	public void addNewVendingCity(VendingCity newVendingCity) {
		assert(newVendingCity != null);
		assert(!getIdentifiers().contains(newVendingCity.getRegionId()));
		
		vendingSystem.add(newVendingCity);
	}
	/**
	 * Elimina varias VendingCity de la lista de VendingCities gestionadas
	 * @param vendingCities lista de VendingCities
	 * @assert.pre{@code vendingCities  != null}
     * @assert.pre Cada vendingCity contenido en la lista {@code vendingMachines} debe ser distinto de null
	 * @assert.pre Cada vendingCity contenido en la lista {@code vendingMachines}  debe existir en el VendingSystem
	 */
	public void deleteVendingCities(List<VendingCity> vendingCities) {
		assert(vendingCities != null);
		
		// Check
		for(VendingCity vendingcity : vendingCities) {
			assert(vendingcity != null);
			assert(getIdentifiers().contains(vendingcity.getRegionId()));
		}
		
		// Delete
		for(VendingCity vendingcity : vendingCities) {
			deleteVendingCity(vendingcity);
		}
	}
	/**Elimina una VendingCity a la lista de VendingCities gestionadas.
	 * @param oldVendingCity VendingCity a eliminar
	 * @assert.pre{@code vendingCity != null}
	 * @assert.pre La vendingCity ({@code VendingCity}) debe existir en el vendingSystem
	 */
	
	public void deleteVendingCity(VendingCity oldVendingCity) {
		assert(oldVendingCity != null);
		assert(getIdentifiers().contains(oldVendingCity.getRegionId()));
		vendingSystem.remove(oldVendingCity);
	}
	/**
	 * Devuelve el numero de maquinas que gestiona una VendingCity a partir de su codigo de region
	 * @param VendingCityRegionId el codigo de region de una VendingCity
	 * @return numero de maquinas que gestiona
	 */
	public long getNumberOfVendingMachinesOfCity(Integer VendingCityRegionId) {
		assert(VendingCityRegionId != null);
		assert(VendingCityRegionId > 0);
		assert(getIdentifiers().contains(VendingCityRegionId));
		return getVendingCity(VendingCityRegionId).getNumberVendingMachinesEnabled();
	}
	/**
	 * Devuelve una lista de las maquinas que gestiona una VendingCity a partir de su codigo de region
	 * @param VendingCityRegionId el codigo de region de una VendingCity
	 * @return lista de maquinas que gestiona
	 */
	public List<VendingMachine>  getVendingMachinesofCity(Integer VendingCityRegionId){
		assert(VendingCityRegionId != null);
		assert(VendingCityRegionId > 0);
		assert(getIdentifiers().contains(VendingCityRegionId));
		return getVendingCity(VendingCityRegionId).getVendingMachines();
	}

	/**
	 * Devuelve el numero de VendingCities que gestiona VendingSystem
	 * @return numerero de VendingCities
	 */
	public long getNumberOfRegions() {
		return vendingSystem.size();
	}
	/**
	 * Devuelve una lista con todos los nombres de provincia que tienen una VendingCity
	 * @return lista con nombres de provincias
	 */
	public ArrayList<String> getRegionNames(){
		ArrayList<String> RegionNames = new ArrayList<>();
		for(VendingCity vendingcity : vendingSystem) {
			RegionNames.add(vendingcity.getRegion());
		}
		return RegionNames;
	}
	/**
	 * Devuelve una lista con el numero de VendingMachines que se gestionan en cada VendingCity
	 * @return Mapa K,V -> siendo K (clave) regionId y V (valor) número de máqs
	 * 
	 */
	public Map<Integer,Integer> getNumberOfMachinesInRegions(){
		Map<Integer,Integer> regionMachines = new TreeMap<>();
		for(VendingCity vendingcity : vendingSystem) {
			regionMachines.put(vendingcity.getRegionId(), (int) vendingcity.getNumberVendingMachinesEnabled());
		}
		return regionMachines;
	}
	
}
