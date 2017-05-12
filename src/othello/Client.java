package othello;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame{
	OthelloUI oUI=new OthelloUI(this);
	RoomUI rUI=new RoomUI(this,oUI);
	RoomCreateUI rcUI=new RoomCreateUI(this,oUI);
	public Client(){
		this.add(oUI);
		oUI.setVisible(false);
		this.add(rcUI);
		rcUI.setVisible(false);
		this.add(rUI);
		rUI.setVisible(true);
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
		}
		if(str=="oUI"){
			oUI.setVisible(true);
		}else if(str=="rUI") {
			rUI.setVisible(true);
		}else if(str=="rcUI"){
			rcUI.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Client cl = new Client(); 
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setVisible(true);
	}

}
