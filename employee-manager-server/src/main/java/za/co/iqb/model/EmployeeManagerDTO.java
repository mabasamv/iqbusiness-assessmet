package za.co.iqb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import za.co.iqb.persistance.model.Address;
import za.co.iqb.persistance.model.ContactInformation;

import java.util.Date;

/**
 *
 * @author vincent
 */
@Data
@Builder
@AllArgsConstructor
public class EmployeeManagerDTO {
    private Long employeeId;
    private String firstName;
    private String maidenName;
    private String lastName;
    private String gender;
    private ContactInformation contactInformation;
    private Address address;
}
