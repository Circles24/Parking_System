import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client implements Runnable
{
	private Socket skt;
	private DataInputStream iStream;
	private DataOutputStream oStream;


	public Client(int port)throws Exception
	{

		System.out.println("connecting to server");

		skt = new Socket("127.0.0.1",port);

		System.out.println("connection established");

		iStream = new DataInputStream(skt.getInputStream());
		oStream = new DataOutputStream(skt.getOutputStream());

		new ClientReader(iStream);

		System.out.println("establishing communication");

		Thread t = new Thread(this);
		t.start();
	}

	public void run()
	{

		String str;
		Scanner scn = new Scanner(System.in);
		byte singleByte;
		int choice;

		try{

			boolean continue_ = true;

			while(continue_){

				choice = scn.nextInt();

				if(choice == -1)return ;

				oStream.writeInt(choice);

			}
		}

		catch(Exception ex){

			System.out.println(ex);
		}

	}

	public static void main(String args[]){

		try{

				Client c = new Client(Integer.parseInt(args[0]));

		}

		catch(Exception ex){

			System.out.println(ex);
		}


	}


}