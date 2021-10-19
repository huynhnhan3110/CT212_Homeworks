import java.io.*;
import java.net.*;


public class b3_b3_Server {

	public static void main(String[] args) {
		
		try {
			InetAddress diachi[] = new InetAddress[2] ;
			diachi[0]=InetAddress.getByName("127.0.0.1");
			diachi[1]=InetAddress.getByName("127.0.0.1");
			int i = 0;
			int cong[] = {0,0};
			//tao UDP Server
			DatagramSocket ds = new DatagramSocket(13);
			//nhan tra loi
			while (true) {
				byte b[]= new byte[600000];
				DatagramPacket goinhan = new DatagramPacket(b,600000);
				ds.receive(goinhan);
				for(int j=0;j<cong.length;j++) {
					if (cong[j]==0) {
						diachi[j] = goinhan.getAddress();
						cong[j] = goinhan.getPort();
						break;
					}
				}
				byte noidung[] = goinhan.getData();
				int len = goinhan.getLength();
				String noidung1 = new String(noidung,0,len);
				System.out.println(goinhan.getAddress()+"-"+goinhan.getPort()+"|noi dung: "+noidung1);
				System.out.println(diachi[1]+"-"+cong[1]+"|noi dung: "+noidung1);
				if(cong[0] == goinhan.getPort() && cong[1]==0) {
					System.out.println("cho ban moi toi");
				}
				else {
					if (cong[0] == goinhan.getPort()) {
						
						DatagramPacket goigui = new DatagramPacket(noidung,len,diachi[1],cong[1]);
						ds.send(goigui);
					}
					else {
						DatagramPacket goigui = new DatagramPacket(noidung,len,diachi[0],cong[0]);
						ds.send(goigui);
					}
				}
				//kiem tra dung
				if (noidung1.equals("exit")) break;
				
			}
			ds.close();
			
			
			
		} catch (SocketException e) {
			System.out.println("khong tao duoc UDP Server");
		} catch (IOException e) {
			System.out.println("loi nhan gui");
		}
		
	}
}
