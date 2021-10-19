import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

class PhucVuGui extends Thread {
	DatagramSocket ds;

	public PhucVuGui(DatagramSocket ds1) {
		ds = ds1;
	}

	public void run() {
		try {
			Scanner kb = new Scanner(System.in); // Hoi thay cach lam sao de co dong "Noi dung: " Phia truoc scanner
			while (true) {
				// Nhap du lieu can gui den server
				String loinhan = kb.nextLine();

				// Ket thuc tro chuyen
				if (loinhan.equals("EXIT"))
					break;

				// Tao goigui
				byte b[] = loinhan.getBytes();
				int len = b.length;
				InetAddress ip = InetAddress.getByName("127.0.0.1");
				int port = 69;

				DatagramPacket goigui = new DatagramPacket(b, len, ip, port);
				// Gui goi tin
				ds.send(goigui);
			}
			ds.close();
		} catch (SocketException e) {
			System.out.println("Loi tao socket udp: " + e);
		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay server: " + e);
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat: " + e);

		}
	}
}

class PhucVuNhan extends Thread {
	DatagramSocket ds;

	public PhucVuNhan(DatagramSocket ds1) {
		ds = ds1;
	}

	public void run() {

		try {
			while (true) {
				// Tao goinhan
				byte b1[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b1, 60000);
				// Nhan goi tin
				ds.receive(goinhan);
				// Hien thi ra man hinh
				byte b2[] = goinhan.getData();
				int len2 = goinhan.getLength();
				String phanhoi = new String(b2, 0, len2);
				System.out.println("Tra loi: " + phanhoi);

			}
		} catch (SocketException e) {
			System.out.println("Loi socket " + e);
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat111: " + e);
		}
	}
}

public class UDPTalkClient {

	public static void main(String[] args) {
		try {
			// Tao ket noi den socket udp
			DatagramSocket ds = new DatagramSocket();
			System.out.println("Chao ban den voi chuong trinh Chat");
			System.out.println("Nhap vao noi dung va nhan [enter] de gui");

			PhucVuGui pvgui = new PhucVuGui(ds);
			pvgui.start();

			PhucVuNhan pvnhan = new PhucVuNhan(ds);
			pvnhan.start();

		} catch (SocketException e) {
			System.out.println("Ket noi socket udp that bai");
		}
	}

}
