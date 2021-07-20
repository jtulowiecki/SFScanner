package burp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import burp.IBurpExtenderCallbacks;
import burp.IHttpService;
import burp.IMessageEditor;
import burp.IMessageEditorController;

public class MessageController extends JSplitPane implements IMessageEditorController {

	private static final long serialVersionUID = 1L;
	private IBurpExtenderCallbacks callbacks;
	
	public MessageController(IBurpExtenderCallbacks callbacks) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.callbacks = callbacks;
		this.init();
	}
	
	public void init() {
		
		IMessageEditor requestViewer = callbacks.createMessageEditor(this, false);
		IMessageEditor responseViewer = callbacks.createMessageEditor(this, false);
		
		JLabel requestLabel = new JLabel("Request");
		requestLabel.setForeground(new Color(0xff6633));
		requestLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JPanel requestPanel = new JPanel();
		requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.PAGE_AXIS));
		requestPanel.add(requestLabel);
		requestPanel.add(requestViewer.getComponent());
		requestPanel.setPreferredSize(new Dimension(100000, 100000));
		
		JLabel responseLabel = new JLabel("Response");
		responseLabel.setForeground(new Color(0xff6633));
		responseLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JPanel responsePanel = new JPanel();
		responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.PAGE_AXIS));
		responsePanel.add(responseLabel);
		responsePanel.add(responseViewer.getComponent());
		responsePanel.setPreferredSize(new Dimension(100000, 100000));
		
		setLeftComponent(requestPanel);
		setRightComponent(responsePanel);
		setResizeWeight(0.50);
	}
	
	@Override
	public IHttpService getHttpService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
