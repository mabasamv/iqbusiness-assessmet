package za.co.iqb.persistance.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "area_code")
    private String areaCode;

    public List<String> validate() {
        List<String> errorMessages = new ArrayList<>();
        if (StringUtils.isBlank(addressLine1)) {
            errorMessages.add("Address Line1 is required");
        }
        if (StringUtils.isBlank(city)) {
            errorMessages.add("City is required");
        }
        if (StringUtils.isBlank(province)) {
            errorMessages.add("Province is required");
        }
        if (StringUtils.isBlank(areaCode)) {
            errorMessages.add("Area Code is required");
        }

        return errorMessages;
    }

}
