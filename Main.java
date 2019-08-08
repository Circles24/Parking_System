import java.util.Scanner;
import java.util.ArrayList;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class Main{

	private static ParkingPlace p;
	private static VehicleFactory f;
	private static ServerSocket svrskt;

	public static void preprocess()throws Exception
	{

		int n;

		System.out.println("Welcome Admin");

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
	
	public static void main(String args[]){

		System.out.println("\n__________________________________");
		System.out.println("************** MAIN **************\n");

		try{

			InetAddress inetAddress = InetAddress.getByName(args[0]);  
			SocketAddress endPoint = new InetSocketAddress(inetAddress, Integer.parseInt(args[1]));  

			preprocess();
			
			svrskt = new ServerSocket();

			svrskt.bind(endPoint);

			Socket skt;

			System.out.println("server is up and running");

			new ParkingManager(Main.p, Main.f, svrskt);

			while(true){

				skt = svrskt.accept();

				new ClientProcessor(Main.p, Main.f,skt);

			}

		}

		catch(ArrayIndexOutOfBoundsException ex){

			System.out.println("Usage < ip adress of server >  < port number >");
		}

		catch(SocketException ex){

			System.out.println("Closing Server\n");
		}

		catch(Exception ex){

			System.out.println(ex);
		}
		
	}
}