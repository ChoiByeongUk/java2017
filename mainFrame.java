import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
	
	private String[] Dates = {"1", "2"};
	JComboBox set_Date = new JComboBox(Dates);
	private static PieDataset createDataset()
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
       
		return dataset;
	}
	private void setDataset(PieDataset origin, ChartPanel jPanel)
	{
		jPanel.removeAll();
		jPanel.revalidate();
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Test_One", new Double(43.2));
        dataset.setValue("Test_Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        
        origin = dataset;
	}
//	public static class Pie extends ApplicationFrame
//	{
//		public Pie(String title)
//		{
//			super(title);
//			setContentPane(createPanel());
//		}
//		
//		
//		
//		private static JFreeChart createChart(PieDataset dataset)
//		{
//			JFreeChart chart = ChartFactory.createPieChart(
//					"Pie",
//					dataset,
//					true,
//					true,
//					false
//					);
//			
//			PiePlot plot = (PiePlot) chart.getPlot();
//			plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
//			plot.setNoDataMessage("No data available");
//			plot.setCircular(false);
//			plot.setLabelGap(0.02);
//			return chart;
//		}
//		public static JPanel createPanel()
//		{
//			JFreeChart chart =  createChart(createDataset());
//			return new ChartPanel(chart);
//		}
//	}
	public mainFrame()
	{
		super("main");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		set_Date.setSelectedItem(2);
		set_Date.addActionListener(this);
		add(set_Date, BorderLayout.NORTH);
		
		PieDataset dataset = createDataset();
		JFreeChart chartdemo = ChartFactory.createPieChart(
				"title",
				dataset,
				true,
				true,
				false);
		
		ChartPanel CP = new ChartPanel(chartdemo);
		add(CP,BorderLayout.CENTER);
		setDataset(dataset, CP);
		CP.repaint();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		
		JButton addInfo = new JButton("addInfo");
		addInfo.setText("�߰�");
		buttonPanel.add(addInfo);
		addInfo.addActionListener(this);
		
		
		JButton moreInfo = new JButton("moreInfo");
		moreInfo.setText("�� ����");
		moreInfo.addActionListener(this);
		buttonPanel.add(moreInfo);
		
		add(buttonPanel,BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		
	}
	public static void main(String args[])
	{
		mainFrame window = new mainFrame();
		window.setVisible(true);
		
	}
}
