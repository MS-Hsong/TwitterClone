package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Post;
import com.example.demo.models.User;

public interface PostRepository extends CrudRepository<Post, Long>{

	List<Post> findByPostedBy_Id(long id);


}