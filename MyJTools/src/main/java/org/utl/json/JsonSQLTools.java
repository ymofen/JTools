package org.utl.json;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;



import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class JsonSQLTools {
	/**
	 * 
	 * @param json
     *  {
     *    "FFormKey":"FB47BBA3-6C21-3485-26BD-5C1A7CBD89D1",
     *    "FKind":102001,
     *  }
	 * @return 
	 *   FFormKey='FB47BBA3-6C21-3485-26BD-5C1A7CBD89D1' and FKind=102001
	 */
	public static String getCondition(JsonElement json)
	{
		String ret = "";
		if (json.isJsonObject())
		{		
			
			Set<Entry<String, JsonElement>> list = json.getAsJsonObject().entrySet();
		    Iterator<Entry<String, JsonElement>> itr =list.iterator();
		    
		    while (itr.hasNext())
		    {
		    	Entry<String, JsonElement> e = itr.next();
		    	
		    	if (!GsonUtil.jsonIsNull(e.getValue()))
		    	{			    
		    		if (e.getValue().isJsonPrimitive())
		    		{
		    			JsonPrimitive jValue = (JsonPrimitive)(e.getValue());
		    			String strValue=jValue.getAsString();
		    			if (jValue.isNumber())
		    			{
		    				ret = ret + " and " + e.getKey() + "=" + strValue + "";
		    			}else if (jValue.isBoolean())
		    			{
		    				if (jValue.getAsBoolean())
		    				{
		    					ret = ret + " and " + e.getKey() + "=1";
		    				}else
		    				{
		    					ret = ret + " and " + e.getKey() + "=0";
		    				}		    				
		    			}else
		    			{  			
				    		if ((strValue=="NULL")||(strValue=="null"))
				    		{
				    			ret = ret + " and " + e.getKey() + " IS NULL";			
				    		}else
				    		{
				    			ret = ret + " and " + e.getKey() + "='" + strValue + "'";
				    		}
		    			}
		    		}
		    	}
		    }			
		}
		if (ret.length() > 0)
		{
			ret = ret.substring(5);
		}
		return ret;
	}
}
