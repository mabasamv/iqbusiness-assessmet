package za.co.iqb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
public class ContactInformationDTO {
    private Long id;
    private String mobileNumber;
    private String telephoneNumber;
    private String emailAddress;
}
