package es.uva.inf.poo.rodralo.mardano;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


/**
 * @author mardano
 * @author rodralo
 *
 */
public class VendingSystemTest {
	
	Product p1;
	Product p2;
	
	ProductLine l1;
	ProductLine l2;
	ProductLine l3;
	ProductLine l4;
	ProductLine l5;

	VendingMachine vm;
	VendingMachine vm2;
	VendingMachine vm3;
	VendingMachine vm4;
	VendingMachine vm5;
	VendingMachine vm6;
	VendingMachine vm7;
	VendingMachine vm8;
	VendingMachine vm9;

	VendingCity vc1;
	VendingCity vc2;
	VendingCity vc3;
	
	
	VendingSystem vs;


	@Before
	public void setUp() {
		p1 = new Product("Product1", "153212179108", 2.50, LocalDate.of(2021, 9, 20));
		p2 = new Product("Product2", "100000012109", 2.50, LocalDate.of(2021, 9, 30));
	
		l1 = new ProductLine(p1, "linea1", 5);
		l2 = new ProductLine(p1, "linea2", 1);
		l3 = new ProductLine(p2, "linea3", 0);
		l4 = new ProductLine(p2, "linea4", 0);
		l5 = new ProductLine(p2, "linea5", 7);
	
		vm = new VendingMachine(true, "mac1", Arrays.asList(l1, l2, l3, l4));
		vm2 = new VendingMachine(true, "mac2", Arrays.asList(l1, l2, l5));
		vm3 = new VendingMachine(false, "mac3", Arrays.asList(l1, l3, l5));
		vm4 = new VendingMachine(true, "mac4", Arrays.asList(l1, l2, l3, l4));
		vm5 = new VendingMachine(true, "mac5", Arrays.asList(l1, l2, l5));
		vm6 = new VendingMachine(false, "mac6", Arrays.asList(l1, l3, l5));
		vm7 = new VendingMachine(true, "mac7", Arrays.asList(l1, l2, l3, l4));
		vm8 = new VendingMachine(true, "mac8", Arrays.asList(l1, l2, l5));
		vm9 = new VendingMachine(false, "mac9", Arrays.asList(l1, l3, l5));
		
		
		vc1= new VendingCity("Valladolid", 47,Arrays.asList(vm,vm2,vm3));
		vc2 = new VendingCity("Madrid", 28,Arrays.asList(vm4,vm5,vm6));
		vc3 = new VendingCity("Barcelona",8,Arrays.asList(vm7,vm8,vm9));
		
		vs = new VendingSystem(Arrays.asList(vc1,vc2,vc3));
	}
	
	@Test
	public void testVendingSystemConstructor() {
		assertNotNull(vs);
		assertEquals(vs.getNumberOfRegions(),3);
		assertTrue(vs.getVendingSystem().contains(vc1));
		assertTrue(vs.getVendingSystem().contains(vc2));
		assertTrue(vs.getVendingSystem().contains(vc3));
	}
	@Test(expected = AssertionError.class)
	public void testVendingSystemListConstructorMachineListNull() {
		new VendingSystem(null);
	}
	@Test
	public void testVendingSystemEmptyConstructor() {
		VendingCity vtemp = new VendingCity();
		assertNotNull(vtemp);
		assertEquals(vtemp.getVendingMachines().size(), 0);
	}
	@Test
	public void testAddNewVendingCities() {
		VendingCity vcnew = new VendingCity("regionTest",1, Arrays.asList(vm,vm2,vm3));
		VendingCity vcnew2 = new VendingCity("regionTest2",2, Arrays.asList(vm4,vm5,vm6));
		vs.addNewVendingCities(Arrays.asList(vcnew,vcnew2));
		assertTrue(vs.getVendingSystem().contains(vcnew));
		assertTrue(vs.getVendingSystem().contains(vcnew2));
		assertEquals(vs.getVendingSystem().size(), 5);
	}
	@Test  (expected = AssertionError.class)
	public void testAddNewVendingCitiesNullCities() {
		vs.addNewVendingCities(null);
	}
	
	@Test  (expected = AssertionError.class)
	public void testAddNewVendingCitiesContainsNullCity() {
		VendingCity vcnew = new VendingCity( "cityTest",1, Arrays.asList(vm3, vm4, vm5));
		vs.addNewVendingCities(Arrays.asList(vcnew,null));
	}
	
