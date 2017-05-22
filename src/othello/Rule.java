package othello;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rule extends JFrame implements MouseListener{
	private JLabel label1,label2;
	private RoundedCornerButton next,back;
	private ImageIcon ruleIcon[],backIcon;
	private int i=0;
	public Rule(){
		this.setSize(700,650);
		next=new RoundedCornerButton("次へ");
		this.add(next);
		next.setBounds(550, 550, 100, 50);
		next.setFont(new Font("富士ポップ", Font.BOLD, 22));
		next.addMouseListener(this);
		back=new RoundedCornerButton("前へ");
		this.add(back);
		back.setBounds(50, 550, 100, 50);
		back.setFont(new Font("富士ポップ", Font.BOLD, 22));
		back.addMouseListener(this);
		ruleIcon=new ImageIcon[6];
		ruleIcon[0]=new ImageIcon("ルール1.png");
		ruleIcon[1]=new ImageIcon("ルール2.png");
		ruleIcon[2]=new ImageIcon("ルール3.png");
		ruleIcon[3]=new ImageIcon("ルール4.png");
		ruleIcon[4]=new ImageIcon("ルール5.png");
		ruleIcon[5]=new ImageIcon("ルール6.png");
		label1=new JLabel(ruleIcon[i]);
		this.add(label1);
		label1.setBounds(0, 0, 700, 550);
		backIcon=new ImageIcon("ルール背景.jpg");
		label2=new JLabel(backIcon);
		this.add(label2);
		label2.setBounds(0, 0, 700, 650);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==next){
			if(i<5) i++; 
		}
		if(e.getSource()==back){
			if(i>0) i--; 
		}
		label1.setIcon(ruleIcon[i]);
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
