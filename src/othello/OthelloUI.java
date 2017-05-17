//オセロUI部分
package othello;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OthelloUI extends JPanel implements MouseListener,ChangeListener,ActionListener{
	private JButton buttonArray[][],rule,giveup;
	private AbstractButton chooseStone[];
	private JCheckBox showAbleDrop;
	private ButtonGroup group;
	private JLabel timeLabel,showTime,showIni,enemy,bwPointTemp,blackPoint,whitePoint;
	private ImageIcon blackIcon[], whiteIcon[], boardIcon[];
	private int boardPoint[]=new int[2];
	private int board[][];
	private int hand[]=new int[6];
	private int handicap;
	private String enemyName;
	private Timer timer;
	private String[] connect;
	private int time=0;
	private int stonecount=0;
	boolean initiative;
	boolean finiFlag;
	Client cl;
	Othello othello = new Othello();
	public OthelloUI(Client c){
		/*othelloBoardPanel=createOthelloBoard(init,handi,enemyName);
		othelloFrame.add(othelloBoardPanel,null);
		othelloFrame.validate();*/
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じる場合の処理
		//this.setTitle("オセロ(仮)―通信要素無し");//ウィンドウのタイトル
		//setSize(700, 550);//ウィンドウのサイズを設定
		cl=c;
		finiFlag=true;
		this.setSize(700, 550);
		this.setLayout(null);
		setName("oUI");
		this.setSize(700, 550);
		//c = getContentPane();//フレームのペインを取得
		//アイコン設定(画像ファイルをアイコンとして使う)
		timer = new Timer(1000,this);
		boardPoint=othello.callBoardPoint();
		enemy=new JLabel();
		this.add(enemy);
		enemy.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		enemy.setBounds(25, 0,400,50);
		timeLabel=new JLabel("残り時間");
		this.add(timeLabel);
		timeLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
		timeLabel.setBounds(490, 275,400,50);
		showTime=new JLabel("");
		this.add(showTime);
		showTime.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));
		showTime.setBounds(485, 310,400,50);
		showIni=new JLabel();
		this.add(showIni);
		showIni.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		showIni.setBounds(450, 360,400,50);
		whiteIcon = new ImageIcon[7];
		whiteIcon[0] = new ImageIcon("White.jpg");
		whiteIcon[1] = new ImageIcon("White+1.jpg");
		whiteIcon[2] = new ImageIcon("White+2.jpg");
		whiteIcon[3] = new ImageIcon("White+3.jpg");
		whiteIcon[4] = new ImageIcon("White-1.jpg");
		whiteIcon[5] = new ImageIcon("White-2.jpg");
		whiteIcon[6] = new ImageIcon("White-3.jpg");
		blackIcon = new ImageIcon[7];
		blackIcon[0] = new ImageIcon("Black.jpg");
		blackIcon[1] = new ImageIcon("Black+1.jpg");
		blackIcon[2] = new ImageIcon("Black+2.jpg");
		blackIcon[3] = new ImageIcon("Black+3.jpg");
		blackIcon[4] = new ImageIcon("Black-1.jpg");
		blackIcon[5] = new ImageIcon("Black-2.jpg");
		blackIcon[6] = new ImageIcon("Black-3.jpg");
		boardIcon=new ImageIcon[2];
		boardIcon[0] = new ImageIcon("GreenFrame.jpg");
		boardIcon[1] = new ImageIcon("YellowFrame.jpg");
		showAbleDrop=new JCheckBox("設置可能場所の表示");
		this.add(showAbleDrop);
		showAbleDrop.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		showAbleDrop.setBounds(410, 235,400,50);
		showAbleDrop.addChangeListener(this);
		bwPointTemp=new JLabel("黒　　　：　　　白 更新まで");
		this.add(bwPointTemp);
		bwPointTemp.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		bwPointTemp.setBounds(50, 430,400,50);
		blackPoint=new JLabel(String.format("%+03d",boardPoint[1]));
		this.add(blackPoint);
		blackPoint.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		blackPoint.setBounds(110, 430,100,50);
		whitePoint=new JLabel(String.format("%+03d",boardPoint[0]));
		this.add(whitePoint);
		whitePoint.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		whitePoint.setBounds(160, 430,100,50);
		rule=new JButton("ルール確認");
		this.add(rule);
		rule.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		rule.setBounds(400,430,150,50);
		rule.addMouseListener(this);
		giveup=new JButton("投了");
		this.add(giveup);
		giveup.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22));
		giveup.setBounds(560,430,100,50);
		giveup.addMouseListener(this);
		buttonArray=new JButton[8][8];
		board=othello.callBoard();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				buttonArray[i][j]=new JButton();
				/*
				if(initiative){
					if(board[i][j]>0) buttonArray[i][j]=new JButton(whiteIcon[0]);
					else if(board[i][j]==-7) buttonArray[i][j]=new JButton(blackIcon[6]);
					else if(board[i][j]==-6) buttonArray[i][j]=new JButton(blackIcon[5]);
					else if(board[i][j]==-5) buttonArray[i][j]=new JButton(blackIcon[4]);
					else if(board[i][j]==-3) buttonArray[i][j]=new JButton(blackIcon[1]);
					else if(board[i][j]==-2) buttonArray[i][j]=new JButton(blackIcon[2]);
					else if(board[i][j]==-1) buttonArray[i][j]=new JButton(blackIcon[3]);
					else if(showAbleDrop.isSelected()){
						if(othello.checkDrop(i,j)) buttonArray[i][j]=new JButton(boardIcon[1]);
						else buttonArray[i][j]=new JButton(boardIcon[0]);
					}else buttonArray[i][j]=new JButton(boardIcon[0]);
				}else{
					if(board[i][j]<0) buttonArray[i][j]=new JButton(blackIcon[0]);
					else if(board[i][j]==1) buttonArray[i][j]=new JButton(whiteIcon[6]);
					else if(board[i][j]==2) buttonArray[i][j]=new JButton(whiteIcon[5]);
					else if(board[i][j]==3) buttonArray[i][j]=new JButton(whiteIcon[4]);
					else if(board[i][j]==5) buttonArray[i][j]=new JButton(whiteIcon[1]);
					else if(board[i][j]==6) buttonArray[i][j]=new JButton(whiteIcon[2]);
					else if(board[i][j]==7) buttonArray[i][j]=new JButton(whiteIcon[3]);
					else if(showAbleDrop.isSelected()){
						if(othello.checkDrop(i,j)) buttonArray[i][j]=new JButton(boardIcon[1]);
						else buttonArray[i][j]=new JButton(boardIcon[0]);
					}else buttonArray[i][j]=new JButton(boardIcon[0]);
				}
				*/
				this.add(buttonArray[i][j]);
				int y = i * 45+50;
				int x = j * 45+25;
				buttonArray[i][j].setBounds(x, y, 45, 45);//ボタンの大きさと位置を設定する．
				buttonArray[i][j].addMouseListener(this);//マウス操作を認識できるようにする
				buttonArray[i][j].setActionCommand(Integer.toString(i*8+j));//ボタンを識別するための名前(番号)を付加する
			}
		}
		chooseStone=new JToggleButton[6];
		group = new ButtonGroup();
		for(int i=0;i<6;i++){
			chooseStone[i]=new JToggleButton();
			chooseStone[i].setHorizontalTextPosition(JToggleButton.CENTER);
			chooseStone[i].setVerticalTextPosition(JToggleButton.BOTTOM);
			/*
			if(initiative){
				chooseStone[i]=new JToggleButton();
				chooseStone[i].setHorizontalTextPosition(JToggleButton.CENTER);
				chooseStone[i].setVerticalTextPosition(JToggleButton.BOTTOM);
			}else{
				chooseStone[i]=new JToggleButton();
				chooseStone[i].setHorizontalTextPosition(JToggleButton.CENTER);
				chooseStone[i].setVerticalTextPosition(JToggleButton.BOTTOM);
			}
			*/
			group.add(chooseStone[i]);
			this.add(chooseStone[i]);
			int y = 60+(i/3)*90;
			int x = 410+(i%3)*80;
			chooseStone[i].setBounds(x, y, 70, 80);//ボタンの大きさと位置を設定する．
		}
		chooseStone[0].setSelected(true);
		//timer.start();
	}
	
	
	public void updateDisp(){	// 画面を更新する、他の用途がなければrenewBoard()と統合
		renewBoard();
	}
	
	public void allDisp(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[i][j]==-7) buttonArray[i][j].setIcon(blackIcon[6]);
				else if(board[i][j]==-6) buttonArray[i][j].setIcon(blackIcon[5]);
				else if(board[i][j]==-5) buttonArray[i][j].setIcon(blackIcon[4]);
				else if(board[i][j]==-3) buttonArray[i][j].setIcon(blackIcon[1]);
				else if(board[i][j]==-2) buttonArray[i][j].setIcon(blackIcon[2]);
				else if(board[i][j]==-1) buttonArray[i][j].setIcon(blackIcon[3]);
				else if(board[i][j]==1) buttonArray[i][j].setIcon(whiteIcon[6]);
				else if(board[i][j]==2) buttonArray[i][j].setIcon(whiteIcon[5]);
				else if(board[i][j]==3) buttonArray[i][j].setIcon(whiteIcon[4]);
				else if(board[i][j]==5) buttonArray[i][j].setIcon(whiteIcon[1]);
				else if(board[i][j]==6) buttonArray[i][j].setIcon(whiteIcon[2]);
				else if(board[i][j]==7) buttonArray[i][j].setIcon(whiteIcon[3]);
			}
		}
	}
	
	public void acceptOperation(String command){	// プレイヤの操作を受付
		
	}
	//マウスクリック時の処理
	public void stateChanged(ChangeEvent e){
		updateDisp();
	}
	
	public void renewBoard(){
		board=othello.callBoard();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(initiative){
					if(board[i][j]>0) buttonArray[i][j].setIcon(whiteIcon[0]);
					else if(board[i][j]==-7) buttonArray[i][j].setIcon(blackIcon[6]);
					else if(board[i][j]==-6) buttonArray[i][j].setIcon(blackIcon[5]);
					else if(board[i][j]==-5) buttonArray[i][j].setIcon(blackIcon[4]);
					else if(board[i][j]==-3) buttonArray[i][j].setIcon(blackIcon[1]);
					else if(board[i][j]==-2) buttonArray[i][j].setIcon(blackIcon[2]);
					else if(board[i][j]==-1) buttonArray[i][j].setIcon(blackIcon[3]);
					else if(showAbleDrop.isSelected()){
						if(othello.checkDrop(i,j)) buttonArray[i][j].setIcon(boardIcon[1]);
						else buttonArray[i][j].setIcon(boardIcon[0]);
					}else buttonArray[i][j].setIcon(boardIcon[0]);
				}else{
					if(board[i][j]<0) buttonArray[i][j].setIcon(blackIcon[0]);
					else if(board[i][j]==1) buttonArray[i][j].setIcon(whiteIcon[6]);
					else if(board[i][j]==2) buttonArray[i][j].setIcon(whiteIcon[5]);
					else if(board[i][j]==3) buttonArray[i][j].setIcon(whiteIcon[4]);
					else if(board[i][j]==5) buttonArray[i][j].setIcon(whiteIcon[1]);
					else if(board[i][j]==6) buttonArray[i][j].setIcon(whiteIcon[2]);
					else if(board[i][j]==7) buttonArray[i][j].setIcon(whiteIcon[3]);
					else if(showAbleDrop.isSelected()){
						if(othello.checkDrop(i,j)) buttonArray[i][j].setIcon(boardIcon[1]);
						else buttonArray[i][j].setIcon(boardIcon[0]);
					}else buttonArray[i][j].setIcon(boardIcon[0]);
				}
			}
		}
		for (int i=0;i<6;i++){
			chooseStone[i].setText("×"+hand[i]);
		}
		if(initiative) showIni.setText("あなたは黒です");//デバッグ用、あとで消す
		else showIni.setText("あなたは白です");
	}
	
	public boolean checkWin(){
		int finish;
		allDisp();
		cl.initConnection();
		if(boardPoint[1]>boardPoint[0])
			finish = JOptionPane.showConfirmDialog(this, boardPoint[1]+":"+boardPoint[0]+"で黒の勝ちです。再戦しますか？","再戦",0);
		else if(boardPoint[1]<boardPoint[0])
			finish = JOptionPane.showConfirmDialog(this, boardPoint[1]+":"+boardPoint[0]+"で白の勝ちです。再戦しますか？","再戦",0);
		else if(boardPoint[1]==boardPoint[0] && handicap==5)
			finish = JOptionPane.showConfirmDialog(this, boardPoint[1]+":"+boardPoint[0]+"で白の引き分け勝ちです。再戦しますか？","再戦",0);
		else if(boardPoint[1]==boardPoint[0] && handicap==-5)
			finish = JOptionPane.showConfirmDialog(this, boardPoint[1]+":"+boardPoint[0]+"で黒の引き分け勝ちです。再戦しますか？","再戦",0);
		else
			finish = JOptionPane.showConfirmDialog(this, boardPoint[1]+":"+boardPoint[0]+"で引き分けです。再戦しますか？","再戦",0);						
		if (finish == JOptionPane.YES_OPTION){
			cl.send("9,1");
			do{
				connect=cl.waitConnection();
			}while(!connect[0].equals("9") && !connect[0].equals("13"));
			if(connect[0].equals("9")){
				if(connect[1].equals("1")){
					JOptionPane.showMessageDialog(null, "再戦をします。");
				}else{
					JOptionPane.showMessageDialog(null, "相手に再戦を拒否されました。ルーム検索画面に戻ります。");
					cl.screenTransition((JPanel)this, "rUI");
					return true;
				}
				cl.initConnection();
			}else{
				cl.initConnection();
				JOptionPane.showMessageDialog(null, "接続に失敗しました。ルーム検索画面に戻ります。");
				cl.screenTransition((JPanel)this, "rUI");
				return true;
			}
			initBoard(initiative,handicap,enemyName);
		}else{
			cl.send("9,0");
			cl.screenTransition((JPanel)this, "rUI");
			return true;
		}
		return false;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==rule){
			Rule rule = new Rule();
			rule.setLocation(this.getLocation().x+800, this.getLocation().y);
			rule.setVisible(true);
		}else if(e.getSource()==giveup){
			int finish = JOptionPane.showConfirmDialog(this, "本当に投了しますか？","投了",0);
			if (finish == JOptionPane.YES_OPTION){
				cl.screenTransition((JPanel)this, "rUI");
			}
		}else{
			JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．キャストを忘れずに
			String command = theButton.getActionCommand();//ボタンの名前を取り出す
			board=othello.callBoard();
			int num = Integer.parseInt(command);
			int i=0;
			int score=0;
			int finish;
			while(!chooseStone[i].isSelected() && i<7) i++;
			if(hand[i]>0){
				if(initiative){
					if(i==0) score=-3;
					else if(i==1) score=-2;
					else if(i==2) score=-1;
					else if(i==3) score=-5;
					else if(i==4) score=-6;
					else if(i==5) score=-7;
				}else{
					if(i==0) score=5;
					else if(i==1) score=6;
					else if(i==2) score=7;
					else if(i==3) score=3;
					else if(i==4) score=2;
					else if(i==5) score=1;
				}
				if(othello.stoneDrop(num/8, num%8, score)){
					timer.stop();
					time=0;
					hand[i]--;
					//System.out.print(Arrays.deepToString(board));
					othello.debugChangeInitiative();//デバッグ用
					initiative=!initiative;
					updateDisp();
					boardPoint=othello.callBoardPoint();
					stonecount++;
					bwPointTemp.setText("黒　　　：　　　白 更新まで"+(16-(stonecount%16)));
					if(initiative){
						blackPoint.setText(String.format("%+03d",boardPoint[1]));
						if(stonecount%16==0)whitePoint.setText(String.format("%+03d",boardPoint[0]));
					}
					else {
						whitePoint.setText(String.format("%+03d",boardPoint[0]));
						if(stonecount%16==0)blackPoint.setText(String.format("%+03d",boardPoint[0]));
					}
					
					if(!othello.checkDropBoard()){
						cl.send("8,"+num/8+","+num%8+","+score);
						finiFlag=checkWin();
					}
					if(!othello.checkDropSingleBoard()&&!finiFlag){
						JOptionPane.showMessageDialog(null, "置き場がないためパスします");
						cl.send("7,-1,-1,-1");
						connect=cl.waitConnection();
						while(!connect[0].equals("7")&&!connect[0].equals("8")){
							cl.initConnection();
							connect=cl.waitConnection();
						}
						if(connect[0].equals("7")){
							if(othello.stoneDrop(Integer.parseInt(connect[1]), Integer.parseInt(connect[2]), Integer.parseInt(connect[3]))){
								JOptionPane.showMessageDialog(null, "あなたの番です");
								stonecount++;
								bwPointTemp.setText("黒　　　：　　　白 更新まで"+(16-(stonecount%16)));
								boardPoint=othello.callBoardPoint();
								if(initiative){
									blackPoint.setText(String.format("%+03d",boardPoint[1]));
									if(stonecount%16==0)whitePoint.setText(String.format("%+03d",boardPoint[0]));
								}
								else {
									whitePoint.setText(String.format("%+03d",boardPoint[0]));
									if(stonecount%16==0)blackPoint.setText(String.format("%+03d",boardPoint[0]));
								}
							}else{
								JOptionPane.showMessageDialog(null, "相手は置き場がなかったようです");
							}
							cl.initConnection();
						}else{
							finiFlag=checkWin();
						}
						othello.debugChangeInitiative();//デバッグ用,本来なら通信
						initiative=!initiative;
						updateDisp();
					}else if(!finiFlag){
						cl.send("7,"+num/8+","+num%8+","+score);
						connect=cl.waitConnection();
						while(!connect[0].equals("7")&&!connect[0].equals("8")){
							cl.initConnection();
							connect=cl.waitConnection();
						}
						if(connect[0].equals("7")){
							if(othello.stoneDrop(Integer.parseInt(connect[1]), Integer.parseInt(connect[2]), Integer.parseInt(connect[3]))){
								JOptionPane.showMessageDialog(null, "あなたの番です");
								stonecount++;
								bwPointTemp.setText("黒　　　：　　　白 更新まで"+(16-(stonecount%16)));
								boardPoint=othello.callBoardPoint();
								if(initiative){
									blackPoint.setText(String.format("%+03d",boardPoint[1]));
									if(stonecount%16==0)whitePoint.setText(String.format("%+03d",boardPoint[0]));
								}
								else {
									whitePoint.setText(String.format("%+03d",boardPoint[0]));
									if(stonecount%16==0)blackPoint.setText(String.format("%+03d",boardPoint[0]));
								}
							}else{
								//JOptionPane.showMessageDialog(null, "相手は置き場がなかったようです");
							}
							cl.initConnection();
						}else{
							finiFlag=checkWin();
						}
						
					}
					if(!finiFlag){
						timer.start();
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "選択された石は手元にありません、別の石を選択してください");
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}//マウスがオブジェクトに入ったときの処理
	public void mouseExited(MouseEvent e) {}//マウスがオブジェクトから出たときの処理
	public void mousePressed(MouseEvent e) {}//マウスでオブジェクトを押したときの処理
	public void mouseReleased(MouseEvent e) {}//マウスで押していたオブジェクトを離したときの処理
	
	

	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		String min=String.format("%02d",(300-time)/60);
		String sec=String.format("%02d",(60-time%60)%60);
		showTime.setText(min+":"+sec);
		if(time>299){
			timer.stop();
			int finish = JOptionPane.showConfirmDialog(this, "時間切れです。再戦しますか？","再戦",0);
			if (finish == JOptionPane.YES_OPTION){
				initBoard(initiative,handicap,enemyName);
			}else{
				cl.screenTransition((JPanel)this, "rUI");
			}
		}else{
			time++;
		}
			
	}
	
	public void initBoard(boolean init,int handi,String eName){
		enemyName=eName;
		initiative=init;
		handicap=handi;
		stonecount=0;
		hand=othello.initBoard(initiative,handicap);
		enemy.setText("対戦相手:"+enemyName);
		boardPoint=othello.callBoardPoint();
		bwPointTemp.setText("黒　　　：　　　白 更新まで"+(16-(stonecount%16)));
		blackPoint.setText(String.format("%+03d",boardPoint[1]));
		whitePoint.setText(String.format("%+03d",boardPoint[0]));
		for(int i=0;i<6;i++) hand[i]*=2;//デバッグ用手駒増やし
		if(initiative){
			showIni.setText("あなたは黒です");
			for(int i=0;i<6;i++){
				chooseStone[i].setIcon(blackIcon[i+1]);
				chooseStone[i].setText("×"+hand[i]);
			}
		}else{
			showIni.setText("あなたは白です");
			for(int i=0;i<6;i++){
				chooseStone[i].setIcon(whiteIcon[i+1]);
				chooseStone[i].setText("×"+hand[i]);
			}
		}
		finiFlag=false;
		updateDisp();
		time=0;
		timer.start();
	}
	
}
