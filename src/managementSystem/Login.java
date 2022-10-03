package managementSystem;
import java.util.Scanner;

public class Login {
	public static String inAccount;
	public static void login() {
		Scanner in = new Scanner(System.in);
		String account;
		String password;
		boolean temp = false;
		System.out.println("欢迎进入学生成绩管理系统！");
		System.out.println("-----------------");
		do {
			System.out.print("请输入您的账号：");
			account = in.next();
			System.out.print("请输入您的密码：");
			password = in.next();
			//输入部分
			
			//账户、密码验证			
			LoginData login = new LoginData();
			for(int i = 0;i<LoginData.list.size();i++) {
				if(account.equals(LoginData.list.get(i).getAccount())) {
					if(password.equals(LoginData.list.get(i).getPassword())) {
						System.out.println("登录成功，欢迎您:"+account);
						temp = true;
						inAccount =account;
						break;
					}else {
						System.out.println("密码错误");
						break;
					}
				}
				if((!account.equals(LoginData.list.get(i).getAccount())||!password.equals(LoginData.list.get(i).getPassword()))&&i==LoginData.list.size()-1) {
					System.out.println("登录失败");
				}
			}
		}while(!temp);
	}
}
