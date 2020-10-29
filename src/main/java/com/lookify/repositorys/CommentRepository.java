package com.lookify.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	@Query(value = "SELECT * FROM comments WHERE user_id = ?1 AND song_id = ?2", nativeQuery = true)
	List<Comment> matchingReviews(Long user_id, Long song_id);
}
