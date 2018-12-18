<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>모델 사용(클래스를 통해 전달)</title>
</head>
<body>
모델의 이름을 정하지 않는 경우 자동으로 저장되는 객체의 클래스명 앞글자를 소문자로 처리한 클래스명을 이름으로 간주함
<h3>이름 : ${productVO.name}</h3>
<h3>가격 : ${productVO.price}원</h3>

</body>
</html>