package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO() {
		try{
			Context init = new InitialContext();
	  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		con = ds.getConnection();
		}catch(Exception ex){
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	
	/*���� ���� ���ϱ�(��ü ���� ������ ��ȯ�Ѵ�)
	 * ���� ������ ǥ�� �� ����¡ó���� �Ҷ� ���ȴ�.*/
	public int getListCount() {
		int x= 0;
		
		try{
			pstmt=con.prepareStatement("select count(*) from memberboard");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);  //count(*)�Ѱ��� x�� �����Ѵ�.
			}
		}catch(Exception ex){
			System.out.println("getListCount ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		return x;
	}
	
	/*�� ��� ����
	 * List��ü�� ��ȯ�Ѵ�. �μ�1) ����� ������, �μ�2) �� �������� ǥ���� �� ��*/
	public List getBoardList(int page,int limit){
		String board_list_sql="select * from "+
		"(select rownum rnum,BOARD_NUM,BOARD_ID,BOARD_SUBJECT,"+
		"BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+
		"BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+
		"(select * from memberboard order by "+
		"BOARD_RE_REF desc,BOARD_RE_SEQ asc)) "+
		"where rnum>=? and rnum<=?";
		
		List list = new ArrayList();
		
		int startrow=(page-1)*10+1; //�б� ������ row ��ȣ.
		int endrow=startrow+limit-1; //���� ������ row ��ȣ.		
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_ID(rs.getString("BOARD_ID"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		}catch(Exception ex){
			System.out.println("getBoardList ���� : " + ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	//�� ���� ���� //�� ���ڵ� ��ȣ�� �μ��� �޾ƿ´�.
	public BoardBean getDetail(int num) throws Exception{
		BoardBean board = null;
		try{
			pstmt = con.prepareStatement(
					"select * from memberboard where BOARD_NUM = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_ID(rs.getString("BOARD_ID"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		}catch(Exception ex){
			System.out.println("getDetail ���� : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	/*�� ���
	 * 1) board_num�ʵ��� �ִ밪�� ���´�. ������ �ߺ����� ���� ���� �������� ����
	 * 2) �۵������ : �۾���� �����̹Ƿ� �亯�� ���õ� �ʵ�� ��� 0����..ref��(�۱׷��ȣ�� ���ο� ��ȣ�� ����)*/
	public boolean boardInsert(BoardBean board){
		int num =0;
		String sql="";
		
		int result=0;
		
		try{
			pstmt=con.prepareStatement(
					"select max(board_num) from memberboard");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num =rs.getInt(1)+1;  //���� ��ϵǾ������� �� ��ȣ +1
			else
				num=1;//�� ����� �Ǿ����� ������ num=1��
			
			sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+
				"BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+
				"BOARD_DATE) values(?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_ID());
			pstmt.setString(3, board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setString(5, board.getBOARD_FILE());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardInsert ���� : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	/*�� �亯 ��� */
	public int boardReply(BoardBean board){
		String board_max_sql="select max(board_num) from memberboard";
		String sql="";
		int num=0;
		int result=0;
		
		int re_ref=board.getBOARD_RE_REF(); //������ ��ȣ
		int re_lev=board.getBOARD_RE_LEV(); // �亯���� ����
		int re_seq=board.getBOARD_RE_SEQ(); //�亯���� ����
		
		try{
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1; //�亯���� ������� max�� +1�ؼ� �۹�ȣ�� �Կ��ش�.
			else num=1;  //�� ����� �Ǿ����� ������ num=1
			
			/*ref���� seq���� Ȯ���Ͽ� ���� �ۿ� �ٸ� �亯 ���� ������, 
			 * �亯 �� �� �亯 �ۺ��� ������ �ִ� ���� seq���� ���� ���� seq���� 1�� ������Ų��.*/
			sql="update memberboard set BOARD_RE_SEQ=BOARD_RE_SEQ+1 ";
			sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ>?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			result=pstmt.executeUpdate();
			
			re_seq = re_seq + 1; //�亯 ���� ���� �� ���� �Ʒ��� ��µǾ�� �ϱ� ������ re_seq���� 1������Ų��.
			re_lev = re_lev+1; // �亯�� �ϴ°��̹Ƿ� ���� �亯 ���� �ܰ迡�� 1�� ������Ų��.
			
			sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,";
			sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) ";
			sql+="values(?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_ID());
			pstmt.setString(3, board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setString(5, ""); //���忡�� ������ ���ε����� ����.
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			return num;
		}catch(SQLException ex){
			System.out.println("boardReply ���� : "+ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		}
		return 0;
	}
	
	//�� ����.
	public boolean boardModify(BoardBean modifyboard) throws Exception{
		String sql="update memberboard set BOARD_SUBJECT=?,";
		sql+="BOARD_CONTENT=? where BOARD_NUM=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify ���� : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	// �� ����(�׼� Ŭ�������� ��й�ȣ ��ġ ���� Ȯ���� �� �޼��带 �����Ѵ�.)
	public boolean boardDelete(int num){
		String board_delete_sql=
			"delete from memberboard where BOARD_num=?";
		
		int result=0;
		
		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete ���� : "+ex);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
			}catch(Exception ex) {}
		}
		
		return false;
	}
	
	//��ȸ�� ������Ʈ(�� ������ Ȯ���ϴ� ���� ȣ��ȴ�)
	public void setReadCountUpdate(int num) throws Exception{
		String sql="update memberboard set BOARD_READCOUNT = "+
			"BOARD_READCOUNT+1 where BOARD_NUM = "+num;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate ���� : "+ex);
		}
	}
	
	//�۾������� Ȯ��(�۾��̸� Ȯ���� ���� ������ ��´�.
	public boolean isBoardWriter(int num,String id){
		String board_sql=
			"select * from memberboard where BOARD_NUM=?";
		
		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			rs.next();
			
			if(id.equals(rs.getString("BOARD_ID"))){
				return true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter ���� : "+ex);
		}
		return false;
	}
}
	