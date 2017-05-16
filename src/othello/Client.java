package othello;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame{
	ConnectServer cs=new ConnectServer();
	private String[] connect;
	OthelloUI oUI=new OthelloUI(this);
	RoomUI rUI=new RoomUI(this,oUI);
	RoomCreateUI rcUI=new RoomCreateUI(this,oUI);
	TitleUI tUI=new TitleUI(this,cs);
	PasswordChangeUI pUI=new PasswordChangeUI(this);
	public Client(){
		connect=new String[1];
		connect[0]="-";
		cs.connectServer("localhost", 10000,this);
		this.add(oUI);
		oUI.setVisible(false);
		this.add(rcUI);
		rcUI.setVisible(false);
		this.add(rUI);
		rUI.setVisible(false);
		this.add(tUI);
		tUI.setVisible(true);
		this.add(pUI);
		pUI.setVisible(false);
		this.setSize(700, 550);
	}
	
	public void screenTransition(JPanel panel,String str){
		String name=panel.getName();
		if(name=="oUI"){
			oUI=(OthelloUI)panel;
			oUI.setVisible(false);
		}else if(name=="rUI") {
			rUI=(RoomUI)panel;
			rUI.setVisible(false);
		}else if(name=="rcUI"){
			rcUI=(RoomCreateUI)panel;
			rcUI.setVisible(false);
		}else if(name=="tUI"){
			tUI=(TitleUI)panel;
			tUI.setVisible(false);
		}else if(name=="pUI"){
			pUI=(PasswordChangeUI)panel;
			pUI.setVisible(false);
		}
		if(str=="oUI"){
			oUI.setVisible(true);
		}else if(str=="rUI") {
			rUI.setVisible(true);
		}else if(str=="rcUI"){
			rcUI.setVisible(true);
		}else if(str=="tUI"){
			tUI.setVisible(true);
		}else if(str=="pUI"){
			pUI.setVisible(true);
		}
	}
	
	synchronized public void changeConnection(String msg){
		connect=msg.split(",",0);
		System.out.println("サーバにメッセージ " + connect[0] + " を送信しました"); //テスト標準出力
	}
	
	public String[] waitConnection(){
		return connect;
	}
	
	public void initConnection(){
		connect=new String[1];
		connect[0]="-";
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Client cl = new Client(); 
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setVisible(true);
	}


}
