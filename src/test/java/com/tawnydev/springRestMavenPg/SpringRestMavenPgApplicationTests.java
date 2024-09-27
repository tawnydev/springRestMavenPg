package com.tawnydev.springRestMavenPg;

import com.tawnydev.springRestMavenPg.controllers.ReservationController;
import com.tawnydev.springRestMavenPg.entities.Customer;
import com.tawnydev.springRestMavenPg.entities.Hotel;
import com.tawnydev.springRestMavenPg.entities.Reservation;
import com.tawnydev.springRestMavenPg.repositories.CustomerRepository;
import com.tawnydev.springRestMavenPg.repositories.HotelRepository;
import com.tawnydev.springRestMavenPg.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringRestMavenPgApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Autowired
	private ReservationRepository reservationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private void initReservation(){
        Hotel h = new Hotel("test");
        h.setId(1L);
        hotelRepository.save(h);
        Customer c = new Customer("Thomas","Dupont");
        c.setId(1L);
        customerRepository.save(c);
        Reservation res = new Reservation(10, h, c);
        res.setId(1L);
        res.setCoutRestaurant(0.0);
        res.setOptionPetitDej(false);
        res.setOptionSpa(true);
        reservationRepository.save(res);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGetReservation() throws Exception {
        initReservation();
        mockMvc.perform(get("/reservations")).andDo(print()).andExpect(status().isOk());
    }
}
