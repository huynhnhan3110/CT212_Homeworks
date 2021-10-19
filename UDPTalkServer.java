import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UDPTalkServer {

	public static void main(String[] args) {
		try {
			// Tao ra server socket udp
			DatagramSocket ds = new DatagramSocket(69);
			System.out.println("Server khoi dong...\n");
			// Tao ra mang chua cac dia chi cua client
			List<String> infoClient = new ArrayList<String>();
			while (true) {
				// Tao ra goinhan
				byte b[] = new byte[60000];
				DatagramPacket goinhan = new DatagramPacket(b, 60000);
				
				// Nhan goi tin
				ds.receive(goinhan);

				// Phan tich goinhan
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();
				String content = new String(b1, 0, len1); // Noi dung loi nhan
				InetAddress ip = goinhan.getAddress(); // Dia chi ip dang gui den
				int port = goinhan.getPort(); // Cong dang gui den
				String id = ip + ":" + port;
				System.out.println(id+" | Noi dung gui: "+content);
				if (!infoClient.contains(id)) { // Neu id khong co trong list
					infoClient.add(id); // Dua vao trong list
				}
				
				// Tao goigui
				for (int i = 0; i < infoClient.size(); i++) {
					if (!infoClient.get(i).equals(id)) { // Gui phanhoi cho tat ca cac client ngoai tru` client dang gui
															// den
						String txtId = infoClient.get(i);
						int seperateIndex = txtId.indexOf(":");
						InetAddress ipToSend = InetAddress.getByName(txtId.substring(1, seperateIndex));
						int portToSend = Integer.parseInt(txtId.substring(seperateIndex + 1));
						DatagramPacket goigui = new DatagramPacket(content.getBytes(), len1, ipToSend, portToSend);
						ds.send(goigui);
					}
				}

			}
		} catch (SocketException e) {
			System.out.println("Loi tao socket udp");
		} catch (IOException e) {
			System.out.println("Loi nhap xuat> " + e);
		}

	}

}
