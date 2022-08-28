package pl.rafalrozek.klg.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rafalrozek.klg.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
