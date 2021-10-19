import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
class PhucVuInChu extends Thread {
	private Socket s;
	public PhucVuInChu(Socket s1) {
		// TODO Auto-generated constructor stub
		s = s1; 
	}
	public void run() {
		try {

			// Lay stream in - out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				// Doc so tu client gui den
				int ch = is.read();
				System.out.println("Nhan duoc: "+(char)ch);
				// Xu ly du lieu
				if(ch == '@') {System.out.println("Client da ket thuc");break;}
				String kq = "Khong phai so nguyen";
				switch (ch) {
					case '0': {kq = "Khong";break;}
					case '1': {kq = "Mot";break;}
					case '2': {kq = "Hai";break;}
					case '3': {kq = "Ba";break;}
					case '4': {kq = "Bon";break;}
					case '5': {kq = "Nam";break;}
					case '6': {kq = "Sau";break;}
					case '7': {kq = "Bay";break;}
					case '8': {kq = "Tam";break;}
					case '9': {kq = "Chin";break;}
				}
				// Tra ve kq cho client
				byte b[] = kq.getBytes();
				os.write(b);
			}
			s.close();
			
			
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		}
		
		
	}
}
public class InChuServer_SS {

	public static void main(String[] args) {
		try {
			// Tao ra server cong 7248
			ServerSocket ss = new ServerSocket(7248);
			System.out.println("Tao server thanh cong!");
			while(true) {
				// Chap nhan ket noi tu client
				try {
					Socket s = ss.accept();
					System.out.println("Da co client ket noi voi dia chi IP: "+s.getInetAddress()+":"+s.getPort());
					PhucVuInChu pv1 = new PhucVuInChu(s);
					pv1.start();
					
				}catch (IOException e) {
					System.out.println("Client da thoat");
				}
				
			}
		}
		catch (IOException e) {
			System.out.println("co loi xay ra voi server");
		}
	}

}
