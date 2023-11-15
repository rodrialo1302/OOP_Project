package es.uva.inf.poo.rodralo.mardano;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * 
 * @author rodralo
 * @author mardano
 *
 */
public class VendingMachineTest {
	Product p1;
	Product p2;
	
	ProductLine l1;
	ProductLine l2;
	ProductLine l3;
	ProductLine l4;
	
	VendingMachine vm;
	
	@Before
	public void setUp() {
		 p1 = new Product("Product1","153212179108",2.50, LocalDate.of(2021, 9, 20));
		 p2 = new Product("Product2","100000012109",2.50, LocalDate.of(2021, 9, 30));
		 
		 l1 = new ProductLine(p1, "linea1", 5);
		 l2 = new ProductLine(p1, "linea2", 1);
		 l3 = new ProductLine(p2, "linea3", 0);
		 l4 = new ProductLine(p2, "linea4", 0);
		 
		 vm = new VendingMachine(true, "mac1", Arrays.asList(l1,l2,l3,l4));
	}

	@Test
	public void testVendingMachine() {
		assertNotNull(vm);
	}
	
	@Test (expected = AssertionError.class)
	public void testVendingMachineNullList() {
		new VendingMachine(true, null);
	}
	
	
	@Test
	public void testVendingMachineEmptyConstructor() {
		VendingMachine vtemp = new VendingMachine(true, "linea10");
		assertNotNull(vtemp);
		assertEquals(vtemp.getProductLines().size(), 0);
	}


	@Test
	public void testAddNewProductLines() {
		ProductLine testLine = new ProductLine(p1, "lineatest", 1);
		ProductLine testLine2 = new ProductLine(p2, "lineatest2", 10);
		vm.addNewProductLines(Arrays.asList(testLine,testLine2));
		assertTrue(vm.getProductLines().contains(testLine));
		assertTrue(vm.getProductLines().contains(testLine2));
	}
	
	
	@Test (expected = AssertionError.class)
	public void testAddNewProductLinesNullLine() {
		ProductLine testLine2 = new ProductLine(p2, "lineatest2", 10);
		vm.addNewProductLines(Arrays.asList(null,testLine2));
	}
	
	@Test  (expected = AssertionError.class)
	public void testAddNewProductLineContainsNullLine() {
		ProductLine testLine2 = new ProductLine(p2, "lineatest2", 10);
		vm.addNewProductLines(Arrays.asList(testLine2,null));
	}
	
	
	@Test  (expected = AssertionError.class)
	public void testAddNewProductLinesAlreadyInMachine() {
		ProductLine testLine2 = new ProductLine(p2, "lineatest2", 10);
		vm.addNewProductLines(Arrays.asList(testLine2,l1));
	}

	@Test
	public void testAddNewProductLine() {
		ProductLine testLine = new ProductLine(p1, "lineatest", 1);
		vm.addNewProductLine(testLine);
		assertTrue(vm.getProductLines().contains(testLine));
	}
	
