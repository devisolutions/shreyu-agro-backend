package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.SoftwareValidity;
import net.javaguides.springboot.repository.ValidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ValidityController {

    private final ValidityRepository validityRepository;

    @Autowired
    public ValidityController(ValidityRepository validityRepository) {
        this.validityRepository = validityRepository;
    }

    @GetMapping("/validity")
    private Boolean checkValidity() {
        List<SoftwareValidity> softwareValidityList = validityRepository.findAll();
        if (!softwareValidityList.isEmpty()) {
            LocalDateTime validityExpiryDate = softwareValidityList.get(0).getValidityExpiryDate();
            if (validityExpiryDate.isBefore(LocalDateTime.now())) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            return Boolean.FALSE;
        }
    }
}
