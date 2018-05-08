package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void testAddPurchase() throws Exception{

		Product product = new Product();
		product.setProdNo(10045);
		
		User user = new User();
		user.setUserId("user01");
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("SCOTT");
		purchase.setReceiverPhone("01010101010");
		purchase.setDivyAddr("½Åµµ¸²");
		purchase.setDivyRequest("»¡¸®»¡¸®");
		purchase.setDivyDate("20180426");
		
		purchaseService.addPurchase(purchase);
		
		System.out.println(purchase);
		purchase = purchaseService.getPurchase(10000);
		
		System.out.println(purchase);
		
		Assert.assertEquals(10000, purchase.getTranNo());
		//Assert.assertEquals(10045, purchase.getPurchaseProd());
		//Assert.assertEquals("user01", purchase.getBuyer());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("SCOTT", purchase.getReceiverName());
		Assert.assertEquals("01010101010", purchase.getReceiverPhone());
		Assert.assertEquals("½Åµµ¸²", purchase.getDivyAddr());
		Assert.assertEquals("»¡¸®»¡¸®", purchase.getDivyRequest());
		Assert.assertEquals("20180426 00:00:00.0", purchase.getDivyDate());
		
	}
	
	//@Test
	public void testGetPurchase() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10000);
		
		System.out.println(purchase);
		
		Assert.assertEquals(10000, purchase.getTranNo());
		//Assert.assertEquals(10045, purchase.getPurchaseProd());
		//Assert.assertEquals("user01", purchase.getBuyer());
		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("SCOTT", purchase.getReceiverName());
		Assert.assertEquals("01010101010", purchase.getReceiverPhone());
		Assert.assertEquals("½Åµµ¸²", purchase.getDivyAddr());
		Assert.assertEquals("»¡¸®»¡¸®", purchase.getDivyRequest());
		Assert.assertEquals("20180426 00:00:00.0", purchase.getDivyDate());
		
		Assert.assertNotNull(purchaseService.getPurchase(10001));
		Assert.assertNotNull(purchaseService.getPurchase(10002));
	
	}
	
	/*@Test
	public void testGetPurchase2() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase2(10045);
		
		System.out.println(purchase);
	}*/
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		Purchase purchase = purchaseService.getPurchase(10000);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals(10000, purchase.getTranNo());
		//Assert.assertEquals(10045, purchase.getPurchaseProd());
		//Assert.assertEquals("user01", purchase.getBuyer());
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("SCOTT", purchase.getReceiverName());
		Assert.assertEquals("01010101010", purchase.getReceiverPhone());
		Assert.assertEquals("½Åµµ¸²", purchase.getDivyAddr());
		Assert.assertEquals("»¡¸®»¡¸®", purchase.getDivyRequest());
		Assert.assertEquals("20180426 00:00:00.0", purchase.getDivyDate());
		
		
		purchase.setPaymentOption("2");
		purchase.setReceiverName("SUZY");
		purchase.setReceiverPhone("01038301839");
		purchase.setDivyAddr("¾È¼º");
		purchase.setDivyRequest("¾ð´É¿ä");
		purchase.setDivyDate("2018-04-27");
		
		purchaseService.updatePurchase(purchase);
		
		System.out.println(purchase);
		
		purchase = purchaseService.getPurchase(20000);
		
		Assert.assertEquals(20000, purchase.getTranNo());
		//Assert.assertEquals(10045, purchase.getPurchaseProd());
		//Assert.assertEquals("user01", purchase.getBuyer());
		Assert.assertEquals("2", purchase.getPaymentOption());
		Assert.assertEquals("SUZY", purchase.getReceiverName());
		Assert.assertEquals("01038301839", purchase.getReceiverPhone());
		Assert.assertEquals("¾È¼º", purchase.getDivyAddr());
		Assert.assertEquals("¾ð´É¿ä", purchase.getDivyRequest());
		Assert.assertEquals("20180427 00:00:00.0", purchase.getDivyDate());
		
	}
	
	//@Test
	public void testDeletePurchase() throws Exception{
		Purchase purchase = purchaseService.getPurchase(10000);
		Assert.assertNotNull(purchase);
		System.out.println(purchase);
		
		purchaseService.deletePurchase(10000);	
	}
	
	@Test
	public void testGetPurchaseList() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, "user01");
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");

	}
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		Purchase purchase = purchaseService.getPurchase(10001);
		Assert.assertNotNull(purchase);
		System.out.println(purchase);
		
		purchase.setTranCode("1");
		purchaseService.updateTranCode(purchase);
		System.out.println(purchase);
	}
	
	


}
