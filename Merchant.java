import java.util.*;

class Merchant implements StakeHolders, Stake_Options{

	class Item_Merchant{
		Item item_details;
		float price;
		int offer = 0;
		int quantity;
		//2 if 25% off; 1 if buy one, get one, 0 if none

		Item_Merchant(String name, int price, int quantity, String category){
			item_details = new Item(name, category);
			this.price = price;
			this.quantity = quantity;
		}

		String getOffer(){
			if (offer == 0){
				return "None";
			}else if (offer == 1){
				return "Buy one, Get one";
			}else{
				return "25% off";
			}
		}

		public String toString(){
			return (this.item_details.getID() + " "
						+ this.item_details.getName() + " "
						+ this.price + " "
						+ this.quantity + " "
						+ this.getOffer() + " "
						+ this.item_details.getCategory());
		}

	}

	private static Scanner sc = new Scanner(System.in);

	private static ArrayList<Merchant> allMerchants = new ArrayList<>();
	private int num_items_allowed = 10;
	private int num_items_on_sale = 0;
	private HashMap <Integer,Item_Merchant> all_items_withMerchant = new HashMap<>();
	private static int id = 0;
	private final String name;
	private final String address;
	private final int merchant_id;
	private float contribution_to_company_account = 0.0f;
	static E_commerce_company comp;

	Merchant(){
		name = "";
		address = "";
		merchant_id  =0;
	}

	Merchant(String name, String address){
		this.name = name;
		this.address = address;
		this.merchant_id = ++id;
		allMerchants.add(this);
	}

	@Override
	public void showInitialMenuOptions(){
		System.out.println("Welcome " + this.name);
		System.out.println("Merchant Menu");
		int count = 0;
		System.out.println(++count + ") " + "Add item");
		System.out.println(++count + ") " + "Edit item");
		System.out.println(++count + ") " + "Search by category");
		System.out.println(++count + ") " + "Add Offer");
		System.out.println(++count + ") " + "Rewards won");
		System.out.println(++count + ") " + "Exit");

		int merchantChoice = sc.nextInt();
		decideNext(merchantChoice);
	}

	private void decideNext(int chosenOption){

		switch (chosenOption){
			case 1:
				addItem();
				this.showInitialMenuOptions();
				break;

			case 2:
				editItem();
				this.showInitialMenuOptions();
				break;

			case 3:
				
				break;

			case 4:

				break;

			case 5:
				
				break;

			case 6:
				comp.showInitialMenuOptions();

			default:
				
				break;
		}

	}

	private void addItem(){

		if (num_items_on_sale >= num_items_allowed){
			System.out.println("Trying to add more items then allowed");
			return;
		}

		System.out.println("Enter item details:");
		System.out.println("Item name");
		sc.nextLine();
		String i_name = sc.nextLine();
		Iterator hm_iter = all_items_withMerchant.entrySet().iterator();

		while (hm_iter.hasNext()){
			Map.Entry mappedElement = (Map.Entry) hm_iter.next();
			Item_Merchant item = (Item_Merchant) mappedElement.getValue();
			if (item.item_details.getName().equals(i_name)){
				System.out.println("Item already exists");
				return;
			}
		}
		System.out.println("Item price");
		int price = sc.nextInt();
		if (price<=0){
			System.out.println("Invalid price");
			return;
		}
		System.out.println("Item Quantity");
		int quantity = sc.nextInt();
		if (quantity<=0){
			System.out.println("Invalid quantity");
			return;
		}
		System.out.println("Item category");
		sc.nextLine();
		String category = sc.nextLine();
		num_items_on_sale++;

		Item_Merchant newItem = new Item_Merchant(i_name, price, quantity, category);
		all_items_withMerchant.put(newItem.item_details.getID(), newItem);
		Item_Merchant added_item = newItem;
		
		System.out.println(added_item);
	}

	private void editItem(){
		System.out.println("Choose item by code");

		Iterator hm_iter = all_items_withMerchant.entrySet().iterator();

		while (hm_iter.hasNext()){
			Map.Entry mappedElement = (Map.Entry) hm_iter.next();
			Item_Merchant item = (Item_Merchant) mappedElement.getValue();
			System.out.println(item);
		}

		int item_to_edit = sc.nextInt();

		Item_Merchant toEdit = all_items_withMerchant.get(item_to_edit);

		if (toEdit == null){
			System.out.println("No such item.");
			return;
		}

		System.out.println("Enter edit Details");

		System.out.println("Item price");
		int price = sc.nextInt();
		System.out.println("Item quantity");
		int quantity = sc.nextInt();

		toEdit.price = price;
		toEdit.quantity = quantity;

		System.out.println(toEdit);
	}


	public static void showDetailsAboutAll(int query){

		if (query == 1)	System.out.println("Choose Merchant");
		else
			System.out.println("Merchants");

		for (int i = 0; i<id; i++){
			Merchant current = allMerchants.get(i);
			System.out.println(current.merchant_id + " " + current.name);
		}

		if (query == 1){
			int chosenMerchant = sc.nextInt();


			if (chosenMerchant > id || chosenMerchant<1){
				System.out.println("Invalid ID chosen.");
				showDetailsAboutAll(1);
			}else{
				allMerchants.get(chosenMerchant - 1).showInitialMenuOptions();
			}
		}
	}

	@Override
	public void showDetails(int merchant_id){
		if (merchant_id > id || merchant_id<1){
			System.out.println("Invalid query");
			return;
		}
			
		Merchant current = allMerchants.get(merchant_id-1);
		System.out.println(current.name);
		System.out.println(current.address);
		System.out.println(current.contribution_to_company_account);

	}


}