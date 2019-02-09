package xyz.riocode.restapi.fruitshop.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorDTO {

    private String name;
    private String vendorUrl;
}
