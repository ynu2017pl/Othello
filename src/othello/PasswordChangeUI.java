package othello;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class PasswordChangeUI extends JPanel implements MouseListener {
	Client cl;
	private JPasswordField oldPass = new JPasswordField(20) {
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
	private JPasswordField newPass = new JPasswordField(20) {
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
	private JPasswordField renewPass = new JPasswordField(20) {
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
	private JLabel title,newPassLabel,oldPassLabel,renewPassLabel,back;
	private ImageIcon backIcon;
	private RoundedCornerButton change,cancel;
	public PasswordChangeUI(Client c){
		cl=c;
		this.setSize(700, 550);
		this.setName("pUI");
		this.setLayout(null);
		title=new JLabel("パスワード変更");
		this.add(title);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("富士ポップ", Font.BOLD, 22));
		title.setBounds(10,10,300,30);
		oldPassLabel=new JLabel("旧パスワード：");
		this.add(oldPassLabel);
		oldPassLabel.setForeground(Color.WHITE);
		oldPassLabel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		oldPassLabel.setBounds(77,100,200,30);
		//oldPass=new JPasswordField();
		this.add(oldPass);
		oldPass.setFont(new Font("富士ポップ", Font.BOLD, 22));
		oldPass.setBounds(250,100,350,30);
		newPassLabel=new JLabel("新パスワード：");
		this.add(newPassLabel);
		newPassLabel.setForeground(Color.WHITE);
		newPassLabel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		newPassLabel.setBounds(77,200,350,30);
		//newPass=new JPasswordField();
		this.add(newPass);
		newPass.setFont(new Font("富士ポップ", Font.BOLD, 22));
		newPass.setBounds(250,200,350,30);
		renewPassLabel=new JLabel("新パスワード(再)：");
		this.add(renewPassLabel);
		renewPassLabel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		renewPassLabel.setBounds(30,300,350,30);
		renewPassLabel.setForeground(Color.WHITE);
		//renewPass=new JPasswordField();
		this.add(renewPass);
		renewPass.setFont(new Font("富士ポップ", Font.BOLD, 22));
		renewPass.setBounds(250,300,350,30);
		
		change=new RoundedCornerButton("変更");
		this.add(change);
		change.setFont(new Font("富士ポップ", Font.BOLD, 22));
		change.setBounds(80,430,200,50);
		change.addMouseListener(this);
		cancel=new RoundedCornerButton("キャンセル");
		this.add(cancel);
		cancel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		cancel.setBounds(400,430,200,50);
		cancel.addMouseListener(this);
		backIcon = new ImageIcon("パスワード変更.jpg");
		back=new JLabel(backIcon);
		this.add(back);
		back.setBounds(0,0,700,550);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==change){
			PasswordManagement pm=new PasswordManagement(cl);
			if(pm.checkRenew(oldPass.getPassword(),newPass.getPassword(),renewPass.getPassword()))
				cl.screenTransition((JPanel)this, "rUI");
		}
		else if(e.getSource()==cancel){
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
