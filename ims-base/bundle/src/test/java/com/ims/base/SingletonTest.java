package com.ims.base;

import org.apache.log4j.Logger;

import com.ims.base.singleton.ClassicSingleton;

import junit.framework.Assert;
import junit.framework.TestCase;
public class SingletonTest extends TestCase {
   private ClassicSingleton sone = null, stwo = null;
   private static Logger logger = Logger.getRootLogger();
   public SingletonTest(String name) {
      super(name);
   }
   public void setUp() {
      if (logger.isDebugEnabled()) {
		logger.debug("entering setUp()");
	}
	logger.info("getting singleton...");
      sone = ClassicSingleton.getInstance();
      logger.info("...got singleton: " + sone);
      logger.info("getting singleton...");
      stwo = ClassicSingleton.getInstance();
      logger.info("...got singleton: " + stwo);
	if (logger.isDebugEnabled()) {
		logger.debug("exiting setUp()");
	}
   }
   public void testUnique() {
      if (logger.isDebugEnabled()) {
		logger.debug("entering testUnique()");
	}
	logger.info("checking singletons for equality");
      Assert.assertEquals(true, sone == stwo);
	if (logger.isDebugEnabled()) {
		logger.debug("exiting testUnique()");
	}
   }
}
