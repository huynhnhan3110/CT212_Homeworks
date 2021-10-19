import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NhiPhanClient {
	public static void main(String[] args) {
		try {
		// ket noi den server
			Socket s = new Socket("127.0.0.1", 1221);
			System.out.println("Ket noi den server thanh cong");
		// lay ra stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
		while(true) {	
			// nhap tu ban phim
				System.out.print("Nhap vao so can doi: ");
				byte b[] = new byte[100]; // chỗ để lưu số cần đổi
				int length = System.in.read(b);
			// gui du lieu den stream os
				os.write(b, 0, length); // khong gui ca mang ma gui doan co gia tri
				if(b[0] == '@') break;
			// doc du lieu tu stream in
				byte bBinary[] = new byte[100];
				int lengthBinary = is.read(bBinary);
			// hien thi ra man hinh ket qua cua stream in
				String binaryStr = new String(bBinary, 0, lengthBinary);
				System.out.println("Ket qua la: "+binaryStr);
		}
		// dong ket noi den server
			s.close();
		}catch (UnknownHostException e) {
			System.out.println("Khong tim thay dia chi server");
		}catch (IOException e) {
			System.out.println("Co loi khi doc ghi");
		}
	}
}
