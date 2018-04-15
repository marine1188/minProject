package chattingFinsh;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private JPanel  jp =new JPanel();
	private ServerBackground server = new ServerBackground();
	private JLabel jLabel1=new JLabel("��ȭ��");//�긻
	private JTextArea jTextArea1=new JTextArea();//�� ��ȭâ
	private JTextArea jTextArea2=new JTextArea("���� ������\n");
	private JScrollPane jScrollPane1=new JScrollPane(jTextArea2);//�����ڵ�
	private JScrollPane jScrollPane=new JScrollPane(jTextArea1);
	private JTextField jTextField2=new JTextField();//��ȭ���� â
	private JButton jButton2=new JButton("������");//�̸�ǥ(������ ��ȭ ������)
	private JTextField jTextField1=new JTextField("");



	public ServerGui() throws IOException {
		jp.setLayout(null);
		//-------------------------------------------------
		
		add(jp); 
		
		JMenu menu1=new JMenu("�޴�");
		menu1.setForeground(Color.WHITE);
		menu1.setFont(new Font("serif", Font.BOLD,15));
		menu1.setBounds(4,10,60,50);
		jp.add(menu1);
		
		JMenu menu2=new JMenu("������ ����");
		menu2.setForeground(Color.WHITE);
		menu2.setFont(new Font("serif", Font.BOLD,15));
		menu2.setBounds(70,10,130,50);
		jp.add(menu2);
		
		
		
		
		//JLabel jLabel1=new JLabel("��ȭ��");//�긻
		jLabel1.setBounds(4,55,50,30);
		jLabel1.setForeground(Color.WHITE);
		jp.add(jLabel1);

		//JTextArea jTextArea2=new JTextArea("���� ������\n");
		jTextArea2.setBounds(4,80,300,50);

		
		jLabel1=new JLabel("����");
		jLabel1.setForeground(Color.WHITE);
		jLabel1.setBounds(4,90,50,30);
		jp.add(jLabel1);
		
		jLabel1=new JLabel("������");
		jLabel1.setForeground(Color.WHITE);
		jLabel1.setBounds(4,110,50,30);
		jp.add(jLabel1);
		
		
		
		
		jTextArea2.setEditable(true);
		//JScrollPane jScrollPane=new JScrollPane(jTextArea2);
		jScrollPane1.setBounds(50,90,300,100);
		//this.add(jScrollPane);//���� �����ڵ�
		jp.add(jScrollPane1);
		//JTextField jTextField1=new JTextField("");
		jTextField1.setBounds(50,50,300,30);
		jp.add(jTextField1);//�ڱ� �г����� �����Ѵ�.


		/*	//-----------------------------------------------
	      JButton jButton1 =new JButton(" �α׾ƿ�");//�� �ڷΰ���
	      jButton1.setBounds(250,10,100,30);
	      add(jButton1);
	//---------------------------------------------------
		 */	      //JTextArea jTextArea1=new JTextArea();//�� ��ȭâ
		//JScrollPane jScrollPane=new JScrollPane(jTextArea1);//��ũ��
		jScrollPane.setBounds(50,200,300,370);
		//jTextArea1.setBounds(50,200,300,370);
		//jp.add(jTextArea1);
		jp.add(jScrollPane);


		//JButton jButton2=new JButton("������");//�̸�ǥ(������ ��ȭ ������)
		jButton2.setBounds(250,570,100,70);
		jp.add(jButton2);
		jButton2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String msg = "���� : "+ jTextField2.getText() + "\n";
				System.out.print(msg);
				server.sendMessage(msg);
				jTextField2.setText("");
				
		    }
		});
		ImageIcon ic =new ImageIcon("C:\\Users\\BIT\\Desktop\\galaxy.jpg");//�̹��� �ҷ�����
		JLabel lbmage1 =new JLabel(ic);  // ������ �ȿ� �� ��ý ����
		
		
		lbmage1.setBounds(0,0,400,700);//  ,���� ,����
		//JpaneTest�� ��� ���� �ϴ� �ڵ�
		jp.add(lbmage1);  
		//JTextField jTextField2=new JTextField();//��ȭ���� â
		jTextField2.setBounds(50,570,200,70);
		jp.add(jTextField2);



		//---------------------------------------------------

		//jp.add(jTextArea1);
		//jp.add(jTextField2);
		jTextField2.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 700);
		setTitle("�����κ�");

		server.setGui(this);
		server.setting();
		
	}

	public static void main(String[] args) throws IOException {
		
		new ServerGui();
		 

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = "���� : "+ jTextField2.getText() + "\n";
		System.out.print(msg);
		server.sendMessage(msg);
		jTextField2.setText("");
	}

	public void appendMsg(String msg) {
		jTextArea1.append(msg);
		jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

	}

	public void appendNick(String nickname) {
		// TODO Auto-generated method stub
		jTextArea2.append(nickname);
		
	}


	

}
