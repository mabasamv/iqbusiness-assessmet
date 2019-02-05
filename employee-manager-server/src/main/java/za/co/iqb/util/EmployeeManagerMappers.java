package za.co.iqb.util;

import org.mapstruct.Mapper;
import za.co.iqb.model.AddressDTO;
import za.co.iqb.model.ContactInformationDTO;
import za.co.iqb.model.EmployeeManagerDTO;
import za.co.iqb.persistance.model.Address;
import za.co.iqb.persistance.model.ContactInformation;
import za.co.iqb.persistance.model.EmployeeManager;

/**
 *
 * @author vincent
 */
@Mapper
public interface EmployeeManagerMappers {
    EmployeeManager employeeManagerDTOToEmployeeManagerEntity(EmployeeManagerDTO employeeManagerDTO);

    ContactInformation contactInformationDTOToContactInformationEntity(ContactInformationDTO contactInformationDTO);

    Address addressDTOTOAddressEntity(AddressDTO addressDTO);
}
