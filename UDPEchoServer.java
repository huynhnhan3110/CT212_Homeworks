import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPEchoServer {

	public static void main(String[] args) {
		try {
			// Tao UDP socket co dinh cong 7
			DatagramSocket ds = new DatagramSocket(7);
			System.out.println("Tao socket UDP thanh cong");
			// Tao goi tin de nhan
			byte b[] = new byte[60000];
			System.out.println("TOa mot cai mang bytemoi");
			DatagramPacket goinhan = new DatagramPacket(b, 60000);
			while (true) {
				// Nhan goi tin tu client
				ds.receive(goinhan);

				// Xu ly du lieu
				int len1 = goinhan.getLength();
				InetAddress ip = goinhan.getAddress();
				int port = goinhan.getPort();
				byte b1[] = goinhan.getData();

				String str = new String(b1, 0, len1);

				// Tao ra goi tin de gui cho client
				DatagramPacket goigui = new DatagramPacket(b1, len1, ip, port);

				// Gui goi tin cho client
				ds.send(goigui);
			}

		} catch (SocketException e) {
			System.out.println("CO loi tao socket UDP");
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
