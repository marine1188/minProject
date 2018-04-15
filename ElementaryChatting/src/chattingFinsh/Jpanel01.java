package chattingFinsh;


import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Jpanel01 extends JPanel {//�ι�° ȭ�� ù��° �г�1 

   private JButton jButton1;
   private JButton jButton2;
   private JScrollPane jScrollPane;
   private JTextArea jTextArea1;
   private JTextArea jTextArea2;
   private JTextField jTextField1;
   private JTextField jTextField2;
   private JPanelTest win;
   private JLabel jLabel1;


   public Jpanel01(JPanelTest win) {
      this.win=win;
      //-------------------------------------------
      setLayout(null);//���� ��ġ
      JMenu menu1=new JMenu("�޴�");
      JMenu menu2=new JMenu("������ ����");

      menu1.setBounds(4,10,50,30);
      add(menu1);

      menu2.setBounds(50,10,100,30);
      add(menu2);
//-------------------------------------------------
      jLabel1=new JLabel("��ȭ��");//�긻
      jLabel1.setBounds(4,50,50,30);
      add(jLabel1);

      jTextArea2=new JTextArea("���� ������\n");
      /*jTextArea2.setBounds(4,80,300,50);*/

      jTextArea2.setEditable(true);
      jScrollPane=new JScrollPane(jTextArea2);
      jScrollPane.setBounds(50,90,300,100);
      this.add(jScrollPane);//���� �����ڵ�

      jTextField1=new JTextField("");
      jTextField1.setBounds(50,50,300,30);
      add(jTextField1);//�ڱ� �г����� �����Ѵ�.


//-----------------------------------------------
      jButton1 =new JButton(" �α׾ƿ�");//�� �ڷΰ���
      jButton1.setBounds(250,10,100,30);
      add(jButton1);
//---------------------------------------------------
      jTextArea1=new JTextArea();//�� ��ȭâ
      jScrollPane=new JScrollPane(jTextArea1);
      jScrollPane.setBounds(50,200,300,370);
      add(jScrollPane);


      jButton2=new JButton("������");//�̸�ǥ(������ ��ȭ ������)
      jButton2.setBounds(250,570,100,70);
      add(jButton2);

      jTextField2=new JTextField();//��ȭ���� â
      jTextField2.setBounds(50,570,200,70);
      add(jTextField2);




      jButton1.addActionListener(new MyActionListener());
   }
   class MyActionListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent age) {

         win.change("panel02");

      }

   }
}

//-----------------�Ű�Ƚᵵ ��--------------------------------------
class JPanel02 extends JPanel{
   private JTextField textField;
   private JPasswordField passwordField;
   private JPanelTest win;
   public JPanel02 (JPanelTest win) {
      setLayout(null);
      this.win=win;

      JLabel lblLbl=new JLabel("���̵�");
      lblLbl.setBounds(40,400,67,15);
      add(lblLbl);

      textField =new JTextField();
      textField.setBounds(100,380,250,70);
      add(textField);
      textField.setColumns(100);

      JLabel lblLbl_1=new JLabel("��ȣ");
      lblLbl_1.setBounds(40,490,100,70);
      add(lblLbl_1);

      passwordField=new JPasswordField();
      passwordField.setBounds(100,490,250,70);
      add(passwordField);

      JButton btn=new JButton("��ư");
      btn.setBounds(40,620,300,20);
      add(btn);



      btn.addActionListener(new MyActionListener());


   }
   class MyActionListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent arg0) {
         win.change("panel01");

      }

   }


}

class JPanelTest extends JFrame {
   public JPanel02 jpanel02=null;
   public Jpanel01 jpanel01=null;

   public void change (String panelName){


      if(panelName.equals("panel02")) {
         getContentPane().removeAll();
         getContentPane().add(jpanel02);
         revalidate();
         repaint();

      }else {
         getContentPane().removeAll();
         getContentPane().add(jpanel01);
         revalidate();
         repaint();
      }

   }

   public static void main(String[] args) {
      JPanelTest win=new JPanelTest();

      win.setTitle("�輱��");
      win.jpanel02 =new JPanel02(win);      
      win.jpanel01 =new Jpanel01(win);

      win.add(win.jpanel02);
      win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      //win.setSize(400,700);
      win.setBounds(400,50,400,700);
      win.setVisible(true);



   }

}