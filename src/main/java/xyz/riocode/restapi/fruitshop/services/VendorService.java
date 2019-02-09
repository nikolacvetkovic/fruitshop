package xyz.riocode.restapi.fruitshop.services;

import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService{

    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    void deleteVendor(Long id);

}
