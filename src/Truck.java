public class Truck extends Vehicle {

	private int priority;

	public Truck(int RegNo, int priority) {

		super(VehicleType.Truck, RegNo);

		this.priority = priority;
	}

	public String honk() {

		return "PPPPPPPPPPPPPP";

	}

	public VehicleType getType() {

		return this.Type;

	}

	public int getPriority() {

		return priority;

	}

}
