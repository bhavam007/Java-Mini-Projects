import java.util.*;

import org.omg.IOP.Codec;

////////////////////////////////////////////

class dice{
	static int roll(){
		Random rand = new Random();
		int numb = (rand.nextInt(6)+1);
		return numb;
	}
}

////////////////////////////////////////////

abstract class tile{
	abstract int getTno();
	abstract void setTno(int Tno);
}

class snake extends tile{
	private int tno;
	@Override
	public int getTno() {
		return tno;
	}
	@Override
	public void setTno(int tno) {
		this.tno = tno;
	}
}

class vulture extends tile{
	private int tno;
	@Override
	public int getTno() {
		return tno;
	}
	@Override
	public void setTno(int tno) {
		this.tno = tno;
	}
}

class cricket extends tile{
	private int tno;
	
	@Override
	public int getTno() {
		return tno;
	}
	@Override
	public void setTno(int tno) {
		this.tno = tno;
	}
}

class white extends tile{
	private int tno;
	@Override
	public int getTno() {
		return tno;
	}
	@Override
	public void setTno(int tno) {
		this.tno = tno;
	}
}

class trampoline extends tile{
	private int tno;
	@Override
	public int getTno() {
		return tno;
	}
	@Override
	public void setTno(int tno) {
		this.tno = tno;
	}
}

////////////////////////////////////////////

class tileaction extends Exception{
	public tileaction(String mess){
		super(mess);
	}
}

////////////////////////////////////////////

class game{
	
	Scanner scan = new Scanner(System.in);
	ArrayList<tile> tilelist = new ArrayList();
	Random rand = new Random();
	int nos=0, noc=0, nov=0, not=0, diceno, roll=0, pos =1, notiles, scount=0, vcount=0,ccount=0,tcount=0;
	int sattack = rand.nextInt(10)+1;
	int vattack = rand.nextInt(10)+1;
	int cattack = rand.nextInt(10)+1;
	int tboost = rand.nextInt(10)+1;
	String pname;
	boolean cond = false, won = false;
	
	void SnakeBiteException(ArrayList<tile> tilelist_loc, int pos_loc) throws tileaction
		{
			throw new tileaction(">>\tHiss...! I am a Snake, you go back "+sattack+" tiles!");
		}
	void VultureBiteException(ArrayList<tile> tilelist_loc, int pos_loc) throws tileaction
		{
			throw new tileaction(">>\tYapping...! I am a Vulture, you go back "+vattack+" tiles!");
		}
	void CricketBiteException(ArrayList<tile> tilelist_loc, int pos_loc) throws tileaction
		{
			throw new tileaction(">>\tChirp...! I am a Cricket, you go back "+cattack+" tiles!");
		}
	void TrampolineException(ArrayList<tile> tilelist_loc, int pos_loc) throws tileaction
		{
			throw new tileaction(">>\tPingPong! I am a Trampoline, you advance "+tboost+" tiles!");
		}
	void GameWinnerException() throws tileaction
		{
			throw new tileaction(">>\t"+pname+" wins the race in "+roll+" rolls!");
		}
	void WhiteTileException() throws tileaction
		{
			throw new tileaction(">>\tI am a White tile!");
		}
	void showdata(){
		System.out.println(">>\t\tTotal snake bites = "+scount);
		System.out.println(">>\t\tTotal vulture bites = "+vcount);
		System.out.println(">>\t\tTotal cricket bites = "+ccount);
		System.out.println(">>\t\tTotal trampolines = "+tcount);
	}
	void setup(){
		System.out.println(">>Enter total number of tiles on the race track (length)");
		notiles = scan.nextInt();
		System.out.println(">>Setting up the race track...");
		int temp = notiles;
		tilelist.add(null);
		
		for(int i=1; i<temp; i++){
			int tt = rand.nextInt(6);
			if(tt == 0){
				tile styl = new snake();
				styl.setTno(i);
				tilelist.add(styl);
				nos++;
			}
			else if(tt == 1){
				tile vtyl = new vulture();
				vtyl.setTno(i);
				tilelist.add(vtyl);
				nov++;
			}
			else if(tt == 2){
				tile ctyl = new cricket();
				ctyl.setTno(i);
				tilelist.add(ctyl);
				noc++;
			}
			else if(tt == 3){
				tile ttyl = new trampoline();
				ttyl.setTno(i);
				tilelist.add(ttyl);
				not++;
			}
			else{
				tile wtyl = new white();
				wtyl.setTno(i);
				tilelist.add(wtyl);
			}
		}
		tile wtyl = new white();
		wtyl.setTno(notiles);
		tilelist.add(wtyl);
	}
	
