//オセロ計算部分
package othello;

public class Othello {
	final private int row=8;
	private int board[][]=new int[row][row];
	int boardPoint[]=new int[2];
	boolean initiative;
	public Othello(){
	}
	
	void debugChangeInitiative(){
		initiative=!initiative;
	}
	
	int[] initBoard(boolean init,int handicap){
		initiative=init;
		board[3][3]=5;
		board[4][4]=5;
		board[3][4]=-3;
		board[4][3]=-3;
		if(handicap==1){
			board[0][0]=-3;
		}else if(handicap==2){
			board[0][0]=-3;
			board[7][7]=-3;
		}else if(handicap==3){
			board[0][0]=-3;
			board[7][7]=-3;
			board[0][7]=-3;
		}else if(handicap==4){
			board[0][0]=-3;
			board[7][7]=-3;
			board[0][7]=-3;
			board[7][0]=-3;
		}else if(handicap==-1){
			board[0][0]=5;
		}else if(handicap==-2){
			board[0][0]=5;
			board[7][7]=5;
		}else if(handicap==-3){
			board[0][0]=5;
			board[7][7]=5;
			board[0][7]=5;
		}else if(handicap==-4){
			board[0][0]=5;
			board[7][7]=5;
			board[0][7]=5;
			board[7][0]=5;
		}
		int[] hand=new int[6];
		hand[0]=10;
		hand[1]=6;
		hand[2]=4;
		hand[3]=10;
		hand[4]=6;
		hand[5]=4;
		return hand;
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
		for (int i=-1;i<+2;i++){
			for (int j=-1;j<+2;j++){
				if(checkLineDrop(y,x,i,j))return true;
			}
		}
		return false;
	}
	
	int[] callBoardPoint(){
		boardPoint[0]=0;
		boardPoint[1]=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if (board[i][j]<0) boardPoint[1]+=board[i][j]+4;
				if (board[i][j]>0) boardPoint[0]+=board[i][j]-4;
			}
		}
		return boardPoint;
	}
	
	boolean checkDropBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(checkDrop(i,j)) return true;
				initiative=!initiative;
				if(checkDrop(i,j)){
					initiative=!initiative;
					return true;
				}
				initiative=!initiative;
			}
		}
		return false;
	}
	
	boolean checkDropSingleBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(checkDrop(i,j)) return true;
			}
		}
		return false;
	}
	
	int[][] callBoard(){
		return board;
	}
	
	boolean stoneDrop(int y,int x,int value){
		int check_x,check_y;
		boolean flag=false;
		if(checkDrop(y,x)){
			board[y][x]=value;
			for (int i=-1;i<+2;i++){
				for (int j=-1;j<+2;j++){
					if(checkLineDrop(y,x,i,j)){
						flag=true;
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
		return flag;
	}
	
	
}
