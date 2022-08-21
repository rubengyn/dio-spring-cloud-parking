package br.com.ruben.cloudparking.controller;

import br.com.ruben.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.ruben.cloudparking.controller.dto.ParkingDTO;
import br.com.ruben.cloudparking.controller.mapper.ParkingMapper;
import br.com.ruben.cloudparking.model.Parking;
import br.com.ruben.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public ResponseEntity< List<ParkingDTO> > findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity< ParkingDTO > findAll(String id){
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity< ParkingDTO > create(@RequestBody ParkingCreateDTO dto){
        System.out.println(dto.getColor());
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        System.out.println(parkingCreate.getColor());
        var parking = parkingService.create(parkingCreate);
        System.out.println(parking.getColor());
        var result = parkingMapper.toParkingDTO(parking);
        System.out.println(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
