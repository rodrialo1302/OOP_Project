package es.uva.inf.poo.rodralo.mardano;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación de VendingCity para la segunda entrega de Programación Orientada a Obejtos.
 * @author rodralo
 * @author mardano
 */
public class VendingCity{

	private Map<String,VendingMachine> vendingMachines;
	private String region;
	private Integer regionId;
	
	/**
	 * Construye un VendingCity vacío (sin vendingMachines)
	 */ 
	public VendingCity() {
		// Initialize lines
		vendingMachines = new HashMap<>();
		
	}
	
	/**
	 * Construye un VendingCity  formado por la lista de vendingMachines pasadas por argumento,un string que indica provincia y un
	 * que indica el código de provincia.
	 * No acepta provincia vacia.
	 * No acepta codigos vacios
	 * No acepta codigos menores o iguales a 0
	 * @param vendingMachines lista de VendingMachine
	 * @param region string que indica provincia
	 * @param regionId región de la provincía
	 * @assert.pre{@code vendingMachines != null}
	 */
	public VendingCity(String region,Integer regionId,List<VendingMachine> vendingMachines) {
		this();
		assert(region != null);
		assert(!region.trim().equals(""));
		//Check white spaces
		this.region= region;
		assert(regionId != null);
		assert(regionId > 0);
		
		this.regionId = regionId;
		// self-check in addLines method

		addNewVendingMachines(vendingMachines);
		
	}
	/**
	 * Añade varias VendingMachine a la lista de VendingMachines gestionadas
	 * @param vendingMachines lista de VendingMachine
	 * @assert.pre{@code vendingMachines != null}
     * @assert.pre Cada vendingMachine contenido en la lista {@code vendingMachines} debe ser distinto de null
	 * @assert.pre Cada vendingMachine contenido en la lista {@code vendingMachines} no debe existir en el VendingSystem
	 */
	public void addNewVendingMachines(List<VendingMachine> vendingMachines) {
		assert(vendingMachines != null);
		
		for(VendingMachine vendingMachine : vendingMachines) {
			// fail if any machine is null
			assert(vendingMachine != null);
			// fail if any machine is already included
			assert(!this.vendingMachines.containsKey(vendingMachine.getId()));
		}
		
		
		// fail if machine is already included
		for (VendingMachine vm : vendingMachines)
			addNewVendingMachine(vm);
	}
	
	/**
	 * Añade una VendingMachine a la lista de VendingMachines gestionadas.
	 * @param vendingMachine VendingMachine a añadir
	 * @assert.pre{@code vendingMachine != null}
	 * @assert.pre La vendingMachine ({@code VendingMachine}) no debe existir en el vendingSystem
	 */
	public void addNewVendingMachine(VendingMachine vendingMachine) {
		assert(vendingMachine != null);
		// fail if machine is already included
		assert(!vendingMachines.containsKey(vendingMachine.getId()));
		vendingMachines.put(vendingMachine.getId(), vendingMachine);

	}
	/**
	 * ELimina varias VendingMachine a partir de sus ids
	 * @param vendingMachinesIds lista de VendingMachine
	 * @assert.pre{@code vendingMachinesIds != null}
	 * @assert.pre Cada identificador contenido en la lista {@code vendingMachinesIds} debe ser distinto de null
	 * @assert.pre Cada identificador contenido en la lista {@code vendingMachinesIds} debe existir en el VendingSystem
	 */
	public void deleteVendingMachines(List<String> vendingMachinesIds) {
		assert(vendingMachinesIds != null);
		
		// fail if any machine is null
		for(String vendingMachinesId : vendingMachinesIds) {
			assert(vendingMachinesId != null);
		}		
		// fail if any machine is not included
		for(String vendingMachinesId : vendingMachinesIds) {
			assert(vendingMachines.containsKey(vendingMachinesId));
		}
		// delete machines
		for (String vm : vendingMachinesIds)
			deleteVendingMachine(vm);

	}
	/**
	 * Borra una VendingMachine a partir de su id
	 * @param idVendingMachine VendingMachine a eliminar
	 * @assert.pre{@code vendingMachine != null}
	 * @assert.pre El id ({@code idVendingMachine}) debe existir en el vendingSystem
	 */
	public void deleteVendingMachine(String idVendingMachine) {
		assert(idVendingMachine != null);
		// fail if machine is not included
		assert(vendingMachines.containsKey(idVendingMachine));
		vendingMachines.remove(idVendingMachine);
	}
	
	/**
	 * Devuelve la lista de las VendingMachine gestionadas por este VendingSystem
	 * @return lista de las VendingMachine gestionadas por este VendingSystem
	 */
	public List<VendingMachine> getVendingMachines() {
		return new ArrayList<>(vendingMachines.values());
	}

	/**
	 * Devuelve el número de VendingMachines activadas
	 * @return número de VendingMachines activadas
	 */
	public long getNumberVendingMachinesEnabled() {
		long numberOfVendingMachinesEnabled = 0;
		for(VendingMachine vendingMachine : vendingMachines.values()) {
			if(vendingMachine.isEnabled()) {
				numberOfVendingMachinesEnabled += 1;
			}
		}
		return numberOfVendingMachinesEnabled;
	}
	
	/**
	 * Obtiene la lista de VendingMachines con alguna línea vacía
	 * @return lista de VendingMachine con alguna línea vacía
	 */
	public ArrayList<VendingMachine> getListMachinesWithEmptyLines() {
		ArrayList<VendingMachine> ListMachinesWithEmptyLines = new ArrayList<VendingMachine>();
		for(VendingMachine vendingMachine : vendingMachines.values()) {
			if(vendingMachine.hasEmptyProductLines()) {
				ListMachinesWithEmptyLines.add(vendingMachine);
			}
		}
		return ListMachinesWithEmptyLines;
	}
	/**
	 * Obtiene la region a la que pertenece la lista de VendingMachines
	 * @return region de VendingMachines como string
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * Obtiene el codigo de la region a la que pertenece la lista de VendingMachines
	 * @return codigo de region de VendingMachines como int
	 */
	public Integer getRegionId() {
		return regionId;
	}
}
