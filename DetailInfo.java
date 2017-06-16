package java2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DetailInfo extends JFrame implements ActionListener{
	public static final int W = 500;
	public static final int H = 600;
	public static final int START_X =100;
	public static final int WIDTH = 30;
	public static final int MAX_LEN=150;
	public RecordTable info; // �޾ƿ�������
	public ChartFrame test;
	
	private class ChartFrame extends JPanel{

	}
	
	public static void main(String[] args){
		DetailInfo test = new DetailInfo();
		test.setVisible(true);
	}
	public DetailInfo(){
		super("Detail Infomation");
		setSize(W,H);
		setLayout(new BorderLayout());
		JPanel data_select = new JPanel();
		data_select.setLayout(new FlowLayout());
		test=new ChartFrame();
		//���ڼ���  �ǳ�
		JTextField year1 =new JTextField(6);
		JTextField month1 =new JTextField(4);
		JTextField day1 =new JTextField(4);
		JLabel to =new JLabel("~");
		JTextField year2 =new JTextField(6);
		JTextField month2 =new JTextField(4);
		JTextField day2 =new JTextField(4);
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
		
		String header[]={"��","��","��","�з�","�󼼳���","����ݾ�"};
		String contens[][] = {{"2017","06","03","�ĺ�","����Ĵ�","3500"}};
		
		JTable table = new JTable(contens,header);
		JScrollPane scrollpane=new JScrollPane(table);
		
		add(test,BorderLayout.CENTER);
		add(scrollpane,BorderLayout.SOUTH);
	}
	public void paint(Graphics g){
		super.paint(g);
		//int[] a=info.search(); // ������ �޾ƿ� 
		int[] a = new int[6];
		int max=0;
		Random rand=new Random();
		for(int i=0;i<a.length;i++){
			a[i]=rand.nextInt(10000);
			if(a[i]>max)
				max=a[i];
		}
		for(int i=0;i<a.length;i++){
			double temp =((double)a[i]/max)*MAX_LEN;
			int length=(int)temp;
			System.out.println(length);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(START_X+(i*50),150+MAX_LEN-length, WIDTH,length);
			g.setColor(Color.black);
			g.drawString(Integer.toString(a[i]),START_X+(i*50),150+MAX_LEN-length-20);
			g.drawString("test"+i,START_X+(i*50) ,320);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}