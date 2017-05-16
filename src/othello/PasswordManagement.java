package othello;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class PasswordManagement {
	public PasswordManagement(){
		
	}
	public String issueHash(char[] pass){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String as=new String(pass);
			md.update(as.getBytes());
			byte[] cipherByte = md.digest();
			StringBuilder sb = new StringBuilder(2*cipherByte.length);
			for(byte b:cipherByte){
				sb.append(String.format("%02x", b&0xff));
			}
			as="0";
			return new String(sb);
		}catch(Exception er){
			er.printStackTrace();
			return "-1";
		}
	}
	public boolean checkAvailable(char[] pass){
		if(pass.length>16 || pass.length<4){
			JOptionPane.showMessageDialog(null, "4字以上16字以下で入力してください");
			return false;
		}
		for(int i=0;i<pass.length;i++){
			if(pass[i]==',') {
				JOptionPane.showMessageDialog(null, ",を使用しないでください");
				return false;
			}
		}
		return true;
	}
	
	public boolean checkRenew(char[] oldpass,char[] newpass,char[] renewpass){
		if(!Arrays.equals(newpass,renewpass)) {
			JOptionPane.showMessageDialog(null, "新しいパスワードが一致してません");
			return false;
		}
		if(newpass.length>16 || newpass.length<4){
			JOptionPane.showMessageDialog(null, "4字以上16字以下で入力してください");
			return false;
		}
		for(int i=0;i<newpass.length;i++){
			if(newpass[i]==',') {
				JOptionPane.showMessageDialog(null, ",を使用しないでください");
				return false;
			}
		}
		String password=this.issueHash(newpass);
		//ここで更新手続き
		//サーバを介して旧パスワードとの比較
		JOptionPane.showMessageDialog(null, "パスワードを変更しました\n"+password);
		return true;
	}
	
}
