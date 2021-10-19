import java.util.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.text.Position;


class student{
	int roll_no;
	float cgpa;
	String branch;
	int score;
	boolean stud_status;
	String selected_by;
	student(int roll_no, float cgpa, String branch){
		this.roll_no = roll_no;
		this.cgpa = cgpa;
		this.branch = branch;
		score = 0;
		stud_status = false;
		selected_by = "";
	}
}

class company{
	String comp_name;
	boolean app_status;
	int no_of_crs_elig, nos_req;
	ArrayList<String> crs_elig;
	ArrayList<Integer> studs_hired = new ArrayList<Integer>();
	
	company(String comp_name, int no_of_crs_elig, ArrayList<String> crs_elig, int nos_req){
		this.comp_name = comp_name;
		this.no_of_crs_elig = no_of_crs_elig;
		this.crs_elig = crs_elig;
		this.nos_req = nos_req;
		app_status = true;
	}
	
}

public class placement {
	
	public static void func_hire(company comp, ArrayList<student> los_shortlisted, int nos_req){
		
		
		ArrayList<student> temp_los_short = new ArrayList<student>();
//		ArrayList<student> toppers = new ArrayList<student>();
		int max = 0;
		int loc = 0;
		for(int j=0; j<los_shortlisted.size(); j++){
			for(int i=0; i<los_shortlisted.size(); i++){
				if(los_shortlisted.get(i).score > max){
					max = los_shortlisted.get(i).score;
					loc = i;
				}
			}
			temp_los_short.add(los_shortlisted.get(loc));
			los_shortlisted.remove(loc);
			max=0;
			loc=0;
		}
		
		for(int i=0; i<temp_los_short.size() && nos_req>0; i++){
			if(i+1<temp_los_short.size() &&temp_los_short.get(i).score == temp_los_short.get(i+1).score){
				if(temp_los_short.get(i).cgpa > temp_los_short.get(i+1).cgpa){
					comp.studs_hired.add(temp_los_short.get(i).roll_no);
					temp_los_short.get(i).stud_status=true;
					temp_los_short.get(i).selected_by = comp.comp_name;
					i++;
				}
				else{
					i++;
					comp.studs_hired.add(temp_los_short.get(i).roll_no);
					temp_los_short.get(i).stud_status=true;
					temp_los_short.get(i).selected_by = comp.comp_name;
				}
			}
			else{
				temp_los_short.get(i).stud_status=true;
				temp_los_short.get(i).selected_by = comp.comp_name;
				comp.studs_hired.add(temp_los_short.get(i).roll_no);
			}
			nos_req--;
		}
		if(nos_req == 0)
			comp.app_status = false;
		
		System.out.println(comp.comp_name);
		System.out.println("Courses eligible : "+ comp.crs_elig);
		System.out.println("Students req : " + comp.nos_req);
		if(comp.app_status){
			System.out.println("App Status : OPEN" );
		}
		else {
			System.out.println("App Status : CLOSED");
		}
		
	}
	
	public static ArrayList<student> shortlist(company comp, ArrayList<student> list_of_studs){
		ArrayList<student> shortlisted = new ArrayList<student>();
		for(int i=0; i<list_of_studs.size(); i++){
			for(int j=0; j<comp.crs_elig.size(); j++){
				if(list_of_studs.get(i).branch.equals(comp.crs_elig.get(j)))
				{
					shortlisted.add(list_of_studs.get(i));
				}
			}
		}

		return shortlisted;
	}
	
	
	public static int unplaced_studs(ArrayList<student> list_of_studs) {
		int count = 0;
		for(int i=0; i<list_of_studs.size(); i++){
			if(list_of_studs.get(i).stud_status == false){
				count ++;
			}
		}
		return count;
	}
	
	public static void func_app_open(ArrayList<company> loc){

		for(int i=0; i<loc.size(); i++){
			if(loc.get(i).app_status == true){
				System.out.print(loc.get(i).comp_name);
			}
		}
	}
	
	public static void select_studs(String cname, ArrayList<student> los){
		for(int i=0; i<los.size(); i++){
			if(los.get(i).selected_by.equals(cname)){
				System.out.print(los.get(i).roll_no);
			}
		}
	}
	
