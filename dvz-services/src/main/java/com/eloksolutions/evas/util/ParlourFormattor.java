package com.eloksolutions.evas.util;

import java.util.List;

import com.eloksolutiions.evas.vo.Parlour;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParlourFormattor {

	public static String format(List<Parlour> parlours) throws Exception{
			return new ObjectMapper().writeValueAsString(parlours);
	}
	
	public static List<Parlour> parse(String strParlours) throws Exception{
		return (List<Parlour>)new ObjectMapper().readValue(strParlours,List.class);
}
}
