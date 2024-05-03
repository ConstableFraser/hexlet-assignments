package exercise.service;

import exercise.dto.BookDTO;
import exercise.dto.BookCreateDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.BadRequestException;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var authors = bookRepository.findAll();
        return authors.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(Long id) {
        var bookModel = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        return bookMapper.map(bookModel);
    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        authorRepository.findById(bookCreateDTO.getAuthorId())
                .orElseThrow(() -> new BadRequestException("Not found: " + bookCreateDTO.getAuthorId()));

        var product = bookMapper.map(bookCreateDTO);
        bookRepository.save(product);
        return bookMapper.map(product);
    }

    public BookDTO update(BookUpdateDTO bookUpdateDTO, Long idTarget) {
        var bookModel = bookRepository.findById(idTarget)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + idTarget));
        bookMapper.update(bookUpdateDTO, bookModel);
        bookRepository.save(bookModel);
        return bookMapper.map(bookModel);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
