package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book save(Book book){
        return repository.save(book);
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

}
