<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<html>
<head><title>ȸ������ ������</title></head>
<body>
<form name="joinform" action="./MemberJoinAction.me" method="post">
<center>
	<table border=1>
		<tr>
			<td colspan="2" align=center><b><font size=5> ȸ������ ������</font></b></td>			
		</tr>
		<tr><td>���̵�</td><td><input type="text" name="MEMBER_ID" maxlength="12"></td></tr>
		<tr><td>��й�ȣ</td><td><input type="password" name="MEMBER_PW" maxlength="12"></td></tr>
		<tr><td>�̸�</td><td><input type="text" name="MEMBER_NAME" maxlength="12"></td></tr>
		<tr><td>����</td><td><input type="text" name="MEMBER_AGE" maxlength=3 size=5></td></tr>
		<tr><td>����</td>
			<td>
				<input type="radio" name="MEMBER_GENDER" value="��">����
				<input type="radio" name="MEMBER_GENDER" value="��">����
			</td>
		</tr>
		<tr><td>�̸��� �ּ�:</td><td><input type="email" name="MEMBER_EMAIL" size=30></td></tr>
		<tr>
			<td colspan="2" align=center>
				<a href="javascript:joinform.submit()">ȸ������</a> &nbsp;&nbsp;
				<a href="javascript:joinform.reset()">�ٽ��ۼ�</a>				
			</td>
		</tr>								
	</table>
</center>
</form>
</body>
</html>