
import java.io.*;

public class inventory implements Serializable{
	private fence_Posts[] stock;
	private static int counter = 0;
	private static double total_amount_cost = 0;
	private static int total_amount_count = 0;
	protected static int six6Foot4Hole = 0;
	protected static int fourFoot1HoleB = 0;
	protected static int fourFoot1HoleS = 0;
	
	public inventory(String filename) {
		load(filename);
	}
	public void exit(String filename) {	
		try (ObjectOutput out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
			out.writeObject(stock);
			out.writeInt(counter);
			out.writeDouble(total_amount_cost);
			out.writeInt(total_amount_count);
			out.writeInt(six6Foot4Hole);
			out.writeInt(fourFoot1HoleB);
			out.writeInt(fourFoot1HoleS);
			System.out.println("Data saved successfully to " + filename + ".");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot save you status!" + e.getMessage());
		} catch (IOException e) {
			System.err.println("I/O Error" + e.getMessage());
		}
	}
	public void load(String filename) {	
		File file = new File(filename);
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			stock = (fence_Posts[]) in.readObject();
			counter = in.readInt();
			total_amount_cost = in.readDouble();
			total_amount_count = in.readInt();
			six6Foot4Hole = in.readInt();
			fourFoot1HoleB = in.readInt();
			fourFoot1HoleS = in.readInt();
			System.out.println("Data loaded from " + filename + ".");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file. Using default values!");
			counter = 0;
			total_amount_cost = 0;
			total_amount_count = 0;
			six6Foot4Hole = 0;
			fourFoot1HoleB = 0;
			fourFoot1HoleS = 0;
			stock = new fence_Posts[100];
		} catch (IOException e) {
			System.err.println("I/O Error" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Internal Error!" + e.getMessage());
		}
	}
	public void add(fence_Posts post) {
		stock[counter] = post;
		counter++;
		total_amount_cost += post.total_Cost();
		total_amount_count += post.getAmount();
		if(post instanceof Sixfoot4hole)
			six6Foot4Hole += post.getAmount();
		else if (post instanceof Fourfoot1hole1_1_4)
			fourFoot1HoleB += post.getAmount();
		else if (post instanceof Fourfoot1hole1)
			fourFoot1HoleS += post.getAmount();
	}
	public void remove(String date) {
		int i;
		for(i = 0; i < counter; i++) {
			if(stock[i].getDate().equals(date)) {
				counter--;
				total_amount_cost -= stock[i].total_Cost();
				total_amount_count -= stock[i].getAmount();
				if(stock[i] instanceof Sixfoot4hole)
					six6Foot4Hole -= stock[i].getAmount();
				else if (stock[i] instanceof Fourfoot1hole1_1_4)
					fourFoot1HoleB += stock[i].getAmount();
				else if (stock[i] instanceof Fourfoot1hole1)
					fourFoot1HoleS += stock[i].getAmount();
				break;
			}
		}
		int j;
		for(j = i; j < counter; j++) {
			stock[j] = stock[j+1];
		}
		stock[j+1] = null;
	}
	public void display() {
		if(stock != null) {
			System.out.println("Number     Date            Type                    Amount     Total Cost");
			for(int i = 0; i < counter; i++) {
				System.out.printf("%6d  %10s  %27s      %4d        $%5.2f \n",i+1,stock[i].getDate(), stock[i].getType(), stock[i].getAmount(), stock[i].total_Cost());
			}
			System.out.println();
			System.out.println("The total costs is : $" + total_amount_cost);
			System.out.println("The total number of posts made is : " + total_amount_count);
			System.out.println("The total number of 6 foot posts in stock is: " + six6Foot4Hole);
			System.out.println("The total number of bigger 4 foot posts in stock is: " + fourFoot1HoleB);
			System.out.println("The total number of smaller 4 foot posts in stock is: " + fourFoot1HoleS);
			System.out.println();
		}
	}
}
