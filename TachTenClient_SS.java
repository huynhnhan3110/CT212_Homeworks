import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TachTenClient_SS {

	public static void main(String[] args) {
		try {
			// Tao ket noi den server
			Socket s = new Socket("localhost", 9999);
			System.out.println("Da ket noi den server");
			// Lay stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				// Nhap vao mot chuoi ho va ten
				Scanner sc = new Scanner(System.in);
				System.out.print("Nhap vao ho ten: ");
				String hovaten = sc.nextLine();
				byte b[] = hovaten.getBytes();
				
				// Gui chuoi den server
				os.write(b);
				
				// Xu ly ket thuc
				if(hovaten.equals("EXIT")) break;
				
				// Nhan chuoi tra ve
				byte ketqua[] = new byte[1000];
				int length = is.read(ketqua);
				String ten = new String(ketqua, 0, length);
	
				// In ra man hinh
				System.out.println("Server tra ve: " + ten);
			}
			// Dong ket noi
			s.close();
		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay server");
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
