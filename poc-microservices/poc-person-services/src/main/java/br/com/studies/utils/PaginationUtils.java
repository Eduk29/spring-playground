package br.com.studies.utils;

public abstract class PaginationUtils {

	public static boolean validatePageNumber(Integer pageNumber) {
		return pageNumber != null && pageNumber != 0;
	}
	
	public static boolean validatePageSize(Integer pageSize) {
		return pageSize != null && pageSize != 0;
	}
	
	public static Integer setDefaultPageNumber() {
		return 0;
	}
	
	public static Integer setDefaultPageSize() {
		return 10;
	}
}
