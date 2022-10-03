package managementSystem;

import java.util.ArrayList;
import java.util.List;

public class Student extends SortCourse{
	private String name;
	private String ID;
	public List<Course> courseList = new ArrayList<Course>();//科目列表
	private double totalHour;//总学时
	private double electiveCourseHour;//选修学时
	private double weightedScore;//加权分
	public static String sortCourse;//需要排名的课程
	private int rank;
	public Student() {
		super();
	}

	public Student(String name, String iD) {
		super();
		this.name = name;
		ID = iD;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourseList() {
		return courseList;
	}
	//常规的get set方法
	
	
	public void addCourse(String name,double hour,double score) {
		Course course = new Course(name,hour,score);
		courseList.add(course);
	}
	//添加课程
	
	public String getCourseScore(String courseName) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0&&courseList.get(i).getName().equals(courseName)) {
				sb.append("课程："+courseList.get(i).getName()+"  \t学分："+courseList.get(i).getHour()+"\t 成绩："+String.format("%.2f", courseList.get(i).getScore())+"    平均分："+String.format("%.2f", courseList.get(i).getAverage())+"\n");
				return sb+"";
			}
		}
		return "未查到您该科目的成绩";
	}
	//查询单科成绩
	
	
	public String getCourse() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0) {
				sb.append("课程："+courseList.get(i).getName()+"  \t学分："+courseList.get(i).getHour()+"\t 成绩："+String.format("%.2f", courseList.get(i).getScore())+"    平均分："+String.format("%.2f", courseList.get(i).getAverage())+"\n");
			}
		}
		return sb+"";
	}
	//得到所有科目学分及成绩
	
	
	public String getCourseAverage(String courseName) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0&&courseList.get(i).getName().equals(courseName)) {
				sb.append("课程："+courseList.get(i).getName()+"  \t学分："+courseList.get(i).getHour()+"\t 成绩："+String.format("%.2f", courseList.get(i).getScore())+"    平均分："+String.format("%.2f", courseList.get(i).getAverage())+"\n");
				return sb+"";
			}
		}
		return "未查到您该科目的成绩";
	}
	//得到单科的平均分
	
	
	public double getTotalHour() {
		double temp=0;
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0) {
				temp += courseList.get(i).getHour();
			}
		}
		this.totalHour = temp;
		return totalHour;
	}
	//得到总学分
	
	
	public double getElectiveCourseHour() {
		double temp=0;
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getName().equals("选修")&&courseList.get(i).getScore()!=0) {
				temp =courseList.get(i).getHour();
				break;
			}
		}
		this.electiveCourseHour = temp;
		return electiveCourseHour;
	}
	//得到选修学分
	
	
	public double getWeightedScore() {
		double temp1=0;
		double temp2=getTotalHour()-getElectiveCourseHour();
		for(int i=0;i<courseList.size();i++) {
			if((!courseList.get(i).getName().equals("选修"))&&courseList.get(i).getScore()!=0) {
				temp1 +=courseList.get(i).getScore()*courseList.get(i).getHour();
			}
		}
		this.weightedScore = temp1/temp2;
		return weightedScore;
	}
	//得到加权分
	

	public String toString() {
		return "姓名："+name+"  学号："+ID+"\n以下是您的课程成绩：\n"+getCourse()+"您修得的总学分为："+getTotalHour()+"    其中选修的学分为："+getElectiveCourseHour()+
				"\n您的加权分数为："+String.format("%.2f", getWeightedScore());
	}
	//重写toString
	
	
	public void getRank(String[] temp) {
		Boolean b = true;
		String courseName = temp[1];
		for1:for(int t=0;t<courseList.size();t++) {
			if((courseList.get(t).getName().equals(courseName)&&courseList.get(t).getScore()!=0)) {
				rank(courseName);
				for(int i =0;i<Main.stuList.size();i++) {
					if(Main.stuList.get(i).getName().equals(name)) {
						this.rank = i+1;
						System.out.println(courseName+":"+String.format("%.0f", getCourseList().get(t).getScore())+"\t          排名："+rank);
						b = false;
						break for1;
					}
				}
			}else if(courseName.equals("加权分")) {
				rank("加权分");
				for(int i =0;i<Main.stuList.size();i++) {
					if(Main.stuList.get(i).getName().equals(name)) {
						this.rank = i+1;
						System.out.println(courseName+":"+String.format("%.2f", getWeightedScore())+"\t   排名："+rank);
						b = false;
						break for1;
					}
				}
			}
			if(courseList.get(t).getName().equals(courseName)&&courseList.get(t).getScore()==0) {
				System.out.println("您未参加该课程！");
				b = false;
			}
		}
		if(b){
			System.out.println("您输入的指令有误！");
		}
	}
	//获取排名
	
	public String toRow() {
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<Main.stuList.size();i++) {
			if(Main.stuList.get(i).getName().equals(name)) {
				this.rank = i+1;
			}
		}
		for(int i=0;i<courseList.size();i++) {
			sb.append(","+String.format("%.0f", courseList.get(i).getScore()));
		}
		return ID + "," + name + sb+ "," + getTotalHour()+","+getElectiveCourseHour()+","+String.format("%.2f", getWeightedScore())+","+rank;
	}
}
