package com.wecodee.SpringBootPractice.common.utils;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import static java.util.Comparator.comparing;
import static org.joor.Reflect.on;
import net.minidev.json.JSONObject;

public class ListUtils {

	private static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
		if (page <= 0 || pageSize <= 0) {
			throw new IllegalArgumentException("invalid page size:" + pageSize);
		}

		int fromIndex = (page - 1) * pageSize;
		if (sourceList == null || sourceList.size() < fromIndex) {
			return Collections.emptyList();
		}
		// toIndex exclusive
		return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));

	}

	private static String getTotalPages(int totalResults, int pageSize) {
		int result = 0;
		if (totalResults % pageSize == 0) {
			result = totalResults / pageSize;
		} else {
			result = totalResults / pageSize + 1;
		}
		return String.valueOf(result);
	}

	public static <T> JSONObject getPaginatedList(List<T> sourceList, int pageNumber, int pageSize) {
		pageNumber = pageNumber + 1;
		List<T> paginatedList = getPage(sourceList, pageNumber, pageSize);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("currentPage", pageNumber);
		jsonObject.put("totalItems", sourceList.size());
		jsonObject.put("totalPages", getTotalPages(sourceList.size(), pageSize));
		jsonObject.put("items", paginatedList);
		return jsonObject;
	}

	public static <T, U extends Comparable<U>> void sort(final List<T> collection, final String fieldName,
			String direction) {
		if (direction.equals(EnumData.ASCENDING.getName())) {
			collection.sort(comparing(ob -> (U) on(ob).get(fieldName)));
		} else {
			collection.sort(comparing(ob -> (U) on(ob).get(fieldName)).reversed());
		}
	}
	


	public static <T> CriteriaQuery<T> setOrders(String sortBy, String direction, CriteriaQuery<T> criteria,
			Root<T> root, CriteriaBuilder builder) {
		if (direction.equals(EnumData.ASCENDING.getName())) {
			criteria.orderBy(builder.asc(root.get(sortBy)));
		} else {
			criteria.orderBy(builder.desc(root.get(sortBy)));
		}
		return criteria;
	}

}
