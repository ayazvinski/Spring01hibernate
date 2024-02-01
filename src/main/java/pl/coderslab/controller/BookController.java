package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.AuthorDao;
import pl.coderslab.repository.BookDao;
import pl.coderslab.entity.Book;
import pl.coderslab.repository.PublisherDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
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

    @PostMapping("/createWithPublisher")
    @ResponseBody
    public String createWithPublisher(@RequestParam String title,
                             @RequestParam int rating,
                             @RequestParam String description,
                             @RequestParam String publisherName
    ) {
        Publisher publisher = new Publisher(publisherName);
        publisher = publisherDao.create(publisher);
        Book b = new Book(title, rating, description);
        b.setPublisher(publisher);
        b = bookDao.create(b);
        return b.toString();
    }

    @PostMapping("/createWithAuthors")
    @ResponseBody
    public String createWithAuthors(@RequestParam String title,
                                      @RequestParam int rating,
                                      @RequestParam String description,
                                      @RequestParam String authors
    ) {
        List<Author> savedAuthors = Arrays.stream(authors.split(","))
                .map(Author::new)
                .map(authorDao::create)
                .collect(Collectors.toList());

        Book b = new Book(title, rating, description);
        b.setAuthors(savedAuthors);
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
