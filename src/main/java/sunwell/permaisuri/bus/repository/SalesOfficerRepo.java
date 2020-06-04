package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sunwell.permaisuri.core.entity.customer.Customer;
import sunwell.permaisuri.core.entity.hr.SalesOfficer;

public interface SalesOfficerRepo extends JpaRepository<SalesOfficer, Long>, JpaSpecificationExecutor<SalesOfficer> {
	public SalesOfficer findByEmail(String _name) ;
}
