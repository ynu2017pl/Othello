package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TitleUI extends JPanel implements MouseListener{
	Client cl;
	private JPasswordField pass;
	private JTextField name;
	private JLabel title,passLabel,nameLabel;
	private JButton login,anew;
	public TitleUI(Client c){
		cl=c;
		this.setSize(700, 550);
		this.setName("tUI");
		this.setLayout(null);
		title=new JLabel("オセロ(仮)");
		this.add(title);
		title.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		title.setBounds(250,10,500,100);
		nameLabel=new JLabel("ユーザ名：");
		this.add(nameLabel);
		nameLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		nameLabel.setBounds(100,200,200,30);
		name=new JTextField();
		this.add(name);
		name.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		name.setBounds(300,200,350,30);
		passLabel=new JLabel("パスワード：");
		this.add(passLabel);
		passLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		passLabel.setBounds(100,300,350,30);
		pass=new JPasswordField();
		this.add(pass);
		pass.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		pass.setBounds(300,300,350,30);
		
		login=new JButton("ログイン");
		this.add(login);
		login.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		login.setBounds(80,430,200,50);
		login.addMouseListener(this);
		anew=new JButton("新規登録");
		this.add(anew);
		anew.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		anew.setBounds(400,430,200,50);
		anew.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
		if(e.getSource()==login){
		}
		else if(e.getSource()==anew){
		}
		PasswordManagement pm=new PasswordManagement();
		if (pm.checkAvailable(pass.getPassword())){
			String password = pm.issueHash(pass.getPassword());
			JOptionPane.showMessageDialog(null, "入力されたデータ\nユーザ名："+name.getText()+"\npass:"+password);
			cl.screenTransition((JPanel)this, "rUI");
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
