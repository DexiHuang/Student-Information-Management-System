package managementSystem;
//�Ƶ�ϣ 41907054
//¬���� 41907038
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static List<Student> stuList = new ArrayList<Student>();  //����ѧ����
	public static int courseNum = 0;
	static {
		FileReader fr = null;
		BufferedReader bfr =null;
		File f = null;
		try {
			fr = new FileReader("importcourses.txt");
			bfr = new BufferedReader(fr);
			String line;
			while((line = bfr.readLine())!=null) {
				String[] s = line.split(",");
				addStudentList(s[0],Double.valueOf(s[1]),s[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//��importcourses.txt��ȡ��Ҫ��ʼ����ӵĿγ�
		for(int i=0;i<stuList.size();i++) {
			LoginData.Register(stuList.get(i).getID(),stuList.get(i).getID());
		}
		//��ʼ���ɼ�����¼����
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Login.login();
		//��¼����
		String cmd = null;
		do {
			cmd = in.nextLine();
			String[] temp = null;
			if (cmd.contains(" ")) {
				temp = cmd.split(" ");
			}else {
				temp =new String[]{cmd};
			}
			if(Login.inAccount.equals("teacher")) {//��ʦ��
				Teacher t = new Teacher();
				switch(temp[0].toLowerCase()) {
					case "set":
						t.setStudent(temp);
						break;
					case "import"://����¿�Ŀ�ɼ�txt
						t.importCourse();
						break;
					case "avg"://���ƽ����
						t.getAvg(temp);
						break;
					case "get":
						t.getInfomation(temp);
						break;
					case "sort"://��ĳһ����Ŀ���Ȩ�ֽ�������
						t.sortCourse(temp[1]);
						break;
					case "export":
						t.exportCsv();
				}
			}else {//ѧ����
				String ID = Login.inAccount;
				int t = 0;
				for(int i =0;i<stuList.size();i++) {
					if(stuList.get(i).getID().equals(ID)) {
						t = i;
						break;
					}
				}
				if(temp.length==2) {
					switch(temp[1]) {
					case "�ɼ�":
						System.out.println(stuList.get(t));
					}
				}else if(temp.length==3) {
					switch(temp[2]) {
					case "����":
						stuList.get(t).getRank(temp);
					}
				}
			}
			System.out.println();
		}while(!cmd.equals("exit"));
		System.out.println("��л����ʹ�ã�");
		in.close();
	}
	public static void info(String str) {
		System.out.println(str);
	}

	public static void addStudentList(String courseName,double hour,String path) {
		courseNum +=1;
		FileReader fr = null;
		BufferedReader br = null;
		File f = new File(path);
		String[][] str;
		int i=0;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String line;
			while(br.readLine()!=null) {
				i++;
			}
			//txt������ȡ
			
			str = new String [i][3];
			i=0;
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while((line = br.readLine())!=null) {
				str[i] = line.split("\t");
				i++;
			}
			//��ȡ��Ϣ����ά����
			if(stuList.isEmpty()){
				for(int t=1;t<str.length;t++) {
					Student stu = new Student(str[t][0],str[t][1]);
					stuList.add(stu);
					stuList.get(t-1).addCourse(courseName,hour,Double.parseDouble(str[t][2]));
				}
			}else{
				for(int t=1;t<str.length;t++) {
					for(int a=0;a<stuList.size();a++) {
						if(stuList.get(a).getID().equals((str[t][1]))) {
							stuList.get(a).addCourse(courseName,hour,Double.parseDouble(str[t][2]));
						}
					}
				}
				for(int t=1;t<str.length;t++) {
					Boolean temp = true;
					for(int a=0;a<stuList.size();a++) {
						if(stuList.get(a).getID().equals((str[t][1]))) {
							temp=false;
						}
					}
					if(temp) {
						Student stu = new Student(str[t][0],str[t][1]);
						stuList.add(stu);
						stuList.get(t-1).addCourse(courseName,hour,Double.parseDouble(str[t][2]));
					}
				}
			}
			//¼������ѧ���ɼ�
			
			for(int a=0;a<stuList.size();a++) {
				if(stuList.get(a).getCourseList().size()<courseNum) {
					stuList.get(a).addCourse(courseName,hour,0);
				}
			}
			//¼��δ���뿼�Ե�ͬѧ�ɼ�����Ϊ0��
		
		} catch (FileNotFoundException e) {
			info("δ�ҵ��ļ�");
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
