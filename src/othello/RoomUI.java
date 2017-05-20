package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RoomUI extends JPanel implements MouseListener{
	Client cl;
	OthelloUI oUI;
	private String[] connect,aikoLabel,check;
	private String[] condi={"条件指定：なし","ハンデ：なし","合言葉：あり"
			,"作成者：1子局","作成者：2子局","作成者：3子局","作成者：4子局","作成者：引き分け勝ち"
			,"入室者：1子局","入室者：2子局","入室者：3子局","入室者：4子局","入室者：引き分け勝ち"};
	private JButton rCreate,rule,passChange,renew,exitButton,roomButton[];
	private JLabel title,roomLabel[];
	private JComboBox<String> condiBox;
	private RoundButton rb[];
	private ButtonGroup rbGp;
	private ImageIcon pageIcon[];
	private int page;
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
		condiBox=new JComboBox<String>(condi);
		this.add(condiBox);
		condiBox.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
		condiBox.setBounds(100,10,210,30);
		rCreate=new JButton("部屋作成");
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
		roomLabel=new JLabel[8*4];
		
		aikoLabel=new String[8];
		for(int i=0;i<8;i++){
			aikoLabel[i]=new String();
			roomLabel[i*4]=new JLabel("ユーザ名");
			this.add(roomLabel[i*4]);
			roomLabel[i*4].setBounds(25+(i%4)*160,65+(i/4-i/8*2)*160,150,20);
			roomLabel[i*4].addMouseListener(this);
			roomLabel[i*4].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			roomLabel[i*4].setHorizontalAlignment(JLabel.CENTER);
			if(i>7) roomLabel[i*4].setVisible(false);
			roomLabel[i*4+1]=new JLabel("名前");
			this.add(roomLabel[i*4+1]);
			roomLabel[i*4+1].setBounds(25+(i%4)*160,85+(i/4-i/8*2)*160,150,20);
			roomLabel[i*4+1].addMouseListener(this);
			roomLabel[i*4+1].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			roomLabel[i*4+1].setHorizontalAlignment(JLabel.CENTER);
			if(i>7) roomLabel[i*4+1].setVisible(false);
			roomLabel[i*4+2]=new JLabel("ハンデ");
			this.add(roomLabel[i*4+2]);
			roomLabel[i*4+2].setBounds(15+(i%4)*160,125+(i/4-i/8*2)*160,170,20);
			roomLabel[i*4+2].addMouseListener(this);
			roomLabel[i*4+2].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 14));
			roomLabel[i*4+2].setHorizontalAlignment(JLabel.CENTER);
			if(i>7) roomLabel[i*4+2].setVisible(false);
			roomLabel[i*4+3]=new JLabel("合言葉");
			this.add(roomLabel[i*4+3]);
			roomLabel[i*4+3].setBounds(25+(i%4)*160,145+(i/4-i/8*2)*160,150,20);
			roomLabel[i*4+3].addMouseListener(this);
			roomLabel[i*4+3].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			roomLabel[i*4+3].setHorizontalAlignment(JLabel.CENTER);
			if(i>7) roomLabel[i*4+3].setVisible(false);
			roomButton[i]=new JButton("入室");
			this.add(roomButton[i]);
			roomButton[i].setBounds(25+(i%4)*160,175+(i/4-i/8*2)*160,150,30);
			roomButton[i].addMouseListener(this);
			roomButton[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			roomButton[i].setVerticalAlignment(JLabel.CENTER);
			if(i>7) roomButton[i].setVisible(false);
			roomButton[i].setActionCommand("-1,"+i);
			
		}
		roomButton[0].setText("入室");
		pageIcon = new ImageIcon[7];
		pageIcon[0] = new ImageIcon("page1.png");
		pageIcon[1] = new ImageIcon("page2.png");
		pageIcon[2] = new ImageIcon("page3.png");
		pageIcon[3] = new ImageIcon("page4.png");
		pageIcon[4] = new ImageIcon("page5.png");
		pageIcon[5] = new ImageIcon("page6.png");
		pageIcon[6] = new ImageIcon("page7.png");
		rb=new RoundButton[7];
		rbGp=new ButtonGroup();
		for(int i=0;i<7;i++){
			rb[i]=new RoundButton(pageIcon[i]);
			this.add(rb[i]);
			rb[i].setBounds(100+i*70,380,50,50);
			rb[i].addMouseListener(this);
			rbGp.add(rb[i]);
		}
		rb[0].isSelected();
		page=1;
		
	}
	
	public void roomInfoButton(String no,String teki,String hande,String aikotoba,String aiko,int noButton){
		try{
			roomButton[noButton].setActionCommand(no+","+noButton);
			roomLabel[noButton*4+1].setText(teki);
			if(hande.equals("0"))roomLabel[noButton*4+2].setText("ハンデ：なし");
			if(hande.equals("1"))roomLabel[noButton*4+2].setText("作成者：1子局");
			if(hande.equals("2"))roomLabel[noButton*4+2].setText("作成者：2子局");
			if(hande.equals("3"))roomLabel[noButton*4+2].setText("作成者：3子局");
			if(hande.equals("4"))roomLabel[noButton*4+2].setText("作成者：4子局");
			if(hande.equals("5"))roomLabel[noButton*4+2].setText("作成者：引き分け勝ち");
			if(hande.equals("-1"))roomLabel[noButton*4+2].setText("入室者：1子局");
			if(hande.equals("-2"))roomLabel[noButton*4+2].setText("入室者：2子局");
			if(hande.equals("-3"))roomLabel[noButton*4+2].setText("入室者：3子局");
			if(hande.equals("-4"))roomLabel[noButton*4+2].setText("入室者：4子局");
			if(hande.equals("-5"))roomLabel[noButton*4+2].setText("入室者：引き分け勝ち");
			if(aikotoba.equals("0"))roomLabel[noButton*4+3].setText("合言葉：なし");
			else roomLabel[noButton*4+3].setText("合言葉：あり");
			aikoLabel[noButton]=aiko;
		}catch(NullPointerException e){
			
		}
	}
	
	public void roomClean(){
		for(int i=0;i<8;i++){
			roomButton[i].setActionCommand("-1,"+i);
			roomLabel[i*4+1].setText("名前");
			roomLabel[i*4+2].setText("ハンデ");
			roomLabel[i*4+3].setText("合言葉");
			aikoLabel[i]="";
		}
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
				cl.send("10");
				System.exit(0);
			}
		}else if(e.getSource()==rb[0]||e.getSource()==rb[1]||e.getSource()==rb[2]||e.getSource()==rb[3]||e.getSource()==rb[4]||e.getSource()==rb[5]||e.getSource()==rb[6]){
			roomClean();
			int con,ai=0,count=0;
			while(!rb[count].isSelected()) count++;
			count++;
			page=count;
			con=changeStrHand(condiBox.getSelectedItem().toString());
			if(con==-6){
				con=0;
				ai=1;
			}
			cl.send("3,"+page+","+con+","+ai);
			connect=cl.waitConnection();
			while(!connect[0].equals("15")&&!connect[0].equals("11")){
				cl.initConnection();
				connect=cl.waitConnection();
			}
			cl.initConnection();
			for(int i=0;i<(connect.length-1)/5;i++){
				roomInfoButton(connect[i*5+2],connect[i*5+1],connect[i*5+3],connect[i*5+4],connect[i*5+5],i);
			}
			this.repaint();
		}else if(e.getSource()==renew){
			int con,ai=0;
			con=changeStrHand(condiBox.getSelectedItem().toString());
			if(con==-6){
				con=0;
				ai=1;
			}
			cl.send("3,"+page+","+con+","+ai);
			connect=cl.waitConnection();
			while(!connect[0].equals("15")&&!connect[0].equals("11")){
				cl.initConnection();
				connect=cl.waitConnection();
			}
			cl.initConnection();
			roomClean();
			for(int i=0;i<(connect.length-1)/5;i++){
				roomInfoButton(connect[i*5+2],connect[i*5+1],connect[i*5+3],connect[i*5+4],connect[i*5+5],i);
				/*
				roomLabel[i+1].setText("仮");//暫定
				String clabel=changeStrHand(Integer.parseInt(connect[2]));
				roomLabel[i+2].setText(clabel);
				if(Integer.parseInt(connect[2])==6)roomLabel[i+3].setText("合言葉：あり");
				else roomLabel[i+3].setText("合言葉：なし");
				roomButton[i].setActionCommand(connect[1]+","+i);
				roomInfoButton("4","ジョンソン","5","1",2);
				*/
			}
			this.repaint();
		}else{
			JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．キャストを忘れずに
			String[] command = theButton.getActionCommand().split(",",0);//ボタンの名前を取り出す
			if (!command[0].equals("-1")){
				if(roomLabel[Integer.parseInt(command[1])*4+3].getText().equals("合言葉：あり")){
					String value = JOptionPane.showInputDialog(this, "合言葉を入力してください");
					if(value == null || !value.equals(aikoLabel[Integer.parseInt(command[1])])){
						JOptionPane.showMessageDialog(null, "合言葉が誤っています");
					}else{
						cl.send("5,"+command[0]);
						connect=cl.waitConnection();
						while(!connect[0].equals("12")&&!connect[0].equals("15")){
							cl.initConnection();
							connect=cl.waitConnection();
						}
						if(connect[0].equals("12")){
							check=cl.waitConnection();
							while(!check[0].equals("14")&&!connect[0].equals("15")){
								cl.initConnection();
								check=cl.waitConnection();
							}
							if(check[0].equals("14")){
								int han=Integer.parseInt(connect[3])*(-1);
								boolean init=false;
								if(Integer.parseInt(connect[2])==1){
									init=true;
									han*=-1;
								}
								oUI.initBoard(init,han,connect[1]);
								if(Integer.parseInt(connect[2])==1){
									cl.screenTransition((JPanel)this, "oUI");
									JOptionPane.showMessageDialog(null, "あなたは先攻です。");
								}else{
									cl.screenTransition((JPanel)this, "oUI");
									JOptionPane.showMessageDialog(null, "あなたは後攻です。");
									oUI.waitEnemy();
								}
							}
							
						}else{
							JOptionPane.showMessageDialog(null, "接続することができませんでした");
						}
					}
				}else{
					cl.send("5,"+command[0]);
					connect=cl.waitConnection();
					while(!connect[0].equals("12")&&!connect[0].equals("15")){
						cl.initConnection();
						connect=cl.waitConnection();
					}
					if(connect[0].equals("12")){
						check=cl.waitConnection();
						while(!check[0].equals("14")&&!connect[0].equals("15")){
							cl.initConnection();
							check=cl.waitConnection();
						}
						if(check[0].equals("14")){
							int han=Integer.parseInt(connect[3]);
							boolean init=false;
							if(Integer.parseInt(connect[2])==1){
								init=true;
								han*=-1;
							}
							oUI.initBoard(init,han,connect[1]);
							if(Integer.parseInt(connect[2])==1){
								cl.screenTransition((JPanel)this, "oUI");
								JOptionPane.showMessageDialog(null, "あなたは先攻です。");
							}else{
								cl.screenTransition((JPanel)this, "oUI");
								JOptionPane.showMessageDialog(null, "あなたは後攻です。");
								oUI.waitEnemy();
							}
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "接続することができませんでした");
					}
				}
			}
		}
		
	}
	
	public String changeStrHand(int ha){
		if(ha==0)return condi[1];
		else if(ha==6)return condi[0];
		else if(ha==5)return condi[7];
		else if(ha==1)return condi[3];
		else if(ha==2)return condi[4];
		else if(ha==3)return condi[5];
		else if(ha==4)return condi[6];
		else if(ha==-5)return condi[12];
		else if(ha==-1)return condi[8];
		else if(ha==-2)return condi[9];
		else if(ha==-3)return condi[10];
		else if(ha==-4)return condi[11];
		else return condi[1];
	}
	
	public int changeStrHand(String ha){
		if(ha.equals(condi[0])) return 6;
		else if(ha.equals(condi[1])) return 0;
		else if(ha.equals(condi[3])) return 1;
		else if(ha.equals(condi[4])) return 2;
		else if(ha.equals(condi[5])) return 3;
		else if(ha.equals(condi[6])) return 4;
		else if(ha.equals(condi[7])) return 5;
		else if(ha.equals(condi[8])) return -1;
		else if(ha.equals(condi[9])) return -2;
		else if(ha.equals(condi[10])) return -3;
		else if(ha.equals(condi[11])) return -4;
		else if(ha.equals(condi[12])) return -5;
		else return -6;
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
