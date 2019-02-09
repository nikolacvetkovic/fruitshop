package xyz.riocode.restapi.fruitshop.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorListDTO {

    private List<VendorDTO> vendors;
}
