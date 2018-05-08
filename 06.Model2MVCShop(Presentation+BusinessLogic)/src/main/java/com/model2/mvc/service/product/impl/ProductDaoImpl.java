package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		String[] splitManuDate = product.getManuDate().split("-");
		String manuDate = "";
		for(String str : splitManuDate) {
			manuDate += str;
		}
		product.setManuDate(manuDate);
		sqlSession.insert("ProductMapper.addProduct",product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		String[] splitManuDate = product.getManuDate().split("-");
		String manuDate = "";
		for(String str : splitManuDate) {
			manuDate += str;
		}
		product.setManuDate(manuDate);
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	@Override
	public List<Product> getProductList(Search search, String orderby) throws Exception {
		// TODO Auto-generated method stub
		//return sqlSession.selectList("ProductMapper.getProductList", search);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchCondition", search.getSearchCondition() );
		map.put("searchKeyword",  search.getSearchKeyword() );
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("orderby", orderby);
		return sqlSession.selectList("ProductMapper.getProductList", map);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}
