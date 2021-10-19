package abc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
		// Tao socket ket noi voi server
		Socket s = new Socket("127.0.0.1", 7);
		System.out.println("Ket noi server thanh cong");
		// Lay stream in - out
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		while(true) {
			// Nhap ky tu tu ban phim
			System.out.println("Nhap vao 1 ky tu: ");
			int ch = System.in.read();
			System.in.skip(2);
			
			// Gui ky tu den server
			os.write(ch);
			if((char)ch == '@') {
				break;
			}
			// In du lieu tu server tra ve
			int kq = is.read();
			System.out.println("Server tra ve la: "+(char)kq);
		}
		// Dong ket noi den server
		s.close();
		
		}catch (UnknownHostException e) {
			System.out.println("Khong tim thay IP server");
		}catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
