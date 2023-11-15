package es.uva.inf.poo.rodralo.mardano;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class VendibleTest {	
	Product[] pr1;
	
	Product p1;
	Product p2;
	
	Vendible v1;
	Vendible v2;
	@Before
	public void setUp() throws Exception {
		v1 = new Product("Product1","153212179108",2.5, LocalDate.of(2021, 12, 22));
		p1 = new Product("Product1","153212179108",2.5, LocalDate.of(2021, 12, 22));
		p2 = new Product("Product2","100000012109",2.5, LocalDate.of(2021, 12, 22));
		Product[] pr1 = {p1,p2};
		v2 = new Pack(pr1,"0", "Pack1");
	}

	@Test
	public void testVendible() {
		assertNotNull(v1);
	}

	@Test 
	public void testGetPriceProduct() {
		assertTrue(2.5 == v1.getPrice());
	}
	
	@Test
	public void testGetPricePack() {
		assertTrue(4.0 == v2.getPrice());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Pack1", v2.getName());
	}
	
	@Test
	public void testGetId() {
		assertEquals("153212179108", v1.getId());
	}
	
	@Test
	public void testSetName() {
		v1.setName("setNameTest");
		assertEquals("setNameTest", v1.getName());
	}
	
	@Test (expected = AssertionError.class)
	public void testSetNameNullName() {
		v1.setName(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testSetNameBlankName() {
		v1.setName(" ");
	}
	
	@Test
	public void testSetId() {
		v1.setId("setIdTest");
		assertEquals("setIdTest", v1.getId());
	}
	
	@Test (expected = AssertionError.class)
	public void testSetIdNullId() {
		v1.setId(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testSetIdBlankId() {
		v1.setId(" ");
	}
}
