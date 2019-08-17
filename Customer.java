import java.util.ArrayList;
import java.util.Scanner;

class Customer implements StakeHolders, Stake_Options{

	private static Scanner sc = new Scanner(System.in);

	private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	private static int id = 0;
	private final String name;
	private final String address;
	private final int customer_id;
	private int num_orders_placed = 0;

	Customer(){
		name = "";
		address = "";
		customer_id = 0;
	}

	Customer(String name, String address){
		this.name = name;
		this.address = address;
		this.customer_id = ++id;
		allCustomers.add(this);
	}

	@Override
	public void showInitialMenuOptions(){};


	public static void showDetailsAboutAll(int query){
		if (query == 1) System.out.println("Choose Customer");
		else 
			System.out.println("Customers");

		for (int i = 0; i<id; i++){
			Customer current = allCustomers.get(i);
			System.out.println(current.customer_id + " " + current.name);
		}

		if (query == 1){
			int chosenCustomer = sc.nextInt();

			if (chosenCustomer > id || chosenCustomer<1){
				System.out.println("Invalid ID chosen.");
				showDetailsAboutAll(1);
			}
		}
	}

	@Override
	public void showDetails(int customer_id){

		if (customer_id > id || customer_id<1){
			System.out.println("Invalid query");
			return;
		}

		Customer current = allCustomers.get(customer_id-1);
		System.out.println(current.name);
		System.out.println(current.address);
		System.out.println(current.num_orders_placed);
	}
}