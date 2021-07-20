package burp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import burp.model.ApexAction;
import burp.model.Param;
import burp.ui.SFScannerTab;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Interceptor implements IHttpListener {

	private IExtensionHelpers helpers;
	private Map<String, ApexAction> actionMap = new HashMap<String, ApexAction>();

	public Interceptor() {

	}

	public Interceptor(IBurpExtenderCallbacks callbacks, SFScannerTab sTab) {
		this.helpers = callbacks.getHelpers();
	}

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {

		if (toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && messageIsRequest == false) {
			String response = this.helpers.bytesToString(messageInfo.getResponse());

			if (response.contains("compound://c")) {
				JsonArray apexList;
				try {
					apexList = this.extractApex(response, "compound://c");
					this.appendActionMap(apexList, messageInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void appendActionMap(JsonArray jArray, IHttpRequestResponse messageInfo) {

		for (JsonElement jElement : jArray) {
			JsonElement acElement = jElement.getAsJsonObject().get("ac");
			JsonArray acArray = acElement.getAsJsonArray();
			for (JsonElement ac : acArray) {
				JsonObject acObj = ac.getAsJsonObject();
				acObj.get("n").getAsString();
				acObj.get("descriptor").getAsString();
				acObj.get("rt").getAsString();
				JsonArray paramArray = ac.getAsJsonObject().get("pa").getAsJsonArray();
				List<Param> params = new ArrayList<Param>();
				for(JsonElement paramElement : paramArray) {
					JsonObject paramObj = paramElement.getAsJsonObject();
					params.add(new Param(paramObj.get("name").getAsString(), paramObj.get("type").getAsString()));
				}
				ApexAction apexAction = new ApexAction(messageInfo, ac.getAsJsonObject().get("n").getAsString(),
						ac.getAsJsonObject().get("rt").getAsString(), params);
				this.actionMap.put(acObj.get("descriptor").getAsString(), apexAction);
			}
		}
	}

	public JsonArray extractApex(String textString, String word) throws Exception {
		JsonArray jArray = new JsonArray();
		boolean exitFlag = false;
		int bracketCount = 0;
		int beginJson;
		int currentIndex;
		Integer index = textString.indexOf(word);
		while (index != -1) {
			beginJson = textString.substring(0, index).lastIndexOf("{");
			textString = textString.substring(beginJson);
			currentIndex = 1;
			bracketCount = 0;
			while (exitFlag != true) {
				if (textString.charAt(currentIndex) == '}') {
					if (bracketCount == 0) {
						break;
					} else {
						bracketCount--;
					}
				}
				if (textString.charAt(currentIndex) == '{') {
					bracketCount++;
				}
				currentIndex++;
			}
			JsonElement jElement = JsonParser.parseString(textString.substring(0, currentIndex + 1));
			jArray.add(jElement);
			textString = textString.substring(currentIndex);
			index = textString.indexOf(word);
		}
		return jArray;
	}
}
