package com.lms.controller;

import com.lms.model.Book;
import com.lms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    private CommonService<Book> bookCommonService;

    @Autowired
    public BookController(CommonService<Book> bookCommonService){
        this.bookCommonService = bookCommonService;

    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){

        bookCommonService.add(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBook(){
        List<Book> bookList = bookCommonService.getAll();
        return bookList;
    }

    @PutMapping("/update/{id}")
    public void updateBook(@PathVariable Long id,@RequestBody Book book){
        bookCommonService.update(id,book);

    }

    @DeleteMapping("/delete/{id}")
    public void  deleteBook(@PathVariable Long id){
        bookCommonService.delete(id);
    }

    @GetMapping("/byId/{id}")
    public Book getBookById(@PathVariable Long id){

        Book book =  (Book) bookCommonService.getById(id);
        return book;
    }

    @GetMapping("/byName/{name}")
    public List<Book> getBookByName(@PathVariable String name){

        List<Book> book=  bookCommonService.getByName(name);
        return book;
    }

}
