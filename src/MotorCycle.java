public class MotorCycle extends Vehicle {
	private int priority;

	public MotorCycle(int RegNo, int priority) {

		super(VehicleType.MotorCycle, RegNo);

		this.priority = priority;

	}

	public String honk() {

		return "ppp ppp";

	}

	public VehicleType getType() {

		return this.Type;

	}

	public int getPriority() {

		return priority;

	}

}
