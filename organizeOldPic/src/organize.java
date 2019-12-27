import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class organize {
	public static FileWriter writeLog;

	public void listPic(String pathInput,String pathOutput) {
		File dir = new File(pathInput);
		File[] files =dir.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				listPic(files[i].getAbsolutePath(),pathOutput); 
			}
			else{
				processFile(files[i],pathOutput);						}
		}
	}
	//}
	public void processFile(File target,String pathOut){
		if(target.getName().contains("C360")) {
			//char[] dates=files[i].getName().toCharArray();
			StringBuffer dates=new StringBuffer(target.getName());
			String year=dates.substring(5, 9);
			String month=dates.substring(10, 12);
			String day=dates.substring(13, 15);
			moveItem(pathOut,target.getAbsolutePath(),year,month,day);

		}
		else if(target.getName().contains("IMG")) {
			if(target.getName().toCharArray().length>15){
				if(!target.getName().contains("FB")){
					StringBuffer dates=new StringBuffer(target.getName());
					String year=dates.substring(4, 8);
					String month=dates.substring(8, 10);
					String day=dates.substring(10, 12);
					moveItem(pathOut,target.getAbsolutePath(),year,month,day);
				}}
		}

	}
	public void moveItem(String pathOut,String file,String y,String m,String d) {

		File target=new File(pathOut);
		//String newTarget=target.getAbsolutePath()+File.separator+y+File.separator+m+File.separator+d;
		String[] splitted=file.split(File.separator);
		String foldName=splitted[splitted.length-2];
		File out=new File(target.getAbsolutePath()+File.separator+y+File.separator+m+File.separator+d);
		if (!out.exists()) {
			out.mkdirs();
		}
		File moving=new File(file);
		try {

			Path temp=Files.move(Paths.get(moving.getAbsolutePath()), Paths.get(out+File.separator+moving.getName().split("\\.")[0]+"_"+foldName+".jpg"));
			writeLog.write(moving.getAbsolutePath()+" --> "+out+File.separator+moving.getName().split("\\.")[0]+"_"+foldName+".jpg");
			System.out.println(moving.getAbsolutePath()+" --> "+out+File.separator+moving.getName().split("\\.")[0]+"_"+foldName+".jpg");
			if(temp != null) 
			{ 
				System.out.println("File renamed and moved successfully"); 
			} 
			else
			{ 
				//Path temp=Files.move(Paths.get(moving.getAbsolutePath()), Paths.get(out+File.separator+moving.getName()));
				System.out.println("Failed to move the file"); 
			} 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		organize go=new organize();
		try {
			writeLog=new FileWriter(new File("/media/rossendra/01D23527E552C810/SDCARD_16GB/DCIM_organized/logMove.txt"));

			go.listPic("/media/rossendra/01D23527E552C810/SDCARD_16GB/DCIM","/media/rossendra/01D23527E552C810/SDCARD_16GB/DCIM_organized");
			writeLog.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


