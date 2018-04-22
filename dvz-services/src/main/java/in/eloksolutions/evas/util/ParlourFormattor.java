package in.eloksolutions.evas.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.eloksolutions.evas.vo.Parlour;

public class ParlourFormattor {

	public static String format(List<Parlour> parlours) throws Exception{
			return new ObjectMapper().writeValueAsString(parlours);
	}
	
	public static List<Parlour> parse(String strParlours){
		if(strParlours==null|| strParlours.isEmpty())return null;
		try {
			List<Parlour> lst= (List<Parlour>)new ObjectMapper().readValue(strParlours,List.class);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}
}
