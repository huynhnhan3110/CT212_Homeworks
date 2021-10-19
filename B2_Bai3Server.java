import java.util.Scanner;
import java.net.*;
import java.io.*;

public class B2_Bai3Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(5500);
			while(true) {
				Socket s = ss.accept();
				System.out.println("Server da duoc khoi tao");
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " noi ket");
				// Lay ra 2 stream
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				// Doi thanh cac doi tuong thuoc lop thua ke
				Scanner sc = new Scanner(is);
				PrintWriter pw = new PrintWriter(os);
				// Nhan cau lenh tu Client
				String caulenh = sc.nextLine();
				String tenfile = caulenh.substring(5);	// Tach ra ten file
				// Xu ly yeu cau (Doc file)
				File f = new File(tenfile);
				int n = (int)f.length();
				if(f.isFile() && f.exists()) {
					pw.println("" + n); pw.flush();
					if(n!=0) {
						byte b[] = new byte[n];
						FileInputStream f1 = new FileInputStream(tenfile);
						DataInputStream dis = new DataInputStream(f1);
						DataOutputStream dos = new DataOutputStream(os);
						dis.readFully(b);
						System.out.println("Da doc file thanh cong");
						f1.close();		// Dong file
						dos.write(b);
						System.out.println("Da gui file thanh cong");
					}
				} 
				else {
					pw.println("-1"); pw.flush();
				}
				s.close();
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " da thoat");
			}
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi Server");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
