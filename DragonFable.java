import java.util.*;

import javax.swing.text.Position;

class sidekick{
	protected float hp, xp, att;
	int onlev;
	public sidekick() {
		hp = 100;
		xp = 0;
		onlev = 1;
	}
	 public Object clone() throws CloneNotSupportedException {
	 return super.clone();
	 }	
	void attack(monster mon, float x){
		
	}
}

class minion extends sidekick implements Cloneable{
	public minion(float x){
		System.out.println("You bought a sidekick: minion");
		att = (float)((x-5)*(0.5))+1;
		System.out.println("XP of sidekick is "+xp);
		System.out.println("Attack of sidekick is "+att);
	}
	@Override
	void attack(monster mon, float x){
		System.out.println("Sidekick attacked and inflicted "+att+"damage to the monster");
		mon.hp -= att;
		System.out.println("Sidekick Hp : "+hp);
		System.out.println("Monster Hp : "+hp);
	}
	

}

class knight extends sidekick{
	public knight (float x){
		System.out.println("You bought a sidekick: knight");
		att = (float)((x-8)*(0.5))+2;
		System.out.println("XP of sidekick is "+xp);
		System.out.println("Attack of sidekick is "+att);
	}
	@Override 
	void attack(monster mon, float x){
			if(mon.montype.equals("zombie")){

		}
	}
}

//////////////////////////////////////////////////////

class user{
	protected String name, type;
	protected float xp, hp;
	protected int lev;
	user(){
		hp = 100;
		xp = 0;
		lev = 1;
	}
	void setter(String name, String type){
		this.name = name;
		this.type = type;
	}
	void attack(monster mon){
		
	}
}

class warrior extends user{
	@Override
	void attack(monster mon){
		System.out.println("\n Attacking the monster. ");
		mon.hp -= 10;
		System.out.println("Your Hp : "+hp);
		System.out.println("Your Xp : "+xp);
		System.out.println("Monster Hp : "+mon.hp);
	}

}

class mage extends user{
	@Override
	void attack(monster mon){
		System.out.println("\n Attacking the monster. ");
		mon.hp -= 5;
		System.out.println("Your Hp : "+hp);
		System.out.println("Monster Hp : "+mon.hp);
	}
}

class thief extends user{
	@Override
	void attack(monster mon){
		System.out.println("\n Attacking the monster. ");
		mon.hp -= 6;
		System.out.println("Your Hp : "+hp);
		System.out.println("Your Xp : "+xp);
		System.out.println("Monster Hp : "+mon.hp);
	}
}

class healer extends user{
	@Override
	void attack(monster mon){
		System.out.println("\n Attacking the monster. ");
		mon.hp -= 4;
		System.out.println("Your Hp : "+hp);
		System.out.println("Your Xp : "+xp);
		System.out.println("Monster Hp : "+mon.hp);
	}
}

//////////////////////////////////////////

class monster{
	float ihp;
	float hp;
	int lev;
	String montype;
	void attack(user hero, boolean check, boolean skstat, ArrayList<sidekick> skList){
		System.out.println("\n MONSTER ATTACK! ");
		Random rand = new Random();
		
		double gaus = rand.nextGaussian();
		for(int i=0; true; i++){
			gaus = rand.nextGaussian()*hp/8 + hp/8;
			if(gaus<=hp/4 && gaus>=0)
				break;
		}
		if(check == true){
			if(gaus < 5)
				gaus = 5;
			if(hero.type == "warrior")
				hero.hp -= gaus+3;
			else if(hero.type == "mage")
				hero.hp -= gaus+5;
			else if(hero.type == "thief")
				hero.hp -= gaus+4;
			else
				hero.hp -= gaus+5;
		}
		else{
			if(hero.type == "warrior")
				hero.hp -= gaus;
			else if(hero.type == "mage")
				hero.hp -= gaus;
			else if(hero.type == "thief")
				hero.hp -= gaus;
			else
				hero.hp -= gaus;
			
		}
		if(hero.hp <= 0){
			System.out.println("\n Hero is killed by the monster. Game Over!");
		}
		else{
			System.out.println("Your Hp : "+hero.hp);
			System.out.println("Your Xp : "+hero.xp);
			System.out.println("Monster Hp : "+hp);
		}
		
		if(skstat){
			for(int i=0; i<skList.size(); i++){
				skList.get(i).hp -= gaus*(1.5);
				System.out.println("Monster attacked and inflicted "+gaus+" damage to sidekick.");
			}
		}
	}
}

