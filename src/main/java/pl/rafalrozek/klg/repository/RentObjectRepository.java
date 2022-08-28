package pl.rafalrozek.klg.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rafalrozek.klg.model.RentObject;

@Repository
public interface RentObjectRepository extends CrudRepository<RentObject, Long> {
}
