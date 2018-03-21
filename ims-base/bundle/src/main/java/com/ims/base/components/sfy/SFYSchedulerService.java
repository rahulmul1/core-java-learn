package com.ims.base.components.sfy;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ims.base.components.dom.TestSfyDomParser;

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
    	
    	ClassLoader classLoader = new TestSfyDomParser().getClass().getClassLoader();
	String fileName = classLoader.getResource("files/AU_DESIGN_1.xml").getFile();
		
	jsonJcrNodePath = createNodeForSFYService.createNodesForSFY(fileName);
    	LOG.error("Executing a cron job (job#1) through the whiteboard pattern , Path of json --> " + jsonJcrNodePath);
    }

}