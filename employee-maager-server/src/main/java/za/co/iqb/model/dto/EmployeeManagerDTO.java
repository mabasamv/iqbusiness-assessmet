package za.co.iqb.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author vincent
 */
@Data
@Builder
public class EmployeeManagerDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
}
