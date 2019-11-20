import java.util.Scanner;
class Raminion{
	static double[] values = {1,4,.5,3,0,0};
	static double[] costs = {1,6,2,8,1,10};
	static String[] cardTitle = {"Copper", "Gold", "Monroe Park", "Ginter house", "Harris", "Ram"};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hello Player! Welcome to Raminion. This is a solo player card game where\n"
				+ " the player is given a constant starter deck and then given options to\n"
				+ "upgrade their deck. If you have any experience in gaming this very similar \n"
				+ "to Heathstone's solo-player Adventure mode. The game is turn based and ends\n"+
				"after 20 turns have passed. The player wins if at the end of the 20 turns, there\n"
				+ "is at least 40 points in the deck. each turn the player draws seven cards.\n "
				+ "There are 6 uniqure cards in the game. Each card can either give money(Money Cards),\n"
				+ "draw cards and give money(Action Cards), or give points to the total points in the deck \n"
				+ "(Point cards). the six cards in the game are:\n"
				+ "1: Copper - Money Card - cost: $1 - value: $1\n"
				+ "2: Gold - Money Card - cost $6 - value: $4\n"
				+ "3: Monroe Park - Action Card - cost: $2 - value: $0.5 - draw: 1\n"
				+ "4: Ginter House - Action Card - cost: $8 - value: $3 - draw: 2\n"
				+ "5: Harris - Point Card - cost: $1 - adds 1 point to total in deck\n"
				+ "6: Ram - Point Card - cost: $10 - adds 5 point to total in deck");
		int[] hand = {0,0,0,0,0,0};
		int[] played = {0,0,0,0,0,0};
		int[] deck = {7,0,0,0,3,0};
		int points = 3;
		for(int i = 1; i <= 20; i++){
			System.out.println("Turn: "+i);
			if (i==20)
				System.out.println("WARNING: Last Turn Buy ONLY Harris");
			System.out.println("Draw Phase");
			for(int j = 0; j < 7; j++) {
				int cardChos = (int) (Math.random()*sum(deck));
				int lvl = 0;
				int hrt = cardChos;
				int ite = 0;
				while(hrt > 0) {
					hrt-=deck[ite];
					ite+=1;
					if(hrt>0) {
						lvl+=1;
					}
				}
				hand[lvl] += 1;
				deck[lvl] -= 1;
			}
			printHand(hand);
			if(hand[2] != 0) {
				while(hand[2] != 0) {
					int cardChos = (int) (Math.random()*sum(deck));
					int lvl = 0;
					int hrt = cardChos;
					int ite = 0;
					while(hrt > 0) {
						hrt-=deck[ite];
						ite+=1;
						if(hrt>0) {
							lvl+=1;
						}
					}
					hand[lvl] += 1;
					deck[lvl] -= 1;
					played[2] += 1;
					hand[2] -= 1;
				}
			}
			if(hand[3] != 0) {
				while(hand[3] != 0) {
					for(int k = 0; k <= 1; k++) {
					int cardChos = (int) (Math.random()*sum(deck));
					int lvl = 0;
					int hrt = cardChos;
					int ite = 0;
					while(hrt > 0) {
						hrt-=deck[ite];
						ite+=1;
						if(hrt>0) {
							lvl+=1;
						}
					}
					hand[lvl] += 1;
					deck[lvl] -= 1;
					}
					played[3] += 1;
					hand[3] -= 1;
				}
			}
			printPlayed(played);
			printHand(hand);
			System.out.println("value of round "+i+" is : "+value(hand,played));
			System.out.println("Buying Phase");
			int avail = value(hand,played);
			boolean stay =true;
			while(avail > 0 && stay) {
				System.out.println("enter the number of the card you would like to purchase 1-6 (as seen in instructions)");
				int buy = scan.nextInt();
				buy-=1;
				if(buy==-1) {
					stay = false;
					continue;
				}
				System.out.println("how many?");
				int numbe = scan.nextInt();
				if(avail-(costs[buy]*numbe) >= 0) {
					avail = (int) (avail-(costs[buy]*numbe ));
					System.out.println("You purchased "+cardTitle[buy]+" and your new balance is "+avail);
					played[buy] += numbe;
					if(buy == 4)
						points+= numbe;
					if(buy==5)
						points+=5*numbe;
				}else {
					System.out.println("This card costs too much");
				}
			}
			printPlayed(played);
			System.out.println("points: "+points);
			System.out.println("End of round :"+i);
			for(int k = 0; k < 6; k ++) {
				deck[k] += hand[k] + played[k];
				hand[k] = 0;
				played[k] = 0;
			}
		}
		printdeck(deck);
		System.out.println("You earned a total of " + points+" points this game");
		if(points > 40) {
			System.out.println("Congratulations you win!!!");
		}else {
			System.out.println("you failed, better luck next time");
		}
	}
	static int sum (int[] array) {
		int sum = 0;
		for (int value : array) {
			sum += value;
		}
		return sum;
	}
	static void printHand(int[] arr) {
		int siz = 0;
		for (int j = 0; j < 6; j++) {
			System.out.println(arr[j]+" * "+cardTitle[j]);
			siz += arr[j];
		}
		System.out.println("Your hand is: Hand size is "+siz+" \n");
	}
	static int value(int[] hand, int played[]) {
		int val = 0;
		for(int i = 0; i < 6; i++) {
			val += values[i]*(hand[i]+played[i]);
		}
		return val;
	}
	static void printPlayed(int[] arr) {
		System.out.print("The cards played are: [");
		for (int i = 0; i < 6; i++) {
			System.out.print(arr[i]+", ");
		}
		System.out.println("]");
	}
	static void printdeck(int[] arr) {
		System.out.print("The cards in deck are: [");
		for (int i = 0; i < 6; i++) {
			System.out.print(arr[i]+", ");
		}
		System.out.println("]");
	}
}
