<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%-- @ page import = "com.model2.mvc.service.domain.*" 

<%
	Purchase purchase = (Purchase) request.getAttribute("purchase");
	String pay = "";
	if(purchase != null){
		if(purchase.getPaymentOption().equals("1")){
			pay = "현금구매";
		}else{
			pay = "신용구매";
		}
	}
--%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="deletePurchase" action="/deletePurchaseView.do?tranNo=${purchase.tranNo}" method="post">

구매가취소가 되었습니다.<br/>
<a href = "/listPurchase.do">돌아가기</a>


</form>

</body>
</html>