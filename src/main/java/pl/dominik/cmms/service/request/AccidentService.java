package pl.dominik.cmms.service.request;

import pl.dominik.cmms.entity.requests.Accident;

import java.util.List;

public interface AccidentService {

    Accident findById(Long id);

    List<Accident> findAllByCreatedBy(Long id);
}
