package burp.model;

import java.util.List;

import burp.IHttpRequestResponse;

public class ApexAction {

	private String name;
	private String returnValue;
	private IHttpRequestResponse messageInfo;
	private List<Param> params;
	
	public ApexAction(IHttpRequestResponse messageInfo, String name, String returnValue, List<Param> params) {
		this.name = name;
		this.returnValue = returnValue;
		this.messageInfo = messageInfo;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public IHttpRequestResponse getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(IHttpRequestResponse messageInfo) {
		this.messageInfo = messageInfo;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}	
}
