package managementSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.awt.Desktop;

public class Teacher extends SortCourse{
	
	public void exportCsv(){
		File csvFile = null;
		BufferedWriter csvWriter = null;
		try {
			csvFile = new File("成绩单.csv");
			csvFile.createNewFile();
			csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
			StringBuffer sb = new StringBuffer();
			for(int t=0;t<Main.stuList.get(0).getCourseList().size();t++) {
				sb.append(","+Main.stuList.get(0).getCourseList().get(t).getName());
			}
			csvWriter.write("学号,姓名"+sb+",总学时,选修学时,加权分,排名");
			csvWriter.newLine();
			for(Student stu :Main.stuList) {
				csvWriter.write(stu.toRow());
				csvWriter.newLine();
			}
			csvWriter.flush();
			System.out.println("导出成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

		try {
		    // 打开当前文件
		    Desktop.getDesktop().open(new File(csvFile.getAbsolutePath()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	//导出成绩
	
	public void getInfomation(String[] temp) {
		Boolean a = false;
		Boolean b = true;
		if (temp.length == 3) {
			for (int i = 0; i < Main.stuList.size(); i++) {
				if (temp[1].equals(Main.stuList.get(i).getName())
						|| temp[2].equals(Main.stuList.get(i).getName())) {
					for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
						if (Main.stuList.get(i).getCourseList().get(t).getScore() != 0
								&& (temp[1].equals(Main.stuList.get(i).getCourseList().get(t).getName())
										|| temp[2].equals(Main.stuList.get(i).getCourseList().get(t).getName()))) {
							a = true;
						}
					}
				}
			}
			if (temp[2].equals("加权分")) {
				b = false;
				for (int i = 0; i < Main.stuList.size(); i++) {
					if (temp[1].equals(Main.stuList.get(i).getName()) || temp[1].equals(Main.stuList.get(i).getID())) {
						System.out.println("姓名：" + Main.stuList.get(i).getName() + "\t学号：" + Main.stuList.get(i).getID()
								+ "\n已修学科如下：");
						for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
							if (Main.stuList.get(i).getCourseList().get(t).getScore() != 0) {
								System.out.print(Main.stuList.get(i).getCourseList().get(t).getName() + ":"
										+ String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore())
										+ "\t");
							}
						}
						System.out.println("加权分：" + String.format("%.2f",Main.stuList.get(i).getWeightedScore()));
					}
				}
			} else if (temp[2].equals("不及格")) {
				int num =0;//不及格人数
				b =false;
				for (int i = 0; i < Main.stuList.size(); i++) {
					for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
						if (temp[1].equals(Main.stuList.get(i).getCourseList().get(t).getName())
								&& Main.stuList.get(i).getCourseList().get(t).getScore() != 0
								&& Main.stuList.get(i).getCourseList().get(t).getScore() < 60) {
							num+=1;
						}
					}
				}
				//判断是否有不及格
				
				if(num!=0) {
					System.out.println(temp[1]+"的不及格人数为："+num);
					System.out.println("姓名\t学号\t                        成绩");
					for (int i = 0; i < Main.stuList.size(); i++) {
						for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
							if (temp[1].equals(Main.stuList.get(i).getCourseList().get(t).getName())
									&& Main.stuList.get(i).getCourseList().get(t).getScore() != 0
									&& Main.stuList.get(i).getCourseList().get(t).getScore() < 60) {
								System.out.println(Main.stuList.get(i).getName() + "\t" + Main.stuList.get(i).getID() + "\t"
										+ String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore())
										+ "\t");
								
							}
						}
					}
				}else {
					System.out.println("您好，"+temp[1]+"没有不及格人员");
				}
			} else if(a&&b){
				for (int i = 0; i < Main.stuList.size(); i++) {
					if (temp[1].equals(Main.stuList.get(i).getName())
							|| temp[2].equals(Main.stuList.get(i).getName())) {
						System.out.print("姓名：" + Main.stuList.get(i).getName() + "\t   学号："
								+ Main.stuList.get(i).getID() + "	");
						b = false;
						for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
							if (Main.stuList.get(i).getCourseList().get(t).getScore() != 0
									&& (temp[1].equals(Main.stuList.get(i).getCourseList().get(t).getName())
											|| temp[2].equals(Main.stuList.get(i).getCourseList().get(t).getName()))) {
								System.out.println(Main.stuList.get(i).getCourseList().get(t).getName() + ":"
										+ String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore()));
							}
						}
					}
				}
			}
		}
		if(b) {
			System.out.println("您输入的指令有误！");
		}
	}
	//get 获取信息
	
	public void setStudent(String temp[]) {
		Boolean b = true;
		if(temp.length==3) {
			Student stu = new Student(temp[1],temp[2]);
			Main.stuList.add(stu);
			System.out.println("已添加学生："+temp[1]+"     "+temp[2]);
			for(int t=0;t<Main.stuList.get(0).getCourseList().size();t++) {
				Main.stuList.get(Main.stuList.size()-1).addCourse(Main.stuList.get(0).getCourseList().get(t).getName(), Main.stuList.get(0).getCourseList().get(t).getHour(), 0);
				b = false;
				//初始化成绩都为0
			}
		}else if(temp.length==4) {
			try {
				double score = Double.valueOf(temp[3]);
				for1:for(int i=0;i<Main.stuList.size();i++) {
					if(temp[1].equals(Main.stuList.get(i).getName())||temp[1].equals(Main.stuList.get(i).getID())) {
						for(int t=0;t<Main.stuList.get(i).getCourseList().size();t++) {
							if(temp[2].equals(Main.stuList.get(i).getCourseList().get(t).getName())) {
								Main.stuList.get(i).getCourseList().get(t).setScore(score);
								System.out.println("学生："+Main.stuList.get(i).getName()+"  课程："+temp[2]+"   分数已设为："+temp[3]);
								b = false;
								break for1;
							}
						}
					}
				}
			}catch(NumberFormatException e){
				//加入保险机制，防止输入非double型
			}
		}
		if(b) {
			System.out.println("您输入的指令有误！");
		}
	}
	//设定学生
	
	
	public void getAvg(String temp[]) {
		Boolean b = true;
		if(temp.length==2) {
			for(int i=0;i<Main.stuList.get(0).getCourseList().size();i++) {
				if(temp[1].equals(Main.stuList.get(0).getCourseList().get(i).getName())) {
					System.out.println("课程："+temp[1]+"\t平均分："+String.format("%.2f",Main.stuList.get(0).getCourseList().get(i).getAverage()));
					b = false;
				}
			}
			for(int i=0;i<Main.stuList.size();i++) {
				if(temp[1].equals(Main.stuList.get(i).getName())||temp[1].equals(Main.stuList.get(i).getID())){
					b = false;
					int temp1 = 0;
					int temp2 = 0;
					System.out.println("姓名："+Main.stuList.get(i).getName()+"\t学号："+Main.stuList.get(i).getID()+"\n已修学科如下：");
					for(int t=0;t<Main.stuList.get(i).getCourseList().size();t++) {
						if(Main.stuList.get(i).getCourseList().get(t).getScore()!=0) {
							temp1 += Main.stuList.get(i).getCourseList().get(t).getScore();
							temp2 += 1;
							System.out.print(Main.stuList.get(i).getCourseList().get(t).getName()+":"+String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore())+"\t");
						}
					}
					System.out.println("平均分："+(temp1/temp2));
				}
			}
		}
		if(b) {
			System.out.println("您输入的指令有误！");
		}
	}
	//得到平均分
	
	public void importCourse() {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入课程名：");
		String temp1 = in.next();
		boolean b = true;
		for(int i=0;i<Main.stuList.get(0).getCourseList().size();i++) {
			if(Main.stuList.get(0).getCourseList().get(i).getName().equals(temp1)) {
				b = false;
			}
		}
		if(b) {
			System.out.print("请输入课程学分：");
			double temp2 = in.nextDouble();
			System.out.print("请输入文本路径：");
			String temp3 = in.next();
			String temp4 = null;
			if(temp3.endsWith(".txt")&&temp3.contains("\\")) {
				temp4 = temp3.replace("\\", "\\\\");
				File f1 = null;
				File f2 = null;
				FileInputStream fis = null;
				FileOutputStream fos = null;
				FileWriter fw = null;
				PrintWriter pw = null;
				try {
					f1 = new File(temp4);
					fis = new FileInputStream(f1);
					fos = new FileOutputStream(temp1+".txt");
					int data;
					while((data = fis.read())!=-1) {
						fos.write(data);
					}
					Main.addStudentList(temp1,temp2,temp1+".txt");
					System.out.println("已添加课程："+temp1);
					f2 = new File("importcourses.txt");
					fw = new FileWriter(f2,true);
					pw = new PrintWriter(fw);
					pw.print("\n"+temp1+","+temp2+","+temp1+".txt");
					pw.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				System.out.println("路径格式不正确");
			}
		}else {
			System.out.println("该课程已经添加！");
		}
	}
	//添加新课程成绩
	
	public void sortCourse(String course) {
		Boolean b = true;
		
		if(course.equals("加权分")) {
			rankCourse("加权分");
			b = false;
		}else {
			for(int i =0;i<Main.stuList.get(0).getCourseList().size();i++) {
				if(course.equals(Main.stuList.get(0).getCourseList().get(i).getName())) {
					rankCourse(course);
					b = false;
				}
			}
			if(b){
				System.out.println("未找到"+course);
			}
		}
	}
	//排序对应科目
	
	public void rankCourse(String courseName) {
		sortCourse = courseName;
		Collections.sort(Main.stuList, new sortRule());
		System.out.print("名次\t姓名\t学号\t");
		for (int a = 0; a < Main.stuList.get(0).getCourseList().size(); a++) {
			System.out.print("\t" + Main.stuList.get(0).getCourseList().get(a).getName());
		}
		System.out.println("\t总学分\t加权分");
		for (int i = 0; i < Main.stuList.size(); i++) {
			System.out.print((i + 1) + "\t" + Main.stuList.get(i).getName() + "\t" + Main.stuList.get(i).getID());
			for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
				System.out.print("\t" + String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore()));
			}
			System.out.print("\t" + Main.stuList.get(i).getTotalHour() + "\t"
					+ String.format("%.2f", Main.stuList.get(i).getWeightedScore()));
			System.out.println();
		}
	}
}



