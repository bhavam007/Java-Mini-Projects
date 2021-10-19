import java.text.DecimalFormat;
import java.util.*;
	
interface int_merch {
	public void search();
}

/////////////////////////////////////////////////////////////////////////////////////

class merchant implements int_merch{
	mercury obj = new mercury();
	String merch_name;
	static ArrayList<items> item = new ArrayList<items>();
	int no_of_slots;
	float tot_comp_share;
	public merchant(String merch_name) {
		this.merch_name = merch_name;
		tot_comp_share = 0;
		no_of_slots = 0;
	}
	public void search() {
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<obj.items_list.size(); i++){
			System.out.println(obj.items_list.get(i).category);
		}
		String temp_cat = scanner.next();
		for(int i=0; i<obj.items_list.size(); i++){
			if(obj.items_list.get(i).category.equals(temp_cat)){
				System.out.println("Code : "+obj.items_list.get(i).item_code);
				System.out.println(obj.items_list.get(i).item_name);
				System.out.println(obj.items_list.get(i).price);
				System.out.println(obj.items_list.get(i).quantity);
			}
		}
	}
}

/////////////////////////////////////////////////////////////////////////////////////

class items{
	static String item_name;
	static String category;
	static float price;
	static int quantity;
	static int offer;
	static int item_code;
	static String of_merch;
}

/////////////////////////////////////////////////////////////////////////////////////

class customer implements int_merch{
	mercury obj = new mercury();
	String cust_name;
	float acc_bal;
	float reward_bal;
	ArrayList<items> cart = new ArrayList<items>();
	ArrayList<items> items_bought = new ArrayList<items>();
	float tot_shop ;
	public customer(String cust_name){
		this.cust_name = cust_name;
		acc_bal = 100;
		tot_shop = 0;
	}
	public void search() {
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<obj.items_list.size(); i++){
			System.out.println(obj.items_list.get(i).category);
		}
		String temp_cat = scanner.next();
		for(int i=0; i<obj.items_list.size(); i++){
			if(obj.items_list.get(i).category.equals(temp_cat)){
				System.out.println("Code : "+obj.items_list.get(i).item_code);
				System.out.println(obj.items_list.get(i).item_name);
				System.out.println(obj.items_list.get(i).price);
				System.out.println(obj.items_list.get(i).quantity);
			}
		}
		System.out.println("Enter product code and quantity : ");
		int sel_code = scanner.nextInt();
		int sel_quant = scanner.nextInt();
		for(int i=0; i<obj.items_list.size(); i++){
			if(obj.items_list.get(i).item_code == sel_code && obj.items_list.get(i).quantity !=0 && acc_bal+reward_bal>=obj.items_list.get(i).price*sel_quant){
				System.out.println("1)Buy \n2)Add to cart \n3) Exit");
				int opt = scanner.nextInt();
				if(opt == 1){
					for(int j=0; j<sel_quant; j++){
						items_bought.add(obj.items_list.get(i));
						obj.items_list.get(i).quantity -= 1;
						if(obj.items_list.get(i).offer != 2){
							if(acc_bal - obj.items_list.get(i).price > 0){
								acc_bal -= obj.items_list.get(i).price;
								tot_shop+=obj.items_list.get(i).price;
							}
							else{
								reward_bal -= obj.items_list.get(i).price;
								tot_shop+=obj.items_list.get(i).price;
							}
						}
					}
				}
				else if(opt == 2){
					for(int j=0; j<sel_quant; j++){
					cart.add(obj.items_list.get(i));
					}
				}
				else if(opt == 3){
					return;
				}
			}
			else{
				System.out.println("Purchase cant be completed ");
			}
		}
	}
}

/////////////////////////////////////////////////////////////////////////////////////
	
public class mercury{
	float balance;
	static ArrayList<merchant> merch_list = new ArrayList<merchant>();
	static ArrayList<customer> cust_list = new ArrayList<customer>();
	static ArrayList<items> items_list = new ArrayList<items>();
	
	//_____________________________________________
	
