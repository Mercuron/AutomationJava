package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate; // Используем String для простоты


    public Book save(Book book){
         Book savedBook =repository.save(book);
         kafkaTemplate.send("book-events", "Book saved: " + savedBook.getId());
         return savedBook;


    }

    public List<Book> findAll(){
        return repository.findAll();
    }

}
