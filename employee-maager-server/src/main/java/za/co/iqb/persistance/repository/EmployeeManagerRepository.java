package za.co.iqb.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.iqb.persistance.model.EmployeeManager;

/**
 *
 * @author vincent
 */
public interface EmployeeManagerRepository extends CrudRepository<EmployeeManager, Long> {
    EmployeeManager findByEmployeeId(Long employeeId);
    Iterable<EmployeeManager> findAll(); 
}
