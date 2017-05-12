package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordChangeUI extends JPanel implements MouseListener {
	Client cl;
	private JPasswordField oldPass,newPass,renewPass;
	private JLabel title,newPassLabel,oldPassLabel,renewPassLabel;
	private JButton change,cancel;
	public PasswordChangeUI(Client c){
		cl=c;
		this.setSize(700, 550);
		this.setName("pUI");
		this.setLayout(null);
		title=new JLabel("パスワード変更");
		this.add(title);
		title.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		title.setBounds(10,10,300,30);
		oldPassLabel=new JLabel("旧パスワード：");
		this.add(oldPassLabel);
		oldPassLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		oldPassLabel.setBounds(100,100,200,30);
		oldPass=new JPasswordField();
		this.add(oldPass);
		oldPass.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		oldPass.setBounds(300,100,350,30);
		newPassLabel=new JLabel("新パスワード：");
		this.add(newPassLabel);
		newPassLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		newPassLabel.setBounds(100,200,350,30);
		newPass=new JPasswordField();
		this.add(newPass);
		newPass.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		newPass.setBounds(300,200,350,30);
		renewPassLabel=new JLabel("新パスワード(再)：");
		this.add(renewPassLabel);
		renewPassLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		renewPassLabel.setBounds(80,300,350,30);
		renewPass=new JPasswordField();
		this.add(renewPass);
		renewPass.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		renewPass.setBounds(300,300,350,30);
		
		change=new JButton("変更");
		this.add(change);
		change.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		change.setBounds(80,430,200,50);
		change.addMouseListener(this);
		cancel=new JButton("キャンセル");
		this.add(cancel);
		cancel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		cancel.setBounds(400,430,200,50);
		cancel.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==change){
		}
		else if(e.getSource()==cancel){
		}
		//JOptionPane.showMessageDialog(null, "変更newPass:まだ未実装");
		cl.screenTransition((JPanel)this, "rUI");
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
