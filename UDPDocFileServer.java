import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPDocFileServer {

	public static void main(String[] args) {
		try {
			// Tao udp socket cong 69
			DatagramSocket ds = new DatagramSocket(69);
			// Tao goinhan
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b, 60000);
			while (true) {
				// Nhan goi tin
				ds.receive(goinhan);

				// Lay cac thong tin
				InetAddress ip = goinhan.getAddress();
				int port = goinhan.getPort();
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();

				// Xu ly
				String caulenh = new String(b1, 0, len1);
				String tenfile = caulenh.substring(8);
				System.out.println("Nhan duoc: "+tenfile);
				File f1 = new File(tenfile);
				if (f1.exists() && f1.isFile()) {
					int fileLen = (int) f1.length();
					byte fileArr[] = new byte[fileLen];
					FileInputStream filein = new FileInputStream(f1);
					filein.read(fileArr);
					filein.close();
					DatagramPacket goigui = new DatagramPacket(fileArr, fileLen, ip, port);
					ds.send(goigui);
				} else {
					byte b2[] = new byte[5];
					int len2 = 0;
					// Tao goi gui
					DatagramPacket goigui = new DatagramPacket(b2, len2, ip, port);
					ds.send(goigui);
				}
			}

		} catch (SocketException e) {
			System.out.println("Co loi voi socket: "+e);
		} catch (FileNotFoundException e) {
			System.out.println("Tap tin khong ton tai");
		} catch (IOException e) {
			System.out.println("Co loi nhap xuat");
		}

	}

}
