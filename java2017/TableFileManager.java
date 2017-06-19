package java2017;

import java.io.*;
import java.util.ArrayList;

public class TableFileManager {
	static String filename = "";
	
	public static void write(RecordTable RT)
	{
		if(filename.isEmpty())
		{
			filename = FileChooser.fileChooseOUT();
		}
		
		ObjectOutputStream OUT = null;
		try {
			OUT = new ObjectOutputStream(new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OUT.writeObject(RT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static RecordTable open()
	{
		filename = FileChooser.fileChooseIN();
		
		RecordTable RT = null;
		ObjectInputStream IN = null;
		
		try {
			IN = new ObjectInputStream(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			RT = (RecordTable) IN.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return RT;
	}
}
