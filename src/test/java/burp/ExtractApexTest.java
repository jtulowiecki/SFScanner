package burp;

import java.io.File;

import java.nio.file.Files;

import org.junit.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ExtractApexTest {

	@Test
	public void testApexExctraction() throws Exception {

		Interceptor i = new Interceptor();

		String fileName = "response";
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());

		String content = new String(Files.readAllBytes(file.toPath()));

		JsonArray jArray = i.extractApex(content, "compound://c");
		
		for(JsonElement jElement : jArray) {
			System.out.println(jElement.getAsJsonObject().get("descriptor").getAsString());
		}
	}
}
