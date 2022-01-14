package ru.posmanager.repository.user;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.user.User;

import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT u FROM User u ORDER BY u.email")
    List<User> getAll();

    @Query("SELECT u FROM User u " +
            "WHERE u.lastName LIKE :last_name% AND u.firstName LIKE :first_name% AND u.middleName LIKE :middle_name%")
    List<User> getAllFilteredByName(@Param("last_name") String lastName, @Param("first_name") String firstName,
                                    @Param("middle_name") String middleName);

    User getByEmail(String email);

    @Modifying
    @Transactional
    default void patch(Map<String, Object> patch, int id) {
        User stored = findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        BeanWrapper wrapper = new BeanWrapperImpl(stored);
        wrapper.setPropertyValues(patch);
    }
}
