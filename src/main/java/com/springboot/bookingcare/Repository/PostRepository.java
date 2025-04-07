package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.PostEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,Integer>,PostRepositoryCustom {
    void deleteByPostId(int postId);
    PostEntity findByPostId(int postId);
    @Query("SELECT p.postId FROM PostEntity p ORDER BY p.likePost DESC, p.createAt DESC")
    List<Integer> findLatestPostIds(Pageable pageable);
    @Query("select p from PostEntity p where p.postId in :ids order by p.likePost desc")
    List<PostEntity> findListPost(@Param("ids") List<Integer> ids);// hiển thị trên trang chủ tối đa 10
    @Query("SELECT p FROM PostEntity p where p.doctor.doctorId = :doctorId  ORDER BY p.createAt DESC")
    Page<PostEntity> findPostsPageable(@Param("doctorId") int doctorId, Pageable pageable);

}
