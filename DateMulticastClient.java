import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class DateMulticastClient {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// Tao ra multicast socket cong 1234
			MulticastSocket ms = new MulticastSocket(1234);

			// Tham gia vao nhom dia chi 230.0.0.1
			InetAddress ip = InetAddress.getByName("230.0.0.1");
			
			ms.joinGroup(ip);

			// Nhan goi tu server
			byte b[] = new byte[10000];
			DatagramPacket goinhan = new DatagramPacket(b, 10000);
			
			ms.receive(goinhan);
			
			// Hien thi ra man hinh
			byte b1[] = goinhan.getData();
			int len = goinhan.getLength();
			String ketqua = new String(b1, 0, len);
			System.out.println("Ngay gio he thong: " + ketqua);

			// Roi khoi nhom
			ms.leaveGroup(ip);

			// Dong socket
			ms.close();
		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay dia chi: " + e);
		} catch (IOException e) {
			System.out.println("Xay ra loi nhap xuat: " + e);
		}
	}

}
