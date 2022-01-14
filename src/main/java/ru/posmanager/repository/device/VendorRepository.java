package ru.posmanager.repository.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.device.Vendor;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vendor v WHERE v.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Vendor v ORDER BY v.name")
    Optional<List<Vendor>> getAll();

    @Query("SELECT v FROM Vendor v WHERE v.name LIKE :vendor_name%")
    Optional<List<Vendor>> getAllFilteredByName(@Param("vendor_name") String vendorName);
}