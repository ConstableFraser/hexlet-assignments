package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO findById(Long id) {
        var authorModel = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        return authorMapper.map(authorModel);
    }

    public AuthorDTO create(AuthorCreateDTO authorCreateDTO) {
        var product = authorMapper.map(authorCreateDTO);
        authorRepository.save(product);
        return authorMapper.map(product);
    }

    public AuthorDTO update(AuthorUpdateDTO authorUpdateDTO, Long idTarget) {
        var authorModel = authorRepository.findById(idTarget)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + idTarget));
        authorMapper.update(authorUpdateDTO, authorModel);
        authorRepository.save(authorModel);
        return authorMapper.map(authorModel);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