	@Test (expected = AssertionError.class)
	public void testAddNewProductLineNullMachine() {
		vm.addNewProductLine(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testAddNewProductLineAlreadyInMachine() {
		vm.addNewProductLine(l1);
	}
	

	@Test
	public void testDeleteProductLines() {
		vm.deleteProductLines(Arrays.asList(l1.getId(),l2.getId()));
		assertFalse(vm.getProductLines().contains(l1));
		assertFalse(vm.getProductLines().contains(l2));
		
	}
	
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductLinesNullListMachines() {
		vm.deleteProductLines(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductLinesContainsNullMachine() {
		vm.deleteProductLines(Arrays.asList(l4.getId(),null));
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductLinesNotInMachine() {
		vm.deleteProductLines(Arrays.asList(l4.getId(),l3.getId(),"lineatest2"));
	}
	

	@Test
	public void testDeleteProductLine() {
		vm.deleteProductLine(l1.getId());
		assertFalse(vm.getProductLines().contains(l1));
		assertTrue(vm.getProductLines().contains(l2));
		assertTrue(vm.getProductLines().contains(l3));
		assertTrue(vm.getProductLines().contains(l4));
		
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductLineNull() {
		vm.deleteProductLine(null);
		
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductLineNotInMachine() {
		vm.deleteProductLine("lineatest2");
	}

	@Test
	public void testGetProductLine() {
		assertEquals(l1, vm.getProductLine("linea1"));
	}

	@Test
	public void testGetProductLines() {
		assertEquals(vm.getProductLines().size(), 4);
		assertTrue(vm.getProductLines().contains(l1));
		assertTrue(vm.getProductLines().contains(l2));
		assertTrue(vm.getProductLines().contains(l3));
		assertTrue(vm.getProductLines().contains(l4));
		
	}

	@Test
	public void testHasEmptyProductLines() {
		assertTrue(vm.hasEmptyProductLines());
	}

	@Test
	public void testSetStockProductLine() {
		vm.setStockProductLine("linea1", 10);
		assertEquals(10, vm.getProductLine("linea1").getStock());
	}
	
	@Test (expected = AssertionError.class)
	public void testSetStockProductLineNegStock() {
		vm.setStockProductLine("linea1", -5);
	}
	
	@Test (expected = AssertionError.class)
	public void testSetStockProductLineWrongId() {
		vm.setStockProductLine("linea5", 10);
	}
	
	
	@Test (expected = AssertionError.class)
	public void testSetStockProductLineNullId() {
		vm.setStockProductLine(null, 10);
	}
	
	
	@Test
	public void testGetProductPrice() {
		assertEquals(2.50, vm.getProductPrice("linea1"), 0.0001);
	}
	
	@Test (expected = AssertionError.class)
	public void testGetProductPriceWrongId() {
		assertEquals(5, vm.getProductPrice("linea5"), 0.0001);
	}
	
	@Test (expected = AssertionError.class)
	public void testGetProductPriceNullId() {
		assertEquals(5, vm.getProductPrice(null), 0.0001);
	}

	@Test
	public void testBuyProduct() {
		TarjetaMonedero card = new TarjetaMonedero("A156Bv09_1zXo894", 100.00);
		vm.buyProduct(card, "linea1");
		assertEquals(card.getSaldoActual(), (100.00-l1.getProduct().getPrice()), 0.0001); // saldo -= producto
		assertEquals(l1.getStock(), 4); // -1 stock
		
	}
	
	@Test (expected = AssertionError.class)
	public void testBuyProductSaldoInsuficiente() {
		TarjetaMonedero card = new TarjetaMonedero("A156Bv09_1zXo894", 1.95);
		vm.buyProduct(card, "linea1");
		
	}
	
	@Test (expected = AssertionError.class)
	public void testBuyProductNullCard() {
		vm.buyProduct(null, "linea1");
		
	}
	
	@Test (expected = AssertionError.class)
	public void testBuyProductNullId() {
		TarjetaMonedero card = new TarjetaMonedero("A156Bv09_1zXo894", 2.95);
		vm.buyProduct(card, null);
		
	}

	
	@Test (expected = AssertionError.class)
	public void testBuyProductWrongId() {
		TarjetaMonedero card = new TarjetaMonedero("A156Bv09_1zXo894", 2.95);
		vm.buyProduct(card, "linea5");
		
	}

	
	
	@Test (expected = AssertionError.class)
	public void testBuyProductNoStock() {
		TarjetaMonedero card = new TarjetaMonedero("A156Bv09_1zXo894", 2.95);
		vm.buyProduct(card, "linea4");
		
	}
	@Test
	public void testIsEnabled() {
		assertTrue(vm.isEnabled());
	}

	@Test
	public void testGetId() {
		assertEquals("mac1", vm.getId());
	}

	@Test
	public void testSetEnabled() {
		assertTrue(vm.isEnabled());
		vm.setEnabled(false);
		assertFalse(vm.isEnabled());
	}

}
