package managementSystem;
//黄德希 41907054
//卢冠男 41907038
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
	public static List<Student> stuList = new ArrayList<Student>();  //创建学生组
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
		//从importcourses.txt获取需要初始化添加的课程
		for(int i=0;i<stuList.size();i++) {
			LoginData.Register(stuList.get(i).getID(),stuList.get(i).getID());
		}
		//初始化成绩及登录名单
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Login.login();
		//登录部分
		String cmd = null;
		do {
			cmd = in.nextLine();
			String[] temp = null;
			if (cmd.contains(" ")) {
				temp = cmd.split(" ");
			}else {
				temp =new String[]{cmd};
			}
			if(Login.inAccount.equals("teacher")) {//教师端
				Teacher t = new Teacher();
				switch(temp[0].toLowerCase()) {
					case "set":
						t.setStudent(temp);
						break;
					case "import"://添加新科目成绩txt
						t.importCourse();
						break;
					case "avg"://获得平均分
						t.getAvg(temp);
						break;
					case "get":
						t.getInfomation(temp);
						break;
					case "sort"://对某一个科目或加权分进行排序
						t.sortCourse(temp[1]);
						break;
					case "export":
						t.exportCsv();
				}
			}else {//学生端
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
					case "成绩":
						System.out.println(stuList.get(t));
					}
				}else if(temp.length==3) {
					switch(temp[2]) {
					case "排名":
						stuList.get(t).getRank(temp);
					}
				}
			}
			System.out.println();
		}while(!cmd.equals("exit"));
		System.out.println("感谢您的使用！");
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
			//txt行数读取
			
			str = new String [i][3];
			i=0;
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while((line = br.readLine())!=null) {
				str[i] = line.split("\t");
				i++;
			}
			//读取信息至二维数组
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
			//录入所有学生成绩
			
			for(int a=0;a<stuList.size();a++) {
				if(stuList.get(a).getCourseList().size()<courseNum) {
					stuList.get(a).addCourse(courseName,hour,0);
				}
			}
			//录入未参与考试的同学成绩，记为0分
		
		} catch (FileNotFoundException e) {
			info("未找到文件");
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
