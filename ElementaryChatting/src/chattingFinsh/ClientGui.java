package chattingFinsh;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import javazoom.jl.player.Player;

public class ClientGui  extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel  jp =new JPanel();
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private JTextArea jTextArea1=new JTextArea();//�� ��ȭâ
	private JTextArea jTextArea2=new JTextArea("���� ������\n");
	private JScrollPane jScrollPane1=new JScrollPane(jTextArea2);//�����ڵ�
	private JScrollPane jScrollPane=new JScrollPane(jTextArea1);
	private JLabel jLabel1=new JLabel("��ȭ��");
	private JTextField jTextField1=new JTextField("");
	private JButton jButton2=new JButton("������");//�̸�ǥ(������ ��ȭ ������)
	private JTextField jTextField2=new JTextField();//��ȭ���� â
	private ClientBackground client = new ClientBackground();
	private static String nickName;
	



	//private Player player;//�ڹ��ܽ���Ʈ ���̺귯�����ϳ�


	public ClientGui() {
		jp.setLayout(null);
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

		//JTextArea jTextArea2=new JTextArea("���� ������\n");
		jTextArea2.setBounds(4,80,300,50);
		jTextArea2.setEditable(true);
		jScrollPane1.setBounds(50,90,300,100);
		jp.add(jScrollPane1);

		jTextField1.setBounds(50,50,300,30);
		jp.add(jTextField1);//�ڱ� �г����� �����Ѵ�.
		jScrollPane.setBounds(50,200,300,370);

		jp.add(jScrollPane);

		//JButton jButton2=new JButton("������");//�̸�ǥ(������ ��ȭ ������)
		jButton2.setBounds(250,570,100,70);
		jp.add(jButton2);

		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play("C:\\Users\\BIT\\Desktop\\midtom.m4a");
				System.out.println("��ư ����");
				String msg = nickName+ ":" + jTextField2.getText()+"\n";
				if(jTextField2.getText().equals("�ٺ�")){
					ImageIcon ic =new ImageIcon("C:\\Users\\BIT\\Desktop\\abong.jpg");//�̹��� �ҷ�����



					//jl.setBounds(800, 100, 400, 700);

					//setSize(300,500);
					/*	jp.add(jl);
					jTextArea1.setVisible(false);
					jTextField2.setVisible(false);*/

					//jp.setVisible(false);
					//jp.setBackground(new Color(255, 0, 0, 0));    //����
					//jp.setOpaque(false);
					//jTextArea1.setOpaque(false);

					JPanel abong =new JPanel();
					jp.setVisible(false);


					//abong.setVisible(true);
					JLabel jl =new JLabel(ic);
					jl.setSize(800, 500);

					abong.add(jl);
					abong.setBounds(0, 0, 400, 700);
					add(abong);
					setLayout(null);
					setResizable(false);
					setVisible(true);
					/*JTextArea abong =new JTextArea();
					abong.setBounds(50,90,300,100);
					jp.add(abong);
					jTextArea1.remove(jTextArea1);
					jTextField2.enable();*/
				}else {
					client.sendMessage(msg);
					jTextField2.setText("");
				}

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


		jTextField2.addActionListener(this);





		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(800, 100, 400, 700);
		setTitle("Ŭ���̾�Ʈ");

		client.setGui(this);
		client.setNickname(nickName);
		client.connet();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("����� �г�����? : ");
		nickName = scanner.nextLine();
		scanner.close();		

		new ClientGui();

	}





	@Override
	//��ġ�� ������ �κ�
	public void actionPerformed(ActionEvent e) {
		String msg = nickName+ ":" + jTextField2.getText()+"\n";
		if(jTextField2.getText().equals("�ٺ�")){
			ImageIcon ic =new ImageIcon("C:\\Users\\BIT\\Desktop\\abong.jpg");//�̹��� �ҷ�����



			//jl.setBounds(800, 100, 400, 700);

			//setSize(300,500);
			/*	jp.add(jl);
			jTextArea1.setVisible(false);
			jTextField2.setVisible(false);*/

			//jp.setVisible(false);
			//jp.setBackground(new Color(255, 0, 0, 0));    //����
			//jp.setOpaque(false);
			//jTextArea1.setOpaque(false);

			JPanel abong =new JPanel();
			jp.setVisible(false);


			//abong.setVisible(true);
			JLabel jl =new JLabel(ic);
			jl.setSize(800, 500);

			abong.add(jl);
			abong.setBounds(0, 0, 400, 700);
			add(abong);
			setLayout(null);
			setResizable(false);
			setVisible(true);
			/*JTextArea abong =new JTextArea();
			abong.setBounds(50,90,300,100);
			jp.add(abong);
			jTextArea1.remove(jTextArea1);
			jTextField2.enable();*/
		}else {
			client.sendMessage(msg);
			jTextField2.setText("");
		}



	}

	public void appendMsg(String msg) {
		jTextArea1.append(msg);
		jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

	}
	/*public void appendNick(String nickName) {
		// TODO Auto-generated method stub
		jTextfield1.setText(nickName);
	}*/













}
