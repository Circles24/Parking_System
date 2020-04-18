import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ParkingPlace {

	private Vehicle[][] parking;

	Comparator<Vehicle> VehicleComparator = new Comparator<Vehicle>() {

		@Override
		public int compare(Vehicle v1, Vehicle v2) {

			if (v1.getPriority() == v2.getPriority())
				return 0;

			else if (v1.getPriority() > v2.getPriority())
				return -1;

			else
				return 1;

		}

	};

	PriorityQueue<Vehicle> queue;

	public ParkingPlace() {

		parking = new Vehicle[3][5];

		queue = new PriorityQueue<Vehicle>(VehicleComparator);

	}

	public String view() {

		StringBuffer str = new StringBuffer();

		str.append("******** parking *******\n");

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++) {

				if (parking[i][j] != null) {

					str.append("Row :: " + i + " Column :: " + j + "\n");
					str.append(parking[i][j].view() + "\n");

				}

				else {

					str.append("Row :: " + i + " Column :: " + j + "\n");
					str.append("Vehicle :: null" + "\n");
				}

			}

		str.append("************* queue *********\n");

		PriorityQueue<Vehicle> tempQueue = new PriorityQueue<Vehicle>(VehicleComparator);

		Vehicle v;

		while (queue.size() != 0) {

			v = queue.peek();

			str.append(v.view());

			queue.remove(v);

			tempQueue.add(v);

		}

		queue = tempQueue;

		return str.toString();
	}

	public int addVehicle(Vehicle v) {

		queue.add(v);

		return v.RegNo;

	}

	public boolean removeVehicle(int RegNo) {

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++) {

				if (parking[i][j] != null && parking[i][j].RegNo == RegNo) {

					parking[i][j] = null;

					return true;
				}

			}

		PriorityQueue<Vehicle> tempQueue = new PriorityQueue<Vehicle>(VehicleComparator);

		Vehicle v;

		while (queue.size() != 0) {

			v = queue.peek();

			if (v.RegNo != RegNo)
				tempQueue.add(v);

			queue.remove(v);

		}

		queue = tempQueue;

		return false;

	}

	public void manageQueue() {

		PriorityQueue<Vehicle> tempQueue = new PriorityQueue<Vehicle>(VehicleComparator);

		Vehicle v;

		boolean flag;

		while (queue.size() != 0) {

			flag = false;
			v = queue.peek();

			if (v.getType() == VehicleType.Truck) {

				for (int i = 0; i < 5; i++) {

					if (parking[0][i] == null) {

						parking[0][i] = v;

						flag = true;

						break;

					}
				}

				if (flag == false)
					tempQueue.add(v);
			}

			else if (v.getType() == VehicleType.Car) {

				for (int i = 0; i < 5; i++) {

					if (parking[1][i] == null) {

						parking[1][i] = v;

						flag = true;

						break;

					}
				}

				for (int i = 0; i < 5; i++) {

					if (parking[0][i] == null && flag == false) {

						parking[0][i] = v;

						flag = true;

						break;

					}
				}

				if (flag == false)
					tempQueue.add(v);
			}

			else {

				for (int i = 0; i < 5; i++) {

					if (parking[2][i] == null) {

						parking[2][i] = v;

						flag = true;

						break;

					}
				}

				for (int i = 0; i < 5; i++) {

					if (parking[1][i] == null && flag == false) {

						parking[1][i] = v;

						flag = true;

						break;

					}
				}

				for (int i = 0; i < 5; i++) {

					if (parking[0][i] == null && flag == false) {

						parking[0][i] = v;

						flag = true;

						break;

					}
				}

				if (flag == false)
					tempQueue.add(v);
			}

			queue.remove(v);

		}

		this.queue = tempQueue;

	}

}
