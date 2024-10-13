package com.example.cnd.common.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.beans.ConstructorProperties;


/**
 * File: com.example.cnd.common.base
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Paging base </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/10/2024</span>
 **/
@Data
public class PagingBase {

    @JsonProperty("page_number")
    private int pageNumber; // Current page number

    @JsonProperty("page_size")
    private int pageSize;   // Number of items per page

    @JsonProperty("page_items")
    private int totalItems; // Total number of items

    @JsonProperty("page_pages")
    private int totalPages;  // Total number of pages

    // Constructor with default values
    public PagingBase() {
        this.pageNumber = 1; // Default page number
        this.pageSize = 100; // Default page size
        this.totalItems = 0; // Default total items
        this.totalPages = 0; // Default total pages
    }

    @ConstructorProperties({"page_number", "page_size", "page_items"})
    public PagingBase(int pageNumber, int pageSize, int totalItems) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }

    public boolean hasNextPage() {
        return pageNumber < totalPages;
    }

    public boolean hasPreviousPage() {
        return pageNumber > 1;
    }
}

