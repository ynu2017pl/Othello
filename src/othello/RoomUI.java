package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RoomUI extends JPanel implements MouseListener{
	Client cl;
	OthelloUI oUI;
	private String[] connect;
	private JButton rCreate,rule,passChange,renew,exitButton,roomButton[];
	private JLabel title,roomLabel[];
	private JComboBox<String> condiBox;
	private RoundButton rb[];
	private ButtonGroup rbGp;
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
		
		roomButton=new JButton[56];
		roomLabel=new JLabel[56*4];
		for(int i=0;i<56;i++){
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
			roomLabel[i*4+2].setBounds(25+(i%4)*160,125+(i/4-i/8*2)*160,150,20);
			roomLabel[i*4+2].addMouseListener(this);
			roomLabel[i*4+2].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
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
			roomButton[i].setActionCommand("-1");
		}
		roomButton[0].setText("入室");
		rb=new RoundButton[7];
		rbGp=new ButtonGroup();
		for(int i=0;i<7;i++){
			rb[i]=new RoundButton(Integer.toString(i+1));
			this.add(rb[i]);
			rb[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
			rb[i].setBounds(100+i*70,380,50,50);
			rb[i].addMouseListener(this);
			rbGp.add(rb[i]);
		}
		rb[0].isSelected();
		
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
		}else if(e.getSource()==rb[0]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=0;i<8;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[1]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=8;i<16;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[2]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=16;i<24;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[3]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=24;i<32;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[4]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=32;i<40;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[5]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=40;i<48;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==rb[6]){
			for(int i=0;i<56;i++){
				roomButton[i].setVisible(false);
			}
			for(int i=48;i<56;i++){
				roomButton[i].setVisible(true);
			}
		}else if(e.getSource()==renew){
			int con,count=0;
			while(rb[count].isSelected()) count++;
			count++;
			if(condiBox.getSelectedItem().equals("条件指定：なし")) con=0;
			else if(condiBox.getSelectedItem().equals("ハンデ:引き分け勝ち")) con=5;
			else if(condiBox.getSelectedItem().equals("ハンデ:1子局")) con=1;
			else if(condiBox.getSelectedItem().equals("ハンデ:2子局")) con=2;
			else if(condiBox.getSelectedItem().equals("ハンデ:3子局")) con=3;
			else if(condiBox.getSelectedItem().equals("ハンデ:4子局")) con=4;
			else con=6;
			cl.send("3,"+count+","+con);
			connect=cl.waitConnection();
			while(!connect[0].equals("3")&&!connect[0].equals("11")){
				cl.initConnection();
				connect=cl.waitConnection();
			}
			cl.initConnection();
			for(int i=0;i<connect.length/3;i++){
				roomLabel[i+1].setText("仮");//暫定
				if(connect[2].equals("0")) {
					roomLabel[i+2].setText("ハンデ:なし");
					roomLabel[i+3].setText("合言葉：なし");
				}else if(connect[2].equals("1")) {
					roomLabel[i+2].setText("ハンデ:1子局");
					roomLabel[i+3].setText("合言葉：なし");
				}else if(connect[2].equals("2")) {
					roomLabel[i+2].setText("ハンデ:2子局");
					roomLabel[i+3].setText("合言葉：なし");
				}else if(connect[2].equals("3")) {
					roomLabel[i+2].setText("ハンデ:3子局");
					roomLabel[i+3].setText("合言葉：なし");
				}else if(connect[2].equals("4")) {
					roomLabel[i+2].setText("ハンデ:4子局");
					roomLabel[i+3].setText("合言葉：なし");
				}else if(connect[2].equals("5")) {
					roomLabel[i+2].setText("ハンデ：引き分け勝ち");
					roomLabel[i+3].setText("合言葉：なし");
				}else{
					roomLabel[i+2].setText("ハンデ：なし");
					roomLabel[i+3].setText("合言葉：あり");
				}
				roomButton[i].setActionCommand(connect[1]);
			}
		}else{
			JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．キャストを忘れずに
			String command = theButton.getActionCommand();//ボタンの名前を取り出す
			if (!command.equals("-1")){
				
				cl.screenTransition((JPanel)this, "oUI");
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
