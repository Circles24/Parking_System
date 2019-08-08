import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ClientProcessor extends Thread
{

	ParkingPlace p;
	VehicleFactory f;
	Socket skt;

	ClientProcessor(ParkingPlace p,VehicleFactory f,Socket skt){

		this.p = p;
		this.f = f;
		this.skt = skt;

		setDaemon(true);
		this.start(); 

	}

	

	public void run(){

		try{

			String mesg;

			int choice;
			int regNo,pri;

			byte[] reading_array = new byte[2048];
			byte[] writing_array = new byte[2048];

			DataInputStream din = new DataInputStream(skt.getInputStream());
			DataOutputStream dout = new DataOutputStream(skt.getOutputStream()); 

			boolean continue_ = true; 

			mesg = "\nHii Client Welcome to Our parking Service\n";

			dout.writeBytes(mesg);

			while(continue_){


				mesg = "\n****************  MENU ******************\n";
				mesg += "Add Custom Vehicle to Queue   ::         1\n";
				mesg += "Add Random Vehicle to Queue   ::         2\n";
				mesg += "Remove Vehicle from Queue     ::         3\n";
				mesg += "EXIT                          ::        -1\n";

				try{

					dout.writeBytes(mesg);

					choice = din.readInt();

					if (choice == 1){

						mesg = "enter choice MotorCycle(1), Car(2), Truck(3)\n";
						dout.writeBytes(mesg);
						choice = din.readInt();

						mesg = "enter the registration number\n";
						dout.writeBytes(mesg);
						regNo = din.readInt();

						mesg = "enter the priority\n";
						dout.writeBytes(mesg);
						pri = din.readInt();

						Vehicle v = f.getCustomVehicle(choice,regNo,pri);

						dout.writeBytes("got the vehicle\n");

						p.addVehicle(v);

						dout.writeBytes("done\n");

					}

					else if (choice == 2){

						mesg = "adding random vehicle\n";
						dout.writeBytes(mesg);

						p.addVehicle(f.getRandomVehicle());

						dout.writeBytes("done\n");

					}

					else if (choice == 3){

						mesg = "enter the registration number\n";
						dout.writeBytes(mesg);

						regNo = din.readInt();

						if(p.removeVehicle(Integer.valueOf(regNo)))dout.writeBytes("done\n");

						else dout.writeBytes("wrong registration number\n");
	
					}

					else throw new Exception("wrong choice\n");

				}

				catch(ArrayIndexOutOfBoundsException ex){

					System.out.println(ex.getMessage());

					dout.writeBytes("Vehicle Registration Number Shortage ");
				}

				catch(Exception ex){

					dout.writeBytes(ex.getMessage());
				}

			}

		}

			
		catch(Exception ex){

			System.out.println(ex);
		}



	}
}