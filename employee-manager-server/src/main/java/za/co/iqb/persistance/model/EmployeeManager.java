package za.co.iqb.persistance.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincent
 */
@Data
@Entity
@Table(name = "employee_details")
public class EmployeeManager {

    @Id
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "maiden_name")
    private String maidenName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private ContactInformation contactInformation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private Address address;

    public List<String> validate() {
        List<String> errorMessages = new ArrayList<>();
        if (StringUtils.isBlank(firstName)) {
            errorMessages.add("First name is required");
        }

        if (StringUtils.isBlank(lastName)) {
            errorMessages.add("Last name is required");
        }
        return errorMessages;
    }
}
