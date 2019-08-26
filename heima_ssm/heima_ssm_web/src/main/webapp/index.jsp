<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>



html>
<body>
<h2>Hello World!</h2>
<jsp:forward page="/pages/main.jsp"/>
<a href="${pageContext.request.contextPath}/product/findAll.do">查询所有产品</a>
</body>
</html>
