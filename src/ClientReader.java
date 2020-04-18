import java.io.DataInputStream;

public class ClientReader extends Thread {

	private DataInputStream iStream;

	public ClientReader(DataInputStream iStream) {

		this.setDaemon(true);

		this.iStream = iStream;

		start();
	}

	public void run() {

		byte[] reading_array = new byte[2048];

		int n;

		while (true) {

			try {

				n = iStream.read(reading_array);

				if (n == -1)
					return;

				System.out.write(reading_array, 0, n);
			}

			catch (Exception ex) {

				System.out.println(ex.getMessage());
			}
		}
	}
}