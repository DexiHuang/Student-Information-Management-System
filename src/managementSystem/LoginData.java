package managementSystem;

import java.util.ArrayList;
import java.util.List;

public class LoginData {
	
	public static List<SignIn> list = new ArrayList<SignIn>();
	//��ʼ����ʦ��¼ �˺ţ�teacher ���룺teacher
	static {
		list.add(new SignIn("teacher","teacher"));	
	}
	
	//ע��
	public static void Register(String account,String password) {
		SignIn s = new SignIn(account,password);
		list.add(s);
	}
}

//�û��˺������¼
class SignIn{
	private String account;
	private String password;
	public SignIn() {
		super();
	}
	public SignIn(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}