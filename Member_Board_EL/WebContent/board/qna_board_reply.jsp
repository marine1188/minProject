<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<!-- ���� ���¾� -->
<%-- <%
	String id=(String)session.getAttribute("id");
	BoardBean board=(BoardBean)request.getAttribute("boarddata");
%> --%>
<html>
<head><title>MVC �Խ���</title></head>
<body>
<form name="boardform" action="./BoardReplyView.bo" method="post" >
	<input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM }">
	<input type="hidden" name="BOARD_RE_REF" value="${boarddata.BOARD_RE_REF }">
	<input type="hidden" name="BOARD_RE_LEV" value="${boarddata.BOARD_RE_LEV }">
	<input type="hidden" name="BOARD_RE_SEQ" value="${boarddata.BOARD_RE_SEQ }">
	<input type="hidden" name="BOARD_ID" value="${id}">

<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC�Խ���</td>	
	</tr>
	<tr>
		<td style="font-family:����; font-size:12" height="16">
		<div align="center">�۾���</div></td>	
		<td>${id}</td>	
	</tr>
	<tr>
		<td style="font-family:����; font-size:12" height="16">
			<div align="center">����</div>
		</td>
		<td>
			<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="Re:${boarddata.BOARD_SUBJECT}"/>			
		</td>		
	</tr>
	<tr>
		<td style="font-family:����; font-size:12"><div align="center">����</div></td>
		<td>
			<textarea name="BOARD_CONTENT" cols="67" rows="15" align="left">RE:${boarddata.BOARD_CONTENT}</textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:����; font-size:12"><div align="center">��й�ȣ</div></td>
		<td><input name="BOARD_PASS" type="password"></td>
	</tr>
	<tr bgcolor="cccccc"> <td colspan="2" style="height:1px"> </td> </tr>
	<tr><td colspan="2"> &nbsp;</td></tr>
	<tr align="center" valign="middel">
		<td colspan="5">
			<a href="javascript:boardform.submit()">[���]</a>&nbsp;&nbsp;
			<a href ="javascript:history.go(-1)">[�ڷ�]</a>
		</td>
	</tr>	
</table>
</form>
</body>
</html>