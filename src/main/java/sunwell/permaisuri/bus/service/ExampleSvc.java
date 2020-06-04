package sunwell.permaisuri.bus.service;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import sunwell.permaisuri.bus.repository.ExampleRepo;
import sunwell.permaisuri.core.entity.example.A;;

@Service
@Transactional
public class ExampleSvc implements ExampleSvcInt
{
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ExampleRepo exRepo;
	
	public A findA(int _id) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		cb.and
		return em.find(A.class, _id);
	}
	
	public List<A> findAllA() {
		return exRepo.findAll();
	}
	
	@Override
	public void testMethod ()
	{
		// TODO Auto-generated method stub
		System.out.println("TEST");
	}
}
