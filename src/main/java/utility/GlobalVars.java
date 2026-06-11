package utility;

import java.util.HashMap;
import java.util.Map;

public class GlobalVars {
	
	private static final ThreadLocal<Map<Object, Object>> globalVariables = ThreadLocal.withInitial(HashMap::new);
	
	public static void setGlobalVars(Object key, Object value) {
		globalVariables.get().put(key, value);
	}
	
	public static Object getGlobalVars(Object key) {
		return globalVariables.get().get(key);
	}
	
	public static void unload() {
		globalVariables.remove();
	}

}
