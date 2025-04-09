package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public List<Book> getAll(){
        return service.findAll();
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        return service.save(book);
    }
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,@RequestBody Book book){
        book.setId(id);
        return service.save(book);
    }
}
