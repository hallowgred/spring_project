<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    HttpSession hs = request.getSession(false);
    Object mid = hs.getAttribute("mid");
    out.print(hs);
    //hs.invalidate();
    hs.removeAttribute("mid");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>