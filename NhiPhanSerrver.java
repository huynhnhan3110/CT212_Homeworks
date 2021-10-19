import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NhiPhanSerrver {

	public static void main(String[] args) {
		
		try {
		// Tao server socket tai cong 1221
		ServerSocket ss = new ServerSocket(1221);
		System.out.println("Tao server thanh cong");
		// Chap nhan client ket noi
		while(true) {
			try {
				Socket s = ss.accept();
				System.out.println("Da co client ket noi voi dia chi IP: "+s.getInetAddress()+":"+s.getPort());
				// Lay stream in out trong socket
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				while(true) {
					// Doc du lieu cua client
					byte b[] = new byte[100]; // mảng lưu chuỗi byte cần đổi sang nhị phân
					int length = is.read(b); // số ký tự client gửi + 1 
					
					// Xu ly du lieu
					if(b[0] == '@') {
						System.out.println("Client da thoat");
						break;
					}
					String clientTxt = new String(b, 0, length-2);
					System.out.println("Nhan duoc: "+clientTxt);
					String binaryNum = "Khong phai la so nguyen";

					if(clientTxt.matches("[0-9]+")) {
						//  String ve int
						int a = Integer.parseInt(clientTxt);
						//int ve string binary
						binaryNum = Integer.toBinaryString(a);
					}
					// Gui du lieu cho client
					os.write(binaryNum.getBytes());
				}
				// Dong noi ket den client
				s.close();
			}catch(IOException e) {
				System.out.println("Client da thoat");
			}
		}
		}catch(IOException e) {
			System.out.println("Co loi khi tao server");
		}
	}

}
