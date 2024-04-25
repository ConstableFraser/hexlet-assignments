package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

import java.time.LocalDate;

@RestController
@RequestMapping("/contacts")
@ResponseStatus(HttpStatus.CREATED)
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path="")
    public ContactDTO create(ContactCreateDTO contactCreateDTO) {
        var contactModel = new Contact();
        contactModel.setFirstName(contactCreateDTO.getFirstName());
        contactModel.setLastName(contactCreateDTO.getLastName());
        contactModel.setPhone(contactCreateDTO.getPhone());

        contactModel = contactRepository.save(contactModel);

        var contactDTO = new ContactDTO();
        contactDTO.setId(contactModel.getId());
        contactDTO.setFirstName(contactModel.getFirstName());
        contactDTO.setLastName(contactModel.getLastName());
        contactDTO.setPhone(contactModel.getPhone());
        contactDTO.setCreatedAt(contactModel.getCreatedAt());
        contactDTO.setUpdatedAt(contactModel.getUpdatedAt());

        return contactDTO;
    }
    // END
}
