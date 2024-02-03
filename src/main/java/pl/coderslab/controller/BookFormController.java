package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.AuthorDao;
import pl.coderslab.repository.BookDao;
import pl.coderslab.repository.PublisherDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bookForm")
public class BookFormController {
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/addBook")
    public String showForm(Model model){
        List<Publisher> publishers = publisherDao.findAll();
        List<Author> authors = authorDao.findAll();
        model.addAttribute("publishers",publishers);
        model.addAttribute("authors",authors);
        model.addAttribute("book",new Book());
        return "addBook";
    }
    @PostMapping("/addBook")
    public String postAddBook(@RequestParam String title,
                              @RequestParam int rating,
                              @RequestParam String description,
                              Publisher publisher,
                              List<Author> authors){
        publisher = publisherDao.create(publisher);
        List<Author> savedAuthors = new ArrayList<>(authors);
        Book book = new Book(title,rating,description);
        book.setPublisher(publisher);
        book.setAuthors(savedAuthors);
        bookDao.create(book);
        return "addBook";
    }

}