class goblins extends monster{
	goblins() {
		ihp = 100;
		hp = 100;
		lev = 1;
		montype = "goblin";
	}
}

class zombies extends monster{
	zombies() {
		ihp = 150;
		hp = 150;
		lev = 2;
		montype = "zombie";
	}
}

class fiends extends monster{
	fiends() {
		ihp = 200;
		hp = 200;
		lev = 3;
		montype = "fiend";
	}

}

class lionfang extends monster{
	public lionfang() {
		ihp = 250;
		hp = 250;
		lev = 4;
		montype = "lionfang";
	}
	
	void attack(user hero, boolean check, boolean skstat, ArrayList<sidekick> skList){
		System.out.println("\n LIONFANG ATTACKED!! ");
		Random rand = new Random();
		double gaus = rand.nextGaussian();
		
		for(int i=0; true; i++){
			gaus = rand.nextGaussian()*hp/8 + hp/8;
			if(gaus<=hp/4 && gaus>=0)
				break;
		}
		
		int special = rand.nextInt(10);
		
		if(special != 1){
			if(check == true){
				if(gaus < 5)
					gaus = 5;
				if(hero.type == "warrior")
					hero.hp -= gaus+3;
				else if(hero.type == "mage")
					hero.hp -= gaus+5;
				else if(hero.type == "thief")
					hero.hp -= gaus+4;
				else
					hero.hp -= gaus+5;
			}
			else{
				hero.hp -= gaus;
			}
		}
		else{
			if(check == true){
				if(gaus < 5)
					gaus = 5;
				if(hero.type == "warrior")
					hero.hp = (hero.hp/2)+3;
				else if(hero.type == "mage")
					hero.hp = (hero.hp/2)+5;
				else if(hero.type == "thief")
					hero.hp = (hero.hp/2)+4;
				else
					hero.hp = (hero.hp/2)+5;
			}
			else{
				if(hero.type == "warrior")
					hero.hp = (hero.hp/2);
				else if(hero.type == "mage")
					hero.hp = (hero.hp/2);
				else if(hero.type == "thief")
					hero.hp = (hero.hp/2);
				else
					hero.hp = (hero.hp/2);
			}
		}
		if(hero.hp <= 0){
			System.out.println("Hero is killed by LIONFANG. Game Over!");
		}
		else{
			System.out.println("Your Hp : "+hero.hp);
			System.out.println("Monster Hp : "+hp);
		}
		if(skstat){
			for(int i=0; i<skList.size(); i++){
				skList.get(i).hp -= gaus*(1.5);
				System.out.println("LionFang attacked and inflicted "+gaus+" damage to sidekick.");
			}
		}
	}
}

//////////////////////////////////////////

class graph{
	
	static ArrayList<Integer>[] al = new ArrayList[12];
	public void make_graph(){
		
        for (int i = 0; i < 12; i++) { 
            al[i] = new ArrayList<Integer>();
        }
		
		al[0].add(1); al[0].add(2); al[0].add(3);
		al[1].add(0); al[1].add(2); al[1].add(4); al[1].add(5); al[1].add(7);
		al[2].add(0); al[2].add(1); al[2].add(3); al[2].add(5);
		al[3].add(0); al[3].add(2); al[3].add(5); al[3].add(6); al[3].add(7);
		al[4].add(1); al[4].add(8); al[4].add(9); al[4].add(10);
		al[5].add(1); al[5].add(2); al[5].add(3); al[5].add(7);
		al[6].add(3); al[6].add(8); al[6].add(9); al[6].add(10);
		al[7].add(1); al[7].add(3); al[7].add(5); al[7].add(8);
		al[8].add(4); al[8].add(6); al[8].add(7); al[8].add(9);
		al[9].add(4); al[9].add(6); al[9].add(8); al[9].add(10);
		al[10].add(4); al[10].add(6); al[10].add(9); al[10].add(11);
		al[11].add(10);
	}
}

