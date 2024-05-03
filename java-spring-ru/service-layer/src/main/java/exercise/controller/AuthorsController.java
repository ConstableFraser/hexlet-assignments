package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> index() {
        return authorService.getAll();
    }

    @GetMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO show(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping(path="")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        return authorService.create(authorCreateDTO);
    }

    @PutMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO update(@RequestBody @Valid AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id) {
        return authorService.update(authorUpdateDTO, id);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
    // END
}
