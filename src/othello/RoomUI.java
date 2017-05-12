package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RoomUI extends JPanel implements MouseListener{
	Client cl;
	OthelloUI oUI;
	private JButton rule;
	public RoomUI(Client c,OthelloUI ou){
		cl=c;
		oUI=ou;
		this.setSize(700, 550);
		this.setName("rUI");
		this.setLayout(null);
		rule=new JButton("部屋作成(仮)");
		this.add(rule);
		rule.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		rule.setBounds(400,430,200,50);
		rule.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		cl.screenTransition((JPanel)this, "rcUI");
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
