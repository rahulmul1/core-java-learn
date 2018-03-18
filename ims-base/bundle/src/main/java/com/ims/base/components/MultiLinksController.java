/**
 * 
 */
package com.ims.base.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;
import org.json.JSONException;

import com.ims.base.constants.PropertyConstants;
import com.ims.base.context.JSPContext;
import com.ims.base.utils.JSONUtils;

/**
 * @author nsin39
 *
 */
public class MultiLinksController extends AbstractController{
	
	/**
     * CarouselController Constructor.
     */
    public MultiLinksController() {
        super();
    }

    /**
     * CarouselController Constructor.
     * 
     * @param jspContext
     *            Context.
     * 
     */
    public MultiLinksController(final JSPContext jspContext) {
        super(jspContext);
    }

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getTitleLinkList(){
		List<Map<String, String>> titleLinkList = Collections.EMPTY_LIST;
		try {
			ValueMap map = getResource().adaptTo(ValueMap.class);
			if(map!=null){
				if(map.get(PropertyConstants.PATHS.getValue()).getClass() == String.class){
					String path = (String)map.get(PropertyConstants.PATHS.getValue());
					titleLinkList = JSONUtils.getTitleLinkList(Arrays.asList(path));	
				}else{
					String[] pathArray = (String[]) map.get(PropertyConstants.PATHS.getValue());
					titleLinkList = JSONUtils.getTitleLinkList(Arrays.asList(pathArray));	
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return titleLinkList;
	}	
		
}
