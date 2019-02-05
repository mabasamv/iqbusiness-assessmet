package za.co.iqb;

import org.apache.commons.collections4.IterableUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.iqb.model.AddressDTO;
import za.co.iqb.model.ContactInformationDTO;
import za.co.iqb.model.EmployeeManagerDTO;
import za.co.iqb.persistance.model.EmployeeManager;
import za.co.iqb.persistance.repository.EmployeeManagerRepository;
import za.co.iqb.util.EmployeeManagerMappers;

/**
 *
 * @author vincent
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = za.co.iqb.EmployeeManagerApplication.class)
public class EmployeeManagerRepositoryTest {

    @Autowired
    EmployeeManagerRepository repository;

    @After
    public void clearDb(){
        repository.deleteAll();
    }

    @Test
    public void shouldRetrieveEmployeeUsingId() {
        EmployeeManager employee = buildEmployeeManager(1L);

        repository.save(employee);

        EmployeeManager employeeManager = repository.findByEmployeeId(1L);
        Assert.assertNotNull(employeeManager);
    }

    @Test
    public void shouldRetrieveAllEmployees() {
        EmployeeManager employee1 = buildEmployeeManager(1110L);
        repository.save(employee1);

        EmployeeManager employee2 = buildEmployeeManager(1212L);
        repository.save(employee2);

        EmployeeManager employee3 = buildEmployeeManager(1333L);
        repository.save(employee3);

        Iterable<EmployeeManager> employees = repository.findAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(IterableUtils.size(employees), 3);
    }

    private EmployeeManager buildEmployeeManager(Long id) {
        EmployeeManagerMappers mapper = Mappers.getMapper(EmployeeManagerMappers.class);

        ContactInformationDTO contactInformationDTO = ContactInformationDTO.builder()
                .id(id)
                .mobileNumber("0123456789")
                .telephoneNumber("0123456789")
                .emailAddress("email@address.com")
                .build();

        AddressDTO addressDTO = AddressDTO.builder()
                .id(id)
                .addressLine1("Line1 Address")
                .addressLine2("Line2 Address")
                .city("Johannesburg")
                .province("Gauteng")
                .areaCode("2001")
                .build();

        EmployeeManagerDTO employeeManagerDTO = EmployeeManagerDTO.builder()
                .employeeId(id)
                .firstName("Makungu")
                .maidenName("Vincent")
                .lastName("Mabasa")
                .gender("Male")
                .contactInformation(mapper.contactInformationDTOToContactInformationEntity(contactInformationDTO))
                .address(mapper.addressDTOTOAddressEntity(addressDTO))
                .build();

        return mapper.employeeManagerDTOToEmployeeManagerEntity(employeeManagerDTO);
    }
}