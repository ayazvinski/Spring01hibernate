package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Person;
import pl.coderslab.repository.PersonDao;

@Controller
@RequestMapping("/person")
public class PersonController {
    private PersonDao personDao;
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping("/addForm")
    public String addForm() {
        return "addForm";
    }
    @PostMapping("/addForm")
    @ResponseBody
    public String postAddForm(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email
    ) {
        Person person = new Person(login, password, email);
        personDao.create(person);
        return person.toString();
    }
    @GetMapping("/addFormBind")
    public String addFormBind(Model m) {
        m.addAttribute("person", new Person());
        return "/addFormBinding";
    }

    @PostMapping("/addFormBind")
    @ResponseBody
    public String postAddFormBind(@ModelAttribute Person person) {
        personDao.create(person);
        return person.toString();
    }

    @PostMapping("/create")
    @ResponseBody
    public String createPerson(@RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String email) {

       Person person = new Person(login,password,email);
       person = personDao.create(person);
        return person.toString();
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public String editPerson(@PathVariable long id,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String email){
        Person person = personDao.findById(id);
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.edit(person);
        return person.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getPersonById(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletePersonById(@PathVariable long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
    }

}
