import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PTCPEchoServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7);
			System.out.println("Tao server thanh cong");
			while(true) {
				try {
					Socket s = ss.accept();
					System.out.print("Bản đồ mạng");
					// Tao phan xu ly rieng
					RequestProccess rp = new RequestProccess(s);
					rp.start();	
				}catch (IOException e) {
					System.out.println("Loi in out"+e);
				}
			}
			
		}catch(IOException e) {
			System.out.println("Co loi ket noi in out"+e);
		}

	}

}
