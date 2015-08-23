package com.shdp.websockets.webservice.usecases.employeews;

public enum EvalRates {

	Excellent("Excellent", 10),
	VeryGood("VeryGood", 9),
	Good("Good", 8),
	Unknown ("Unknown", 5) ;

	   
	    private String rate;
	    private Integer  level ;
	    
	    public String getRate() {
			return rate;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		
	    
	    EvalRates(String ratei,Integer leveli)
	    {
	    	rate = ratei;
	    	level = leveli ;
	    }
		
		
	   
	    
	   
	}