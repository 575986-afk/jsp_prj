package day0622;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class CalendarDTO {
	
	public CalendarDTO cDTO;
	
	private String year, month;
	
	public CalendarDTO() {
		
	}

	public CalendarDTO(CalendarDTO cDTO, String year, String month) {
		super();
		this.cDTO = cDTO;
		this.year = year;
		this.month = month;
	}

	public CalendarDTO getcDTO() {
		return cDTO;
	}

	public void setcDTO(CalendarDTO cDTO) {
		this.cDTO = cDTO;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "CalendarDTO [cDTO=" + cDTO + ", year=" + year + ", month=" + month + "]";
	}

	
	
	
}
