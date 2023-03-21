package com.wecodee.SpringBootPractice.admin.requestDTO;

public class SearchFilterDTO<T> {

	private T filterFieldValues;

	private int pageNumber;

	private int pageSize;

	private String sortBy;

	private String direction;

	public T getFilterFieldValues() {
		return filterFieldValues;
	}

	public void setFilterFieldValues(T filterFieldValues) {
		this.filterFieldValues = filterFieldValues;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "SearchFilterDTO [filterFieldValues=" + filterFieldValues + ", pageNumber=" + pageNumber + ", pageSize="
				+ pageSize + ", sortBy=" + sortBy + ", direction=" + direction + "]";
	}

}
