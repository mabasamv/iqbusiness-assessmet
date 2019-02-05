package za.co.iqb.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import za.co.iqb.model.AddressDTO;
import za.co.iqb.model.ContactInformationDTO;
import za.co.iqb.model.EmployeeManagerDTO;
import za.co.iqb.persistance.model.Address;
import za.co.iqb.persistance.model.ContactInformation;
import za.co.iqb.persistance.model.EmployeeManager;

import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author vincent
 */
public class EmployeeManagerMappersTest {

    EmployeeManagerMappers mapper;

    @Before
    public void setUp() {
        mapper = Mappers.getMapper(EmployeeManagerMappers.class);
    }

    @Test
    public void employeeManagerDTOToEmployeeManagerEntity() {
        ContactInformation mappedContactInfo = mapper.contactInformationDTOToContactInformationEntity(new ContactInformationDTO(1L, "0123456789", "0123456789", "email@address.africa"));
        Address mappedAddress = mapper.addressDTOTOAddressEntity(new AddressDTO(1L, "Address1", "Address2", "Giyani", "Limpopo", "0826"));

        EmployeeManagerDTO employeeManagerDTO = EmployeeManagerDTO.builder()
                .employeeId(1L)
                .firstName("Makungu")
                .maidenName("Vincent")
                .lastName("Mabasa")
                .gender("Male")
                .contactInformation(mappedContactInfo)
                .address(mappedAddress)
                .build();

        EmployeeManager mappedEmployeeManager = mapper.employeeManagerDTOToEmployeeManagerEntity(employeeManagerDTO);

        Assert.assertThat(employeeManagerDTO.getFirstName(), is(mappedEmployeeManager.getFirstName()));
        Assert.assertThat(employeeManagerDTO.getLastName(), is(mappedEmployeeManager.getLastName()));
        Assert.assertThat(employeeManagerDTO.getContactInformation().getEmailAddress(), is(mappedContactInfo.getEmailAddress()));
        Assert.assertThat(employeeManagerDTO.getAddress().getCity(), is(mappedAddress.getCity()));
    }

    @Test
    public void contactInformationDTOToContactInformationEntity() {
        ContactInformationDTO contactInformationDTO = new ContactInformationDTO(1L, "0123456789", "0123456789", "email@address.africa");
        ContactInformation mappedContactInfo = mapper.contactInformationDTOToContactInformationEntity(contactInformationDTO);

        Assert.assertThat(contactInformationDTO.getMobileNumber(), is(mappedContactInfo.getMobileNumber()));
        Assert.assertThat(contactInformationDTO.getEmailAddress(), is(mappedContactInfo.getEmailAddress()));
    }

    @Test
    public void addressDTOTOAddressEntity() {
        AddressDTO addressDTO = new AddressDTO(1L, "Address1", "Address2", "Giyani", "Limpopo", "0826");
        Address mappedAddress = mapper.addressDTOTOAddressEntity(addressDTO);

        Assert.assertThat(addressDTO.getAddressLine1(), is(mappedAddress.getAddressLine1()));
        Assert.assertThat(addressDTO.getCity(), is(mappedAddress.getCity()));
    }

}