package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class BoardDeleteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id"); //���ǿ� ����Ǿ� �ִ� ���̵� id������ �����Ѵ�.
		
		boolean result=false;
		int num=Integer.parseInt(request.getParameter("num")); //������ �� ��ȣ�� num ������ �����Ѵ�.
		
		BoardDAO boarddao = new BoardDAO();
		boolean usercheck=boarddao.isBoardWriter(num, id); //dao Ŭ������ isBoardWriter()�޼ҵ忡 �����Ѵ�.
		if(!(id.equals("MASTER"))){
			if(usercheck==false){ // �۾��̰� �ƴ� ��� ��� �޽����� ��� �� �Խ��� ���� �������� �̵�.
				response.setContentType("text/html;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('������ ������ �����ϴ�.');");
				out.println("location.href='./BoardList.bo'");
				out.println("</script>");
				out.close();
				return null;
			}
		}
		result=boarddao.boardDelete(num);
	
		if(result==false){ //�� ���� ������ ��� null
			System.out.println("�Խ��� ���� ����");
			return null;
		}
		//�� ���� �����Ҷ� �Խ��� ���� �������� �̵�
		System.out.println("�Խ��� ���� ����");
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		return forward;
	}
}