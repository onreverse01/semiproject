package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class TempFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
		File newFile = null;

		// 확장자 구하기
		String oldName = f.getName();
		String ext = "";
		int dot = oldName.lastIndexOf(".");
		if (dot > -1)
			ext = oldName.substring(dot);

		String newName = "temp" + ext;
		
		newFile = new File(f.getParent(), newName);
		
		try(BufferedReader br = new BufferedReader(new FileReader(f));
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
		){
			String data = null;
			
			while((data = br.readLine()) != null) {
				System.out.println(data+":");
				bw.write(data+"\n"); //readLine은 개행문자는 가져오지 않아서 추가해줌
			}
			
		}catch (Exception e) {
		}

		return newFile;
	}

}
