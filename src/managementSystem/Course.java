package managementSystem;

public class Course {
	private String name;
	private double hour;
	private double score;
	public double average;

	public Course() {
		super();
	}
	public Course(String name, double score) {
		super();
		this.name = name;
		this.score = score;
	}
	public Course(String name, double hour, double score) {
		super();
		this.name = name;
		this.hour = hour;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHour() {
		return hour;
	}
	public void setHour(double hour) {
		this.hour = hour;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	//�����get��set����
	
	public double getAverage() {
		double temp1=0;//ͳ���ܷ���
		int temp2=0;//ͳ��������
		for(int i=0;i<Main.stuList.size();i++) {
			for(int t=0;t<Main.stuList.get(i).getCourseList().size();t++) {
				if(Main.stuList.get(i).getCourseList().get(t).getName().equals(name)&&Main.stuList.get(i).getCourseList().get(t).getScore()!=0) {
					temp1+=Main.stuList.get(i).getCourseList().get(t).getScore();
					temp2+=1;
				}    
			}
		}
		this.average=temp1/temp2;
		return average;
	}
}