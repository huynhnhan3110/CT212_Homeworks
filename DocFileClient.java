
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DocFileClient {

	public static void main(String[] args) {
		try {
			// Tao ket noi cong 1212
			Socket s = new Socket("localhost", 1212);
			System.out.println("Ket noi den server thanh cong");
			// Lay stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			Scanner sc = new Scanner(is);
			PrintWriter pw = new PrintWriter(os);
			// Nhap vao ten file
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap vao ten file");
			String tenfile = kb.nextLine();

			String caulenh = "READ " + tenfile;
			// Gui den server
			pw.println(caulenh);
			pw.flush();

			// Nhan du lieu tra ve
			String trangthaiNoiDungStr = sc.nextLine();
			int trangthaiNoiDung = Integer.parseInt(trangthaiNoiDungStr);

			// In ra man hinh va ghi vao file
			if (trangthaiNoiDung == -1) {
				System.out.println("Khong ton tai tap tin");
			} else if (trangthaiNoiDung == 0) {
				System.out.println("Tap tin khong co noi dung gi");
			} else {
				System.out.println("Tap tin co " + trangthaiNoiDung + "bytes da duoc ghi vao file trong may cua ban");
				FileWriter writer = new FileWriter(tenfile.substring(tenfile.lastIndexOf("/") + 1), true); // true de
																											// cho khong
																											// xoa file
																											// cu
				BufferedWriter bw = new BufferedWriter(writer);
				while (sc.hasNextLine()) {
//					System.out.println(sc.nextLine()); // in ra man hinh client
					bw.write(sc.nextLine());
					bw.write('\n'); // Xuong hang
				}
				bw.close();
//				writer.close();
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
