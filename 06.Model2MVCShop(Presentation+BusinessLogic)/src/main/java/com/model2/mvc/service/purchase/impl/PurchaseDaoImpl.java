package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public PurchaseDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		Purchase purchase = sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
		
		if (purchase.getDivyDate() != null) {
			String[] splitDivyDate = purchase.getDivyDate().split("-");
			String divyDate = "";
			for(String str : splitDivyDate) {
				divyDate += str;
			}
			purchase.setDivyDate(divyDate);
		}
		
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		
		return purchase;
	}

	public Purchase getPurchase2(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchase2", prodNo);
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	@Override
	public void deletePurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("PurchaseMapper.deletePurchase", tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
	
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
		
	}

	@Override
	public int getTotalCount(String buyerId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId);
	}

}
