package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 5/16/17.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisher.setAddress("12th Street, LA");
        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher hc = new Publisher("Harper Collins", "195 Broadway, New York, NY 10007" );
        Book  ddd = new Book("Domain Driven Design", "1234", hc);
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(hc);
        bookRepository.save(ddd);
        
        


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher wx = new Publisher("Wrox", "111 River Street, Hoboken, NJ 07030-5774" );
        Book noEJB = new Book("J2EE Development without EJB", "23444", wx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(wx);
        bookRepository.save(noEJB);
        
    }
}
