package organizeOldPic;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class organize_meta {
	public static void main(String[] ar) throws ImageProcessingException, IOException{
//		File jpegFile = new File("/media/rossendra/01D23527E552C810/tesMove/in/IMG001.jpg");
//		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
//
//		for (Directory directory : metadata.getDirectories()) {
//			for (Tag tag : directory.getTags()) {
//				System.out.format("[%s] - %s = %s",
//						directory.getName(), tag.getTagName(), tag.getDescription());
//			}
//			Collection<Tag> tag=directory.getTags();
//			if(tag.equals("Date/Time Digitized")){
//				System.out.format("[%s] - %s = %s",
//						directory.getName(), tag.getTagName(), tag.getDescription());
//			}
//			if (directory.hasErrors()) {
//				for (String error : directory.getErrors()) {
//					System.err.format("ERROR: %s", error);
//				}
//			}
//		}

		
		 
			 File jpegFile = new File("/media/rossendra/01D23527E552C810/tesMove/in/IMG001.jpg");
			    Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
			    Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
			    int dateTag = ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL;

			    if (directory != null && directory.containsTag(dateTag)) {
			      //Date date = directory.getDate(dateTag, TimeZone.getDefault());
			      
			      Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
			      
			      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			      String strDate=date.toString();
			      
			      System.out.println(strDate);
			    } 
			  
			//}
		
		
		//		File jpegFile = new File("/media/rossendra/01D23527E552C810/tesMove/in/IMG001.jpg");
		//		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
		//		
		//		for (Directory directory : metadata.getDirectories()) {
		//		    for (Tag tag : directory.getTags()) {
		//		        System.out.println(tag);
		//		    }
		//		}
		//
		//	}

	}
}