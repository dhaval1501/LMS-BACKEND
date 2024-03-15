package com.lms.controller;

import com.lms.model.Book;
import com.lms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/book")
@CrossOrigin( origins = "http://Localhost:4200")
public class BookController {

    private CommonService<Book> bookCommonService;

    @Autowired
    public BookController(CommonService<Book> bookCommonService){
        this.bookCommonService = bookCommonService;

    }

    @CrossOrigin( origins = "http://Localhost:4200")
    @PostMapping("/add")
        public Book addBook(@RequestBody Book book){
       return bookCommonService.add(book);
    }

    @GetMapping("/all")
    @CrossOrigin( origins = "http://Localhost:4200")
    public List<Book> getAllBook(){
        List<Book> bookList = bookCommonService.getAll();
        return bookList;
    }

    @PutMapping("/update/{id}")
    @CrossOrigin( origins = "http://Localhost:4200")
    public Book updateBook(@PathVariable Long id,@RequestBody Book book){
      return  bookCommonService.update(id,book);

    }

    @DeleteMapping("/delete/{id}")

    public void  deleteBook(@PathVariable Long id){
        bookCommonService.delete(id);
    }

    @GetMapping("/byId/{id}")
    @CrossOrigin( origins = "http://Localhost:4200")
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
