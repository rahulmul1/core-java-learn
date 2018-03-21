package com.ims.base.utilityclasses;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.csvreader.CsvReader;


public class CSVUtility {
	
public static void main(String[] args) {
		
		try {
			Integer count = 0;
			Integer highestStockCompanyA = 0;
			Integer highestStockCompanyB = 0;
			String companyMonthA = StringUtils.EMPTY;
			String companyYearA = StringUtils.EMPTY;
			String companyMonthB = StringUtils.EMPTY;
			String companyYearB = StringUtils.EMPTY;
			//List<Integer> stockCompanyAList = new ArrayList<Integer>();
			CsvReader products = new CsvReader("./src/main/resources/files/shares.csv");
			products.readHeaders();
			    @SuppressWarnings("unused")
				String [] headers = products.getHeaders();
			      //int count=(headers.length-2);
			      while (products.readRecord()) {
			    	  count++;
			    	  String month = products.get("Month");
			    	  String year = products.get("Year");
			    	  String stockForCompanyA = products.get("CompanyA");
			    	  String stockForCompanyB = products.get("CompanyB");
			    	  int stockForCompanyAInt = Integer.parseInt(stockForCompanyA);
			    	  int stockForCompanyBInt = Integer.parseInt(stockForCompanyB);
			    	  if(count == 1){
			    		  highestStockCompanyA = stockForCompanyAInt;
			    		  highestStockCompanyB = stockForCompanyBInt;
			    		  companyMonthA  = month;
		    			  companyYearA = year;
		    			  companyMonthB = month;
		    			  companyYearB = year;
			    	  }
			    	  else{
			    		  if(highestStockCompanyA < stockForCompanyAInt){
			    			  highestStockCompanyA = stockForCompanyAInt;
			    			  companyMonthA  = month;
			    			  companyYearA = year;
			    			  
			    		  }
			    		  if(highestStockCompanyB < stockForCompanyBInt){
			    			  highestStockCompanyB = stockForCompanyBInt;
			    			  companyMonthB = month;
			    			  companyYearB = year;
			    		  }
			    		 
			    	  }
			    	  
			    	  //stockCompanyAList.add(stockForCompanyAInt);
			    	  
				} 
			      System.out.println(companyMonthA+companyYearA);
			      System.out.println(companyMonthB+companyYearB);
			      //Integer max = Collections.max(stockCompanyAList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
	}
}
