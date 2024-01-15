package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping("/create")
    @ResponseBody
    public String createBook(@RequestParam String title,
                             @RequestParam int rating,
                             @RequestParam String description
    ) {
        Book b = new Book(title, rating, description);
        b = bookDao.create(b);
        return b.toString();
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public String editBook(@PathVariable long id,
                           @RequestParam String title,
                           @RequestParam int rating,
                           @RequestParam String description){
        Book book = bookDao.findById(id);
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.edit(book);
        return book.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString(); // todo handle book NOT found exception
    }

    @RequestMapping("/all")
    @ResponseBody
    public String findAll() {
        List<Book> all = bookDao.findAll();
        return all.toString();
    }
}
