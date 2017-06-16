package java2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DetailInfo extends JFrame implements ActionListener{
	public static final int W = 500;
	public static final int H = 600;
	public static final int START_X =50;
	public static final int CHART_W = 30;
	public static final int MAX_LEN=150;
	private RecordTable info; // �޾ƿ�������
	private RecordTable ret; //�ӽ÷� �޾ƿ�������
	private ChartFrame chart; //�׸�������
	private DefaultTableModel model;
	private JTable table;
	private String[][] contens;
	private String[] categories={"������","�ĺ�","��ź�","�����","��Ȱ��ǰ","��Ÿ"};
	private String header[]={"��","��","��","�з�","�󼼳���","����ݾ�"};
	private JScrollPane scrollpane;
	private JTextField year1;
	private JTextField month1;
	private JTextField day1;
	private JTextField year2;
	private JTextField month2;
	private JTextField day2;
	
	
	private class ChartFrame extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int[] a=ret.sumByCategories();
			int max=0;
			for(int i=0;i<a.length;i++){
				if(a[i]>max)
					max=a[i];
			}
			for(int i=0;i<a.length;i++){
				double temp =((double)a[i]/max)*MAX_LEN;
				int length=(int)temp;
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(START_X+(i*70),50+MAX_LEN-length, CHART_W,length);
				g.setColor(Color.black);
				g.drawString(Integer.toString(a[i]),START_X+(i*70),50+MAX_LEN-length-20);
				g.drawString(categories[i],START_X+(i*70) ,220);
			}
		}
	}
	
	public static void main(String[] args){
		//DetailInfo test = new DetailInfo();
		//test.setVisible(true);
		//test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public DetailInfo(RecordTable record){
		super("Detail Infomation");
		info=record;
		ret=info;
		setSize(W,H);
		setLayout(new BorderLayout());
		JPanel data_select = new JPanel();
		data_select.setLayout(new FlowLayout());
		chart=new ChartFrame();
		//���ڼ���  �ǳ�
		year1 =new JTextField("2015",6);
		month1 =new JTextField("11",4);
		day1 =new JTextField("18",4);
		JLabel to =new JLabel("~");
		year2 =new JTextField("2017",6);
		month2 =new JTextField("6",4);
		day2 =new JTextField("11",4);
		JButton search=new JButton("��ȸ");
		search.addActionListener(this);
		
		data_select.add(year1);
		data_select.add(month1);
		data_select.add(day1);
		data_select.add(to);
		data_select.add(year2);
		data_select.add(month2);
		data_select.add(day2);
		data_select.add(search);
		
		add(data_select,BorderLayout.NORTH);
		//���̺� 

		
		contens=new String[info.size()][6];
		for(int i=0;i<info.size();i++){
			contens[i][0]=Integer.toString(info.get(i).getDate().get(Calendar.YEAR));
			contens[i][1]=Integer.toString(info.get(i).getDate().get(Calendar.MONTH));
			contens[i][2]=Integer.toString(info.get(i).getDate().get(Calendar.DAY_OF_MONTH));
			contens[i][3]=Record.categoryStr(info.get(i).getCategory());
			contens[i][4]=info.get(i).getNote();
			contens[i][5]=Integer.toString(info.get(i).getAmount());
		}
		model=new DefaultTableModel(contens,header);
		table = new JTable(model);
		scrollpane=new JScrollPane(table);
		JPanel temp =new JPanel();
		temp.setLayout(new GridLayout(2,1));
		temp.add(chart);//��Ʈ �ǳ� �߰� 
		temp.add(scrollpane);
		add(temp,BorderLayout.CENTER);
		
		//���� ���� ��ư
		JPanel button_panel=new JPanel();
		JButton modify=new JButton("����");
		JButton delete=new JButton("����");
		modify.addActionListener(this);
		delete.addActionListener(this);
		
		button_panel.add(modify);
		button_panel.add(delete);
		
		add(button_panel,BorderLayout.SOUTH);
		
		
	}	
	public void actionPerformed(ActionEvent e) {
		String comand =e.getActionCommand();
		if(comand.equals("����")){
			if(table.getSelectedRow()==-1)
				return;
			else{
				Record target = new Record();
				
				/*int year =Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),0));
				int month = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),1));
				int day =Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),2));
				target.setDate(new GregorianCalendar(year,month,day,0,0,0));
				target.setCategory(Record.categoryId((String)table.getValueAt(table.getSelectedRow(), 3)));
				target.setNote((String)table.getValueAt(table.getSelectedRow(),4));
				target.setAmount(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),5)));
				*/
				target=ret.get(table.getSelectedRow());
				info.remove(target);
				ret.remove(target);
				//���ڵ带 �޾ƿͼ� info���� ���� (ret���� �޾ƿ�)
				for(Record it : info)
				{
					System.out.println(it);
				}
				//���� �� ������Ʈ ���
				chart.repaint();
				model.removeRow(table.getSelectedRow());
			}
		}
		else if(comand.equals("��ȸ")){
			//����ó�� ���߿� �ݵ�� �� ��
			int start_year=Integer.parseInt(year1.getText().trim());
			int start_month=Integer.parseInt(month1.getText().trim());
			int start_day=Integer.parseInt(day1.getText().trim());
			int end_year=Integer.parseInt(year2.getText().trim());
			int end_month=Integer.parseInt(month2.getText().trim());
			int end_day=Integer.parseInt(day2.getText().trim());
		
			model.setRowCount(0);
			ret=info.listByDate(start_year,start_month,start_day
					,end_year, end_month, end_day);
			contens=new String[ret.size()][6];
			for(int i=0;i<ret.size();i++){
				contens[i][0]=Integer.toString(ret.get(i).getDate().get(Calendar.YEAR));
				contens[i][1]=Integer.toString(ret.get(i).getDate().get(Calendar.MONTH));
				contens[i][2]=Integer.toString(ret.get(i).getDate().get(Calendar.DAY_OF_MONTH));
				contens[i][3]=Record.categoryStr(ret.get(i).getCategory());
				contens[i][4]=ret.get(i).getNote();
				contens[i][5]=Integer.toString(ret.get(i).getAmount());
			}
			for(int i=0;i<ret.size();i++)
				model.addRow(contens[i]);
			
			
			chart.repaint();
			
			
		}
		
	}
}