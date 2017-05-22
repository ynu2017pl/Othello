package othello;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RoomCreateUI extends JPanel implements MouseListener{
	OthelloUI oUI;
	Client cl;
	private String[] connect,check;
	private RoundedCornerButton roomCreButton,cancel;
	private JLabel handi,aiko,dIni,title,back;
	private ImageIcon backIcon;
	private JRadioButton handiRadio1,handiRadio2,handiRadio3,aikoRadio1,aikoRadio2,dRadio1,dRadio2;
	private JComboBox<String> handiCombo;
	private JTextField aikoText = new JTextField(20) {
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
	private boolean initiative=true;
	private int handicap=0;
	private String enemyName="エネミー";
	private ButtonGroup handiGroup,aikoGroup,dGroup;
	public RoomCreateUI(Client c,OthelloUI oi){
		oUI=oi;
		cl=c;
		this.setSize(700, 550);
		this.setName("rcUI");
		this.setLayout(null);
		title=new JLabel("ルーム作成");
		this.add(title);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("富士ポップ", Font.BOLD, 22));
		title.setBounds(10,10,150,30);
		handi=new JLabel("ハンデ");
		this.add(handi);
		handi.setForeground(Color.WHITE);
		handi.setFont(new Font("富士ポップ", Font.BOLD, 22));
		handi.setBounds(10,50,200,50);
		handiRadio1=new JRadioButton("なし");
		this.add(handiRadio1);
		handiRadio1.setForeground(Color.WHITE);
		handiRadio1.setOpaque(false);
		handiRadio1.setFont(new Font("富士ポップ", Font.BOLD, 22));
		handiRadio1.setBounds(25,100,75,50);
		handiRadio2=new JRadioButton("作成者にハンデ");
		this.add(handiRadio2);
		handiRadio2.setForeground(Color.WHITE);
		handiRadio2.setOpaque(false);
		handiRadio2.setFont(new Font("富士ポップ", Font.BOLD, 22));
		handiRadio2.setBounds(115,100,200,50);
		handiRadio3=new JRadioButton("入室者にハンデ");
		this.add(handiRadio3);
		handiRadio3.setForeground(Color.WHITE);
		handiRadio3.setOpaque(false);
		handiRadio3.setFont(new Font("富士ポップ", Font.BOLD, 22));
		handiRadio3.setBounds(310,100,200,50);
		handiGroup = new ButtonGroup();
		handiGroup.add(handiRadio1);
		handiGroup.add(handiRadio2);
		handiGroup.add(handiRadio3);
		handiRadio1.setSelected(true);
		String[] combodata={"引き分け勝ち","1子局","2子局","3子局","4子局"};
		handiCombo=new JComboBox<String>(combodata);
		this.add(handiCombo);
		handiCombo.setBackground(Color.WHITE);
		handiCombo.setFont(new Font("富士ポップ", Font.BOLD, 18));
		handiCombo.setBounds(510,100,140,50);
		
		aiko=new JLabel("合言葉");
		this.add(aiko);
		aiko.setForeground(Color.WHITE);
		aiko.setFont(new Font("富士ポップ", Font.BOLD, 22));
		aiko.setBounds(10,200,700,50);
		aikoRadio1=new JRadioButton("なし");
		this.add(aikoRadio1);
		aikoRadio1.setForeground(Color.WHITE);
		aikoRadio1.setOpaque(false);
		aikoRadio1.setFont(new Font("富士ポップ", Font.BOLD, 22));
		aikoRadio1.setBounds(50,250,75,50);
		aikoRadio2=new JRadioButton("あり");
		this.add(aikoRadio2);
		aikoRadio2.setForeground(Color.WHITE);
		aikoRadio2.setOpaque(false);
		aikoRadio2.setFont(new Font("富士ポップ", Font.BOLD, 22));
		aikoRadio2.setBounds(260,250,75,50);
		aikoGroup = new ButtonGroup();
		aikoGroup.add(aikoRadio1);
		aikoGroup.add(aikoRadio2);
		aikoRadio1.setSelected(true);
		this.add(aikoText);
		aikoText.setFont(new Font("富士ポップ", Font.BOLD, 18));
		aikoText.setBounds(350,250,300,50);
		/*
		dIni=new JLabel("デバッグ");
		this.add(dIni);
		dIni.setFont(new Font("富士ポップ", Font.BOLD, 20));
		dIni.setBounds(10,300,700,50);
		dRadio1=new JRadioButton("黒");
		this.add(dRadio1);
		dRadio1.setFont(new Font("富士ポップ", Font.BOLD, 18));
		dRadio1.setBounds(50,350,65,50);
		dRadio2=new JRadioButton("白");
		this.add(dRadio2);
		dRadio2.setFont(new Font("富士ポップ", Font.BOLD, 18));
		dRadio2.setBounds(230,350,65,50);
		dGroup = new ButtonGroup();
		dGroup.add(dRadio1);
		dGroup.add(dRadio2);
		dRadio1.setSelected(true);
		dText=new JTextField();
		this.add(dText);
		dText.setFont(new Font("富士ポップ", Font.BOLD, 18));
		dText.setBounds(320,350,300,50);
		*/
		roomCreButton=new RoundedCornerButton("オセロ開始");
		this.add(roomCreButton);
		roomCreButton.setFont(new Font("富士ポップ", Font.BOLD, 22));
		roomCreButton.setBounds(80,430,200,50);
		roomCreButton.addMouseListener(this);
		cancel=new RoundedCornerButton("キャンセル");
		this.add(cancel);
		cancel.setFont(new Font("富士ポップ", Font.BOLD, 22));
		cancel.setBounds(400,430,200,50);
		cancel.addMouseListener(this);
		//this.setBackground(Color.BLACK);//各ボタンの範囲確認用
		backIcon = new ImageIcon("ルーム作成背景.jpg");
		back=new JLabel(backIcon);
		this.add(back);
		back.setBounds(0,0,700,550);
	}
	
public boolean battleStart(){
	while(!connect[0].equals("12")){
		connect=cl.waitConnection();
	}
	cl.initConnection();
	int start;
	start = JOptionPane.showConfirmDialog(this, connect[1]+"と戦いますか？","対戦開始",0);
	if (start == JOptionPane.YES_OPTION){
		cl.send("6,1");
		do{
			check=cl.waitConnection();
		}while(!check[0].equals("14")&&!check[0].equals("15")&&!check[0].equals("13")&&!check[0].equals("8"));
		if(check[0].equals("14")) {
			cl.initConnection();
			return true;
		}else if(check[0].equals("13")||check[0].equals("8")){
			JOptionPane.showMessageDialog(null, "時間切れです、もう一度部屋を作成してください");
			cl.initConnection();
			cl.screenTransition((JPanel)this, "rUI");
			return true;
		}else{
			cl.initConnection();
			connect=cl.waitConnection();
			return false;
		}
	}else{
		cl.send("6,0");
		do{
			check=cl.waitConnection();
		}while(!check[0].equals("15")&&!check[0].equals("13")&&!check[0].equals("8"));
		if(check[0].equals("13")||check[0].equals("8")){
			JOptionPane.showMessageDialog(null, "時間切れです、もう一度部屋を作成してください");
			cl.initConnection();
			cl.screenTransition((JPanel)this, "rUI");
			return true;
		}else{
			cl.initConnection();
			connect=cl.waitConnection();
			return false;
		}
	}
}
	
	@Override	
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==roomCreButton){
			int che=0;
			if(handiRadio1.isSelected()){
				handicap=0;
			}else{
				String check=(String)handiCombo.getSelectedItem();
				if(check=="引き分け勝ち")handicap=5;
				else if(check=="1子局")handicap=1;
				else if(check=="2子局")handicap=2;
				else if(check=="3子局")handicap=3;
				else if(check=="4子局")handicap=4;
				if(handiRadio3.isSelected()) {
					handicap*=-1;
				}
			}
			if(aikoRadio2.isSelected())che=1;
			cl.initConnection();
			//ここで通信待機、敵の名前やら先手後手情報が来るまで待つ
			if(!aikoText.getText().equals("")){
				cl.send("4,"+handicap+","+che+","+aikoText.getText());
			}else{
				cl.send("4,"+handicap+","+che+",名称不明");
			}
			connect=cl.waitConnection();
			while(true){
				if (battleStart()) break;
			}
			/*
			if(dRadio1.isSelected()){
				initiative=true;
			}else{
				initiative=false;
			}
			enemyName=dText.getText();
			*/
			if(!check[0].equals("13")&&!check[0].equals("8")){
				boolean init=true;
				int hand=Integer.parseInt(connect[3]);
				if(Integer.parseInt(connect[2])==0){
					hand*=-1;
					init=false;
				}
				oUI.initBoard(init,hand,connect[1]);
				if(Integer.parseInt(connect[2])==1){
					cl.screenTransition((JPanel)this, "oUI");
					JOptionPane.showMessageDialog(null, "あなたは先攻です。");
				}else{
					cl.screenTransition((JPanel)this, "oUI");
					JOptionPane.showMessageDialog(null, "あなたは後攻です。");
					oUI.waitEnemy();
				}
			}
		}else if(e.getSource()==cancel){
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

