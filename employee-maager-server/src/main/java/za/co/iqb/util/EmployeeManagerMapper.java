package za.co.iqb.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import za.co.iqb.model.dto.EmployeeManagerDTO;
import za.co.iqb.persistance.model.EmployeeManager;

/**
 *
 * @author vincent
 */
@Mapper
public interface EmployeeManagerMapper {
     @Mappings({
            @Mapping(constant = "0", target = "id"),
    })
    EmployeeManager employeeManagerDTOToEmployeeManagerEntity(EmployeeManagerDTO employeeManagerDTO);
    
}
