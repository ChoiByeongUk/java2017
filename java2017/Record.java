import java.text.SimpleDateFormat;
import java.util.*;

public class Record implements Categories {
	private Calendar date;
	private int amount;
	private String note;
	private int category;
	private String secondCategory;
	
	public void setDate(Calendar date) { this.date = date; }
	public void setAmount(int amount) { this.amount = amount; }
	public void setNote(String note) { this.note = note; }
	public void setCategory(int category) { this.category = category; }
	public void setSecondCategory(String category) { this.secondCategory = category; }

	public Calendar getDate() { return date; }
	public int getAmount() { return amount; }
	public int getCategory() { return category; }
	public String getSecondCategory() { return secondCategory; }
	public String getNote() { return note; }
	
	public String toString()
	{
		String ret = "";
		SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
		
		ret += ft.format(date);
		ret += " ";
		ret += Integer.toString(amount) + "won";
		
		return ret;
	}
}
