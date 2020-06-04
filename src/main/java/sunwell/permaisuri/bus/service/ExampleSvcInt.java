package sunwell.permaisuri.bus.service;

import org.springframework.scheduling.annotation.Async;

public interface ExampleSvcInt
{
	@Async
	public void testMethod();
}
