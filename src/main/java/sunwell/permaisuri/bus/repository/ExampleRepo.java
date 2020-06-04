package sunwell.permaisuri.bus.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import sunwell.permaisuri.core.entity.example.A;

public interface ExampleRepo extends JpaRepository<A, Integer> {
}
