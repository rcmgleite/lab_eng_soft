package exemplo3.dao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class UtilsDAO {
	public static String buildWhere(Map<String, String> parameters){
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
			if(pair.getKey().contains("initial_date")){
				strb.append("date >= '" + pair.getValue() + "'");
			}
			else if(pair.getKey().contains("final_date")){
				strb.append("date <= '" + pair.getValue() + "'");
			}
			else if(pair.getKey().contains("date")){
				strb.append("date = '" + pair.getValue() + "'");
			}
			else{
				strb.append(pair.getKey() + " = " + pair.getValue());
			}
		}
		return strb.toString();
	}
}
