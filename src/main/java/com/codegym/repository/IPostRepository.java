package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
}
