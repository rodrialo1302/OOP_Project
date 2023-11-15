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
public class PackTest {
	Pack pk1;
	Pack pk2;
	
	Product[] pr1;
	Product[] pr2;
	
	Product p1;
	Product p2;
	Product p3;

	
	@Before
	public void setUp() throws Exception {
		p1 = new Product("Product1","153212179108",2.5, LocalDate.of(2021, 12, 22));
		p2 = new Product("Product2","100000012109",2.5, LocalDate.of(2021, 12, 22));
		p3 = new Product("Product3","036000291452",5.0, LocalDate.of(2021, 12, 22));
		
		Product[] pr1 = {p1,p2,p3};
		Product[] pr2 = {p1,p2};
		
		pk1 = new Pack(pr1,"0", "Pack1");
		pk2 = new Pack(pr2, "1", "Pack2");
	}

	@Test
	public void testPack() {
		assertNotNull(pk1);
	}
	
	@Test (expected = AssertionError.class)
	public void testPackNullProducts() {
		pk1 = new Pack(null,"0", "Pack1");
	}

	
	@Test (expected = AssertionError.class)
	public void testPackNullId() {
		new Pack(pr1,null, "Pack1");
	}

	@Test (expected = AssertionError.class)
	public void testPackBlankId() {
		new Pack(pr1,"          ","P1");
	}
	
	@Test (expected = AssertionError.class)
	public void testPackNullName() {
		new Pack(pr1,"0",null);
	}
	
	@Test (expected = AssertionError.class)
	public void testPackBlankName() {
		new Pack(pr1,"0","        ");
	}


	@Test (expected = AssertionError.class)
	public void testPackOneProduct() {
		Product[] pr3 = {p1};
		pk1 = new Pack(pr3,"0", "Pack1");
	}

	@Test (expected = AssertionError.class)
	public void testPackRepeatedProducts() {
		Product[] pr3 = {p1,p1};
		pk1 = new Pack(pr3,"0", "Pack1");
	}
	
	@Test
	public void testGetPack() {
		Product[] pr1 = {p1,p2,p3};
		assertArrayEquals(pr1,pk1.getPack());
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(4.0, pk2.getPrice(), 0.001);
	}
	
	@Test
	public void testGetPackSize() {
		assertEquals(3, pk1.getPackSize());
		assertEquals(2, pk2.getPackSize());
	}
	
	@Test
	public void testIsInPack() {
		assertTrue(pk1.isInPack("153212179108"));
	}
	
	@Test (expected = AssertionError.class)
	public void testIsInPackNullUpc() {
		pk1.isInPack(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testIsInPackBlankUpc() {
		pk1.isInPack("   ");
	}
	
	@Test
	public void testIsInPackNotInPack() {
		assertFalse(pk2.isInPack("036000291452"));
	}
	
	@Test
	public void testFindProduct() {
		assertEquals(1,pk1.findProduct("100000012109"));
	}
	
	@Test
	public void testFindProductNotInPack() {
		assertEquals(-1,pk2.findProduct("036000291452"));
	}
	
	@Test (expected = AssertionError.class)
	public void testFindProductkNullUpc() {
		pk1.findProduct(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testFindProductkBlankUpc() {
		pk1.findProduct("");
	}
	
	@Test
	public void testAddProduct() {
		pk2.addProduct(p3);
		assertEquals(p3, pk2.getPack()[2]);
	}
	
	@Test (expected = AssertionError.class)
	public void testAddProductNullProduct() {
		pk2.addProduct(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testAddProductRepeatedProduct() {
		pk2.addProduct(p1);
	}
	
	@Test
	public void testDeleteProduct() {
		pk1.deleteProduct("036000291452");
		assertEquals(2, pk1.getPackSize());
		assertEquals(p1, pk1.getPack()[0]);
		assertEquals(p2, pk1.getPack()[1]);
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductNotEnoughSize() {
		pk2.deleteProduct("153212179108");
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductNullUpc() {
		pk2.deleteProduct(null);
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductBlankUpc() {
		pk2.deleteProduct("            ");
	}
	
	@Test (expected = AssertionError.class)
	public void testDeleteProductNotInPack() {
		pk1.deleteProduct("123456789098");
	}
}

