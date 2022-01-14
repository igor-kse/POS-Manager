package ru.posmanager.repository.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.device.Device;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Device d WHERE d.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Device d ORDER BY d.deviceType, d.vendor.name, d.model")
    List<Device> getAll();
}
