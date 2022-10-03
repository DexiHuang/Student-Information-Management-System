package managementSystem;

import java.util.ArrayList;
import java.util.List;

public class Student extends SortCourse{
	private String name;
	private String ID;
	public List<Course> courseList = new ArrayList<Course>();//��Ŀ�б�
	private double totalHour;//��ѧʱ
	private double electiveCourseHour;//ѡ��ѧʱ
	private double weightedScore;//��Ȩ��
	public static String sortCourse;//��Ҫ�����Ŀγ�
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
	//�����get set����
	
	
	public void addCourse(String name,double hour,double score) {
		Course course = new Course(name,hour,score);
		courseList.add(course);
	}
	//��ӿγ�
	
	public String getCourseScore(String courseName) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0&&courseList.get(i).getName().equals(courseName)) {
				sb.append("�γ̣�"+courseList.get(i).getName()+"  \tѧ�֣�"+courseList.get(i).getHour()+"\t �ɼ���"+String.format("%.2f", courseList.get(i).getScore())+"    ƽ���֣�"+String.format("%.2f", courseList.get(i).getAverage())+"\n");
				return sb+"";
			}
		}
		return "δ�鵽���ÿ�Ŀ�ĳɼ�";
	}
	//��ѯ���Ƴɼ�
	
	
	public String getCourse() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0) {
				sb.append("�γ̣�"+courseList.get(i).getName()+"  \tѧ�֣�"+courseList.get(i).getHour()+"\t �ɼ���"+String.format("%.2f", courseList.get(i).getScore())+"    ƽ���֣�"+String.format("%.2f", courseList.get(i).getAverage())+"\n");
			}
		}
		return sb+"";
	}
	//�õ����п�Ŀѧ�ּ��ɼ�
	
	
	public String getCourseAverage(String courseName) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getScore()!=0&&courseList.get(i).getName().equals(courseName)) {
				sb.append("�γ̣�"+courseList.get(i).getName()+"  \tѧ�֣�"+courseList.get(i).getHour()+"\t �ɼ���"+String.format("%.2f", courseList.get(i).getScore())+"    ƽ���֣�"+String.format("%.2f", courseList.get(i).getAverage())+"\n");
				return sb+"";
			}
		}
		return "δ�鵽���ÿ�Ŀ�ĳɼ�";
	}
	//�õ����Ƶ�ƽ����
	
	
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
	//�õ���ѧ��
	
	
	public double getElectiveCourseHour() {
		double temp=0;
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getName().equals("ѡ��")&&courseList.get(i).getScore()!=0) {
				temp =courseList.get(i).getHour();
				break;
			}
		}
		this.electiveCourseHour = temp;
		return electiveCourseHour;
	}
	//�õ�ѡ��ѧ��
	
	
	public double getWeightedScore() {
		double temp1=0;
		double temp2=getTotalHour()-getElectiveCourseHour();
		for(int i=0;i<courseList.size();i++) {
			if((!courseList.get(i).getName().equals("ѡ��"))&&courseList.get(i).getScore()!=0) {
				temp1 +=courseList.get(i).getScore()*courseList.get(i).getHour();
			}
		}
		this.weightedScore = temp1/temp2;
		return weightedScore;
	}
	//�õ���Ȩ��
	

	public String toString() {
		return "������"+name+"  ѧ�ţ�"+ID+"\n���������Ŀγ̳ɼ���\n"+getCourse()+"���޵õ���ѧ��Ϊ��"+getTotalHour()+"    ����ѡ�޵�ѧ��Ϊ��"+getElectiveCourseHour()+
				"\n���ļ�Ȩ����Ϊ��"+String.format("%.2f", getWeightedScore());
	}
	//��дtoString
	
	
	public void getRank(String[] temp) {
		Boolean b = true;
		String courseName = temp[1];
		for1:for(int t=0;t<courseList.size();t++) {
			if((courseList.get(t).getName().equals(courseName)&&courseList.get(t).getScore()!=0)) {
				rank(courseName);
				for(int i =0;i<Main.stuList.size();i++) {
					if(Main.stuList.get(i).getName().equals(name)) {
						this.rank = i+1;
						System.out.println(courseName+":"+String.format("%.0f", getCourseList().get(t).getScore())+"\t          ������"+rank);
						b = false;
						break for1;
					}
				}
			}else if(courseName.equals("��Ȩ��")) {
				rank("��Ȩ��");
				for(int i =0;i<Main.stuList.size();i++) {
					if(Main.stuList.get(i).getName().equals(name)) {
						this.rank = i+1;
						System.out.println(courseName+":"+String.format("%.2f", getWeightedScore())+"\t   ������"+rank);
						b = false;
						break for1;
					}
				}
			}
			if(courseList.get(t).getName().equals(courseName)&&courseList.get(t).getScore()==0) {
				System.out.println("��δ�μӸÿγ̣�");
				b = false;
			}
		}
		if(b){
			System.out.println("�������ָ������");
		}
	}
	//��ȡ����
	
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
