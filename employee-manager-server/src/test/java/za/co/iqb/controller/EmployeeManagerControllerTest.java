package za.co.iqb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import za.co.iqb.model.AddressDTO;
import za.co.iqb.model.ContactInformationDTO;
import za.co.iqb.model.EmployeeManagerDTO;
import za.co.iqb.persistance.model.EmployeeManager;
import za.co.iqb.service.EmployeeManagerService;
import za.co.iqb.util.EmployeeManagerMappers;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author vincent
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeManagerController.class)
public class EmployeeManagerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EmployeeManagerService service;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldSaveEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employee-manager/save-or-update")
                .content(asJsonString(buildEmployeeManager(1L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldGetEmployeeWithID() throws Exception {
        when(service.getEmployeeById(1L)).thenReturn(buildEmployeeManager(1L));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee-manager/find-employee")
                .param("employeeId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Makungu"))
                .andDo(print());
    }

    @Test
    public void shouldGetEmployeeWithIDNotFound() throws Exception {
       when(service.getEmployeeById(1L)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee-manager/find-employee")
                .param("employeeId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldGetAllEmployees() throws Exception {
        when(service.getAllEmployees()).thenReturn((Arrays.asList(buildEmployeeManager(1L), buildEmployeeManager(2L))));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee-manager/find-all-employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value("Makungu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].employeeId").value(2))
                .andDo(print());
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
                .addressLine1("Line 1 Address")
                .addressLine2("Line 2 Address")
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}