import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList; 
import java.util.HashMap;

public class VehicleFactory
{

	HashMap<Integer,Boolean > present = new HashMap<Integer, Boolean>(); 

	private Random rand = new Random();

	private ArrayList<Integer> RegNos;

	public VehicleFactory(ArrayList<Integer> RegNos)throws Exception
	{

		System.out.println("Vehicle factory Creation underway");

		for(int i:RegNos){

			if(present.get(i) == null)present.put(i,true);

			else throw new Exception("wrong input data");
		}

		this.RegNos = RegNos;

	}

	private Vehicle getRandomVehicle(){

		int regNo = RegNos.get(0);

		RegNos.remove(0);

		switch(rand.nextInt(3)){
			
			case 0:return new MotorCycle(regNo,rand.nextInt(100));

			case 1:return new Car(regNo,rand.nextInt(100));

			case 2:return new Truck(regNo,rand.nextInt(100));
		}

		return new Car(regNo,rand.nextInt(100));

	}

	public Vehicle getVehicle()throws Exception
	{

		if(RegNos.size() == 0)throw new Exception("Regnos worn out");

		System.out.println("Random vehicle(0) or Custom vehicle(1)");

		Scanner sin = new Scanner(System.in);

		int choice = sin.nextInt();

		if(choice == 0 ){

			return getRandomVehicle();
		}

		else if(choice == 1){

			System.out.println("enter the type of vehicle Truck(0), Car(1), MotorCycle(2)");

			choice = sin.nextInt();

			if(choice < 0 || choice > 2)throw new Exception("wrong choice");

			System.out.println("choose the registration number");

			for( int i : RegNos ){

				System.out.println(i);
			}

			int regNoIndex = sin.nextInt();

			if(regNoIndex > RegNos.size() || regNoIndex <= 0 )throw new Exception("wrong regNo Index");

			System.out.println("enter the priority for the vehicle");

			int pri = sin.nextInt(),k=0;

			int regNo = RegNos.get(regNoIndex);

			RegNos.remove(regNoIndex);			

			if(pri < 0)throw  new Exception("wrong priority");

			if(choice == 0)return new Truck(regNo,pri);

			else if(choice == 1)return new Car(regNo,pri);

			else return new MotorCycle(regNo,pri);

		}

		else{

				throw new Exception("wrong choice");

		} 

		
	}

	public void addRegistrationNumbers(int regNo){

		if(present.get(regNo) == null){

			present.put(regNo,true);

			RegNos.add(regNo);


		}
	}

	public void removeRegistrationNumbers(int RegNo){

		RegNos.remove(RegNo);
	}


}
