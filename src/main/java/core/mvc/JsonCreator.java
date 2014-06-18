package core.mvc;

import java.util.ArrayList;

public class JsonCreator {
	private JsonCreator() {
	}

	private static JsonCreator ac = null;

	public static JsonCreator getInstance() {
		if (ac == null) {
			ac = new JsonCreator();
		}
		return ac;
	}

	public String createNameValuePair(String name, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append("\"");
		sb.append(name);
		sb.append("\" : \"");
		sb.append(value);
		sb.append("\"");
		return sb.toString();
	}

	public String createNameValuePair(String name, Object obj) {
		//여기 진짜 인스턴스 객체가 들어오거나 하면 안 되는데 어떻게 방지할 수 있을까 
		StringBuffer sb = new StringBuffer();
		sb.append("\"");
		sb.append(name);
		sb.append("\" : ");
		sb.append(obj);
		sb.append("");
		return sb.toString();
	}
	
	public String createObject(String... pairs) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		for (int i = 0; i < pairs.length; i++) {
			sb.append(pairs[i]);
			if (i != (pairs.length - 1)) {
				sb.append(", ");
			}
		}
		sb.append("}");

		return sb.toString();
	}
	
	public String createArray(String key, String...values){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"");
		sb.append(key);
		sb.append("\" : [");
		
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i != (values.length - 1)) {
				sb.append(", ");
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}

	public String createArray(String key, ArrayList<String> values) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"");
		sb.append(key);
		sb.append("\" : [");
		
		for (int i = 0; i < values.size(); i++) {
			sb.append(values.get(i));
			if (i != (values.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}
	
	public String createArray(ArrayList<String> values) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		for (int i = 0; i < values.size(); i++) {
			sb.append(values.get(i));
			if (i != (values.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}



	
}
