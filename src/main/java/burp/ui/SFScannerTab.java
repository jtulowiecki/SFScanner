package burp.ui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

import burp.IBurpExtenderCallbacks;
import burp.ITab;

public class SFScannerTab extends JSplitPane implements ITab {

	private static final long serialVersionUID = 1L;
	private ActionList actionList;
	private String tabName;
	final Dimension configurationPaneDimension = new Dimension(470, 200);

	public SFScannerTab(String tabName, IBurpExtenderCallbacks callbacks) {

		super(JSplitPane.VERTICAL_SPLIT);
		
		this.actionList = new ActionList();
				
		JScrollPane logTableScrollPane = new JScrollPane(actionList);
		logTableScrollPane.setMinimumSize(configurationPaneDimension);
		logTableScrollPane.setPreferredSize(new Dimension(10000, 10));

		MessageController mc = new MessageController(callbacks);

		JSplitPane userInterfaceSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		userInterfaceSplitPane.setRightComponent(new ConfigurationPane(configurationPaneDimension));
		userInterfaceSplitPane.setLeftComponent(logTableScrollPane);
		userInterfaceSplitPane.setResizeWeight(1.0);
		
		this.setResizeWeight(.00000000000001);
		this.setBottomComponent(mc);
		this.setTopComponent(userInterfaceSplitPane);
		this.tabName = tabName;

		callbacks.customizeUiComponent(this);
		callbacks.addSuiteTab(this);
	}

	public void addComponent(JPanel customPanel) {
		this.add(customPanel);
		this.revalidate();
		this.doLayout();
	}

	@Override
	public String getTabCaption() {
		return tabName;
	}

	@Override
	public Component getUiComponent() {
		return this;
	}
	
	public ActionList getActionList() {
		return this.actionList;
	}
}
