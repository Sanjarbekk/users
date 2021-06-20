package com.example.employee.resource;


import com.example.employee.model.Phone;
import com.example.employee.resource.errors.BadRequestAlertException;
import com.example.employee.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PhoneResource {

    private final Logger log = LoggerFactory.getLogger(PhoneResource.class);

    private static final String ENTITY_NAME = "phone";

    private final PhoneService phoneService;

    public PhoneResource(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping("/phone")
    public ResponseEntity<Phone> createPhone(@Valid @RequestBody Phone phone) {
        log.debug("REST request to save Phone : {}", phone);
        if (phone.getId() != null) {
            throw new BadRequestAlertException("A new phone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Phone result =phoneService.save(phone);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/phone/{id}")
    public ResponseEntity<Phone> getPhone(@PathVariable Long id) {
        log.debug("REST request to get Phone : {}", id);
        Optional<Phone> result = phoneService.findOne(id);
        if(result.isEmpty()) {
            throw  new BadRequestAlertException("Not found", ENTITY_NAME, "idexists");
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> getAllPhone() {
        List<Phone> result = phoneService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        log.debug("Delete by Id : {}", id);
        phoneService.delete(id);
        return ResponseEntity.ok().build();
    }
}
