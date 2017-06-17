package java2017;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.util.StringTokenizer;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class DateSelectListener implements ItemListener{
	
	
	private RecordTable table;
	private DefaultPieDataset dataset;
	int year, month;
	
	public DateSelectListener()
	{
		
	}
	public DateSelectListener(DefaultPieDataset d)
	{
		super();
		dataset = d;
		
	}
	
	public void itemStateChanged(ItemEvent event)
	{
		if(event.getStateChange() == ItemEvent.SELECTED)
		{
			String item = event.getItem().toString();
			StringTokenizer token = new StringTokenizer(item, "/ ");
			
			int year, month;
			year = Integer.parseInt(token.nextToken());
			month = Integer.parseInt(token.nextToken());
			
			
			
			if(item.equals("2017/07"))
			{
				
				int categories[] = table.sumByCategories(); // 수정 필요함. 날짜 별 카테고리 구분 필ㄴ요.
				
				dataset.setValue("Phone", new Double(43.2));
		        dataset.setValue("Food", new Double(10.0));
		        dataset.setValue("Management", new Double(27.5));
		        dataset.setValue("Move", new Double(17.5));
		        dataset.setValue("Life", new Double(11.0));
		        dataset.setValue("etc.", new Double(19.4));
		        
			}
			else if(item.equals("2017/06"))
			{
				dataset.setValue("Management", new Double(43.2));
		        dataset.setValue("Food", new Double(10.0));
		        dataset.setValue("Phone", new Double(27.5));
		        dataset.setValue("Move", new Double(17.5));
		        dataset.setValue("Life", new Double(11.0));
		        dataset.setValue("etc.", new Double(19.4));
			}
			System.out.println(event.getItem());
		}
		
	}

}
