import java.util.Scanner;
import java.io.DataInputStream;
import java.net.ServerSocket;

public class ParkingManager implements Runnable {

	private ParkingPlace p;
	private VehicleFactory f;
	private ServerSocket svrSkt;

	ParkingManager(ParkingPlace p, VehicleFactory f, ServerSocket svrSkt) {

		this.p = p;
		this.f = f;
		this.svrSkt = svrSkt;

		(new Thread(this)).start();
	}

	public int menu() {

		System.out.println("__________ MANAGER MENU ___________\n");

		System.out.println("View Parking Place            ::    0");
		System.out.println("Add Vehicle                   ::    1");
		System.out.println("Remove Vehicle                ::    2");
		System.out.println("Manage  Queue                 ::    3");
		System.out.println("Add Registration Number       ::    4");
		System.out.println("Remove Registration Number    ::    5");
		System.out.println("stop program                  ::  default");

		return (new Scanner(System.in)).nextInt();
	}

	public void run() {

		DataInputStream din = new DataInputStream(System.in);

		int choice, regNo, pri;
		boolean present;
		Scanner scn = new Scanner(System.in);

		while (true) {

			try {

				switch (menu()) {

					case 0: {

						System.out.println(p.view());

					}
						break;

					case 1: {

						System.out.println("enter the type of vehicle  MotorCycle(1), Car(2), Truck(3)");

						choice = scn.nextInt();

						if (choice > 3 || choice < 1)
							throw new Exception("wrong choice");

						System.out.println("enter the registration number");

						regNo = scn.nextInt();

						present = false;

						if (f.hasRegNo(regNo) == false)
							throw new Exception("wrong registration number");

						System.out.println("enter the priority");

						pri = scn.nextInt();

						if (pri <= 0)
							throw new Exception("wrong priority");

						p.addVehicle(f.getCustomVehicle(choice, regNo, pri));

						System.out.println("done");

					}
						break;

					case 2: {
						regNo = scn.nextInt();

						p.removeVehicle(regNo);

						System.out.println("done");

					}
						break;

					case 3: {
						p.manageQueue();

					}
						break;

					case 4: {
						regNo = scn.nextInt();

						f.addRegistrationNumber(regNo);

					}
						break;

					case 5: {

						regNo = scn.nextInt();

						f.removeRegistrationNumber(regNo);

					}
						break;

					default: {

						System.out.println("stopping the program");

						svrSkt.close();

						return;
					}

				}
			}

			catch (Exception ex) {

				System.out.println(ex.getMessage());
			}
		}
	}
}