//////////////////////////////////////////

class game {
	
	Scanner s = new Scanner(System.in);
	public static boolean print1 = true;
	public static boolean print2 = true;
	public static boolean print3 = true;
	public static boolean cloner = true;
	public static boolean check_min = false;
	public static boolean check_nyt = false;
	ArrayList<sidekick> clone_list = new ArrayList<sidekick>();
	ArrayList<sidekick> nyt_list = new ArrayList<sidekick>();
	final ArrayList<String> user_list = new ArrayList<String>();
	
	monster gob = new goblins();
	monster zomb = new zombies();
	monster fiend = new fiends();
	monster lion = new lionfang();
	
	user input(){
		System.out.println("Welcome to Archlegends");
		System.out.println("Choose option : ");
		System.out.println("1. New user");
		System.out.println("2. Existing user");
		System.out.println("3. Exit");
		int optn = s.nextInt();
		System.out.println("Enter user name : ");
		final String name = s.next();
		if(optn == 1){
			user_list.add(name);
		}
		else if(optn == 2){
			for (int i = 0; i < user_list.size(); i++) {
				if(user_list.get(i).equals(name))
					System.out.println("User found. Logging in.. ");
			}
		}
		else {
			System.exit(0);
		}
		System.out.print("Which hero do you want to choose? \n 1)warrior\n 2)mage\n 3)thief\n 4)healer");
		int opt = s.nextInt();

		if(opt == 1){
			user hero = new warrior();
			hero.setter(name, "warrior");
			return hero;
		}
		else if(opt == 2){
			user hero = new mage();
			hero.setter(name, "mage");
			return hero;
		}
		else if(opt == 3){
			user hero = new thief();
			hero.setter(name, "thief");
			return hero;
		}
		else{
			user hero = new healer();
			hero.setter(name, "healer");
			return hero;
		}
	}
	
	ArrayList<monster> monst_list = new ArrayList<monster>();
	ArrayList<monster> temp = new ArrayList<monster>();
	void set_monst(){
		temp.add(gob);
		temp.add(zomb);
		temp.add(fiend);
		temp.add(lion);
		
		Random rand = new Random();
		monst_list.add(null);
		for(int i=1; i<11; i++){
			monst_list.add(temp.get(rand.nextInt(3)));
		}
		monst_list.add(lion);
	}
	
	void clone_fight(){
		
	}

	void make_sidekick(user hero) throws CloneNotSupportedException{

		System.out.println("Your current XP is "+ hero.xp);
		System.out.println("If you want to buy a minion, press 1.");
		System.out.println("If you want to buy a knight, press 2.");
		int ans2 = s.nextInt();
		System.out.println("Enter XP to spend : ");
		float ans3 = s.nextFloat();
		if(ans2 == 1){
			sidekick min = new minion(ans3);
			min.onlev = hero.lev;
			clone_list.add(min);
			check_min = true;
			if(cloner == true){
				System.out.println("Press c to use cloning ability. Else press f to move to the fight");
				String op = s.next();
				if(op.equals("c")){
					sidekick clone1 = (minion) min.clone();
					sidekick clone2 = (minion) min.clone();
					sidekick clone3 = (minion) min.clone();
					cloner = false;
					clone_list.add(clone1);
					clone_list.add(clone2);
					clone_list.add(clone3);
				}
				else if(op.equals("f")){
					return;
				}
			}
		}
		else if(ans2 == 2){
			sidekick nyt = new knight(ans3);
			check_nyt = true;
			nyt_list.add(nyt);
		}
	}
	
	void min_attack(monster mon){
		for(int i=0; i<clone_list.size(); i++){
			mon.hp -= clone_list.get(i).att;
			System.out.println("Sidekick attacked and inflicted "+clone_list.get(i).att+" damage to the monster");
			System.out.println("Sidekick Hp : "+clone_list.get(i).hp+"/100");
		}
	}
	
