package xyz.riocode.restapi.fruitshop.services;

import org.springframework.stereotype.Service;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.VendorMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;
import xyz.riocode.restapi.fruitshop.controllers.v1.VendorController;
import xyz.riocode.restapi.fruitshop.domain.Vendor;
import xyz.riocode.restapi.fruitshop.exceptions.ResourceNotFoundException;
import xyz.riocode.restapi.fruitshop.repository.VendorRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = new VendorDTO();
                    vendorDTO.setName(vendor.getName());
                    vendorDTO.setVendorUrl(VendorController.VENDOR_BASE_URL + vendor.getId());
                    return vendorDTO;
                })
        .collect(toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl(VendorController.VENDOR_BASE_URL + vendor.getId());

        return vendorDTO;
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendotDTOToVendor(vendorDTO);

        return saveVendorAndReturn(vendor);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendotDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveVendorAndReturn(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendotDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveVendorAndReturn(vendor);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveVendorAndReturn(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setVendorUrl(VendorController.VENDOR_BASE_URL + savedVendor.getId());

        return vendorDTO;
    }
}
