package othello;

import java.util.Arrays;

public class Othello {
	final private int row=8;
	private int i,j;
	public int board[][]=new int[row][row];
	boolean initiative;
	public Othello(){
		board[3][3]=1;
		board[4][4]=1;
		board[3][4]=1;
		board[4][3]=-7;
		System.out.print(Arrays.deepToString(board));
		int boardPoint[];
		int hand[]=new int[6];
		initiative=true;
	}
	
	boolean checkLineDrop(int y,int x,int i,int j){
		boolean flag=false;
		if(i==0&&j==0)return false;
		if (initiative){
			while(y+i>=0 && y+i<=7 && x+j>=0 && x+j<=7 && board[y+i][x+j]>0){
				flag=true;
				y+=i;
				x+=j;
			}
			if(flag && y+i>=0 && y+i<=7 && x+j>=0 && x+j<=7 && board[y+i][x+j]<0)
				return true;
			return false;
		}else{
			while(y+i>=0 && y+i<=7 && x+j>=0 && x+j<=7 && board[y+i][x+j]<0){
				flag=true;
				y+=i;
				x+=j;
			}
			if(flag && y+i>=0 && y+i<=7 && x+j>=0 && x+j<=7 && board[y+i][x+j]>0)
				return true;
			return false;
		}
	}
	
	boolean checkDrop(int y,int x){
		if (board[y][x]!=0) return false;
		for (i=-1;i<+2;i++){
			for (j=-1;j<+2;j++){
				if(checkLineDrop(y,x,i,j))return true;
				/*flag=false;
				if (initiative && (i!=0 || j!=0)){
					check_x=x;
					check_y=y;
					while(check_x+i>=0 && check_x+i<=7 && check_y+j>=0 && check_y+j<=7 && board[check_x+i][check_y+j]>0){
						flag=true;
						check_x+=i;
						check_y+=j;
					}
					if(flag && check_x+i>=0 && check_x+i<=7 && check_y+j>=0 && check_y+j<=7 && board[check_x+i][check_y+j]<0)
						return true;
				}else if(i!=0||j!=0){
					check_x=x;
					check_y=y;
					while(check_x+i>=0 && check_x+i<=7 && check_y+j>=0 && check_y+j<=7 && board[check_x+i][check_y+j]<0){
						flag=true;
						check_x+=i;
						check_y+=j;
					}
					if(flag && check_x+i>=0 && check_x+i<=7 && check_y+j>=0 && check_y+j<=7 && board[check_x+i][check_y+j]>0)
						return true;
				}*/
			}
		}
		return false;
	}
	
	void stoneDrop(int y,int x,int value){
		int check_x,check_y;
		if(checkDrop(y,x)){
			board[y][x]=value;
			for (i=-1;i<+2;i++){
				for (j=-1;j<+2;j++){
					if(checkLineDrop(y,x,i,j)){
						check_y=y+i;
						check_x=x+j;
						if(initiative){
							while(board[check_y][check_x]>0) {
								board[check_y][check_x]-=8;
								check_y+=i;
								check_x+=j;
							}
						}else{
							while(board[check_y][check_x]<0) {
								board[check_y][check_x]+=8;
								check_y+=i;
								check_x+=j;
							}
						}
					}
				}
			}
		}
	}
	
	
}
