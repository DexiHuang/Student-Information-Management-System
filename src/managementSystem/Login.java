package managementSystem;
import java.util.Scanner;

public class Login {
	public static String inAccount;
	public static void login() {
		Scanner in = new Scanner(System.in);
		String account;
		String password;
		boolean temp = false;
		System.out.println("��ӭ����ѧ���ɼ�����ϵͳ��");
		System.out.println("-----------------");
		do {
			System.out.print("�����������˺ţ�");
			account = in.next();
			System.out.print("�������������룺");
			password = in.next();
			//���벿��
			
			//�˻���������֤			
			LoginData login = new LoginData();
			for(int i = 0;i<LoginData.list.size();i++) {
				if(account.equals(LoginData.list.get(i).getAccount())) {
					if(password.equals(LoginData.list.get(i).getPassword())) {
						System.out.println("��¼�ɹ�����ӭ��:"+account);
						temp = true;
						inAccount =account;
						break;
					}else {
						System.out.println("�������");
						break;
					}
				}
				if((!account.equals(LoginData.list.get(i).getAccount())||!password.equals(LoginData.list.get(i).getPassword()))&&i==LoginData.list.size()-1) {
					System.out.println("��¼ʧ��");
				}
			}
		}while(!temp);
	}
}
