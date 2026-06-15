<%@page import="java.time.Month"%>
<%@page import="java.time.LocalDate"%>
<%@page import="day0612.TestDTO"%>
<%@page import="java.util.List"%>
<%@page import="day0612.TestService"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="script의 사용"
    %>
<%
//method 내 정의 하는 java코드 작성. 변수선언, 연산자 사용, 제어문, 객체생성  
String name="홍길동";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=name %>님 안녕하세요</title>
<link rel="shortcut icon" href="http://192.168.10.78/html_prj/common/images/favicon.ico"/>
<!-- Bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js" integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y" crossorigin="anonymous"></script>

<style type="text/css">
#wrap{width: 1000px; height: 900px; margin: 0px auto;}
#header{height: 200px;}
#container{height: 600px;}
#footer{height: 100px;}


.age{font-weight:bold}
.age2{font-weight:bold; color:#FF0000}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
	
});
</script>
</head>
<body>
<div id="wrap">
<div id="header">
</div>
<div id="container">
<%
//_jspService() 안쪽으로 코드가 생성된다.
int age=25; //지역변수=>초기화를 하지 않고 사용하면 error

String css="age";
if(new Random().nextBoolean()){
	css="age2";
}
%>
<div>
나이는 <span class="<%=css %>"><%=age %></span>살 입니다.
</div>
<table class="table table-hover">
<thead>
<tr>
<th>번호</th>
<th>이름</th>
<th>나이</th>
<th>비고</th>
</tr>
</thead>
<tbody>
<%
TestService ts=new TestService();
List<TestDTO> list=ts.searchMember();

TestDTO tDTO=null;
for(int i=0; i<list.size(); i++){
	tDTO=list.get(i);
%>
<tr>
<td><input type="text" value="<%= i+1 %>" readonly="readonly" style="width:80px"/></td>
<td><input type="text" value="<%= tDTO.getName() %>"/></td>
<td>
<select>
<%for(int j=0; j<101; j++){%>
<option <%=j==tDTO.getAge()?" selected='selected'":"" %>><%=j %></option>	
<% }%>

</select>
</td>
<td><button class="btn btn-warning btn-sm">삭제</button></td>
</tr>
	
<% }%>



</tbody>
</table>

<!-- 올해를 기준으로 2026이전 2년 이후 2년을 옵션으로 보여주는 select을
 Calendar를 사용하여 만들어보세요. -->
 <%
 LocalDate Id=LocalDate.now();
 int nowYear=Id.getYear();
 int nowMonth=Id.getMonthValue();
 int lastDay=Id.lengthOfMonth();//그 달의 마지막 달
 int nowDay=Id.getDayOfMonth();

 %>
 <select>
 <%for(int i=1950; i<nowYear+1;i++){ %>
 <option<%=i==nowYear? "selected='selected'":""%>><%=i %></option>
 <%} %>
 </select>
 <select>
 <%for(int i=1; i<13; i++){ %>
 <option<%= i==nowMonth? "selected='selected'":"" %>><%=i%></option>
 <%} %>
 </select>
 <select>
 <%for(int i=1; i<lastDay+1; i++){ %>
 <option<%= i==nowDay? "selected='selected'":"" %>><%=i%></option>
 <%} %>
 </select>
 
 <!-- 0점에서 10점까지 선택할 수 있는 라디오버튼 11개 생성 -->
 <div>
 <%for(int i=0; i<11; i++){ %>
 <%=i %><input type="radio" name="num" <%= i==5 ? "checked='checked'" : "" %>/>
 <%} %>
 </div>
 
 <!-- 아래 이미지가 강아지 또는 googlo.png, naver.png, daum.png가 보여지도록 -->
 <%
 String[] imgArr="default_img2,google,naver,daum".split(","); %>
 

 <img src="../common/images/<%=imgArr[new Random().nextInt(imgArr.length)] %>.png">
 <%
 //method안에 method는 중첩정의할 수 있다.:error
 //public void test(){
	 
// }
 %>

</div>
<div id="footer"></div>
</div>
</body>
</html>