import java.util.*;

public class Adder { 
	static Scanner in = new Scanner(System.in);
	static inventory inv = new inventory("posts.dat");
	public static void main(String[] args) {
		start();
		inv.exit("posts.dat");
	}
	
	public static void start() {
		boolean condition = true;
		boolean con2 = true;
		int tracker = 0;
		while(con2) {
			while (condition) {
				System.out.println("What would you currently like to do: ");
				System.out.println("	1. See current inventory");
				System.out.println("	2. Add a set of posts to the stock");
				System.out.println("	3. Remove an entry ");
				System.out.println("    4. Sold Some posts ");
				System.out.print("    5. Shut the program down ");
				condition = false;
				try{
					tracker = in.nextInt();
				}catch(Exception e) {
					System.out.println("That is an invalid input. Please try again. ");
					in.next();
					condition = true;
				}
			}
			condition = true;
			System.out.println();
			switch(tracker) {
				case 1: display();break;
				case 2: add();break;
				case 3: remove();break;
				case 4: sale();break;
				case 5: con2 = false;break;
				default: System.out.println("That is an invalid answer please try again");
			}
		}
	}
	public static void add() {
		int amount = 0;
		fence_Posts post = null;
		boolean condition = true;
		int selection = 0;
		double amo = 0;
		String date = "";
		while(condition) {
			System.out.print("Enter the date in the form mm/dd/yyyy: ");
			date = in.next();
			if(date.length() == 10)
				condition = false;
			else
				System.out.println("That is not a valid form for the date. Please try again! ");
		}
		condition = true;
		while(condition) {
			System.out.print("Please enter the amount of posts that were made: ");
			condition = false;
			try{
				amount = in.nextInt();
			}catch(Exception e) {
				System.out.println("That is an invalid input. Please try again. ");
				in.next();
				condition = true;
			}
		}
		condition = true;
		while(condition) {
			System.out.print("Please enter the amount being paid: ");
			condition = false;
			try{
				amo = in.nextDouble();
			}catch(Exception e) {
				System.out.println("That is an invalid input. Please try again! ");
				in.next();
				condition = true;
			}
		}
		condition = true;
		while(condition) {
			System.out.println("What kind of post do you wish to add to the inventory?  ");
			System.out.println("   1. 6 foot 4 holes");
			System.out.println("   2. 4 foot 1 hole 1 1/4 inches");
			System.out.print("   3. 4 foot 1 hole 1 inch  ");
			condition = false;
			try {
				selection = in.nextInt();
			}catch(Exception e) {
				System.out.println("That is not a valid entry. Please try again! ");
				in.next();
				condition = true;
			}
			switch(selection) {
				case 1: post = new Sixfoot4hole(amount,amo,date); break;
				case 2: post = new Fourfoot1hole1_1_4(amount,amo,date); break;
				case 3: post = new Fourfoot1hole1(amount,amo,date); break;
				default: System.out.println("That is an invalid answer. Please try again. ");
			}
		}	
		System.out.println("Are you sure you would like to add this post this is what it will look like in the inventory?");
		System.out.println();
		System.out.printf("%10s  %27s      %4d        $%5.2f \n",post.getDate(), post.getType(), post.getAmount(), post.total_Cost());
		System.out.println();
		System.out.println("Yes or No (Y/N)");
		String answer = in.next();
		if(answer.equalsIgnoreCase("Y")) {
			if(post != null) {
				inv.add(post);
				System.out.println("Posts were successfully added to the inventory.");
			}
		}
		else 
			System.out.println("Adding the posts was unsuccessful.");		
	}
	public static void remove() {
		System.out.println("Which entry would you like to remove enter the date of the item in the form mm/dd/yyyy: ");
		String date = in.next();
		inv.remove(date);
	}
	public static void sale() {
		int sold = 0;
		int answer = 0;
		System.out.print("How many posts were sold? ");
		boolean con = true;
		while(con) {
			con = false;
			try {
				sold = in.nextInt();
			}catch(Exception e) {
				System.out.println("That is not a valid input. Please try again.");
				in.next();
				con = true;
			}
		}
		con = true;
		while(con) {
			con = false;
			System.out.println("What type of posts were sold? ");
			System.out.println("   1. 6 foot 4 holes");
			System.out.println("   2. 4 foot 1 hole 1 1/4 inches");
			System.out.print("   3. 4 foot 1 hole 1 inch  ");
			try {
				answer = in.nextInt();
			}catch(Exception e) {
				System.out.println("That is not a valid input. Please try again");
				in.next();
				con = true;
			}
			switch(answer) {
				case 1: inv.six6Foot4Hole -= sold; break;
				case 2: inv.fourFoot1HoleB -= sold; break;
				case 3: inv.fourFoot1HoleS -= sold; break;
				default: System.out.println("That is an invalid answer. Please try again. "); con = true;
			}
		}
		System.out.println();
	}
	public static void display() {
		inv.display();
	}
}
