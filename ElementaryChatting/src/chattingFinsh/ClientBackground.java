package chattingFinsh ;

import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGui gui;
	private String msg;
	private String nickName;
	
	public final void setGui(ClientGui gui) {
		this.gui = gui;
	}

	public void connet() {
		try {
			socket = new Socket("172.22.226.200", 3333);
			System.out.println("���� �����.");

			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

			//�������ڸ��� �г��� �����ϸ�. ������ �̰� �г������� �ν��� �ϰ� �ʿ� ����ְ�����?
			out.writeUTF(nickName); 
			System.out.println("Ŭ���̾�Ʈ : �޽��� ���ۿϷ�");
			while(in!=null){
				msg=in.readUTF();
				gui.appendMsg(msg);	
				//gui.appendNick(nickName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientBackground clientBackground = new ClientBackground();
		clientBackground.connet();
	}

	public void sendMessage(String msg2) {
		try {
			out.writeUTF(msg2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNickname(String nickName) {
		this.nickName = nickName;
	}

}
