package za.co.iqb.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.iqb.model.dto.EmployeeManagerDTO;
import za.co.iqb.persistance.model.EmployeeManager;
import za.co.iqb.persistance.repository.EmployeeManagerRepository;
import za.co.iqb.util.EmployeeManagerMapper;

/**
 *
 * @author vincent
 */
@Service
public class EmployeeManagerService {
    
    @Autowired
    private EmployeeManagerRepository repository;
    private EmployeeManagerMapper mapper;
    
    public EmployeeManagerService() {
        this.mapper = Mappers.getMapper(EmployeeManagerMapper.class);
    }
    
    public EmployeeManager getEmployeeById(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
    
    public Iterable<EmployeeManager> getAllEmployees() {
        return repository.findAll();
    }

    public void saveEmployee(EmployeeManagerDTO employeeManagerDTO) {
        EmployeeManager employee = mapper.employeeManagerDTOToEmployeeManagerEntity(employeeManagerDTO);
        repository.save(employee);
    }
}
