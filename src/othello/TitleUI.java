package othello;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TitleUI extends JPanel implements MouseListener,ActionListener{
	Client cl;
	private JPasswordField pass;
	private JTextField name;
	private JLabel title,passLabel,nameLabel;
	private JButton login,anew;
	private String[] connect;
	RoomUI rUI;
	public TitleUI(Client c,RoomUI r){
		cl=c;
		rUI=r;
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
		PasswordManagement pm=new PasswordManagement(cl);
		if (pm.checkAvailable(pass.getPassword())){
			String password = pm.issueHash(pass.getPassword());
			if(e.getSource()==login){
				cl.send(new String("1,"+name.getText()+","+password));
				do{
					connect=cl.waitConnection();
				}while(!connect[0].equals("14") && !connect[0].equals("15"));
				if(connect[0].equals("14")){
					cl.initConnection();
					JOptionPane.showMessageDialog(null, "ログインできました。\nユーザ名："+name.getText());
					cl.writeUserName(name.getText());
					cl.screenTransition((JPanel)this, "rUI");
				}else{
					cl.initConnection();
					JOptionPane.showMessageDialog(null, "ログインに失敗しました。もう一度試してください");
				}
			}
			else if(e.getSource()==anew){
				cl.send(new String("0,"+name.getText()+","+password));
				do{
					connect=cl.waitConnection();
				}while(!connect[0].equals("14") && !connect[0].equals("15"));
				if(connect[0].equals("14")){
					cl.initConnection();
					JOptionPane.showMessageDialog(null, "登録できました\n入力されたデータ\nユーザ名："+name.getText());
					cl.writeUserName(name.getText());
					cl.send("3,1,0");
					do{
						connect=cl.waitConnection();
					}while(!connect[0].equals("11") && !connect[0].equals("15"));
					for(int i=0;i<connect.length/5;i++){
						rUI.roomInfoButton(connect[i*5+2],connect[i*5+1],connect[i*5+3],connect[i*5+4],i);
					}
					cl.screenTransition((JPanel)this, "rUI");
				}else{
					cl.initConnection();
					JOptionPane.showMessageDialog(null, "新規登録に失敗しました。もう一度試してください");
				}
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
