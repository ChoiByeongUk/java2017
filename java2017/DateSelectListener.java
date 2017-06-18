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
	public DateSelectListener(DefaultPieDataset d, RecordTable data)
	{
		super();
		dataset = d;
		table = data;
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
			
			RecordTable ret = table.listByMonth(year, month ,year, month+1);/**/
			int categories[] = ret.sumByCategories(); // 수정 필요함. 날짜 별 카테고리 구분 필ㄴ요.
			double sum = 0;
			for(int i =0; i<categories.length; i++)
				sum += categories[i];
				
			dataset.setValue("Management", new Double((categories[0] / sum )* 100));
		    dataset.setValue("Food", new Double((categories[1] / sum )* 100));
		    dataset.setValue("Phone", new Double((categories[2] / sum )* 100));
		    dataset.setValue("Move", new Double((categories[3] / sum )* 100));
		    dataset.setValue("Life", new Double((categories[4] / sum )* 100));
		    dataset.setValue("etc.", new Double((categories[5] / sum )* 100));
		}
		
	}

}
