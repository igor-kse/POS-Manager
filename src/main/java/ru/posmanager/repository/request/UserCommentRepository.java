package ru.posmanager.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.request.UserComment;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserComment u WHERE u.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT u FROM UserComment u WHERE u.request.id = :request_id ORDER BY u.created")
    Optional<List<UserComment>> getAll(@Param("request_id") int requestId);
}
