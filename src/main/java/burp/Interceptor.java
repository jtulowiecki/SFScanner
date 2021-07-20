package burp;

import java.io.PrintWriter;

public class Interceptor implements IHttpListener {
	
	private PrintWriter stdout;
	private IExtensionHelpers helpers;
	private IRequestInfo requestInfo;
	
	public Interceptor(IBurpExtenderCallbacks callbacks, PrintWriter stdout, PrintWriter stderr) {
		this.helpers = callbacks.getHelpers();
		this.stdout = stdout;
	}

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && messageIsRequest == true) {
            
			requestInfo = this.helpers.analyzeRequest(messageInfo);
            this.stdout.println("Requesting to " + requestInfo.getMethod() + " " + requestInfo.getUrl());
        }		
	}

}
