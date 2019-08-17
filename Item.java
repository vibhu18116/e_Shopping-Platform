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