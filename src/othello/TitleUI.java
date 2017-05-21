package othello;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TitleUI extends JPanel implements MouseListener,ActionListener{
	Client cl;
	private JTextField name = new JTextField(20) {
		  @Override protected void paintComponent(Graphics g) {
		    if (!isOpaque()) {
		      int w = getWidth() - 1;
		      int h = getHeight() - 1;
		      Graphics2D g2 = (Graphics2D) g.create();
		      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                          RenderingHints.VALUE_ANTIALIAS_ON);
		      g2.setPaint(UIManager.getColor("TextField.background"));
		      g2.fillRoundRect(0, 0, w, h, h, h);
		      g2.setPaint(Color.GRAY);
		      g2.drawRoundRect(0, 0, w, h, h, h);
		      g2.dispose();
		    }
		    super.paintComponent(g);
		  }
		  @Override public void updateUI() {
		    super.updateUI();
		    setOpaque(false);
		    setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		  }
		};
	private JPasswordField pass = new JPasswordField(20) {
			  @Override protected void paintComponent(Graphics g) {
			    if (!isOpaque()) {
			      int w = getWidth() - 1;
			      int h = getHeight() - 1;
			      Graphics2D g2 = (Graphics2D) g.create();
			      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			                          RenderingHints.VALUE_ANTIALIAS_ON);
			      g2.setPaint(UIManager.getColor("TextField.background"));
			      g2.fillRoundRect(0, 0, w, h, h, h);
			      g2.setPaint(Color.GRAY);
			      g2.drawRoundRect(0, 0, w, h, h, h);
			      g2.dispose();
			    }
			    super.paintComponent(g);
			  }
			  @Override public void updateUI() {
			    super.updateUI();
			    setOpaque(false);
			    setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			  }
		};
	private JLabel title,passLabel,nameLabel,back;
	private RoundedCornerButton login,anew;
	private ImageIcon backIcon;
	private String[] connect;
	RoomUI rUI;
	public TitleUI(Client c,RoomUI r){
		cl=c;
		rUI=r;
		this.setSize(700, 550);
		this.setName("tUI");
		this.setLayout(null);
		title=new JLabel("ロセオ");
		this.add(title);
		title.setFont(new Font("ふみゴシック", Font.BOLD, 40));
		title.setBounds(250,10,500,100);
		nameLabel=new JLabel("ユーザ名：");
		this.add(nameLabel);
		nameLabel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		nameLabel.setBounds(100,200,200,30);
		this.add(name);
		name.setFont(new Font("富士ポップ", Font.BOLD, 22));
		name.setBounds(300,200,350,30);
		passLabel=new JLabel("パスワード：");
		this.add(passLabel);
		passLabel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		passLabel.setBounds(100,300,350,30);
		this.add(pass);
		pass.setFont(new Font("MS ゴシック", Font.BOLD, 22));
		pass.setBounds(300,300,350,30);
		
		login=new RoundedCornerButton("ログイン");
		this.add(login);
		login.setFont(new Font("富士ポップ", Font.BOLD, 22));
		login.setBounds(80,430,200,50);
		login.addMouseListener(this);
		anew=new RoundedCornerButton("新規登録");
		this.add(anew);
		anew.setFont(new Font("富士ポップ", Font.BOLD, 22));
		anew.setBounds(400,430,200,50);
		anew.addMouseListener(this);
		backIcon = new ImageIcon("タイトル背景.jpg");
		back=new JLabel(backIcon);
		this.add(back);
		back.setBounds(0,0,700,550);
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
					cl.writeUserName(name.getText());
					cl.send("3,1,6,0");
					do{
						connect=cl.waitConnection();
					}while(!connect[0].equals("11") && !connect[0].equals("15"));
					for(int i=0;i<connect.length/5;i++){
						rUI.roomInfoButton(connect[i*5+2],connect[i*5+1],connect[i*5+3],connect[i*5+4],connect[i*5+5],i);
					}
					JOptionPane.showMessageDialog(null, "ログインできました。\nユーザ名："+name.getText());
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
					cl.writeUserName(name.getText());
					cl.send("3,1,0,0");
					do{
						connect=cl.waitConnection();
					}while(!connect[0].equals("11") && !connect[0].equals("15"));
					for(int i=0;i<connect.length/5;i++){
						rUI.roomInfoButton(connect[i*5+2],connect[i*5+1],connect[i*5+3],connect[i*5+4],connect[i*5+5],i);
					}
					JOptionPane.showMessageDialog(null, "登録できました\n入力されたデータ\nユーザ名："+name.getText());
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
