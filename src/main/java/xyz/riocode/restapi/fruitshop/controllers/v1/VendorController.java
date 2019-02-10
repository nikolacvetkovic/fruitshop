package xyz.riocode.restapi.fruitshop.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorListDTO;
import xyz.riocode.restapi.fruitshop.services.VendorService;

@Api(description = "Vendor API")
@RestController
@RequestMapping(VendorController.VENDOR_BASE_URL)
public class VendorController {

    public static final String VENDOR_BASE_URL = "/api/v1/vendors/";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "View List of Vendors", notes = "There are some API Notes")
    @GetMapping
    public VendorListDTO listAllVendors(){
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @ApiOperation(value = "Get Vendor By Id")
    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create a new Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO newVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @ApiOperation(value = "Update a existing Vendor")
    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Update a Vendor property")
    @PatchMapping("/{id}")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation("Delete a Vendor")
    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
    }
}
