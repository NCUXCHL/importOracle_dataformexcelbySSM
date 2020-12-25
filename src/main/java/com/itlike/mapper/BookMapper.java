package com.itlike.mapper;

import com.itlike.domain.Book;
import java.util.List;

public interface BookMapper {
    int insert(Book record);

    List<Book> selectAll();
}