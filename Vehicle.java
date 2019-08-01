
public abstract class Vehicle implements ParkingClient
{
	
	public Vehicle(VehicleType Type,int RegNo){

		System.out.println("Vehicle Underway");

		this.Type = Type;
		this.RegNo = RegNo;

	}

	protected VehicleType Type;

	protected abstract String honk();

	public int RegNo;
	
	public abstract int getPriority();

	public VehicleType getType(){

		return this.Type;

	}

	public void view(){

		System.out.println("Type :: "+Type);
		System.out.println("RegNo :: "+RegNo);
		System.out.println("Priority:: "+getPriority());
	}

}
