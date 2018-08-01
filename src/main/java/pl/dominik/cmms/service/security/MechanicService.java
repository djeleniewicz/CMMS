package pl.dominik.cmms.service.security;

import pl.dominik.cmms.entity.mechanics.Mechanic;


public interface MechanicService {

    Mechanic findByName(String name);
    void saveMechanic(Mechanic mechanic);
}