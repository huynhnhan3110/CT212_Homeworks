import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class DateMulticastServer {

	public static void main(String[] args) {
		try {
			// Tao UDP socket
			DatagramSocket ds = new DatagramSocket();
			while (true) {
				// Lay thong tin thoi gian
				Date d = new Date();
				String kq = d.toString();
				System.out.println(kq);
				// Tao goigui
				InetAddress ip = InetAddress.getByName("230.0.0.1");
				int port = 1234;
				byte[] b = kq.getBytes();
				int len = b.length;

				DatagramPacket goigui = new DatagramPacket(b, len, ip, port);

				// Gui goi tin
				ds.send(goigui);
				Thread.sleep(1000);
			}
		} catch (SocketException e) {
			System.out.println("Loi tao socket udp: " + e);
		} catch (UnknownHostException e) {
			System.out.println("Loi dia chi ip: " + e);
		} catch (IOException e) {
			System.out.println("Loi nhap xuat: " + e);
		}catch (InterruptedException e) {
			System.out.println("Loi khi ngu: " + e);
		}

	}

}
