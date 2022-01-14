package ru.posmanager.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.Department;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Department d WHERE d.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Department d ORDER BY d.name")
    List<Department> getAll();

    Department getByName(@Param("name") String name);
}
