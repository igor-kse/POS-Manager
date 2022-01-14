package ru.posmanager.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.Affiliate;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AffiliateRepository extends JpaRepository<Affiliate, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Affiliate a WHERE a.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT a FROM Affiliate a ORDER BY a.name")
    Optional<List<Affiliate>> getAll();

    Optional<Affiliate> getByName(@Param("name") String name);
}