	public static void add_item(int merch_no, int code) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter item details : ");
		items item1 = new items();
		items_list.add(item1);
		System.out.println("Item name : ");
		item1.item_name = scanner.next();
		System.out.println("Enter quantity");
		item1.quantity = scanner.nextInt();
		System.out.println("Item price : ");
		item1.price = scanner.nextFloat();
		System.out.println("Item category : ");
		item1.category = scanner.next();
		System.out.println("Enter offer : "); 
		item1.offer = scanner.nextInt();
		item1.item_code = code;
		merch_list.get(merch_no).item.add(item1);
		item1.of_merch = merch_list.get(merch_no).merch_name;
	}
	
	//_____________________________________________
	
	public static void edit_item(int code) {
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<items_list.size(); i++){
			if(items_list.get(i).item_code == code){
				System.out.println("Item price : ");
				items_list.get(i).price = scanner.nextFloat();
				System.out.println("Enter quantity : ");
				items_list.get(i).quantity = scanner.nextInt();
			}
		}
	}

	//_____________________________________________
	
	public static void add_offer(int temp_code, int i) {
		Scanner scanner = new Scanner(System.in);
		for(int j=0; j<merch_list.get(i).item.size(); j++){
			if(merch_list.get(i).item.get(j).item_code == temp_code){
				System.out.print("1) 25% off \n 2) Buy 1 get 1 free ");
				int temp_offer = scanner.nextInt();
				merch_list.get(i).item.get(j).offer = temp_offer;
				System.out.println(merch_list.get(i).item.get(j).item_name);
				System.out.println(merch_list.get(i).item.get(j).item_code);
				System.out.println(merch_list.get(i).item.get(j).price);
				System.out.println(merch_list.get(i).item.get(j).quantity);
				if(merch_list.get(i).item.get(j).offer == 1){
					System.out.println("25% off");
				}
				else if(merch_list.get(i).item.get(j).offer == 2){
					System.out.println("Buy 1 get 1 free ");
				}
				else{
					System.out.println("None ");
				}
			}
		}
	}
	
	//_____________________________________________
	
	public static int merch_rewards(int temp_no) {
		float rewards = merch_list.get(temp_no).tot_comp_share/100;
		int rew = (int) rewards;
		return rew;
	}
	
	//_____________________________________________
	
	public static void cust_rewards(customer cust) {
		int rew = 0;
		rew = cust.items_bought.size()/5;
		cust.reward_bal=rew;
		System.out.println("Rewards = " + rew);
	}
	
	//_____________________________________________

	public static void checkout(customer cust){
		for(int i=0; i<cust.cart.size(); i++){
			if(cust.acc_bal >= cust.cart.get(i).price){
				cust.items_bought.add(cust.cart.get(i));
				if(cust.cart.get(i).offer != 2){
					cust.acc_bal -= cust.cart.get(i).price;
					cust.tot_shop += cust.cart.get(i).price;
				}
				cust.cart.get(i).quantity -=1;
				cust.cart.remove(i);
			}
			else{
				if(cust.reward_bal >= cust.cart.get(i).price){
					cust.items_bought.add(cust.cart.get(i));
					if(cust.cart.get(i).offer != 2){
						cust.acc_bal -= cust.cart.get(i).price;
						cust.tot_shop += cust.cart.get(i).price;
					}
					cust.cart.get(i).quantity -=1;
					cust.cart.remove(i);
				}
			}
		}
	}
	
	//_____________________________________________

	public static void print_order(customer cust) {
		for(int i=0; i<cust.items_bought.size(); i++){
			System.out.print(cust.items_bought.get(i).item_name+" ");
			System.out.println("from merchant : "+cust.items_bought.get(i).of_merch);
		}
		System.out.println(" for Rs. "+cust.tot_shop);
	}
	
	//_____________________________________________

	public static float comp_balance(float comp_bal) {
		for(int i=0; i<cust_list.size(); i++){
			for(int j=0; j<cust_list.get(i).items_bought.size(); j++){
				comp_bal += (cust_list.get(i).items_bought.get(j).price)/100;
			}
		}
		return comp_bal;
	}
	
	//_____________________________________________

	public static void user_det() {
		for(int i=0; i<cust_list.size(); i++){
			System.out.println("Customers : ");
			System.out.println(cust_list.get(i).cust_name + " " + cust_list.get(i).acc_bal);
		}
		for(int j=0; j<merch_list.size(); j++){
			System.out.println("Merchants : ");
			System.out.println(merch_list.get(j).merch_name + " " + merch_list.get(j).no_of_slots);
		}
	}
	
	//_____________________________________________

	public static void refresh(){
		for(int i=0; i<merch_list.size(); i++){
			if(merch_list.get(i).item.size()!=0){
				for(int j=0; j<merch_list.get(i).item.size(); j++){
					if(merch_list.get(i).item.get(j).offer == 1){
						DecimalFormat numb = new DecimalFormat("#.00");
						float temp = (merch_list.get(i).item.get(j).price*3)/4;
						numb.format(temp);
						merch_list.get(i).item.get(j).price = temp;
					}
				}
			}
		}
	}
	
	//_____________________________________________

	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		merchant jack = new merchant("jack");
		merchant john = new merchant("john");
		merchant james = new merchant("james");
		merchant jeff = new merchant("jeff");
		merchant joseph = new merchant("joseph");
		merch_list.add(jack);
		merch_list.add(john);
		merch_list.add(james);
		merch_list.add(jeff);
		merch_list.add(joseph);
		
		customer ali = new customer("ali");
		customer nobby = new customer("nobby");
		customer bruno = new customer("bruno");
		customer borat = new customer("borat");
		customer aladeen = new customer("aladeen");
		cust_list.add(ali);
		cust_list.add(nobby);
		cust_list.add(bruno);
		cust_list.add(borat);
		cust_list.add(aladeen);
		int choice1 = 1;
		float comp_bal = 0;
		
		while(choice1!=5){	
			System.out.println("WELCOME TO MERCURY");
			System.out.println("1) Enter as Merchant");
			System.out.println("2) Enter as Customer");
			System.out.println("3) See user details	");
			System.out.println("4) Company account balance");
			System.out.println("5) Exit");
			choice1 = scanner.nextInt();
			int code = 0;
			
			while(choice1 != 6){
				
				if(choice1 == 1){
					System.out.println("CHOOSE MERCHANT");
					System.out.println("1) Jack");
					System.out.println("2) John");
					System.out.println("3) James");
					System.out.println("4) Jeff");
					System.out.println("5) Joseph");
					int choice2 = scanner.nextInt();
					int merch_no = choice2-1;
					System.out.println("WELCOME " + merch_list.get(merch_no).merch_name);
					System.out.println("MERCHANT MENU");
					System.out.println("1) Add item");
					System.out.println("2) Edit item");
					System.out.println("3) Search by category");
					System.out.println("4) Add offer");
					System.out.println("5) Rewards won");
					System.out.println("6) Exit");
					int choice3 = scanner.nextInt();
	
					
					if(choice3 == 1){
						code++;
						add_item(merch_no, code);
					}
					else if (choice3 == 2){
						System.out.println("Enter item name : ");
						int item_code = scanner.nextInt();
						edit_item(item_code);
					}
					else if(choice3 == 3){
						merch_list.get(merch_no).search();
					}
					else if(choice3 == 4){
						System.out.println("Enter code : ");
						int temp_code = scanner.nextInt();
						add_offer(temp_code, merch_no);
					}
					else if(choice3 == 5){
						System.out.print(merch_rewards(merch_no));
					}
					else if(choice3 == 6){
						break;
					}
				}
				
				else if(choice1 == 2){
					System.out.println("CHOOSE CUSTOMER");
					System.out.println("1) Ali");
					System.out.println("2) Nobby");
					System.out.println("3) Bruno");
					System.out.println("4) Borat");
					System.out.println("5) Aladeen");
					int choice2 = scanner.nextInt();
					int cust_no = choice2-1;
					System.out.println("WELCOME " + cust_list.get(cust_no).cust_name);
					System.out.println("CUSTOMER MENU");
					System.out.println("1) Search item");
					System.out.println("2) Checkout cart");
					System.out.println("3) Reward won");
					System.out.println("4) Print latest orders");
					System.out.println("5) Exit");
					int choice3 = scanner.nextInt();
					
					if(choice3 == 1){
						cust_list.get(cust_no).search();
					}
					else if(choice3 == 2){
						checkout(cust_list.get(cust_no));
					}
					else if(choice3 == 3){
						cust_rewards(cust_list.get(cust_no));
					}
					else if(choice3 == 4){
						print_order(cust_list.get(cust_no));
					}
					else if(choice3 == 5){
						break;
					}
				}
				else if(choice1 == 4){
					System.out.print("Company balance = " + comp_balance(comp_bal));
					break;
				}
				else if(choice1 == 3){
					user_det();
					break;
				}
				refresh();
			}
		}
	}
}