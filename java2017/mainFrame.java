package java2017;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	private static int MAIN_HEIGHT = 1000;
	DefaultPieDataset dataset = new DefaultPieDataset();
	
	private RecordTable data = new RecordTable();
	
	private String[] Dates = {"2017/06", "2017/07"};
	String[] categories = {"Management", "Food", "Phone", "Move", "Life", "etc."};
	
	
	private void setDataset(DefaultPieDataset dataset, RecordTable table)
	{
		
	}
	public mainFrame()
	{
		super("main");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		//Top buttons, combobox
		JPanel Top = new JPanel();
		JPanel Top_Button = new JPanel();
		Top_Button.setLayout(new GridLayout(2,1));
		Top.setLayout(new FlowLayout());
		
		JButton add_Date = new JButton("+");
		JButton del_Date = new JButton("-");
		add_Date.addActionListener(this);
		del_Date.addActionListener(this);
		
		Top_Button.add(add_Date);
		Top_Button.add(del_Date);
		Top.add(Top_Button);
		
		//setdate panel
		JComboBox set_Date = new JComboBox(Dates); // 날짜 선택 콤보박스
		set_Date.addItem("2017/03");
		set_Date.setSelectedItem(0);
		Top.add(set_Date);
		set_Date.addItemListener(new DateSelectListener(dataset, data));
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
		data.addRecord(2017, 3, 25, 1000, Categories.FOOD, "APPLE");
		data.addRecord(2017, 3, 26, 2000, Categories.MOVE, "BUS");
		data.addRecord(2017, 2, 13, 1345, Categories.LIFE, "WATER");
		data.addRecord(2017, 6, 11, 1632, Categories.FOOD, "SUSHI");
		data.addRecord(2017, 6, 13, 1632, Categories.PHONE, "SUSHI");
		
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
	}
	
	public static void main(String args[])
	{
		mainFrame window = new mainFrame();
		window.setVisible(true);
//		
	}
}
