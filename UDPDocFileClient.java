import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPDocFileClient {

	public static void main(String[] args) {
		try {
			// Tao udp socket
			DatagramSocket ds = new DatagramSocket();
			while (true) {
				// Nhap du lieu tu ban phim
				Scanner kb = new Scanner(System.in);
				System.out.print("Nhap ten file: ");
				String tenfile = kb.nextLine();

				// Xu ly ket thuc
				if (tenfile.equals("EXIT"))
					break;
				String caulenh = "READUDP " + tenfile;

				// Tao goigui den server
				byte b[] = caulenh.getBytes();
				int len = b.length;
				InetAddress ip = InetAddress.getByName("127.0.0.1");
				int port = 69;
				DatagramPacket goigui = new DatagramPacket(b, len, ip, port);

				// Gui goi tin
				ds.send(goigui);

				// Tao goinhan luu gia tri server
				byte b1[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b1, 60000);

				// Nhan goi tin
				ds.receive(goinhan);
				// Hien thi
				byte b2[] = goinhan.getData();
				int len2 = goinhan.getLength();
				if (len2 == 0) {
					System.out.println("File rong hoac khong ton tai");
				} else {
					System.out.print("Hay nhap ten file can luu: ");
					String tenfilemoi = kb.nextLine();
					FileOutputStream fileout = new FileOutputStream(tenfilemoi);
					fileout.write(b2, 0, len2);
					fileout.close();
					System.out.println("Da ghi thanh cong");
				}
			}
			// Dong socket
			ds.close();

		} catch (SocketException e) {
			System.out.println("Co loi khi tao socket server");
		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay ip server");
		} catch (FileNotFoundException e) {
			System.out.println("Tap tin khong hop le");
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}
	}

}
