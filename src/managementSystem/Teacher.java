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
			csvFile = new File("�ɼ���.csv");
			csvFile.createNewFile();
			csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
			StringBuffer sb = new StringBuffer();
			for(int t=0;t<Main.stuList.get(0).getCourseList().size();t++) {
				sb.append(","+Main.stuList.get(0).getCourseList().get(t).getName());
			}
			csvWriter.write("ѧ��,����"+sb+",��ѧʱ,ѡ��ѧʱ,��Ȩ��,����");
			csvWriter.newLine();
			for(Student stu :Main.stuList) {
				csvWriter.write(stu.toRow());
				csvWriter.newLine();
			}
			csvWriter.flush();
			System.out.println("�����ɹ���");
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
		    // �򿪵�ǰ�ļ�
		    Desktop.getDesktop().open(new File(csvFile.getAbsolutePath()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	//�����ɼ�
	
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
			if (temp[2].equals("��Ȩ��")) {
				b = false;
				for (int i = 0; i < Main.stuList.size(); i++) {
					if (temp[1].equals(Main.stuList.get(i).getName()) || temp[1].equals(Main.stuList.get(i).getID())) {
						System.out.println("������" + Main.stuList.get(i).getName() + "\tѧ�ţ�" + Main.stuList.get(i).getID()
								+ "\n����ѧ�����£�");
						for (int t = 0; t < Main.stuList.get(i).getCourseList().size(); t++) {
							if (Main.stuList.get(i).getCourseList().get(t).getScore() != 0) {
								System.out.print(Main.stuList.get(i).getCourseList().get(t).getName() + ":"
										+ String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore())
										+ "\t");
							}
						}
						System.out.println("��Ȩ�֣�" + String.format("%.2f",Main.stuList.get(i).getWeightedScore()));
					}
				}
			} else if (temp[2].equals("������")) {
				int num =0;//����������
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
				//�ж��Ƿ��в�����
				
				if(num!=0) {
					System.out.println(temp[1]+"�Ĳ���������Ϊ��"+num);
					System.out.println("����\tѧ��\t                        �ɼ�");
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
					System.out.println("���ã�"+temp[1]+"û�в�������Ա");
				}
			} else if(a&&b){
				for (int i = 0; i < Main.stuList.size(); i++) {
					if (temp[1].equals(Main.stuList.get(i).getName())
							|| temp[2].equals(Main.stuList.get(i).getName())) {
						System.out.print("������" + Main.stuList.get(i).getName() + "\t   ѧ�ţ�"
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
			System.out.println("�������ָ������");
		}
	}
	//get ��ȡ��Ϣ
	
	public void setStudent(String temp[]) {
		Boolean b = true;
		if(temp.length==3) {
			Student stu = new Student(temp[1],temp[2]);
			Main.stuList.add(stu);
			System.out.println("�����ѧ����"+temp[1]+"     "+temp[2]);
			for(int t=0;t<Main.stuList.get(0).getCourseList().size();t++) {
				Main.stuList.get(Main.stuList.size()-1).addCourse(Main.stuList.get(0).getCourseList().get(t).getName(), Main.stuList.get(0).getCourseList().get(t).getHour(), 0);
				b = false;
				//��ʼ���ɼ���Ϊ0
			}
		}else if(temp.length==4) {
			try {
				double score = Double.valueOf(temp[3]);
				for1:for(int i=0;i<Main.stuList.size();i++) {
					if(temp[1].equals(Main.stuList.get(i).getName())||temp[1].equals(Main.stuList.get(i).getID())) {
						for(int t=0;t<Main.stuList.get(i).getCourseList().size();t++) {
							if(temp[2].equals(Main.stuList.get(i).getCourseList().get(t).getName())) {
								Main.stuList.get(i).getCourseList().get(t).setScore(score);
								System.out.println("ѧ����"+Main.stuList.get(i).getName()+"  �γ̣�"+temp[2]+"   ��������Ϊ��"+temp[3]);
								b = false;
								break for1;
							}
						}
					}
				}
			}catch(NumberFormatException e){
				//���뱣�ջ��ƣ���ֹ�����double��
			}
		}
		if(b) {
			System.out.println("�������ָ������");
		}
	}
	//�趨ѧ��
	
	
	public void getAvg(String temp[]) {
		Boolean b = true;
		if(temp.length==2) {
			for(int i=0;i<Main.stuList.get(0).getCourseList().size();i++) {
				if(temp[1].equals(Main.stuList.get(0).getCourseList().get(i).getName())) {
					System.out.println("�γ̣�"+temp[1]+"\tƽ���֣�"+String.format("%.2f",Main.stuList.get(0).getCourseList().get(i).getAverage()));
					b = false;
				}
			}
			for(int i=0;i<Main.stuList.size();i++) {
				if(temp[1].equals(Main.stuList.get(i).getName())||temp[1].equals(Main.stuList.get(i).getID())){
					b = false;
					int temp1 = 0;
					int temp2 = 0;
					System.out.println("������"+Main.stuList.get(i).getName()+"\tѧ�ţ�"+Main.stuList.get(i).getID()+"\n����ѧ�����£�");
					for(int t=0;t<Main.stuList.get(i).getCourseList().size();t++) {
						if(Main.stuList.get(i).getCourseList().get(t).getScore()!=0) {
							temp1 += Main.stuList.get(i).getCourseList().get(t).getScore();
							temp2 += 1;
							System.out.print(Main.stuList.get(i).getCourseList().get(t).getName()+":"+String.format("%.0f", Main.stuList.get(i).getCourseList().get(t).getScore())+"\t");
						}
					}
					System.out.println("ƽ���֣�"+(temp1/temp2));
				}
			}
		}
		if(b) {
			System.out.println("�������ָ������");
		}
	}
	//�õ�ƽ����
	
	public void importCourse() {
		Scanner in = new Scanner(System.in);
		System.out.print("������γ�����");
		String temp1 = in.next();
		boolean b = true;
		for(int i=0;i<Main.stuList.get(0).getCourseList().size();i++) {
			if(Main.stuList.get(0).getCourseList().get(i).getName().equals(temp1)) {
				b = false;
			}
		}
		if(b) {
			System.out.print("������γ�ѧ�֣�");
			double temp2 = in.nextDouble();
			System.out.print("�������ı�·����");
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
					System.out.println("����ӿγ̣�"+temp1);
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
				System.out.println("·����ʽ����ȷ");
			}
		}else {
			System.out.println("�ÿγ��Ѿ���ӣ�");
		}
	}
	//����¿γ̳ɼ�
	
	public void sortCourse(String course) {
		Boolean b = true;
		
		if(course.equals("��Ȩ��")) {
			rankCourse("��Ȩ��");
			b = false;
		}else {
			for(int i =0;i<Main.stuList.get(0).getCourseList().size();i++) {
				if(course.equals(Main.stuList.get(0).getCourseList().get(i).getName())) {
					rankCourse(course);
					b = false;
				}
			}
			if(b){
				System.out.println("δ�ҵ�"+course);
			}
		}
	}
	//�����Ӧ��Ŀ
	
	public void rankCourse(String courseName) {
		sortCourse = courseName;
		Collections.sort(Main.stuList, new sortRule());
		System.out.print("����\t����\tѧ��\t");
		for (int a = 0; a < Main.stuList.get(0).getCourseList().size(); a++) {
			System.out.print("\t" + Main.stuList.get(0).getCourseList().get(a).getName());
		}
		System.out.println("\t��ѧ��\t��Ȩ��");
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



