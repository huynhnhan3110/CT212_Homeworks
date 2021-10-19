import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPEchoClient {

	public static void main(String[] args) {
		try {
			// Tao 1 socket UDP
			DatagramSocket ds = new DatagramSocket();

			while (true) {
				// Nhap chuoi
				Scanner kb = new Scanner(System.in);
				System.out.print("Nhap vao mot chuoi: ");
				String str = kb.nextLine();
				// Xu ly ket thuc
				if (str.equals("EXIT")) {
					break;
				}
				// Tao ra goi tin dung de gui
				byte[] b = str.getBytes();
				int len = b.length;
				InetAddress ip = InetAddress.getByName("127.0.0.1");
				int port = 7;
				DatagramPacket goigui = new DatagramPacket(b, 0, len, ip, port);

				// Gui goi UDP den server
				ds.send(goigui);
				// Tao ra goi tin dung de nhan
				byte b1[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b1, 60000);

				// Nhan goi UDP tu server
				ds.receive(goinhan);

				// Hien thi
				byte b2[] = goinhan.getData();
				int len2 = goinhan.getLength();
				String str2 = new String(b2, 0, len2);
				System.out.println(str2);
			}
			// Dong socket
			ds.close();
		} catch (SocketException e) {
			System.out.println("Loi tao udp socket");
		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay host");
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}
	}

}
