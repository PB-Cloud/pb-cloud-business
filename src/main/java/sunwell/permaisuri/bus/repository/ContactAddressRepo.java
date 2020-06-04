package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.contact.ContactAddress;;

public interface ContactAddressRepo extends JpaRepository<ContactAddress, Long> {
}
