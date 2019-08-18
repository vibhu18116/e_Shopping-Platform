import java.util.*;

class Customer implements StakeHolders, Stake_Options{

	private static Scanner sc = new Scanner(System.in);

	private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	private Stack<Item_Customer> last_10_purchases = new Stack<>();
	private static int id = 0;
	private final String name;
	private final String address;
	private final int customer_id;
	private int num_orders_placed = 0;
	private float mainAccountBalance = 100;
	private int rewardAccountBalance = 0;
	private Queue <Item_Customer> cart = new LinkedList<>();
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

		int customerChoice = sc.nextInt();
		decideNext(customerChoice);
	}

	private void decideNext(int chosenOption){

		switch (chosenOption){
			case 1:
				search_item();
				this.showInitialMenuOptions();
				break;

			case 2:
				checkoutCart();
				this.showInitialMenuOptions();
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

	private void search_item(){
		display_all_categories();
	}

	private void display_all_categories(){
		System.out.println("Choose a category");
		HashMap <Integer, String> temp = new HashMap<>();

		int count = 0;
		for (String cat: Merchant.all_categories.keySet()){
			System.out.println(++count + " " + cat);
			temp.put(count, cat);
		}

		int chosenCat = sc.nextInt();

		if (chosenCat<=0 || chosenCat>count){
			System.out.println("Invalid query");
			return;
		}

		System.out.println("Choose item by code");

		ArrayList <Item_Merchant> chosen = Merchant.all_categories.get(temp.get(chosenCat));

		for (int i = 0; i<chosen.size(); i++){
			System.out.println(chosen.get(i));
		}

		System.out.println("Enter item code");
		int itemCode = sc.nextInt();

		if (itemCode <=0 || itemCode>chosen.size()){
			System.out.println("Invalid query");
			return;
		}

		System.out.println("Enter item quantity");
		int quantity = sc.nextInt();

		if (quantity<=0){
			System.out.println("Invalid number of items.");
			return;
		}

		System.out.println("Choose method of transaction: ");

		int count_ = 0;
		System.out.println(++count_ + ") " + "Buy item");
		System.out.println(++count_ + ") " + "Add item to cart");
		System.out.println(++count_ + ") " + "Exit");
		int option = sc.nextInt();

		Item_Merchant toOrder = null;
		for (int i = 0; i<chosen.size(); i++){
			if (chosen.get(i).item_details.getID() == itemCode){
				toOrder = chosen.get(i);
				break; 
			}
		}

		switch (option){
			case 1:
				buyItem(toOrder, quantity);
				break;
			case 2:
				addToCart(toOrder, quantity);
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid query");
				break;
		}
	}

	private void addToCart(Item_Merchant toAdd, int quantity){

		if (quantity>toAdd.quantity){
			System.out.println("Out of Stock");
			return;
		}

		cart.add(new Item_Customer(toAdd, quantity));

		System.out.println(toAdd.item_details.getName() + " successfully added to cart");

	}

	private void buyItem(Item_Merchant toPurchase, int quantity){
		int offer = toPurchase.offer;
		float orderTotal = 0;

		if (offer == 1){
			orderTotal = toPurchase.price*((int) quantity/2);
			
			if (quantity%2 != 0)
				orderTotal += toPurchase.price;
		}else if (offer == 2)
			orderTotal = (float) toPurchase.price*quantity*((float) 0.25);
		else
			orderTotal = toPurchase.price*quantity;

		float transactionCharges = (float) orderTotal* ((float) 0.005);

		orderTotal += transactionCharges;

		if (quantity > toPurchase.quantity){
			System.out.println("Out of Stock");
			return;
		}else if(orderTotal>mainAccountBalance){
			System.out.println("Out of money");
			return;
		}else{
			toPurchase.quantity -= quantity;
			
			this.mainAccountBalance -= orderTotal;
			this.num_orders_placed++;
			toPurchase.merchant.setContribution(transactionCharges);

			last_10_purchases.push(new Item_Customer(toPurchase, orderTotal, quantity));

			if (num_orders_placed%5 == 0){
				rewardAccountBalance += 10;
				mainAccountBalance += 10;
			}

			System.out.println(toPurchase.item_details.getName() + " successfully bought");

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

			allCustomers.get(chosenCustomer - 1).showInitialMenuOptions();
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