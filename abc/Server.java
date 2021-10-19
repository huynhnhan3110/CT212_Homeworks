package abc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		try {
			// Tao server tai cong so 7
				ServerSocket ss = new ServerSocket(7);
				System.out.println("Tao server thanh cong");
				while(true) {
					try {
						// Chap nhan ket noi tu client
						System.out.println("Dang cho Client ket noi...");
						Socket s = ss.accept();
						System.out.println("Da co client ket noi: "+s.getInetAddress().toString()+":"+s.getPort());
						// Lay stream in - out
						InputStream is = s.getInputStream();
						OutputStream os = s.getOutputStream();
						while(true) {
							// Nhan du lieu tu client
							int ch = is.read();
							System.out.println("Da nhan: "+(char)ch);
							// Xu ly du lieu
							int ch1 = ch;
							if((char)ch == '@') {
								break;
							}
							// Gui du lieu cho client
							os.write(ch1);
						}
						// Dong ket noi
						s.close();
					}catch (IOException e) {
						System.out.println("Client Bi mat ket noi dot ngot.");
					}
				}
				

		} catch (IOException e) {
			System.out.println("Co loi khi tao server");
		}

	}

}
