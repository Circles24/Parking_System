import java.util.Scanner;
import java.util.ArrayList;

public class Main{

	private static ParkingPlace p;
	private static VehicleFactory f;

	public static void preprocess()throws Exception
	{

		int n;

		System.out.println("enter the number of registration numbers to be given to factory");

		n = new Scanner(System.in).nextInt();

		ArrayList<Integer> RegNos = new ArrayList<Integer>();

		System.out.println("now enter the "+n+" registration numbers");

		Scanner scn = new Scanner(System.in);

		for(int i=0;i<n;i++){

			RegNos.add(scn.nextInt());
		}

		f = new VehicleFactory(RegNos);
		p = new ParkingPlace(); 

			
	}

	public static int inner_menu() throws Exception
	{

		System.out.println("\n******** MENU **********\n");
		System.out.println("View Parking       ::     0");
		System.out.println("Add Vehicle        ::     1");
		System.out.println("Remove Vehicle     ::     2");
		System.out.println("Manage Queue       ::     3");
		System.out.println("Add Reg Numbers    ::     4");
		System.out.println("Remove Reg NUmber  ::     5");
		System.out.println("Exit               ::   default");

		return(new Scanner(System.in).nextInt());
	}

	public static void menu(){

		boolean continue_ = true;

		Scanner scn = new Scanner(System.in);

		while(continue_){

			try{

				switch(inner_menu()){

					case 0:
					{

						Main.p.view();

					}break;

					case 1:
					{
						Vehicle v =f.getVehicle();

						Main.p.addVehicle(v);
					
					}break;

					case 2:
					{

						System.out.println("enter the registration number of the vehicle to be removed");

						Main.p.removeVehicle(scn.nextInt());

					}break;

					case 3:
					{

						p.manageQueue();

					}break;

					case 4:
					{

						System.out.println("enter th Registration number to be added");

						int regNo = scn.nextInt();

						f.addRegistrationNumbers(regNo);

					}break;

					case 5:
					{

						System.out.println("enter th Registration number to be added");
						
						int regNo = scn.nextInt();

						f.removeRegistrationNumbers(regNo);

					}break;

					default: return;

				}

			}

			catch(Exception e){

				System.out.println(e);
			}
		}

	}

	public static void main(String args[]){

		System.out.println("\n__________________________________");
		System.out.println("************** MAIN **************\n");

		try{

			preprocess();
			menu();

		}

		catch(Exception ex){

			System.out.println(ex);
		}
		
	}
}