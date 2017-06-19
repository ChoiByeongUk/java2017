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
	public mainFrame()
	{
		
		super("main");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JMenuItem openMenu = new JMenuItem("OPEN"), saveMenu = new JMenuItem("SAVE");
		JMenu fileMenu = new JMenu("FILE");
		JMenuBar MB = new JMenuBar();
		
		openMenu.addActionListener( (ActionEvent e) -> data = TableFileManager.open());
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
		
		JButton add_Date = new JButton("new");
		JButton del_Date = new JButton("delete");
		add_Date.addActionListener(this);
		del_Date.addActionListener(this);
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(this);
		Top_Button.add(add_Date);
		Top_Button.add(del_Date);
		Top.add(Top_Button);
		
		//setdate panel
		set_Date.setSelectedItem(0);
		Top.add(set_Date);
		set_Date.addItemListener(new DateSelectListener(dataset, data));
		Top.add(refresh);
		//end setdate panel
		
		//pie chart
		JFreeChart chartdemo = ChartFactory.createPieChart(
				"title",
				dataset,
				true,
				true,
				false);
		chartdemo.setTitle("");
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
		else if(command.equals("new"))
		{
			DateAddFrame new_window = new DateAddFrame(set_Date);
			new_window.setVisible(true);
		}
		else if(command.equals("delete"))
		{
			ConfirmFrame new_window = new ConfirmFrame(set_Date);
			new_window.setVisible(true);
		}
		else if(command.equals("refresh"))
		{
			String item = set_Date.getSelectedItem().toString();
			StringTokenizer token = new StringTokenizer(item, "/ ");
			
			int year, month;
			year = Integer.parseInt(token.nextToken());
			month = Integer.parseInt(token.nextToken());
			
			RecordTable ret = data.listByMonth(year, month ,year, month+1);/**/
			int categories[] = ret.sumByCategories(); // ���� �ʿ���. ��¥ �� ī�װ� ���� �ʤ���.
			double sum = 0;
			for(int i =0; i<categories.length; i++)
				sum += categories[i];
			System.out.println(ret.size());
			dataset.setValue("Management", new Double((categories[0] / sum )* 100));
		    dataset.setValue("Food", new Double((categories[1] / sum )* 100));
		    dataset.setValue("Phone", new Double((categories[2] / sum )* 100));
		    dataset.setValue("Move", new Double((categories[3] / sum )* 100));
		    dataset.setValue("Life", new Double((categories[4] / sum )* 100));
		    dataset.setValue("etc.", new Double((categories[5] / sum )* 100));
		   
			
			
		}
	}
	
	public static void main(String args[])
	{
		mainFrame window = new mainFrame();
		window.setVisible(true);
//		
	}
}
