package br.com.studies.utils;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaginationUtilsTest {

	 @Test
	    void testSingleItemPage() {
	        String item = "Test";
	        Page<String> page = PaginationUtils.singleItemPage(item);

	        assertEquals(1, page.getTotalElements());
	        assertEquals(1, page.getContent().size());
	        assertEquals("Test", page.getContent().get(0));
	        assertEquals(0, page.getNumber());
	        assertEquals(1, page.getSize());
	    }

	    @Test
	    void testEmptyPage() {
	        Page<String> page = PaginationUtils.emptyPage();

	        assertEquals(0, page.getTotalElements());
	        assertTrue(page.getContent().isEmpty());
	        assertEquals(0, page.getNumber());
	        assertEquals(1, page.getSize());
	    }

	    @Test
	    void testListAsPage_withContent() {
	        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
	        Pageable pageable = PageRequest.of(1, 2); // página 1, tamanho 2 → pega "C", "D"

	        Page<String> page = PaginationUtils.listAsPage(list, pageable);

	        assertEquals(5, page.getTotalElements());
	        assertEquals(2, page.getContent().size());
	        assertEquals("C", page.getContent().get(0));
	        assertEquals("D", page.getContent().get(1));
	    }

	    @Test
	    void testListAsPage_withEmptyList() {
	        Pageable pageable = PageRequest.of(0, 2);
	        Page<String> page = PaginationUtils.listAsPage(Collections.emptyList(), pageable);

	        assertEquals(0, page.getTotalElements());
	        assertTrue(page.getContent().isEmpty());
	    }

	    @Test
	    void testListAsPage_withNullList() {
	        Pageable pageable = PageRequest.of(0, 2);
	        Page<String> page = PaginationUtils.listAsPage(null, pageable);

	        assertEquals(0, page.getTotalElements());
	        assertTrue(page.getContent().isEmpty());
	    }

	    @Test
	    void testValidatePageNumber() {
	        assertTrue(PaginationUtils.validatePageNumber(1));
	        assertFalse(PaginationUtils.validatePageNumber(0));
	        assertFalse(PaginationUtils.validatePageNumber(null));
	    }

	    @Test
	    void testValidatePageSize() {
	        assertTrue(PaginationUtils.validatePageSize(5));
	        assertFalse(PaginationUtils.validatePageSize(0));
	        assertFalse(PaginationUtils.validatePageSize(null));
	    }

	    @Test
	    void testSetDefaultPageNumber() {
	        assertEquals(0, PaginationUtils.setDefaultPageNumber());
	    }

	    @Test
	    void testSetDefaultPageSize() {
	        assertEquals(10, PaginationUtils.setDefaultPageSize());
	    }
}
