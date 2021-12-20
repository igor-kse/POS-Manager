package ru.posmanager.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.model.bank.BankDevice;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface BankDeviceRepository extends JpaRepository<BankDevice, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BankDevice b WHERE b.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT b FROM BankDevice b ORDER BY b.affiliate.name, b.device.vendor.name, b.device.model")
    List<BankDevice> getAll();
}
