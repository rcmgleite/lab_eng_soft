package exemplo3.dao;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class UtilsDAO {
	public static <T> String buildWhere(Map<String, String> parameters, Class<T> clazz){
		boolean firstTime = true;
		StringBuilder strb = new StringBuilder();
		Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
		while(it.hasNext()){
			if(!firstTime){
				strb.append(" and ");
			}
			firstTime = false;
			//falta colocar o AND da query
			Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
			Field field = null;
			try {
				if(!pair.getKey().equals("initial_date") && !pair.getKey().equals("final_date"))
					field = clazz.getDeclaredField(pair.getKey());
			} catch (NoSuchFieldException e) {
				// Não é para acontecer...ocorreu um erro de implementação caso aconteça.
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pair.getKey().contains("initial_date")){
				strb.append("date >= '" + pair.getValue() + "'");
			}
			else if(pair.getKey().contains("final_date")){
				strb.append("date <= '" + pair.getValue() + "'");
			}
			else if(pair.getKey().contains("date")){
				strb.append("date = '" + pair.getValue() + "'");
			}
			else if(field != null && field.getType().equals(String.class)){
				strb.append(pair.getKey() + " like('" + pair.getValue() + "')");
			}
			else{
				strb.append(pair.getKey() + " = " + pair.getValue());
			}
		}
		return strb.toString();
	}
}
