<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%-- <%BoardBean board = (BoardBean)request.getAttribute("boarddata"); %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC�Խ���</title>
</head>
<body>
 <!-- �Խ��� ���� -->
 <table cellpadding="0" cellspacein="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC�Խ���</td>
	</tr> 
	<tr>
		<td style="font-family:����;font-size:12" height="16">
			<div align="center">���� &nbsp;&nbsp;</div>
		</td>		
		<td style="font-family:����; font-size:12"> 
			${boarddata.BOARD_SUBJECT}
		</td>		
	</tr>
	<tr bgcolor="ccccc">
		<td colspan="2" style="height:1px"></td>
	</tr>
	<tr>
		<td style="font-family:���� ; font-size:12">
			<div align="center">�� ��</div>
		</td>
		<td style="font-family:����; font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:����; font-size:12">
						${boarddata.BOARD_CONTENT}
					</td>
				</tr>
			</table>	
		</td>
	</tr>
	<tr>
		<td style="font-family:����; font-size:12">
			<div align="center">÷������</div>
		</td>
		<c:if test="${not boarddata.BOARD_FILE == null }">
		<td style="font-family:����; font-size:12">
			
				<a href="./boardupload/${boarddata.BOARD_FILE}">
					${boarddata.BOARD_FILE}
				</a>
				</c:if>
		</td>
	</tr>
	<tr bgcolor="cccccc"> <td colspan="2" style="height:1px;"></td> </tr>
	<tr> <td colspan="2">&nbsp;</td>	</tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
				<a href="./BoardReplyAction.bo?num=${boarddata.BOARD_NUM }">[�亯]</a>
				<a href="./BoardModify.bo?num=${boarddata.BOARD_NUM}">[����]</a>				
				<a href="./BoardDelete.bo?num=${boarddata.BOARD_NUM}">[����]</a>
				<a href="./BoardList.bo">[���]</a>				
			</font>
		</td>
	</tr>
 </table>
</body>
</html>