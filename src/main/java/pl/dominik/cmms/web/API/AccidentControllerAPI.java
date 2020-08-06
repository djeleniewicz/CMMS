package pl.dominik.cmms.web.API;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.requests.Accident;
import pl.dominik.cmms.repository.equipment.AccidentRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class AccidentControllerAPI {
    private final AccidentRepository accidentRepository;

    public AccidentControllerAPI(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    @GetMapping("/accident")
    public List<Accident> getAllAccidents() {
        List<Accident> accidents = accidentRepository.findAll();
        return accidents;
    }


    @GetMapping("/accident/{id}")
    public ResponseEntity<Accident> getAccidentById(@PathVariable(value = "id") Long accidentId) {
        Accident accident = accidentRepository.findById(accidentId);
        return ResponseEntity.ok(accident);
    }

    @PostMapping("/add-accident")
    public Accident createAccident(@Valid @RequestBody Accident accident) {
//        equipment.setCreated(new Timestamp(now()));
        return accidentRepository.save(accident);
    }

    @PutMapping("/update-accident/{id}")
    public ResponseEntity<Accident> updateUser(
            @PathVariable(value = "id") Long accidentId, @Valid @RequestBody Accident accidentDetails) {
        Accident accident = accidentRepository.findById(accidentId);

        accident.setNote(accidentDetails.getNote());
        final Accident updatedAccident = accidentRepository.save(accident);
        return ResponseEntity.ok(updatedAccident);
    }
}
