package br.com.studies.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomPage<T> {
	private List<T> content;
	private Integer totalPages;
	private Long totalElements;
	private Integer pageSize;
	private Integer pageNumber;
	private String timestamp;

	public CustomPage(Page<T> page) {
		this.content = (List<T>) page.getContent();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.pageSize = page.getSize();
		this.pageNumber = page.getNumber();
		this.timestamp = new Date().toInstant().toString();
	}
	
	public CustomPage(T input) {
		List<T> inputList = new ArrayList<T>();
		inputList.add(input);
		
		this.content = (List<T>) inputList;
		this.totalElements = (long) 1;
		this.totalPages = 1;
		this.pageSize = 1;
		this.pageNumber = 1;
		this.timestamp = new Date().toInstant().toString();
	}
}
