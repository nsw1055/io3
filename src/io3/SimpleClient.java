package io3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) {
		
		Map<String, String> ipMap = new HashMap<>();
		ipMap.put("chimco", "192.168.0.24");
		
		Scanner keyScanner = new Scanner(System.in);
		System.out.println("어디로 메시지를 전송 하실래요?");
		
		String ip = ipMap.get(keyScanner.nextLine());
		
		System.out.println("보내고 싶은 메시지를 입력");
		String cmd = keyScanner.nextLine();
		
		
		try(Socket socket = new Socket(ip,8080);
			InputStream in = socket.getInputStream();
			Scanner inScanner = new Scanner(in);
			OutputStream out = socket.getOutputStream();
			
			){
			out.write((cmd+"\n").getBytes("UTF-8"));
			out.flush();
			
			String serverMsg = inScanner.nextLine();
			System.out.println("--------------------");
			System.out.println(serverMsg);
			
		}catch(Exception e) {
			e.printStackTrace();
		}//end catch
		
		
	}

}
