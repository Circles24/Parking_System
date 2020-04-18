import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class VehicleFactory {

	HashMap<Integer, Boolean> present = new HashMap<Integer, Boolean>();

	private Random rand = new Random();

	private ArrayList<Integer> RegNos;

	public VehicleFactory(ArrayList<Integer> RegNos) throws Exception {

		for (int i : RegNos) {

			if (present.get(i) == null)
				present.put(i, true);

			else
				throw new Exception("wrong input data");
		}

		this.RegNos = RegNos;

	}

	public boolean hasRegNo(int regNo) {

		for (int i : RegNos)
			if (i == regNo)
				return true;

		return false;
	}

	public Vehicle getRandomVehicle() {

		int regNo = RegNos.get(0);

		RegNos.remove(0);

		switch (rand.nextInt(3)) {

			case 0:
				return new MotorCycle(regNo, rand.nextInt(100));

			case 1:
				return new Car(regNo, rand.nextInt(100));

			case 2:
				return new Truck(regNo, rand.nextInt(100));
		}

		return new Car(regNo, rand.nextInt(100));

	}

	public Vehicle getCustomVehicle(int choice, int regNo, int pri) throws Exception {

		if (choice > 3 || choice < 1)
			throw new Exception("wrong choice");

		boolean present = false;

		for (int i : RegNos)
			if (i == regNo)
				present = true;

		if (!this.hasRegNo(regNo))
			throw new Exception("Wrong regNo");

		if (pri <= 0)
			throw new Exception("Wrong priority");

		RegNos.remove(Integer.valueOf(regNo));

		if (choice == 1)
			return new Car(regNo, pri);

		else if (choice == 2)
			return new MotorCycle(regNo, pri);

		else
			return new Truck(regNo, pri);
	}

	public void addRegistrationNumber(int regNo) {

		if (present.get(regNo) == null) {

			present.put(regNo, true);

			RegNos.add(regNo);

		}
	}

	public void removeRegistrationNumber(int RegNo) {

		RegNos.remove(RegNo);
	}

}
