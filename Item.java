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