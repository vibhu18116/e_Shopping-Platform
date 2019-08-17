import java.util.*;

class Customer implements StakeHolders, Stake_Options{

	private static Scanner sc = new Scanner(System.in);

	private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	private static int id = 0;
	private final String name;
	private final String address;
	private final int customer_id;
	private int num_orders_placed = 0;
	private int mainAccountBalance = 100;
	private int rewardAccountBalance = 0;
	private Queue <Item_Merchant> cart = new LinkedList<>();
	static E_commerce_company comp;

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
	public void showInitialMenuOptions(){
		System.out.println("Welcome " + this.name);
		System.out.println("Customer Menu");
		int count = 0;
		System.out.println(++count + ") " + "Search item");
		System.out.println(++count + ") " + "checkout cart");
		System.out.println(++count + ") " + "Reward won");
		System.out.println(++count + ") " + "print latest orders");
		System.out.println(++count + ") " + "Exit");

		int merchantChoice = sc.nextInt();
		decideNext(merchantChoice);
	}

	private void decideNext(int chosenOption){

		switch (chosenOption){
			case 1:
				
				break;

			case 2:
				
				break;

			case 3:
				
				break;

			case 4:
				
				break;

			case 5:
				comp.showInitialMenuOptions();
				break;

			default:
				System.out.println("Invalid query");
				this.showInitialMenuOptions();
				break;
		}

	}


	@Override
	public void showDetailsAboutAll(int query){
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