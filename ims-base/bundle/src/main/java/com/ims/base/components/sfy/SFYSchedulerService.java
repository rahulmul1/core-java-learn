package com.ims.base.components.sfy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Property;

/**
 * @author ragga7
 *
 */
@Component
@Service(value = Runnable.class)
@Property( name = "scheduler.expression", value = "0 53 09 * * ?")
public class SFYSchedulerService implements Runnable {
	
	@Reference
	private CreateNodeForSFYService createNodeForSFYService;
	
	/** The Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SFYSchedulerService.class);

    public void run() {
    	String jsonJcrNodePath = StringUtils.EMPTY;
    	jsonJcrNodePath = createNodeForSFYService.createNodesForSFY("D:/rahul/Richemonte/A_Desktop/SFY/AU_DESIGN_1.xml");
    	LOG.error("Executing a cron job (job#1) through the whiteboard pattern , Path of json --> " + jsonJcrNodePath);
    }

}