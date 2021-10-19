import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LietKeServer {

	public static void main(String[] args) {
		try {
			// Tao server socket 2828
			ServerSocket ss = new ServerSocket(2828);
			System.out.println("Tao server thanh cong");
			while (true) {
				try {
					// Chap nhan cho client ket noi
					Socket s = ss.accept();
					System.out.println("Da co client ket noi voi dia chi IP: "+s.getInetAddress()+":"+s.getPort());
					// Lay stream in out
					InputStream is = s.getInputStream();
					OutputStream os = s.getOutputStream();

					// Chuyen ve Scanner va PrintWrite
					Scanner sc = new Scanner(is);
					PrintWriter pw = new PrintWriter(os);
					while (true) {
						// Lay ra ten thu muc
						String caulenh = sc.nextLine();
						String tenthumuc = caulenh.substring(5);
						System.out.println("Yeu cau: " + caulenh);

						// Xu ly ket thuc phien
						if (tenthumuc.equals("!"))
							break;

						// Xu ly yeu cau
						File file = new File(tenthumuc);
						if (file.isDirectory() && file.exists()) { // kiem tra vua la thu muc vua ton tai
							String kq[] = file.list();
							int n = kq.length;
							if (n == 0) {
								pw.println(0);
								pw.flush();

							} else {
								pw.println(n);
								pw.flush();
								for (int i = 0; i < n; i++) {
									File checkFile = new File(tenthumuc + "/" + kq[i]);
									if (checkFile.isDirectory()) {
										pw.println("[" + kq[i] + "]");
									} else {
										pw.println(kq[i]);

									}
									pw.flush();
								}
							}

						} else {
							pw.println(-1);
							pw.flush();
						}

					}
					// Dong ket noi
					s.close();
				} catch (IOException e) {
					System.out.println("Client da ngat ket noi dot ngot");
				}
			}
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
