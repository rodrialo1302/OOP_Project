package es.uva.inf.poo.rodralo.mardano;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rodralo
 * @author mardano
 *
 */
public class ProductLineTest {
	ProductLine l1;
	
	Vendible p1;
	Vendible p2;
	
	
	

	@Before
	public void setUp() throws Exception {
				
		p1 = new Product("Product1","153212179108",2.50, LocalDate.of(2021, 9, 20));
		p2 = new Product("Product2","100000012109",2.50, LocalDate.of(2021, 9, 30));
		
		l1 = new ProductLine(p1, "linea1", 5);
				
	}

	@Test
	public void testProductLine() {
		assertNotNull(l1);
	}
	
	
	@Test (expected = AssertionError.class)
	public void testProductLineNullId() {
		new ProductLine(p1, null, 5);
	}
	
	
	@Test (expected = AssertionError.class)
	public void testProductLineInvalidId() {
		new ProductLine(p1, "   ", 5);
	}
	
	
	
	@Test (expected = AssertionError.class)
	public void testProductLineNullProduct() {
		new ProductLine(null, "linea1", 5);
	}
	
	@Test (expected = AssertionError.class)
	public void testProductLineNegativeStock() {
		new ProductLine(null, "linea1", -5);
	}
	

	@Test
	public void testGetStock() {
		assertEquals(5, l1.getStock());
	}

	@Test
	public void testSetStock() {
		l1.setStock(10);
		assertEquals(10, l1.getStock());
	}
	
	
	@Test (expected = AssertionError.class)
	public void testSetStockNegative() {
		l1.setStock(-2);
	}
	
	@Test
	public void testGetProduct() {
		assertEquals(p1, l1.getProduct());
	}

	@Test
	public void testGetId() {
		assertEquals("linea1", l1.getId());
	}

}
