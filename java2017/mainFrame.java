package java2017;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.StringTokenizer;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class mainFrame extends JFrame implements ActionListener{
	private static int MAIN_WIDTH = 500;
	private static int MAIN_HEIGHT = 700;
	DefaultPieDataset dataset = new DefaultPieDataset();
	
	private RecordTable data = new RecordTable();
	
	
//	String[] categories = {"Management", "Food", "Phone", "Move", "Life", "etc."};
	String[] categories = {"관리비", "식비", "통신비", "교통비", "생활용품", "기타"};
	JComboBox set_Date = new JComboBox();
	DateSelectListener origin;
	public mainFrame()
	{
		
		super("main");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JMenuItem openMenu = new JMenuItem("OPEN"), saveMenu = new JMenuItem("SAVE");
		JMenu fileMenu = new JMenu("FILE");
		JMenuBar MB = new JMenuBar();
		
		openMenu.addActionListener(this);
		saveMenu.addActionListener( (ActionEvent e) -> TableFileManager.write(data));
		
		fileMenu.add(openMenu);
		fileMenu.add(saveMenu);
		MB.add(fileMenu);
		
		setJMenuBar(MB);
		
		//Top buttons
		JPanel Top = new JPanel();		
		JPanel Top_Button = new JPanel();
		Top_Button.setLayout(new GridLayout(2,1));
		Top.setLayout(new FlowLayout());
		
		
		
		
		
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(this);
		Top.add(Top_Button);
		
		//setdate panel
		set_Date.setSelectedItem(0);
		Top.add(set_Date);
		origin = new DateSelectListener(dataset, data);
		set_Date.addItemListener(origin);
		Top.add(refresh);
		//end setdate panel
		
		//pie chart
		JFreeChart chartdemo = ChartFactory.createPieChart(
				"Graph",
				dataset,
				true,
				true,
				false);
		ChartPanel CP = new ChartPanel(chartdemo);
		//end pie chart
		
		
		
		
		//buttonpanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		
		JButton addInfo = new JButton("Add");
		buttonPanel.add(addInfo);
		addInfo.addActionListener(this);
		
		
		JButton moreInfo = new JButton("More Info");
		moreInfo.addActionListener(this);
		buttonPanel.add(moreInfo);
		//end buttonpanel
		
		//add panels
		add(CP, BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
		add(Top, BorderLayout.NORTH);
		
		
		/**/
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if(command.equals("Add"))
		{
			addFrame add = new addFrame(data);
			add.setVisible(true);
		}
		else if(command.equals("More Info"))
		{
			DetailInfo detail = new DetailInfo(data);
			detail.setVisible(true);
		}
		else if(command.equals("OPEN"))
		{
			data = TableFileManager.open();
			set_Date.removeItemListener(origin);
			DateSelectListener new_Listener = new DateSelectListener(dataset, data);
			set_Date.addItemListener(new_Listener);
			origin = new_Listener;
		}
		else if(command.equals("refresh"))
		{
			String item = null;
			
			
			set_Date.removeAllItems();
			int min_year = data.least_year();
			int max_year = data.most_year();
			for(int i = min_year; i<=max_year;i++)
			{
				for(int j = 1; j<=12; j++)
				{
					int year, month;
					RecordTable ret = data.listByMonth(i, j, i, j);
					//System.out.print(i + "/" + j +":");
					//System.out.println(ret.size());
					if(ret.size() > 0)
					{
						year = i; month = j;
						String date_item = (String)(i + "/" + j);
						set_Date.addItem(date_item);
					}
				}
			}
			try
			{
				item = set_Date.getSelectedItem().toString();
				
			}
			catch(NullPointerException exception)
			{
				
			}
			if(item != null)
			{
				StringTokenizer token = new StringTokenizer(item, "/ ");
				
				int year, month;
				year = Integer.parseInt(token.nextToken());
				month = Integer.parseInt(token.nextToken());
				RecordTable ret = data.listByMonth(year, month ,year, month);/**/
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
//			for(int i =0; i<set_Date.getItemCount(); i++)
//			{
//				
//			}

			
			
		}
	}
	
//	public static void main(String args[])
//	{
//		mainFrame window = new mainFrame();
//		window.setVisible(true);
////		
//	}
}
