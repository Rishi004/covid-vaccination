package com.uni.covid.vaccination.responses;

import com.uni.covid.vaccination.enums.RestApiResponseStatus;

public class PaginatedContentResponse<T> extends ContentResponse<T> {
	public PaginatedContentResponse(RestApiResponseStatus restApiResponseStatus) {
		super(restApiResponseStatus);
	}

	private Pagination pagination;

	public PaginatedContentResponse(String key, T dto, RestApiResponseStatus restApiResponseStatus,
			Pagination pagination) {
		super(key, dto, restApiResponseStatus);
		this.pagination = pagination;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public static class Pagination {

		public Pagination(Integer pageNumber, Integer pageSize, Integer totalPages, Long totalRecords) {
			this.pageNumber = pageNumber;
			this.pageSize = pageSize;
			this.totalPages = totalPages;
			this.totalRecords = totalRecords;
		}

		private Integer pageNumber;

		private Integer pageSize;

		private Integer totalPages;

		private Long totalRecords;

		public Integer getPageNumber() {
			return pageNumber;
		}

		public void setPageNumber(Integer pageNumber) {
			this.pageNumber = pageNumber;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public Integer getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(Integer totalPages) {
			this.totalPages = totalPages;
		}

		public Long getTotalRecords() {
			return totalRecords;
		}

		public void setTotalRecords(Long totalRecords) {
			this.totalRecords = totalRecords;
		}

	}
}
