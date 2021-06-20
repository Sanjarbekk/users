package com.example.employee.service;

import com.example.employee.model.Phone;
import com.example.employee.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PhoneService {

    private final Logger log = LoggerFactory.getLogger(PhoneService.class);

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    /**
     * Save a phone.
     *
     * @return the persisted entity.
     */
    public Phone save(Phone phone) {
        log.debug("Request to save Phone : {}", phone);
        Phone result = phoneRepository.save(phone);
        return result;
    }

    /**
     * Partially update a phone.
     *
     * @return the persisted entity.
     */
    public Phone partialUpdate(Phone phone) {
        log.debug("Request to partially update Phone : {}", phone);
        return phoneRepository.save(phone);
    }

    /**
     * Get one phone by id.
     *
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Phone> findOne(Long id) {
        log.debug("Request to get Phone : {}", id);
        return phoneRepository.findById(id);
    }

    /**
     * Delete the phone by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Phone : {}", id);
        phoneRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Phone> findAll() {
        log.debug("Request to get all Phones");
        return new ArrayList<Phone>(phoneRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Phone> findAllByEmployeeId(Long employeeId) {
        log.debug("Request to get All by Id: {}", employeeId);
        return new ArrayList<Phone>(phoneRepository.findAllByEmployeeId(employeeId));
    }
}
