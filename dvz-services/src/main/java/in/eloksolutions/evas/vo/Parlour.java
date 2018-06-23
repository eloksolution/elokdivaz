package in.eloksolutions.evas.vo;

import java.util.Date;

public class Parlour implements Comparable<Parlour>{
	String name;
	Integer id;
	Date createDate;
	
	public Parlour(){
		
	}
	
	public Parlour(String name, Integer id, Date createDate) {
		super();
		this.name = name;
		this.id = id;
		this.createDate = createDate;
	}
	
	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parlour other = (Parlour) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Parlour [name=" + name + ", id=" + id + ", createDate=" + createDate + "]";
	}

	@Override
	public int compareTo(Parlour o) {
		return id.compareTo(o.getId());
	}
	
	
}
