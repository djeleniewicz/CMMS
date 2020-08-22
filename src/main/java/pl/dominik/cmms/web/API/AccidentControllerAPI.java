package pl.dominik.cmms.web.API;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.requests.Accident;
import pl.dominik.cmms.repository.equipment.StatusRepository;
import pl.dominik.cmms.repository.requests.AccidentRepository;
import pl.dominik.cmms.repository.security.UserRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RestController
@RequestMapping("/api/v1")
public class AccidentControllerAPI {

    private final AccidentRepository accidentRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public AccidentControllerAPI(AccidentRepository accidentRepository, UserRepository userRepository,
                                 StatusRepository statusRepository) {
        this.accidentRepository = accidentRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping("/accidents")
    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    @GetMapping("/accident/{id}")
    public ResponseEntity<Accident> getAccidentById(@PathVariable(value = "id") Long accidentId) {
        Accident accident = accidentRepository.findById(accidentId);
        return ResponseEntity.ok(accident);
    }

    @PostMapping("/add-accident-user")
    public Accident createAccident(@Valid @RequestBody Accident accident) {
        accident.setCreated(LocalDateTime.now());
        accident.setReportedBy(userRepository.findById((long) 9));
        accident.setStatus(statusRepository.findByStatus("New"));
        return accidentRepository.save(accident);
    }

    @PutMapping("/take-accident/{id}")
    public ResponseEntity<Accident> takeAccident(
            @PathVariable(value = "id") Long accidentId, @Valid @RequestBody Accident accidentDetails) {
        Accident accident = accidentRepository.findById(accidentId);
        accident.setNote(accidentDetails.getNote());
        accident.setStatus(statusRepository.findByStatus("In progress"));
        final Accident updatedAccident = accidentRepository.save(accident);
        return ResponseEntity.ok(updatedAccident);
    }

    @PutMapping("/end-accident/{id}")
    public ResponseEntity<Accident> endAccident(
            @PathVariable(value = "id") Long accidentId, @Valid @RequestBody Accident accidentDetails) {
        Accident accident = accidentRepository.findById(accidentId);
        accident.setNote(accidentDetails.getNote());
        accident.setStatus(statusRepository.findByStatus("Closed"));
        final Accident updatedAccident = accidentRepository.save(accident);
        return ResponseEntity.ok(updatedAccident);
    }

    @PostMapping("/update-accident/{id}")
    public ResponseEntity<Accident> updateAccident(
            @PathVariable(value = "id") Long accidentId, @Valid @RequestBody Accident accidentDetails) {
        Accident accident = accidentRepository.findById(accidentId);
        accident.setNote(accidentDetails.getNote());
        final Accident updatedAccident = accidentRepository.save(accident);
        return ResponseEntity.ok(updatedAccident);
    }

    @GetMapping("/user/accidents")
    public List<Accident> getAllAccidentsCreatedByUser() {
        long id = 23;
        return accidentRepository.findAllByCreatedBy(id);
    }

    @PostMapping("/user/add-accident")
    public Accident createAccidentsCreatedByUser(@Valid @RequestBody Accident accident) {
        accident.setCreated(LocalDateTime.now());
        accident.setReportedBy(userRepository.findById((long) 9));
        accident.setStatus(statusRepository.findByStatus("New"));
        return accidentRepository.save(accident);
    }

    @GetMapping("/user/update-accident/{id}")
    public Accident getAccidentCreatedByUser(@PathVariable(value = "id") Long accidentId) {
        return accidentRepository.findById(accidentId);
    }

    @PutMapping("/user/update-accident/{id}")
    public ResponseEntity<Accident> updateAccidentUser(
            @PathVariable(value = "id") Long accidentId, @Valid @RequestBody Accident accidentDetails) {
        Accident accident = accidentRepository.findById(accidentId);
        accident.setNote(accidentDetails.getNote());
        accident.setTitle(accidentDetails.getTitle());
        accident.setPriority(accidentDetails.getPriority());
        final Accident updatedAccident = accidentRepository.save(accident);
        return ResponseEntity.ok(accidentRepository.save(updatedAccident));
    }

}
