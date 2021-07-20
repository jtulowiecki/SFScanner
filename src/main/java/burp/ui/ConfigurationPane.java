package burp.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigurationPane extends JPanel {

	private static final long serialVersionUID = 1L;
	Dimension configurationPaneDimension;
	
	public ConfigurationPane(Dimension configurationPaneDimension) {
		this.configurationPaneDimension = configurationPaneDimension;
		this.init();
	}
	
	public void init() {
		JPanel configurationPane = new JPanel();
		configurationPane.setLayout(new GridBagLayout());
		configurationPane.setMinimumSize(configurationPaneDimension);
		configurationPane.setPreferredSize(configurationPaneDimension);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 1;
		JLabel label = new JLabel("hi");
		configurationPane.add(label, c);
	}
}