	void nyt_attack(monster mon){
		for(int i=0; i<nyt_list.size(); i++){
			mon.hp -= nyt_list.get(i).att;
			System.out.println("Sidekick attacked and inflicted "+nyt_list.get(i).att+" damage to the monster");
			System.out.println("Sidekick Hp : "+nyt_list.get(i).hp+"/100");
		}
	}
	
	void level(user hero, monster mon) throws CloneNotSupportedException{
		Scanner s = new Scanner(System.in);
		if(hero.xp >= 20 && hero.xp<40 && print1 == true){
			System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
			String ans1 = s.next();
			if(ans1.equals("yes")){
				make_sidekick(hero);
				print1 = false;
			}
			else{
				System.out.println("Level upgraded. Level 2 reached. ");
				print1 = false;
				hero.hp = 150;
				hero.lev = 2;
			}
		}
		else if(hero.xp >= 40 && hero.xp < 60 && print2 == true){
			System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
			String ans1 = s.next();
			if(ans1.equals("yes")){
				make_sidekick(hero);
				print2 = false;
			}
			else{
				System.out.println("Level upgraded. Level 3 reached. ");
				hero.hp = 200;
				print2 = false;
				hero.lev = 3;
			}
		}
		else if(hero.xp >= 60 && print3 == true){
			System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
			String ans1 = s.next();
			if(ans1.equals("yes")){
				make_sidekick(hero);
				print3 = false;
			}
			else{
				System.out.println("Level upgraded. Level 4 reached. ");
				print3 = false;
				hero.lev = 4;
			}
		}
		System.out.println("Your Hp : " + hero.hp);
	}
	
	void killsk(ArrayList<sidekick> nyt_list, ArrayList<sidekick> clone_list){
		for(int i=0; i<nyt_list.size(); i++){
			if(nyt_list.get(i).hp <= 0){
				System.out.println("Sidekick killed");
				nyt_list.remove(i);
			}
		}
		for(int i=0; i<clone_list.size(); i++){
			if(clone_list.get(i).hp <= 0){
				System.out.println("Sidekick killed");
				clone_list.remove(i);
			}
		}
	}
	
	void rem_min(user hero, ArrayList<sidekick> cl, ArrayList<sidekick> nl){
		boolean ch = false;
		for(int i=1; i<cl.size(); i++){
			if(cl.get(i).onlev != hero.lev){
				cl.remove(i);
				ch = true;
			}
		}
		if(ch)
			System.out.println("Clones removed");
		int n=0;
		for(int j=1; j<nl.size(); j++){
			n = (int)nl.get(j).xp/5;
			nl.get(j).att = n;
		}
		for(int j=1; j<nl.size(); j++){
			n = (int)cl.get(j).xp/5;
			cl.get(j).att = n;
		}
	}
	
