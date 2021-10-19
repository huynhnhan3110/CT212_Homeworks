import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class InChuClient_SS {

	public static void main(String[] args) {
		try {
			// Tao ket noi den server cong 7248
			Socket s = new Socket("127.0.0.1", 7248);
			System.out.println("Ket noi den server thanh cong 1");
			// Lay stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				// Nhap vao so tu ban phim
				System.out.print("Nhap vao 1 chu so: ");
				int ch = System.in.read();
				System.in.skip(2);
				// Gui so den server
				os.write(ch);
				if(ch == -1) break;
				// Nhan kq tu server
				byte b[] = new byte[100];
				int length = is.read(b);
				String kq = new String(b, 0, length);
				System.out.println("Server tra ve: "+kq);
			}
			// Dong ket noi
			s.close();
		}catch (UnknownHostException e) {
			System.out.println("Co loi khi nhap xuat");
		}catch (IOException e) {
			System.out.println("Khong tim thay IP server");
		}
	}

}
