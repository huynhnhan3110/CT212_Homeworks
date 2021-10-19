import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
class RequestProccess extends Thread{
	private Socket s = new Socket();
	public RequestProccess(Socket s1) {
		this.s = s1;
	}
	public void start() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			// nhan du lieu tu client
			int ch = 0;
			while(true) {
				ch = is.read();
				if(ch == -1) break;
				// tra du lieu cho client
				os.write(ch);
			}
			s.close();
			
		}catch(IOException e) {
			System.out.println("Loi in out");
		}
	}

}