	void play(user hero) throws CloneNotSupportedException{
		Scanner s = new Scanner(System.in);
		int pos = 0;
		System.out.println("\n You are on position "+pos+" \n Move to position : ");
		int k=0;
		int cond = 1;
		boolean def = false;
		boolean spec = false;
		int scount = 0;
		int batcount = 0, ex=0;
		
		while(cond == 1){
			for(int i=0; i<graph.al[k].size(); i++){
				System.out.println(graph.al[k].get(i));
			}
			pos = s.nextInt();
			System.out.println("Enter -1 to exit, 1 to continue.");
			 ex = s.nextInt();
			 if(ex == -1){
				 break;
			 }
			while(monst_list.get(pos).hp >= 0){
				if(monst_list.get(pos).hp <= 0){
					System.out.println("\n You killed the monster. ");
					break;
				}
				System.out.println("\n 1.) Attack \n 2.) Defence \n 3.) Special attack");
				int move = s.nextInt();
				if(move == 2){
					def = true;
				}
				else if(move == 1){
					hero.attack(monst_list.get(pos));
					if(check_min){
						min_attack(monst_list.get(pos));
					}
					else if(check_nyt){
						nyt_attack(monst_list.get(pos));
					}
				}
				else if(move == 3){
					if(batcount >= 4){
						spec = true;
						scount = 3;
					}
					else
						System.out.println("Special Power can't be activated. ");
				}
				if(spec == true && scount != 0 && monst_list.get(pos).hp>0){
					if(hero.type == "warrior"){
						monst_list.get(pos).hp -= 15;
						hero.hp += 5;
					}
					else if(hero.type == "mage"){
						monst_list.get(pos).hp -= (monst_list.get(pos).hp*5/100);
						monst_list.get(pos).hp -= 5;
					}
					else if(hero.type == "thief"){
						hero.hp += monst_list.get(pos).hp*3/10;
						monst_list.get(pos).hp -= 6;
					}
					else if(hero.type == "healer"){
						hero.hp += hero.hp*5/100;
						monst_list.get(pos).hp -= 4;
					}
					scount-- ;
					if(scount == 0){
						batcount = 0;
						spec = false;
					}
					System.out.println("\n SPECIAL ATTACK ACTIVATED! ");
				}
				if(def == true && pos != 11){
					monst_list.get(pos).attack(hero, def, false, clone_list);
					def = false;
					if(hero.hp <= 0){
						cond = 0;
						break;
					}
				}
				else if(def == false && pos != 11 && monst_list.get(pos).hp>0){
					if(check_min)
						monst_list.get(pos).attack(hero, def, check_min, clone_list);
					else if(check_nyt)
						monst_list.get(pos).attack(hero, def, check_nyt, nyt_list);
					else
						monst_list.get(pos).attack(hero, def, check_nyt, nyt_list);
					if(hero.hp <= 0){
						cond = 0;
						break;
					}
				}
				else if(def == true && pos == 11){
					lion.attack(hero, def, false, clone_list);
					if(hero.hp <= 0){
						cond = 0;
						break;
					}
				}
				else if(def == false && pos == 11){
					if(check_min)
						lion.attack(hero, def, check_min, clone_list);
					else if(check_nyt)
						lion.attack(hero, def, check_nyt, nyt_list);
					else
						lion.attack(hero, def, check_nyt, nyt_list);
					if(hero.hp <= 0){
						cond = 0;
						break;
					}
				}
				
				killsk(nyt_list, clone_list);
				
				if(monst_list.get(pos).hp <= 0 && pos != 11){
					System.out.println("\n Monster Killed ");
					hero.xp += monst_list.get(pos).lev*20;
					if(check_min){
						for(int i=0; i<clone_list.size(); i++){
							clone_list.get(i).xp += hero.xp/10;
							clone_list.get(i).hp = 100;
						}
					}
					if(check_nyt){
						for(int i=0; i<nyt_list.size(); i++){
							nyt_list.get(i).xp += hero.xp/10;
							nyt_list.get(i).hp = 100;
						}
					}
					if(hero.xp >= 20)
						hero.hp = 100;
					else if(hero.xp >= 40)
						hero.hp = 150;
					else if(hero.xp >= 60)
						hero.hp = 200;
					
					monst_list.get(pos).hp = monst_list.get(pos).ihp;
					System.out.println("You are on position "+pos+"\n Move to position : ");
					break;
				}
				else if(monst_list.get(pos).hp <= 0 && pos == 11){
					System.out.println("\n You killed LionFang. You won!");
					break;
				}
				level(hero, monst_list.get(pos));
				rem_min(hero, clone_list, nyt_list);
				batcount ++;
			}
			k=pos;
		}
	}
}

//////////////////////////////////////////////////////

class clone {
	public static void main(String[] args) throws CloneNotSupportedException{
		String replay = "yes";
		Scanner s = new Scanner(System.in);
		while(replay.equals("yes")){
			graph g = new graph();
			g.make_graph();
			game game = new game();
			user hero = game.input();
			game.set_monst();
			game.play(hero);
			System.out.println("Play Again? \n yes or no");
			replay = s.next();
		}
	}
}
