package burp.ui;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class RequestTable extends JTable {

	private static final long serialVersionUID = 1L;

	public RequestTable() {
		this.init();
	}
	
	public void init() {
		
		TableColumn firstCol = new TableColumn();
		firstCol.setPreferredWidth(5);
		addColumn(firstCol);
		
		TableColumn secondCol = new TableColumn();
		secondCol.setPreferredWidth(50);
		addColumn(secondCol);
		
		TableColumn thirdCol = new TableColumn();
		thirdCol.setPreferredWidth(250);
		addColumn(thirdCol);
		
		TableColumn fourthCol = new TableColumn();
		fourthCol.setPreferredWidth(80);
		addColumn(fourthCol);
		
		setAutoCreateRowSorter(true);
	}
}
