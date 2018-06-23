package in.eloksolutions.evas.util;


import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.eloksolutions.evas.vo.Parlour;

public class ParlourFormattor {

	public static String format(Set<Parlour> parlours) throws Exception{
		if(parlours==null|| parlours.isEmpty())return null;
			return new ObjectMapper().writeValueAsString(parlours);
	}
	
	public static Set<Parlour> parse(String strParlours){
		if(strParlours==null|| strParlours.isEmpty())return null;
		try {
			Set<Parlour> lst= (Set<Parlour>)new ObjectMapper().readValue(strParlours,Set.class);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}
}
