import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPDateTimeClient {
	public static void main(String[] args) {
		try {
			// Tao socket udp
			DatagramSocket ds = new DatagramSocket();

			// Tao goigui
			InetAddress ip = InetAddress.getByName("127.0.0.1");
			int port = 13;
			byte b[] = new byte[5];
			int len = 0;
			DatagramPacket goigui = new DatagramPacket(b, len, ip, port);

			// Gui goi tin
			ds.send(goigui);
			// Tao goinhan
			byte b1[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b1, 60000);

			// Nhan goi tin
			ds.receive(goinhan);

			// Hien thi
			byte b2[] = goinhan.getData();
			int len2 = goinhan.getLength();
			String ketqua = new String(b2, 0, len2);
			System.out.println("Ngay gio hien tai: "+ketqua);

			// Dong socket
			ds.close();
		} catch (SocketException e) {
			System.out.println("Loi tao socket");
		} catch (UnknownHostException e) {
			System.out.println("Loi dia chi server");
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}
