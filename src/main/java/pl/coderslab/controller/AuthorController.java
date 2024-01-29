package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.repository.AuthorDao;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private AuthorDao authorDao;
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @PostMapping("/create")
    @ResponseBody
    public String createAuthor(@RequestParam String firstName,
                             @RequestParam String lastName) {
        Author a = new Author(firstName,lastName);
        a= authorDao.create(a);
        return a.toString();
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public String editAuthor(@PathVariable long id,
                             @RequestParam String firstName,
                             @RequestParam String lastName){
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.edit(author);
        return author.toString();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findAuthorById(@PathVariable long id){
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @GetMapping("/all")
    @ResponseBody
    public String findAll() {
        List<Author> all = authorDao.findAll();
        return all.toString();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public void deleteAuthor(@PathVariable long id){
        Author author = authorDao.findById(id);
        authorDao.delete(author);
    }
}
