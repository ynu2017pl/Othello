package othello;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rule extends JFrame {
	JLabel label1;
	public Rule(){
		this.setSize(700,550);
		label1=new JLabel("ルール書いて");
		this.add(label1);
		label1.setBounds(10, 10, 100, 100);
	}
}
