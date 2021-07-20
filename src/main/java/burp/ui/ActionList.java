package burp.ui;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ActionList extends JList<String> {

	private static final long serialVersionUID = 1L;

	public ActionList() {
		this.init();
	}

	private void init() {
		this.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(this);
		listScroller.setPreferredSize(new Dimension(250, 80));
	}
}
