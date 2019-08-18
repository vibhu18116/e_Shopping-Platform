class Item{

	private static int id = 0;
	private final int item_id;
	private final String name;
	private final String category;

	Item(String name, String category){
		item_id = ++id;
		this.name = name;
		this.category = category;
	}

	String getName(){
		return name;
	}

	int getID(){
		return item_id;
	}

	String getCategory(){
		return category;
	}
}


class Item_Merchant{
		Item item_details;
		float price;
		int offer = 0;
		int quantity;
		final Merchant merchant;
		//2 if 25% off; 1 if buy one, get one, 0 if none

		Item_Merchant(String name, int price, int quantity, String category, Merchant merchant){
			item_details = new Item(name, category);
			this.price = price;
			this.quantity = quantity;
			this.merchant = merchant;
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

class Item_Customer{

	final Item_Merchant most_details;
	float boughtFor;
	final int quantity;

	Item_Customer(Item_Merchant merchant, float boughtFor, int quantity){
		most_details = merchant;
		this.boughtFor = boughtFor;
		this.quantity = quantity;
	}

	Item_Customer(Item_Merchant merchant, int quantity){
		most_details = merchant;
		this.quantity = quantity;
	}

	public String toString(){
		return "Bought item " + this.most_details.item_details.getName() +
			 " quantity: " + this.quantity + " for Rs " + this.boughtFor + 
			 " from Merchant " + this.most_details.merchant.getName();
	}
}