package br.com.studies.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public abstract class PaginationUtils {
	
	public static <T> Page<T> singleItemPage(T item) {
		return new PageImpl<>(
			Collections.singletonList(item),
			PageRequest.of(0, 1),
			1
		);
	}
	
	public static <T> Page<T> emptyPage() {
		return new PageImpl<>(
			Collections.emptyList(),
			PageRequest.of(0, 1),
			0
		);
	}
	
	public static <T> Page<T> listAsPage(List<T> list, Pageable pageable) {
		if (list == null || list.isEmpty()) {
			return new PageImpl<>(Collections.emptyList(), pageable, 0);
		}

		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), list.size());

		List<T> sublist = list.subList(start, end);
		return new PageImpl<>(sublist, pageable, list.size());
	}

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
