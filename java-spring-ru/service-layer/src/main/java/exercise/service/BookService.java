package exercise.service;

import exercise.dto.AuthorDTO;
import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
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
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var result = books.stream().map(bookMapper::map).toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        bookRepository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("400 Bad request"));
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO update(Long id, BookUpdateDTO bookData) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("400 Bad request"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // END
}
