package io3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class FileCopy3 {

	public static void main(String[] args) {

		// close() 규칙
		// 반드시 finally에서 close()
		// 따로따로 close

		InputStream fin = null;
		OutputStream fos = null;

		try {
			fin = new FileInputStream("C:\\zzz\\smaple.jpg");
			fos = new FileOutputStream("C:\\zzz\\smapleCopy.jpg");

			byte[] buffer = new byte[1024 * 8];

			while (true) {
				int count = fin.read(buffer);
				if (count == -1) {
					break;
				}
				fos.write(buffer, 0, count);
			}
	

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (Exception e) {
				}
			if (fos != null)
				try {
					fos.close();
				} catch (Exception e) {
				}
		}
	}

}
