package com.springboot.bookingcare.Repository;

import com.springboot.bookingcare.Entity.PostEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
@Repository
public class PostRepositoryImpl implements PostRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<PostEntity> PostsPublic() {
        // lấy 10 bài viết gần nhâ
        TypedQuery<PostEntity> query=entityManager.createQuery("select p from PostEntity p order by p.createAt desc", PostEntity.class);
        query.setMaxResults(10);
        // chạy lệnh và gán lại kết quả
        List<PostEntity> results=query.getResultList();
        // sắp xếp lại kết quả
        results.sort(Comparator.comparing(PostEntity::getLikePost).reversed());
        return results;
    }
}
