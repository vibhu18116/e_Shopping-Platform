import java.util.Scanner;
import java.lang.*;

public class E_commerce_company implements StakeHolders{

	private float accountBalance = 0.0f;

	private Scanner sc = new Scanner(System.in);

	public void showDetails(Stake_Options stakeholder, int id){
		stakeholder.showDetails(id);
	}

	E_commerce_company(){
		Merchant _jack = new Merchant("jack", "Jumbo Dealers, Sector 21, NOIDA, UP");
		Merchant _john = new Merchant("john", "Keith and Sons, Sector 20, Dwarka, Delhi");
		Merchant _james = new Merchant("james", "Cambray Shoppers, Pitampura, Delhi");
		Merchant _jeff = new Merchant("jeff", "Aricson Services, Kalkaji, Delhi");
		Merchant _joseph = new Merchant("joseph", "Gimper Sellers, Sector 54, Gurgaon, Haryana");
		Customer _ali = new Customer("ali", "H-508, Imperial Societies, Greater Noida, UP");
		Customer _nobby = new Customer("nobby", "House No 343, Jeevan Appartments, Greater Kailash, New Delhi");
		Customer _bruno = new Customer("bruno", "C-1203, Appolo Acers, Hudson Lane, Delhi");
		Customer _borat = new Customer("borat", "9-Euler Tower, Sacred Soccers, GyanKhand, Meerut, UP");
		Customer _aladeen = new Customer("aladeen", "D-93, Delphi Homes, Sector 6, Faridabad");
		Merchant.comp = this;
	}

	void startApp(){
		this.showInitialMenuOptions();
	}

	@Override
	public void showInitialMenuOptions(){
		int num_options = 0;
		System.out.println("Welcome to Mercury");
		++num_options;
		System.out.println(num_options + ") " + "Enter as Merchant");
		++num_options;
		System.out.println(num_options + ") " + "Enter as Customer");
		++num_options;
		System.out.println(num_options + ") " + "See user details");
		++num_options;
		System.out.println(num_options + ") " + "Company account balance");
		++num_options;
		System.out.println(num_options + ") " + "Exit");

		int chosenOption = sc.nextInt();

		decideNext(chosenOption);


	}

	private void decideNext(int chosenOption){

		switch (chosenOption){
			case 1:
				Merchant.showDetailsAboutAll(1);
				break;

			case 2:
				Customer.showDetailsAboutAll(1);
				break;

			case 3:
				Merchant.showDetailsAboutAll(0);
				Customer.showDetailsAboutAll(0);
				System.out.println("Choose using M/C id number.");
				Character query = sc.next().charAt(0);
				int id = sc.nextInt();

				if (query.equals('M'))
					showDetails(new Merchant(), id);
				else if (query.equals('C'))
					showDetails(new Customer(), id);
				else{
					System.out.println("Invalid query");
				}
				showInitialMenuOptions();
				break;

			case 4:
				System.out.println("Account balance of the company is " + accountBalance);
				break;

			case 5:
				System.out.println("Exiting...");
				System.exit(0);
				break;

			default:
				System.out.println("Enter valid option");
				showInitialMenuOptions();
				break;
		}

	}
}