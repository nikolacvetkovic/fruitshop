package xyz.riocode.restapi.fruitshop.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.riocode.restapi.fruitshop.api.v1.model.VendorDTO;
import xyz.riocode.restapi.fruitshop.controllers.RestResponseEntityExceptionHandler;
import xyz.riocode.restapi.fruitshop.services.VendorService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    static final String NAME = "Huawei";
    static final String VENDOR_URL = VendorController.VENDOR_BASE_URL + "1";

    @Mock
    VendorService vendorService;

    MockMvc mockMvc;

    @InjectMocks
    VendorController vendorController;

    VendorDTO vendorDTO1;

    ObjectMapper om;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
        vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME);
        vendorDTO1.setVendorUrl(VENDOR_URL);
        om = new ObjectMapper();
    }

    @Test
    public void testListAllVendors() throws Exception {
        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setName("Samsung");
        vendorDTO2.setVendorUrl(VendorController.VENDOR_BASE_URL + "2");

        List<VendorDTO> vendorDTOs = Arrays.asList(vendorDTO1, vendorDTO2);

        when(vendorService.getAllVendors()).thenReturn(vendorDTOs);

        mockMvc.perform(get(VendorController.VENDOR_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)))
                .andExpect(jsonPath("$.vendors[0].name", equalTo(NAME)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);

        mockMvc.perform(get(VendorController.VENDOR_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testNewVendor() throws Exception {
        when(vendorService.createVendor(any(VendorDTO.class))).thenReturn(vendorDTO1);

        mockMvc.perform(post(VendorController.VENDOR_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(vendorDTO1))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendorUrl", equalTo(VENDOR_URL)));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO1);

        mockMvc.perform(put(VendorController.VENDOR_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(vendorDTO1))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendorUrl", equalTo(VENDOR_URL)));
    }

    @Test
    public void testPatchVendorName() throws Exception {
        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTO1);

        mockMvc.perform(put(VendorController.VENDOR_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(vendorDTO1))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete(VendorController.VENDOR_BASE_URL + "1"))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendor(anyLong());
    }
}