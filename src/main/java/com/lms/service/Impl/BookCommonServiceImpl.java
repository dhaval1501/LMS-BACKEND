package com.lms.service.Impl;

import com.lms.model.Book;
import com.lms.repository.BookRepository;
import com.lms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookCommonServiceImpl implements CommonService<Book> {


    private  BookRepository bookRepository;

    @Autowired
    public BookCommonServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;

    }
    @Override
    public Book add(Book book) {
        Book book1 = bookRepository.save(book);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public Book update(Long id, Book book) {

        Optional<Book> optionalUser =bookRepository.findById(id);
        Book book1 = new Book();
        if(optionalUser.isPresent()){
            book1 = optionalUser.get();
            book1.setName(book.getName());
            book1.setAuthor(book.getAuthor());
            book1.setAvailableQuantity(book.getAvailableQuantity());
            book1.setTotalQuantity(book.getTotalQuantity());
        }
       return bookRepository.save(book1);
    }

    @Override
    public void delete(Long id) {

        Optional<Book> book =bookRepository.findById(id);
        bookRepository.delete(book.get());

    }

    @Override
    public Book getById(Long id) {

        Optional<Book> book =bookRepository.findById(id);
        return book.get();

    }

    public List<Book> getByName(String name){

        List<Book> bookList = bookRepository.getByName(name);
        return bookList;
    }
}