	void unlock(){
		pos = 1;
		while (true){
			diceno = dice.roll(); roll++;
			if(diceno == 6){
				System.out.println(">>[Roll-"+roll+"]: "+pname+" rolled 6 at Tile-1. You are out of the cage! You get a free roll");
				break;
			}
			else {
				System.out.println(">>[Roll-"+roll+"]: "+pname+" rolled "+diceno+" at Tile-1, OOPs you need 6 to start");
			}
		}
		activeplay();
	}
	
	void activeplay(){
		int oldpos;
		for(int i=1; i<=tilelist.size(); i++){
			if(won){
				break;
			}
			diceno = dice.roll();roll++;
			oldpos = pos;
			pos += diceno;
			if(pos > notiles)
				pos -= diceno;
			System.out.println(">>[Roll-"+roll+"]: "+pname+" rolled "+diceno+" at Tile-"+oldpos+", landed on Tile "+pos);
			try{
				if(pos == notiles){
					won = true;
					GameWinnerException();
				}
			}
			catch(tileaction e){
				System.out.println(e.getMessage());
				if(won){
					break;
				}
			}
			System.out.println(">> Trying to shake the Tile-"+pos);
			if(pos > notiles){
				pos -= diceno;
			}
			if(pos <= 1){
				pos = 1;
				unlock();
			}
			try{
				if(tilelist.get(pos) instanceof snake){
					pos -= sattack;
					scount++;
					SnakeBiteException(tilelist, pos);
				}
				else if(tilelist.get(pos) instanceof vulture){
					pos -= vattack;
					vcount++;
					VultureBiteException(tilelist, pos);
				}
				else if(tilelist.get(pos) instanceof cricket){
					pos -= cattack;
					ccount++;
					CricketBiteException(tilelist, pos);
				}
				else if(tilelist.get(pos) instanceof trampoline){
					pos += tboost;
					if(pos > notiles){
						pos -= tboost;
					}
					else if(pos == notiles){
						won = true;
						System.out.println(">>\tPingPong! I am a Trampoline, you advance "+tboost+" tiles!");
						tcount++;
						GameWinnerException();
					}
					tcount++;
					TrampolineException(tilelist, pos);
				}
				else if(tilelist.get(pos) instanceof white){
					WhiteTileException();
				}
			}
			catch(tileaction e){
				System.out.println(e.getMessage());
				if(won)
					break;
				else if(pos <= 1)
					unlock();
			}
			
			//______delay_______
			try
			{
			    Thread.sleep(250);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			System.out.println("");
		}
		showdata();
	}

	void startgame(){
		System.out.println(">>Danger: There are "+nos+", "+noc+", "+nov+" numbers of Snakes, Cricket, and Vultures respectively on your track!");
		System.out.println(">>Danger: Each Snake, Cricket, and Vultures can throw you back by "+sattack+", "+cattack+", "+vattack+" number of Tiles respectively!");
		System.out.println(">>Good News: There are "+not+" number of Trampolines on your track!");
		System.out.println(">>Good News: Each Trampoline can help you advance by "+tboost+" number of Tiles");
		System.out.println(">>Enter the Player Name");
		pname = scan.next();
		System.out.println(">>Starting the game with "+pname+" at Tile-1");
		System.out.println(">>Control transferred to Computer for rolling the Dice for "+pname);
		System.out.println(">>Type 'start' to start the game");
		String enter = scan.next();
		if(enter.equals("start")){
			cond = true;
			System.out.println(">>Game Started ======================>");
			dice dice = new dice();
			unlock();
		}
	}
}

////////////////////////////////////////////

public class DiceGame {
	public static void main(String[] args){
		game game = new game();
		game.setup();
		game.startgame();
	}
}