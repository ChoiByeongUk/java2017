import java.util.*;

class RecordTable extends ArrayList<Record> implements Categories
{
	public RecordTable()
	{
		
	}
	
	public void addRecord(int year, int month, int day, int amount, int category,
			String secondCategory, String note) {
		Record r = new Record();

		Calendar d = new GregorianCalendar(year, month, day, 0, 0, 0);
		
		r.setDate(d);
		r.setNote(note);
		r.setAmount(amount);
		r.setCategory(category);
		r.setSecondCategory(secondCategory);

		this.add(r);
	}

	public RecordTable listByDate(int beginYear, int beginMonth, int beginDay, int endYear, int endMonth, int endDay)
	{
		RecordTable ret = new RecordTable();
		
		Calendar begin = new GregorianCalendar(beginYear, beginMonth, beginDay, 0, 0, 0);
		begin.add(Calendar.SECOND, -1);
		Calendar end = new GregorianCalendar(endYear, endMonth, endDay, 0, 0, 0);
		end.add(Calendar.DATE, 1);
		
		for(Record it : this)
		{
			if(it.getDate().after(begin) && it.getDate().before(end))
			{
				ret.add(it);
			}
		}
		
		return ret;
	}
	
	public RecordTable listByCategory(int Category)
	{
		RecordTable ret = new RecordTable();
		
		for(Record it : this)
		{
			if(it.getCategory() == Category)
			{
				ret.add(it);
			}
		}
		
		return ret;
	}
	
	public int[] sumByCategories()
	{
		int sum[] = new int[Categories.numOfCategories];
		for(Record it : this)
		{
			sum[it.getCategory()] += it.getAmount();
		}
		
		return sum;
	}
	
	
	public static void main(String args[])
	{
		RecordTable RT = new RecordTable();
		RT.addRecord(2017, 3, 25, 1000, Categories.FOOD, "APPLE", "");
		RT.addRecord(2017, 3, 26, 2000, Categories.MOVE, "BUS", "");
		RT.addRecord(2017, 2, 13, 1345, Categories.LIFE, "WATER", "");
		RT.addRecord(2017, 6, 11, 1632, Categories.FOOD, "SUSHI", "");
	
		RecordTable list = RT.listByDate(2016, 3, 1, 2017, 6, 11);
		for(Record it : list)
		{
			System.out.println(it.getAmount());
		}
		
		int[] sum = RT.sumByCategories();
		for(int it : sum)
		{
			System.out.println(it);
		}
		
	}
}
