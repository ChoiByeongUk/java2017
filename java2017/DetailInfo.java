package java2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private RecordTable info; // 받아오는정보
	private RecordTable ret; //임시로 받아오는정보
	private ChartFrame chart; //그림프레임
	private DefaultTableModel model;
	private JTable table;
	private String[][] contens;
	private String[] categories={"관리비","식비","통신비","교통비","생활용품","기타"};
	private String header[]={"년","월","일","분류","상세내역","지출금액"};
	private JScrollPane scrollpane;
	private JComboBox year1;
	private JComboBox month1;
	private JComboBox day1;
	private JComboBox year2;
	private JComboBox month2;
	private JComboBox day2;
	private String revision_text;
	
	
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
	private class DisposeExit extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			dispose();
		}
	}
	public class RevisionFrame extends JFrame implements ActionListener{
		public static final int W=300;
		public static final int H=130;
		
		private JTextField input;
		
		
		public RevisionFrame(){
			super("Revision Input");
			setSize(W,H);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new DisposeExit(){
				public void windowClosing(WindowEvent e){
					DetailInfo.this.setEnabled(true);
					dispose();
				}
			});
			
			JPanel text_panel=new JPanel();
			text_panel.setLayout(new FlowLayout());
			
			
			JLabel label= new JLabel("수정값 입력 : ");
			input=new JTextField(10);
			
			text_panel.add(label);
			text_panel.add(input);
			
			add(text_panel,BorderLayout.CENTER);
			
			JPanel button_panel=new JPanel();
			
			JButton yes=new JButton("확인");
			JButton no=new JButton("취소");
			yes.addActionListener(this);
			no.addActionListener(this);
			button_panel.add(yes);
			button_panel.add(no);
			
			add(button_panel,BorderLayout.SOUTH);
			
		}


		public void actionPerformed(ActionEvent e) {
			String commant = e.getActionCommand();
			if(commant.equals("확인")){
				if(table.getSelectedRow()==-1)
				{
					DetailInfo.this.setEnabled(true);
					dispose();
					return;
				}
				Record target = new Record();
				revision_text=input.getText();
				target=ret.get(table.getSelectedRow());
				switch(table.getSelectedColumn()){
				case 0 : target.getDate().set(Calendar.YEAR,Integer.parseInt(revision_text.trim()));
						year1.removeAllItems();
						for(int i=info.least_year();i<info.most_year()+1;i++)
								year1.addItem(Integer.toString(i));
						year2.removeAllItems();
						for(int i=info.least_year();i<info.most_year()+1;i++)
								year2.addItem(Integer.toString(i));
						break;
				case 1 : target.getDate().set(Calendar.MONTH,Integer.parseInt(revision_text.trim()));
						break;
				case 2 : target.getDate().set(Calendar.DAY_OF_MONTH,Integer.parseInt(revision_text.trim()));
						break;
				case 3 : int categori=Record.categoryId(revision_text);
						if(categori==-1) break;
						target.setCategory(categori);
						break;
				case 4 : target.setNote(revision_text.trim());
						break;
				case 5 : target.setAmount(Integer.parseInt(revision_text.trim()));
						break;
				}
				table.setValueAt(revision_text, table.getSelectedRow(),table.getSelectedColumn());
				chart.repaint();
				
				DetailInfo.this.setEnabled(true);
				dispose();
			}
			else if(commant.equals("취소")){
				DetailInfo.this.setEnabled(true);
				dispose();
			}
			
			
		}
		

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
		//날자선택  판넬
		year1 =new JComboBox();
		for(int i=info.least_year();i<info.most_year()+1;i++)
			year1.addItem(Integer.toString(i));
		year1.setBackground(Color.WHITE);

		month1 =new JComboBox();
		for(int i=1;i<13;i++)
			month1.addItem(Integer.toString(i));
		month1.setBackground(Color.WHITE);
		day1 =new JComboBox();
		for(int i=1;i<32;i++)
			day1.addItem(Integer.toString(i));
		day1.setBackground(Color.WHITE);
		JLabel to =new JLabel("~");
		year2 =new JComboBox();
		for(int i=info.least_year();i<info.most_year()+1;i++)
			year2.addItem(Integer.toString(i));
		year2.setBackground(Color.WHITE);

		month2 =new JComboBox();
		for(int i=1;i<13;i++)
			month2.addItem(Integer.toString(i));
		month2.setBackground(Color.WHITE);
		day2 =new JComboBox();
		for(int i=1;i<32;i++)
			day2.addItem(Integer.toString(i));
		day2.setBackground(Color.WHITE);
		JButton search=new JButton("조회");
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
		//테이블 

		
		contens=new String[info.size()][6];
		for(int i=0;i<info.size();i++){
			contens[i][0]=Integer.toString(info.get(i).getDate().get(Calendar.YEAR));
			contens[i][1]=Integer.toString(info.get(i).getDate().get(Calendar.MONTH));
			contens[i][2]=Integer.toString(info.get(i).getDate().get(Calendar.DAY_OF_MONTH));
			contens[i][3]=Record.categoryStr(info.get(i).getCategory());
			contens[i][4]=info.get(i).getNote();
			contens[i][5]=Integer.toString(info.get(i).getAmount());
		}
		model=new DefaultTableModel(contens,header){
			public boolean isCellEditable(int row,int column){
				return false;
			}
			
		};

		table = new JTable(model);
		
		scrollpane=new JScrollPane(table);
		JPanel temp =new JPanel();
		temp.setLayout(new GridLayout(2,1));
		temp.add(chart);//차트 판넬 추가 
		temp.add(scrollpane);
		add(temp,BorderLayout.CENTER);
		
		//수정 삭제 버튼
		JPanel button_panel=new JPanel();
		JButton modify=new JButton("수정");
		JButton delete=new JButton("삭제");
		modify.addActionListener(this);
		delete.addActionListener(this);
		
		button_panel.add(modify);
		button_panel.add(delete);
		
		add(button_panel,BorderLayout.SOUTH);
		
		
	}	
	public void actionPerformed(ActionEvent e) {
		String comand =e.getActionCommand();
		if(comand.equals("삭제")){
			if(table.getSelectedRow()==-1)
				return;
			else{
				Record target = new Record();
				target=ret.get(table.getSelectedRow());
				info.remove(target);
				ret.remove(target);
				/*for(Record it : info)
				{
					System.out.println(it);
				}*/
				//삭제 후 리페인트 요망
				chart.repaint();
				model.removeRow(table.getSelectedRow());
			}
		}
		else if(comand.equals("조회")){
			//예외처리 나중에 반드시 할 것 콤보박스로 교체도 할것
			int start_year=Integer.parseInt(year1.getSelectedItem().toString().trim());
			int start_month=Integer.parseInt(month1.getSelectedItem().toString().trim());
			int start_day=Integer.parseInt(day1.getSelectedItem().toString().trim());
			int end_year=Integer.parseInt(year2.getSelectedItem().toString().trim());
			int end_month=Integer.parseInt(month2.getSelectedItem().toString().trim());
			int end_day=Integer.parseInt(day2.getSelectedItem().toString().trim());
		
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
		else if(comand.equals("수정")){
			//프렝미 하나 더만들어서 수정할 것 
			RevisionFrame revision=new RevisionFrame();
			revision.setVisible(true);
			revision.setAlwaysOnTop(true);
			this.setEnabled(false);
			
		}
		
	}
}