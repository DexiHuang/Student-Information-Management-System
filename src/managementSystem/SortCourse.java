package managementSystem;

import java.util.Collections;
import java.util.Comparator;

public class SortCourse {
	public static String sortCourse;

	public void rank(String courseName) {
		sortCourse = courseName;
		Collections.sort(Main.stuList, new sortRule());
	}
	
}

//������򣺷����Ӹߵ����ţ�������ͬ��ѧ�Ŵӵ͵�����
class sortRule implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Student stu1 = (Student) o1;
		Student stu2 = (Student) o2;
		
		for(int i=0;i<stu1.courseList.size();i++) {
			if(SortCourse.sortCourse.equals("��Ȩ��")) {//��Ȩ�ֵ�����
				if(stu1.getWeightedScore()>stu2.getWeightedScore()) {
					return -1;
				}else if(stu1.getWeightedScore()==stu2.getWeightedScore()) {
					if(Integer.parseInt(stu1.getID())<Integer.parseInt(stu2.getID())) {
						return -1;
					}else {
						return 1;
					}
				}else {
					return 1;
				}
			}else {
				if(stu1.courseList.get(i).getName().equals(SortCourse.sortCourse)) {//ĳ�γ̵�����
					if(stu1.courseList.get(i).getScore()>stu2.courseList.get(i).getScore()) {
						return -1;
					}else if(stu1.courseList.get(i).getScore()==stu2.courseList.get(i).getScore()) {
						if(Integer.parseInt(stu1.getID())<Integer.parseInt(stu2.getID())) {
							return -1;
						}else {
							return 1;
						}
					}else {
						return 1;
					}
				}
			}
		}
		return 0;
	}
}