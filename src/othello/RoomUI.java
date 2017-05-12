package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RoomUI extends JPanel implements MouseListener{
	Client cl;
	OthelloUI oUI;
	private JButton rCreate,rule,passChange,renew,exitButton,roomButton[];
	private JLabel title;
	private JComboBox<String> condiBox;
	public RoomUI(Client c,OthelloUI ou){
		cl=c;
		oUI=ou;
		this.setSize(700, 550);
		this.setName("rUI");
		this.setLayout(null);
		title=new JLabel("ルーム");
		this.add(title);
		title.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		title.setBounds(10,10,75,30);
		String[] condi={"条件指定：なし","合言葉：あり","ハンデ:引き分け勝ち","ハンデ:1子局","ハンデ:2子局","ハンデ:3子局","ハンデ:4子局"};
		condiBox=new JComboBox<String>(condi);
		this.add(condiBox);
		condiBox.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
		condiBox.setBounds(100,10,210,30);
		rCreate=new JButton("部屋作成(仮)");
		this.add(rCreate);
		rCreate.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		rCreate.setBounds(450,10,200,30);
		rCreate.addMouseListener(this);
		rule=new JButton("ルール表示");
		this.add(rule);
		rule.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		rule.setBounds(20,450,200,30);
		rule.addMouseListener(this);
		passChange=new JButton("パスワード変更");
		this.add(passChange);
		passChange.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		passChange.setBounds(240,450,200,30);
		passChange.addMouseListener(this);
		exitButton=new JButton("終了");
		this.add(exitButton);
		exitButton.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		exitButton.setBounds(460,450,200,30);
		exitButton.addMouseListener(this);
		renew=new JButton("検索");
		this.add(renew);
		renew.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		renew.setBounds(320,10,100,30);
		renew.addMouseListener(this);
		roomButton=new JButton[8];
		for(int i=0;i<8;i++){
			roomButton[i]=new JButton();
			this.add(roomButton[i]);
			roomButton[i].setBounds(25+(i%4)*160,75+(i/4)*150,150,140);
			roomButton[i].addMouseListener(this);
			roomButton[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			roomButton[i].setVerticalAlignment(JLabel.CENTER);
		}
		roomButton[0].setText("<html>ユーザ名<br>ジョン<br><br>合言葉<br>なし<br>ハンデ<br>引き分け勝ち");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==rCreate){
			cl.screenTransition((JPanel)this, "rcUI");
		}else if(e.getSource()==rule){
			Rule rule = new Rule();
			rule.setLocation(this.getLocation().x+800, this.getLocation().y);
			rule.setVisible(true);
		}else if(e.getSource()==passChange){
			cl.screenTransition((JPanel)this, "pUI");
		}else if(e.getSource()==exitButton){
			int finish = JOptionPane.showConfirmDialog(this, "本当に終了しますか？","終了",0);
			if (finish == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		
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
