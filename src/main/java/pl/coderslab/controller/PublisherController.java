package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherDao;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }
    @PostMapping("/create")
    @ResponseBody
    public String createPublisher(@RequestParam String name) {
        Publisher p = new Publisher(name);
        p = publisherDao.create(p);
        return p.toString();
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public String editPublisher(@PathVariable long id,
                             @RequestParam String name){
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.edit(publisher);
        return publisher.toString();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findPublisherById(@PathVariable long id){
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @GetMapping("/all")
    @ResponseBody
    public String findAll() {
        List<Publisher> all = publisherDao.findAll();
        return all.toString();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public void deletePublisher(@PathVariable long id){
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
    }
}