	public static void comp_details(String cname, ArrayList<company> loc) {
		for(int i=0; i<loc.size(); i++){
			if(loc.get(i).comp_name.equals(cname)){
				System.out.println(loc.get(i).comp_name);
				System.out.println(loc.get(i).crs_elig);
				System.out.println(loc.get(i).nos_req);
				if(loc.get(i).app_status == true){
					System.out.print("OPEN");
				}
				else {
					System.out.print("CLOSED");

				}
			}
		}
	}
	
	public static void rem_comp(ArrayList<company> loc) {
		for(int i=0; i<loc.size(); i++){
			if(loc.get(i).app_status == false){
				System.out.print(loc.get(i).comp_name);
			}
		}
	}
	
	public static void rem_stud(ArrayList<student> los) {
		for(int i=0; i<los.size(); i++){
			if(los.get(i).stud_status == true){
				System.out.print(los.get(i).roll_no);
			}
		}
	}
	
	public static void stud_details(int rno, ArrayList<student> los) {
		for(int i=0; i<los.size(); i++){
			if(los.get(i).roll_no == rno){
				System.out.println(los.get(i).roll_no);
				System.out.println(los.get(i).cgpa);
				System.out.println(los.get(i).branch);
				System.out.println(los.get(i).selected_by);
				if(los.get(i).stud_status == true){
					System.out.print("PLACED");
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter no of students in the college : ");
		int nos = scanner.nextInt();
		ArrayList<student> list_of_studs = new ArrayList<student> (nos);
		ArrayList<company> list_of_comp = new ArrayList<company>();
		
		for(int i=1; i<=nos; i++){
			float cgpa = scanner.nextFloat();
			String branch = scanner.next();

			student stud = new student(i, cgpa, branch);
			list_of_studs.add(stud);
		}
		
		while(true){
			int query = scanner.nextInt();
			if(query == 1){
				System.out.println("Enter Company's name : ");
				String comp_name = scanner.next();
				
				System.out.println("Enter course criteria : ");
				int no_of_crs_elig = scanner.nextInt();

				int temp = 0;
				ArrayList<String> crs_elig = new ArrayList<String>(no_of_crs_elig);
				
				while(temp != no_of_crs_elig){
					String crs_name = scanner.next();
					crs_elig.add(crs_name);
					temp++;
				}
								
				System.out.println("Enter no of students req : ");
				int nos_req = scanner.nextInt();
				company comp = new company(comp_name, no_of_crs_elig, crs_elig, nos_req);
				list_of_comp.add(comp);
				
				ArrayList<student> studs_shortlisted = new ArrayList<student>();
				studs_shortlisted = shortlist(comp, list_of_studs);
				
				int nos_shorlisted = studs_shortlisted.size();
				
				for(int i=0; i<nos_shorlisted; i++){
					System.out.println("Enter score in tech round for roll no. " + studs_shortlisted.get(i).roll_no + " : ");
					studs_shortlisted.get(i).score = scanner.nextInt();
				}
				
				func_hire(comp, studs_shortlisted, nos_req);
			}
			
			else if (query == 2) {
				rem_stud(list_of_studs);
			}
			
			else if (query == 3){
				rem_comp(list_of_comp);
			}
			
			else if (query == 4) {
				int unplaced_studs = unplaced_studs(list_of_studs);
				System.out.print(unplaced_studs);
			}
			
			else if (query == 5) {
				func_app_open(list_of_comp);
			}
			
			else if(query == 6){
				System.out.print("Enter company name : ");
				String cname = scanner.next();
				select_studs(cname, list_of_studs);
			}
			else if(query == 7){
				System.out.print("Enter company name : ");
				String cname = scanner.next();
				comp_details(cname, list_of_comp);
			}
			else if(query == 8){
				System.out.print("Enter student's roll no : ");
				int rno = scanner.nextInt();
				stud_details(rno, list_of_studs);
			}
			if( unplaced_studs(list_of_studs) == 0)
			{
				break;
			}
		}
	}
}