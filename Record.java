import java.util.*;

public class Record implements Categories {
	private Calendar date;
	private int amount;
	private String note;
	private int category;
	
	public void setDate(Calendar date) { this.date = date; }
	public void setAmount(int amount) { this.amount = amount; }
	public void setNote(String note) { this.note = note; }
	public void setCategory(int category) { this.category = category; }

	public Calendar getDate() { return date; }
	public int getAmount() { return amount; }
	public int getCategory() { return category; }
	public String getNote() { return note; }
	
	public static int categoryId(String s)
	{
		if(s == Categories.sMANAGEMENT) return Categories.MANGEMENT;
		if(s == Categories.sFOOD) return Categories.FOOD;
		if(s == Categories.sLIFE) return Categories.LIFE;
		if(s == Categories.sETC) return Categories.ETC;
		if(s == Categories.sPHONE) return Categories.PHONE;
		if(s == Categories.sMOVE) return Categories.MOVE;

		return -1;
	}
	
	public static String categoryStr(int id)
	{
		if(Categories.MANGEMENT == id) return Categories.sMANAGEMENT;
		if(Categories.FOOD == id) return Categories.sFOOD;
		if(Categories.LIFE == id) return Categories.sLIFE;
		if(Categories.ETC == id) return Categories.sETC;
		if(Categories.PHONE == id) return Categories.sPHONE;
		if(Categories.MOVE == id) return Categories.sMOVE;
		
		return "";
	}
	
	public boolean equals(Record other)
	{
		boolean flag = true;
		
		if(!date.equals(other.getDate())) flag = false;
		if(amount != other.getAmount()) flag = false;
		if(note.equals(other.getNote())) flag = false;
		if(category != (other.getCategory())) flag = false;
		
		return flag;
	}
	
	public String toString()
	{
		String ret = "";
		ret += date.getTime().toString();
		ret += " ";
		ret += Integer.toString(amount) + "won";
		
		return ret;
	}
}
