import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class PhucVuTachTen extends Thread {
	Socket socket;
	public PhucVuTachTen(Socket s1) {
		socket = s1;
	}
	public void run() {
		try {
			// Lay stream in out
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			while (true) {
				// Nhan du lieu tu client
				byte b[] = new byte[1000];
				int length = is.read(b);
				String hovaten = new String(b, 0, length);
				System.out.println("Nhan duoc: " + hovaten);
				if (hovaten.equals("EXIT"))
					break;
				// Xu ly tach ten
				String ten = hovaten.substring(hovaten.lastIndexOf(" ") + 1);
				System.out.println("Sau tach: " + ten);
				// Tra du lieu ve client
				os.write(ten.getBytes());
			}
			// Dong ket noi
			socket.close();
		}catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}
	}
	
}

public class TachTenServer_SS {

	public static void main(String[] args) {
		try {
			// Tao server socket cong 9999
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Tao server thanh cong");
			// Chap nhan cho client ket noi
			while (true) {
				try {
					Socket s = ss.accept();
					PhucVuTachTen phucvu = new PhucVuTachTen(s);
					phucvu.start();
				} catch (IOException e) {
					System.out.println("Co loi nhap xuat");
				}
			}
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
