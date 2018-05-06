package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//�����ڿ��� DB����
	public MemberDAO() {
		try{
			Context init = new InitialContext();
	  		DataSource ds = 
	  			(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		con = ds.getConnection();
		}catch(Exception ex){
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	//ȸ������(id�� passwdȮ��)
	public int isMember(MemberBean member){
		String sql ="select member_pw from boardmember where member_id=?";
		int result=-1;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("MEMBER_PW").equals(
									member.getMEMBER_PW())){
					result=1;//��ġ.
				}else{
					result=0;//����ġ.
				}
			}else{
				result=-1;//���̵� �������� ����.
			}
		}catch(Exception ex){
			System.out.println("isMember ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		
		return result;
	}
	
	//ȸ�� ����
	public boolean joinMember(MemberBean member){
		String sql="INSERT INTO BOARDMEMBER VALUES (?,?,?,?,?,?)";
		int result=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setInt(4, member.getMEMBER_AGE());
			pstmt.setString(5, member.getMEMBER_GENDER());
			pstmt.setString(6, member.getMEMBER_EMAIL());
			result=pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		}catch(Exception ex){
			System.out.println("joinMember ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	
	//ȸ�� ����Ʈ ����	
	public List getMemberList(){
		String sql="SELECT * FROM BOARDMEMBER";
		List memberlist=new ArrayList();
		
		try{
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MemberBean mb=new MemberBean();
				mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
				mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
				mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
				mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
				mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				
				memberlist.add(mb);
			}
			
			return memberlist;
		}catch(Exception ex){
			System.out.println("getDeatilMember ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	//�ش� ȸ�� ���� ����
	public MemberBean getDetailMember(String id){
		String sql="SELECT * FROM BOARDMEMBER WHERE MEMBER_ID=?";
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			
			MemberBean mb=new MemberBean();
			mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
			mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
			mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
			mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
			mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
			mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
			
			return mb;
		}catch(Exception ex){
			System.out.println("getDeatilMember ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		
		return null;
	}
	
	//ȸ�� ����
	public boolean deleteMember(String id){
		String sql="DELETE FROM BOARDMEMBER WHERE MEMBER_ID=?";
		int result=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result=pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		}catch(Exception ex){
			System.out.println("deleteMember ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}
		
		return false;
	}
}