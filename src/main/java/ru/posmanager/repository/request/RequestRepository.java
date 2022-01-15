package ru.posmanager.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.request.Request;
import ru.posmanager.domain.request.RequestStatus;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Request r WHERE r.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Request r ORDER BY r.created DESC, r.modified DESC")
    Optional<List<Request>> getAll();

    @Query("SELECT r FROM Request r WHERE r.title LIKE :title% ORDER BY r.created DESC")
    Optional<List<Request>> getAllFiltered(@Param("title") String title);

    @Query("SELECT r FROM Request r WHERE r.title LIKE :title% AND r.requestStatus = :request_status ORDER BY r.created DESC")
    Optional<List<Request>> getAllFilteredWithStatus(@Param("title") String title, @Param("request_status") RequestStatus requestStatus);
}
