import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DocFileServer {

	public static void main(String[] args) {
		try {
			// Tao socket server cong 1212
			ServerSocket ss = new ServerSocket(1212);

			// Chap nhan client ket noi
			Socket s = ss.accept();

			// Lay stream in out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			Scanner sc = new Scanner(is);
			PrintWriter pw = new PrintWriter(os);

			// Doc du lieu tu client
			String caulenh = sc.nextLine();
			String tentaptin = caulenh.substring(5);

			// Xu ly du lieu

			// Gui du lieu cho client
			File file = new File(tentaptin);
			if (file.exists()) {
				if (file.length() == 0) {
					pw.println(0);
					pw.flush();
				} else {
					pw.println(file.length());
					pw.flush();

					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = null;
					while ((line = br.readLine()) != null) {
						String singleLine = line;
						pw.println(singleLine);
						pw.flush();
					}

				}

			} else {
				pw.println(-1);
				pw.flush();
			}

			// Dong ket noi
			s.close();
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat: " + e);
		}
	}

}
