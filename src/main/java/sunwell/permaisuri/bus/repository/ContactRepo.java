package sunwell.permaisuri.bus.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.contact.Contact;
import sunwell.permaisuri.core.entity.cred.UserCredential;

public interface ContactRepo extends JpaRepository<Contact, Long> {
	Contact findByUserCredential(UserCredential _user);
	Contact findByUserCredential_SystemId(Integer _id);
}
