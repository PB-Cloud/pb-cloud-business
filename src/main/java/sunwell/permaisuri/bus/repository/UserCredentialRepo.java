package sunwell.permaisuri.bus.repository;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.cred.UserCredential;


public interface UserCredentialRepo extends JpaRepository<UserCredential, Integer> {
	public UserCredential findByUserName(String _name) ;
	public UserCredential findByRegistrationToken(String _token);
//	public Page<UserCredential> findByGroup(UserGroup _group, Pageable _page);
//	public Page<UserCredential> findByGroup_SystemId(Integer _id, Pageable _page);
}
