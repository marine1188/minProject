<%@page import="net.board.db.BoardBean"%>
<%@page import="net.board.action.BoardReplyView"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%
	String id=(String)session.getAttribute("id");
	BoardBean board=(BoardBean)request.getAttribute("boarddata");
%>
<html>
<head>
	<title>MVC�Խ���</title>
<!-- 	<script type="text/javascript">
		function addboard(){
			boardform.submit();
		}
	</script> -->
</head>
<body>
<!-- �Խ��� ��� -->
<form  name="modifyform" action="./BoardModifyAction.bo" method="post">
<input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>">
<input type="hidden" name="BOARD_ID" value="<%=id %>">


<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle"><td colspan="5"> MVC�Խ���</td></tr>
	<tr>
		<td style="font-family:����; font-size:12" height="16">
			<div align="center">�۾���</div>
		</td>
		<td><%=id %></td>
	</tr>
		<tr>
		<td style="font-family:����; font-size:12" height="16">
			<div align="center">�� ��</div>
		</td>
		<td>
			<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="<%=board.getBOARD_SUBJECT()%>"/>
		</td>		
	</tr>
	<tr>
		<td style="font-family:����; font-size:12">
			<div align="center">�� ��</div>
		</td>
		<td>
			<textarea name="BOARD_CONTENT" cols="67" rows="15" align="left"><%=board.getBOARD_CONTENT() %></textarea>
		</td>
	</tr>
	<tr>
	<%if(board.getBOARD_FILE()!=null){%>
		<td style="font-family:���� ; font-size:12">
			<div align="center">����÷��</div>
		</td>
		<td style="font-family:���� ; font-size:12">
			&nbsp;&nbsp;<%=board.getBOARD_FILE() %>
		</td>
	</tr>	
	<% }%>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<a href="javascript:modifyform.submit()">[����]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[�ڷ�]</a>			
		</td>
	</tr>
</table>
</form>
</body>
</html>