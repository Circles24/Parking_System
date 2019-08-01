public class Car extends Vehicle 
{

	private int priority;

	public Car(int RegNo,int priority){

		super(VehicleType.Car,RegNo);

		this.priority = priority;

	}

	public  String honk(){

		return "PPPPPP PPPPPPP";

	}


	public int getPriority(){

		return priority;

	}



}
