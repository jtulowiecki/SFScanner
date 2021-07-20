package burp;

import burp.ui.SFScannerTab;
import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender {

	private String pluginName = "SF Scanner";
	protected SFScannerTab sTab;

	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		
		PrintWriter stdout = new PrintWriter(callbacks.getStdout(), true);
        
		callbacks.setExtensionName(pluginName);
		callbacks.registerHttpListener(new Interceptor(callbacks, stdout, stdout));
		
		sTab = new SFScannerTab(pluginName, callbacks);
		callbacks.customizeUiComponent(sTab);
        callbacks.addSuiteTab(sTab);
	}
}
