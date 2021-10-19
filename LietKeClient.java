import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LietKeClient {

	public static void main(String[] args) {
		try {
			// Tao ket noi den server 2828
			Socket s = new Socket("localhost", 2828);
			System.out.println("Da ket noi den server");
			// Lay stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			// Scanner thay cho InputStream
			Scanner sc = new Scanner(is);
			// PrintWriter thay cho OutputStream
			PrintWriter printWriter = new PrintWriter(os);
				while(true) {
				// Nhap ban phim
				Scanner kb = new Scanner(System.in);
				System.out.print("Nhap vao thu muc(! de ket thuc): ");
				String tenthumuc = kb.nextLine();
				String caulenh = "LIST "+ tenthumuc;
				
				// Gui Qua Server
				printWriter.println(caulenh);
				printWriter.flush(); // day dong nuoc qua ben kia
				
				// Xu ly ket thuc
				if(tenthumuc.equals("!")) break;
				
				// Nhan du lieu tra ve
				String soLuongStr = sc.nextLine();
				int n = Integer.parseInt(soLuongStr);
				
				// In ra man hinh
				if(n==-1) {
					System.out.println("Khong ton tai thu muc "+tenthumuc);
				} else if(n==0) {
					System.out.println("Khong co gi trong thu muc "+tenthumuc);
				} else {
					System.out.println(tenthumuc + " co "+n+" thanh phan");
					for(int i = 0; i<n; i++) {
						String kq = sc.nextLine();
						System.out.println(kq);
					}
				}
				}
			// Dong ket noi
			s.close();

		} catch (UnknownHostException e) {
			System.out.println("Khong tim thay host");
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
