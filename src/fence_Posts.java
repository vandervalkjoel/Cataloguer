import java.io.*;

//Alright i guess we need a game plan of what we actally are going to need for the fence posts. So the goal is to keep track of how many of each post is made. I think 
//for now i can keep it in three categories and that includes 6 foot 4 holes 1 1/4 inches, 4 foot 1 holes 1 1/4 inches and 4 foot 1 hole 1 inch. These will be my 3 
//different categories i think that i can have a parent class and then they can all be sub classes. the parent class will need the date it was made and the amount of posts made
//also i will need to have a price point for each of these posts. I can create new subclasses each time there is a new type of post made. The other thing that i will
//need to do is to make a export that will save the posts to some file so that i dont have to keep inputting the whole inventory every time. I could also have a 
//feature that will remove the posts as they get sold. 

public abstract class fence_Posts implements Serializable{
	protected String type;
	protected int amount;
	protected double price;
	protected String date;
	public fence_Posts(int amount, double price, String date) {
		this.amount = amount;
		this.price = price;
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public String getType() {
		return type;
	}
	public String getDate() {
		return date;
	}
	public double total_Cost() {
		return amount * price;
	}
}
class Sixfoot4hole extends fence_Posts {
	public Sixfoot4hole(int amount, double price, String date) {
		super(amount,price,date);
		type = "6 foot 4 holes 1 1/4 inches";
	} 
}
class Fourfoot1hole1_1_4 extends fence_Posts {
	public Fourfoot1hole1_1_4(int amount, double price, String date) {
		super(amount,price, date);
		type = "4 foot 1 holes 1 1/4 inches";
	} 
}
class Fourfoot1hole1 extends fence_Posts {
	public Fourfoot1hole1(int amount, double price, String date) {
		super(amount,price,date);
		type = "4 foot 1 hole 1 inch";
	} 
	
}
