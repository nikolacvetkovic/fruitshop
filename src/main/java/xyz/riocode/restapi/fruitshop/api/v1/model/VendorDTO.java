package xyz.riocode.restapi.fruitshop.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorDTO {

    @ApiModelProperty(value = "Name of the Vendor", required = true)
    private String name;
    private String vendorUrl;
}
