package usermain;

public class RangeDTO {

	private int startNum;
	private int endNum;
	private String searchType;
	private String keyword;
	
	public RangeDTO() {
		super();
	}
	
	public RangeDTO(int startNum, int endNum, String searchType, String keyword) {
		super();
		this.startNum = startNum;
		this.endNum = endNum;
		this.searchType = searchType;
		this.keyword = keyword;
		
	}
	
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	@Override
	public String toString() {
		return "RangeDTO [startNum=" + startNum + ", endNum=" + endNum + ", searchType=" + searchType + ", keyword="
				+ keyword + "]";
	}

	
	
	
	
	
	
}
