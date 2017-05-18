package othello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Client extends JFrame implements ActionListener{
	ConnectServer cs=new ConnectServer();
	private String[] connect;
	private String userName="";
	OthelloUI oUI=new OthelloUI(this);
	RoomUI rUI=new RoomUI(this,oUI);
	RoomCreateUI rcUI=new RoomCreateUI(this,oUI);
	TitleUI tUI=new TitleUI(this,rUI);
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
	
	public String[] catchConnection(){
		return connect;
	}
	
	public String[] waitConnection(){
		String[] con;
		Timer timer=new Timer(1000,this);
		do{
			timer.setInitialDelay(5000);
			con=this.catchConnection();
		}while(con[0]=="-");
		return con;
	}
	
	public String[] popConnection(){
		
		return new String[4];
	}
	
	public void pushConnection(String msg){
		//connect.add(msg);
	}
	
	public void writeUserName(String name){
		userName=name;
	}
	
	public String catchUserName(){
		return userName;
	}
	
	public void initConnection(){
		connect=new String[1];
		connect[0]="-";
	}
	
	public void send(String msg){
		cs.sendMessage(msg);
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Client cl = new Client(); 
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}


}
