package com.wojnarowicz.sfg.gw.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.wojnarowicz.sfg.gw.validators.ValidationResult;

public enum BsoStatus {
	
	NEW (1, "Not used"),
	//2("Not used"),
	//3("Not used"),
    RETURNED (4, "Returned"),
	USED (5, "Used");

    private static final Map<Integer, BsoStatus> lookup = new TreeMap<Integer, BsoStatus>();
    
	private final int code;
	private final String description;
	
	private BsoStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	static {
        for (BsoStatus el : BsoStatus.values()) {
            lookup.put(el.getCode(), el);
        }
    }
	
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static BsoStatus getByCode(int code) {
	    return lookup.get(code);
	}

	public static BsoStatus findByDescription(final String description){
	    return Arrays.stream(values()).filter(el -> el.description.equals(description)).findFirst().get();
	}
	
    public ValidationResult checkIfValid(BsoStatus bsoStatus) {
      //In case of check request
        if(bsoStatus == null) {
            List<BsoStatus> toUse = new ArrayList<BsoStatus>(Arrays.asList(NEW, RETURNED));
            if(!toUse.contains(this)) {
                return ValidationResult.fail("BSO already in use");
            }
        }
        
      //In case set status to USED
        if(bsoStatus == USED) {
            List<BsoStatus> toUse = new ArrayList<BsoStatus>(Arrays.asList(NEW, RETURNED));
            if(!toUse.contains(this)) {
                return ValidationResult.fail("BSO already in use");
            }
        }
        
        //In case set status to RETURN
        if(bsoStatus == RETURNED) {
            if(!this.equals(USED)) {
                return ValidationResult.fail("BSO in wrong state: " + this.description);
            }
        }
        
        return ValidationResult.ok();
    }
}
