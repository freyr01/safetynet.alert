package com.safetynet.alert.utils;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class FilterUtils {
	
	public static MappingJacksonValue applyPersoninfoExcludeFilter(Object object, String... fieldExclude) {
	     if(object == null) {
	    	 return null;
	     }
	     
		 SimpleBeanPropertyFilter personInfoFilter = SimpleBeanPropertyFilter.serializeAllExcept(fieldExclude);
	     FilterProvider filterList = new SimpleFilterProvider().addFilter("personInfoFilter", personInfoFilter);
	     MappingJacksonValue objectFiltered = new MappingJacksonValue(object);
	     objectFiltered.setFilters(filterList);
	     
	     return objectFiltered;
	}

}
