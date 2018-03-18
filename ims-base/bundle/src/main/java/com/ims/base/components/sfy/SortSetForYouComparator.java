/**
 * 
 */
package com.ims.base.components.sfy;

import java.util.Comparator;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ragga7
 *
 */
public class SortSetForYouComparator implements Comparator<JSONObject> {
	/** The Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SortSetForYouComparator.class);
	public int compare(JSONObject o1, JSONObject o2) {
		try {
			Double price1 = (Double) o1.get(SetForYouConstants.PRICE.getValue());
			Double price2 =	(Double) o2.get(SetForYouConstants.PRICE.getValue());
			
		return price1>price2?1
				:price2>price1?-1
				:0;
				
		} catch (JSONException e) {
			LOG.error("JSONException occured : " + e.getMessage());
		}
		return 0;
		
	}

}
