package othello;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class OthelloUI extends JFrame implements MouseListener{
	private JButton buttonArray[][];
	private AbstractButton chooseStone[];
	private ButtonGroup group;
	private Container c;
	private ImageIcon blackIcon[], whiteIcon, boardIcon;
	Othello othello = new Othello();
	public OthelloUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じる場合の処理
		setTitle("ネットワーク対戦型オセロゲーム");//ウィンドウのタイトル
		setSize(1200, 900);//ウィンドウのサイズを設定
		c = getContentPane();//フレームのペインを取得
		//アイコン設定(画像ファイルをアイコンとして使う)
		whiteIcon = new ImageIcon("White.jpg");
		blackIcon = new ImageIcon[7];
		blackIcon[0] = new ImageIcon("Black.jpg");
		blackIcon[1] = new ImageIcon("Black-1.jpg");
		blackIcon[2] = new ImageIcon("Black-2.jpg");
		blackIcon[3] = new ImageIcon("Black-3.jpg");
		blackIcon[4] = new ImageIcon("Black+1.jpg");
		blackIcon[5] = new ImageIcon("Black+2.jpg");
		blackIcon[6] = new ImageIcon("Black+3.jpg");
		boardIcon = new ImageIcon("GreenFrame.jpg");
		c.setLayout(null);
		buttonArray=new JButton[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(othello.board[i][j]>0) buttonArray[i][j]=new JButton(whiteIcon);
				if(othello.board[i][j]<0) buttonArray[i][j]=new JButton(blackIcon[0]);
				if(othello.board[i][j]==0) buttonArray[i][j]=new JButton(boardIcon);
				c.add(buttonArray[i][j]);
				int y = i * 45+50;
				int x = j * 45;
				buttonArray[i][j].setBounds(x, y, 45, 45);//ボタンの大きさと位置を設定する．
				buttonArray[i][j].addMouseListener(this);//マウス操作を認識できるようにする
				buttonArray[i][j].setActionCommand(Integer.toString(i*8+j));//ボタンを識別するための名前(番号)を付加する
			}
		}
		chooseStone=new JToggleButton[6];
		group = new ButtonGroup();
		for(int i=0;i<6;i++){
			chooseStone[i]=new JToggleButton(blackIcon[i+1]);
			group.add(chooseStone[i]);
			c.add(chooseStone[i]);
			int y = 50+(i/3)*100;
			int x = 400+(i%3)*100;
			chooseStone[i].setBounds(x, y, 90, 90);//ボタンの大きさと位置を設定する．
			chooseStone[i].addMouseListener(this);//マウス操作を認識できるようにする
			chooseStone[i].setActionCommand(Integer.toString(100+i));//ボタンを識別するための名前(番号)を付加する
		}
	}
	
	public void renewBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(othello.board[i][j]>0) buttonArray[i][j].setIcon(whiteIcon);
				if(othello.board[i][j]<0) buttonArray[i][j].setIcon(blackIcon[0]);
				if(othello.board[i][j]==0) buttonArray[i][j].setIcon(boardIcon);
			}
		}
	}
	
	public void connectServer(String ipAddress, int port){	// サーバに接続
	}
	public void sendMessage(String msg){	// サーバに操作情報を送信
	}
	public void receiveMessage(String msg){	// メッセージの受信
	}
	public void updateDisp(){	// 画面を更新する
		renewBoard();
	}
	public void acceptOperation(String command){	// プレイヤの操作を受付
	}
	//マウスクリック時の処理
	public void mouseClicked(MouseEvent e) {
		JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．キャストを忘れずに
		String command = theButton.getActionCommand();//ボタンの名前を取り出す
		int num = Integer.parseInt(command);
		System.out.println("マウスがクリックされました。押されたボタンは " + command + "です。");//テスト用に標準出力
		othello.stoneDrop(num/8, num%8, -7);
		updateDisp();
		System.out.print(Arrays.deepToString(othello.board));
	}
	public void mouseEntered(MouseEvent e) {}//マウスがオブジェクトに入ったときの処理
	public void mouseExited(MouseEvent e) {}//マウスがオブジェクトから出たときの処理
	public void mousePressed(MouseEvent e) {}//マウスでオブジェクトを押したときの処理
	public void mouseReleased(MouseEvent e) {}//マウスで押していたオブジェクトを離したときの処理
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		OthelloUI o_UI=new OthelloUI();
		o_UI.setVisible(true);
	}
	
}
