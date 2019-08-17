import java.util.ArrayList;
import java.util.Scanner;

class Merchant implements StakeHolders, Stake_Options{

	private static Scanner sc = new Scanner(System.in);

	
	private static ArrayList<Merchant> allMerchants = new ArrayList<Merchant>();
	private static int id = 0;
	private final String name;
	private final String address;
	private final int merchant_id;
	private float contribution_to_company_account = 0.0f;

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
	public void showInitialMenuOptions(){};


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