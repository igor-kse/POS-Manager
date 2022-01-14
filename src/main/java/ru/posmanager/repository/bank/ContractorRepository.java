package ru.posmanager.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.Contractor;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Contractor c WHERE c.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT c FROM Contractor c ORDER BY c.name")
    Optional<List<Contractor>> getAll();

    Optional<Contractor> getByUnp(String unp);
}
