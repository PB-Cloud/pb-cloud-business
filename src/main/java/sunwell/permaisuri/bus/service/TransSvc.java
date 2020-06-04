package sunwell.permaisuri.bus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransSvc
{
	@Transactional
	public void transService() {
		System.out.println("Trans service called");
	}
}
