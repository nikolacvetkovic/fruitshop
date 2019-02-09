package xyz.riocode.restapi.fruitshop.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorListDTO;
import xyz.riocode.restapi.fruitshop.services.VendorService;

@RestController
@RequestMapping(VendorController.VENDOR_BASE_URL)
public class VendorController {

    public static final String VENDOR_BASE_URL = "/api/v1/vendors/";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public VendorListDTO listAllVendors(){
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO newVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
    }
}
