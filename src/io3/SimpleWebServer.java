package io3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SimpleWebServer {

	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(8080)) {

			System.out.println("ready.........................");
			while (true) {
				try (Socket socket = server.accept();
					InputStream sin = socket.getInputStream();
					Scanner socketScanner = new Scanner(sin,"UTF-8");
					OutputStream sout = socket.getOutputStream();
					) {
					System.out.println("a client connected........");
					String cmd = socketScanner.nextLine();
					System.out.println(cmd);
					
					String[] arr = cmd.split(" ");
					
					File file = new File("C:\\zzz"+arr[1]);
					
					long len = file.length();
					
					if(len==0) {
						continue;
					}
					Path path = file.toPath();
					String mimeType = Files.probeContentType(path);
					
					sout.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
					sout.write(new String("Cache-Control: private\r\n").getBytes());
					sout.write(new String("Content-Length: " + len + "\r\n").getBytes());
					sout.write(new String("Content-Type: "+mimeType+"\r\n\r\n").getBytes());
								
					FileInputStream fin = new FileInputStream(file);
					byte[] buffer = new byte[1024*8];
					
					while(true) {
						int count = fin.read(buffer);
						if(count == -1) { break;}
						sout.write(buffer,0,count);
					}
					
					sout.flush();
					
				} catch (Exception e) {
					System.out.println("문제발생");
				}

			} // end while

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
