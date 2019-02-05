package za.co.iqb.persistance.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import za.co.iqb.util.ValidationUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "contact_information")
public class ContactInformation {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    public List<String> validate() {
        List<String> errorMessages = new ArrayList<>();
        if (!ValidationUtil.validTelephone(mobileNumber)) {
            errorMessages.add("Mobile number is not valid telephone number");
        }
        if (!ValidationUtil.validTelephone(telephoneNumber)) {
            errorMessages.add("Telephone number is not valid telephone number");
        }
        if (!ValidationUtil.isValidEmail(emailAddress)) {
            errorMessages.add("email is not valid");
        }
        return errorMessages;
    }
}