	@Test  (expected = AssertionError.class)
	public void testAddNewVendingCitiesAlreadyInSystem() {
		VendingCity vcnew = new VendingCity( "cityTest",1, Arrays.asList(vm3, vm4, vm5));
		vs.addNewVendingCities(Arrays.asList(vcnew,vc1));
	}
	@Test
	public void testAddNewVendingCity() {
		VendingCity vcnew = new VendingCity( "cityTest",1, Arrays.asList(vm, vm2, vm3));
		vs.addNewVendingCity(vcnew);
		assertTrue(vs.getVendingSystem().contains(vcnew));
		assertEquals(vs.getVendingSystem().size(), 4);
	}
	@Test (expected = AssertionError.class)
	public void testAddNewVendingCityNullCity() {
		vs.addNewVendingCity(null);
	}
	@Test (expected = AssertionError.class)
	public void testAddNewVendingCityAlreadyInSystem() {
		vs.addNewVendingCity(vc1);
	}
	@Test
	public void testDeleteVendingCities() {
		vs.deleteVendingCities(Arrays.asList(vc1,vc2));
		assertFalse(vs.getVendingSystem().contains(vc1));
		assertFalse(vs.getVendingSystem().contains(vc2));
		assertTrue(vs.getVendingSystem().contains(vc3));
	}
	@Test (expected = AssertionError.class)
	public void testDeleteVendingMachinesNullListMachines() {
		vs.deleteVendingCities(null);
	}
	@Test (expected = AssertionError.class)
	public void testDeleteVendingMachinesContainsNullMachine() {
		vs.deleteVendingCities(Arrays.asList(vc2,null));
	}
	@Test (expected = AssertionError.class)
	public void testDeleteVendingMachinesNotInSystem() {
		VendingCity vcnew = new VendingCity( "cityTest",1, Arrays.asList(vm, vm2, vm3));
		vs.deleteVendingCities(Arrays.asList(vc1,vc2,vcnew));
	}
	@Test
	public void testDeleteVendingCity() {
		vs.deleteVendingCity(vc1);
		assertFalse(vs.getVendingSystem().contains(vc1));
	}
	@Test (expected = AssertionError.class)
	public void testDeleteVendingMachineNullMachine() {
		vs.deleteVendingCity(null);
		
	}
	@Test (expected = AssertionError.class)
	public void testDeleteVendingCityNotInSystem() {
		VendingCity vcnew = new VendingCity( "cityTest",1, Arrays.asList(vm, vm2, vm3));
		vs.deleteVendingCity(vcnew);
	}
	@Test 
	public void testGetIdentifiers() {
		assertTrue(vs.getIdentifiers().contains(47));
		assertTrue(vs.getIdentifiers().contains(28));
		assertTrue(vs.getIdentifiers().contains(8));
	}
	@Test
	public void testGetVendingSystem() {
		assertTrue(vs.getVendingSystem().contains(vc1));
		assertTrue(vs.getVendingSystem().contains(vc2));
		assertTrue(vs.getVendingSystem().contains(vc3));
	}
	@Test
	public void testGetVendingCity() {
		assertTrue(vs.getVendingCity(47) == vc1);
		assertTrue(vs.getVendingCity(28) == vc2);
		assertTrue(vs.getVendingCity(8) == vc3);
	}
	
	@Test (expected = AssertionError.class)
	public void testGetVendingCityOfNegativeCode() {
		vs.getVendingCity(-1);
	}
	@Test (expected = AssertionError.class)
	public void testGetVendingCityOfNotContainedCode() {
		vs.getVendingCity(5);
	}
	@Test 
	public void testGetNumberOfVendingMachinesOfCity() {
		assertTrue(vs.getNumberOfVendingMachinesOfCity(47) == 2);
		assertTrue(vs.getNumberOfVendingMachinesOfCity(28) == 2);
		assertTrue(vs.getNumberOfVendingMachinesOfCity(8) == 2);
	}
	

	@Test (expected = AssertionError.class)
	public void testGetNumberOfVendingMachinesOfCityOfNegativeCode() {
		vs.getNumberOfVendingMachinesOfCity(-1);
	}
	@Test (expected = AssertionError.class)
	public void testGetNumberOfVendingMachinesOfCityOfNotContainedCode() {
		vs.getNumberOfVendingMachinesOfCity(5);
	}
	@Test 
	public void testGetVendingMachinesOfCity() {
		assertTrue(vs.getVendingMachinesofCity(47).contains(vm));
		assertTrue(vs.getVendingMachinesofCity(47).contains(vm2));
		assertTrue(vs.getVendingMachinesofCity(47).contains(vm3));
		assertTrue(vs.getVendingMachinesofCity(28).contains(vm4));
		assertTrue(vs.getVendingMachinesofCity(28).contains(vm5));
		assertTrue(vs.getVendingMachinesofCity(28).contains(vm6));
		assertTrue(vs.getVendingMachinesofCity(8).contains(vm7));
		assertTrue(vs.getVendingMachinesofCity(8).contains(vm8));
		assertTrue(vs.getVendingMachinesofCity(8).contains(vm9));
	}
	@Test (expected = AssertionError.class)
	public void testGetVendindMachinesOfCityofNontContainedCode() {
		vs.getVendingMachinesofCity(5);
	}
	@Test (expected = AssertionError.class)
	public void testGetVendindMachinesOfCityofNegativeCode() {
		vs.getVendingMachinesofCity(-1);
	}
	@Test
	public void testGetNumberOfRegions(){
		assertTrue(vs.getNumberOfRegions() == 3);
	}
	@Test
	public void testGetRegionNames() {
		assertTrue(vs.getRegionNames().contains("Valladolid"));
		assertTrue(vs.getRegionNames().contains("Madrid"));
		assertTrue(vs.getRegionNames().contains("Barcelona"));
	}
	@Test
	public void testGetNumbersOfMachinesInRegions() {
		assertTrue(vs.getNumberOfMachinesInRegions().get(47) == 2);
		assertTrue(vs.getNumberOfMachinesInRegions().get(28) == 2);
		assertTrue(vs.getNumberOfMachinesInRegions().get(8) == 2);
	}
}