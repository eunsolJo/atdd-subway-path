package atdd.station;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StationController {

    private final StationRepository stationRepository;

    public StationController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @PostMapping("/stations")
    public ResponseEntity createStation(@RequestBody Station station){
        Station save = stationRepository.save(station);
        System.out.println("TEST");
        return ResponseEntity
                .created(URI.create("/stations/"+save.getId()))
                .body(save);
    }

    @GetMapping("/stations")
    public ResponseEntity getStations(){
        List<Station> stations = stationRepository.findAll();
        return ResponseEntity
                .ok()
                .body(stations);

    }

    @GetMapping("/station/{id}")
    public ResponseEntity getStationById(@PathVariable Integer id){
        Optional<Station> station = stationRepository.findById(id);
        System.out.println("TEST");
        return ResponseEntity
                .ok()
                .body(station);
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity deleteStationById(@PathVariable Integer id){
        stationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}