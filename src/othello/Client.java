package othello;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame{
	OthelloUI oUI=new OthelloUI(this,false,-3,"エネミー");
	RoomUI rUI=new RoomUI(this);
	public Client(){
		this.add(oUI);
		oUI.setVisible(false);
		this.add(rUI);
		rUI.setVisible(true);
		this.setSize(700, 550);
	}
	
	public void screenTransition(JPanel panel,String str){
		String name=panel.getName();
		System.out.println(name);
		if(name=="oUI"){
			System.out.println("入った");
			oUI=(OthelloUI)panel;
			oUI.setVisible(false);
		}else {
			rUI=(RoomUI)panel;
			rUI.setVisible(false);
		}
		if(str=="oUI"){
			System.out.println("入った！");
			oUI.setVisible(true);
		}else {
			System.out.println("入った？");
			rUI.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Client cl = new Client(); 
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setVisible(true);
	}

}
