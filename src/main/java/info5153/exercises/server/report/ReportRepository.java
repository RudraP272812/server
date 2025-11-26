package info5153.exercises.server.report;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByEmployeeId(Long id);
}