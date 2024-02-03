package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Author;
import pl.coderslab.repository.AuthorDao;

public class AuthorConverter implements Converter<String, Author> {
    @Autowired
    private AuthorDao dao;

    @Override
    public Author convert(String id) {
        return dao.findById(Long.parseLong(id));
    }
}
