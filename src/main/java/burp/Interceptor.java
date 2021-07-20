package burp;

import java.io.PrintWriter;

import burp.ui.SFScannerTab;

public class Interceptor implements IHttpListener {
	
	private PrintWriter stdout;
	private IExtensionHelpers helpers;
	private IRequestInfo requestInfo;
	private IHttpService service;
	private SFScannerTab sTab;
	
	public Interceptor(IBurpExtenderCallbacks callbacks, PrintWriter stdout, PrintWriter stderr, SFScannerTab sTab) {
		this.helpers = callbacks.getHelpers();
		this.sTab = sTab;
		this.stdout = stdout;
	}

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && messageIsRequest == false) {
			if(this.helpers.bytesToString(messageInfo.getResponse()).contains("compound://c")) {
				service = messageInfo.getHttpService();
				requestInfo = this.helpers.analyzeRequest(messageInfo);
				String host = service.getProtocol() + "://" + service.getHost();
				this.sTab.getRequestTable().addRow(1, host, requestInfo.getMethod(), requestInfo.getUrl().getPath());
			}
        }		
	}
}
