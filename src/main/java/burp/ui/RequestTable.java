package burp.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RequestTable extends JTable {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;

	public RequestTable(DefaultTableModel model) {
		super(model);
		this.model = model;
		this.init();
	}
	
	public void init() {
		
		model.addColumn("#");
		model.addColumn("Host");
		model.addColumn("Method");
		model.addColumn("URL");
		model.addColumn("data");
		
		this.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.getColumnModel().getColumn(1).setPreferredWidth(220);
		this.getColumnModel().getColumn(2).setPreferredWidth(100);
		this.getColumnModel().getColumn(3).setPreferredWidth(430);
		
		// Hide data column from user
		this.removeColumn(this.getColumn(4));
		
		setAutoCreateRowSorter(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
	}
	
	public void addRow(int number, String host, String method, String url, String data) {
		this.model.addRow(new Object[]{String.valueOf(number) , host, method, url, data});
	}
}
