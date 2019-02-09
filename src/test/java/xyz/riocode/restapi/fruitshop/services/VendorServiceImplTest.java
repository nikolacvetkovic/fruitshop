package xyz.riocode.restapi.fruitshop.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.VendorMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;
import xyz.riocode.restapi.fruitshop.controllers.v1.VendorController;
import xyz.riocode.restapi.fruitshop.domain.Vendor;
import xyz.riocode.restapi.fruitshop.repository.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    static final Long ID = 1L;
    static final String NAME = "Huawei";
    static final String VENDOR_URL = VendorController.VENDOR_BASE_URL + "1";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    Vendor vendor1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
        vendor1 = new Vendor();
        vendor1.setId(ID);
        vendor1.setName(NAME);
    }

    @Test
    public void testGetAllVendors() {
        Vendor vendor2 = new Vendor();

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        List<VendorDTO> vendorDTOs = vendorService.getAllVendors();

        assertNotNull(vendorDTOs);
        assertEquals(2, vendorDTOs.size());
    }

    @Test
    public void testGetVendorById() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(VENDOR_URL, vendorDTO.getVendorUrl());
    }

    @Test
    public void testCreateVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor1.getName());
        vendorDTO.setVendorUrl(VENDOR_URL);

        when(vendorRepository.save(any(Vendor.class))).thenReturn((vendor1));

        VendorDTO savedVendorDTO = vendorService.createVendor(vendorDTO);

        assertNotNull(savedVendorDTO);
        assertEquals(NAME, savedVendorDTO.getName());
        assertEquals(VENDOR_URL, savedVendorDTO.getVendorUrl());
    }

    @Test
    public void testUpdateVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor1.getName());
        vendorDTO.setVendorUrl(VENDOR_URL);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor1);

        VendorDTO updatedVendor = vendorService.updateVendor(ID, vendorDTO);

        assertNotNull(updatedVendor);
        assertEquals(NAME, updatedVendor.getName());
        assertEquals(VENDOR_URL, updatedVendor.getVendorUrl());
    }

    @Test
    public void testPatchVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor1.getName());
        vendorDTO.setVendorUrl(VENDOR_URL);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor1);

        VendorDTO updatedVendor = vendorService.patchVendor(ID, vendorDTO);

        assertNotNull(updatedVendor);
        assertEquals(NAME, updatedVendor.getName());
        assertEquals(VENDOR_URL, updatedVendor.getVendorUrl());
    }

    @Test
    public void testDeleteVendor() {

        vendorService.deleteVendor(ID);

        verify(vendorRepository).deleteById(anyLong());
    }
}