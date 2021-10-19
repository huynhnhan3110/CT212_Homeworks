import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPDateTimeServer {

	public static void main(String[] args) {
		try {
			// Tao socket udp server cong 13
			DatagramSocket ds = new DatagramSocket(13);

			// Tao goinhan
			byte b[] = new byte[60000];

			DatagramPacket goinhan = new DatagramPacket(b, 60000);
			while (true) {
				// Nhan goi tin
				ds.receive(goinhan);

				// Xu ly
				InetAddress ip = goinhan.getAddress();
				int port = goinhan.getPort();
				Date currentDateTime = new Date();
				byte b1[] = currentDateTime.toString().getBytes();
				int len1 = b1.length;
				System.out.println("Ngay gio hien tai: " + currentDateTime.toString());
				// Tao goigui
				DatagramPacket goigui = new DatagramPacket(b1, len1, ip, port);
				// Gui goi tin
				ds.send(goigui);
			}

		} catch (SocketException e) {
			System.out.println("Loi tao socket udp");
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
