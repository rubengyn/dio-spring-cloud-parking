package br.com.ruben.cloudparking.controller;

import br.com.ruben.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.ruben.cloudparking.controller.dto.ParkingDTO;
import br.com.ruben.cloudparking.controller.mapper.ParkingMapper;
import br.com.ruben.cloudparking.exception.ParkingNotFoundException;
import br.com.ruben.cloudparking.model.Parking;
import br.com.ruben.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO parkingCreateDTO) {
        var parkingCreate = parkingMapper.toParkingCreate(parkingCreateDTO);
        var parkingUpdate = parkingService.update(id, parkingCreate);
        var result = parkingMapper.toParkingDTO(parkingUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}