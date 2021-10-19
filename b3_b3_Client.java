import java.io.*;
import java.net.*;
import java.util.Scanner;

class phucvugui extends Thread{
	DatagramSocket ds;
	public phucvugui(DatagramSocket ds1) {
		this.ds = ds1;
	}
	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				String data = sc.nextLine();
				// dong goi
				byte b[]= data.getBytes();
				int len = data.length();
				InetAddress diachi = InetAddress.getByName("127.0.0.1");
				int cong = 13;
				//gui server
				DatagramPacket goigui = new DatagramPacket(b,len,diachi,cong);
				ds.send(goigui);
				////kiem tra dung
				if (data.equals("exit")) break;
			}
			ds.close();
		} catch (UnknownHostException e) {
			System.out.println("dia chi sai");
		} catch (IOException e) {
			System.out.println("loi khi gui");
		}
		
	}
}
class phucvunhan extends Thread{
	DatagramSocket ds;
	public phucvunhan(DatagramSocket ds1) {
		this.ds = ds1;
	}
	public void run() {
		try {
			
			//nhan tra loi
			byte b[]= new byte[600000];
			DatagramPacket goinhan = new DatagramPacket(b,600000);
			while(true) {
				ds.receive(goinhan);
				//hien ra mang hinh
				byte b2[] = goinhan.getData();
				int len = goinhan.getLength();
				String NDtraloi = new String(b2,0,len);
				////kiem tra dung
				if (NDtraloi.equals("exit")) break;
				System.out.println("Tra loi: "+NDtraloi);
			}
			ds.close();
		} catch (IOException e) {
			System.out.println("loi nhan tra loi");
		}
		
	}
	
}

public class b3_b3_Client {
	public static void main(String[] args) {
		try {
			//tao UDP
			DatagramSocket ds = new DatagramSocket();
			System.out.println("chao mung den voi talk");
			System.out.println("chi can nhap noi dung xong nhan ENTER de gui");
			phucvunhan pvnhan = new phucvunhan(ds);
			pvnhan.start();
			phucvugui pvgui = new phucvugui(ds);
			pvgui.start();
			
		} catch (SocketException e) {
			System.out.println("khong tao UDP duoc");
		}
		

	}

}
