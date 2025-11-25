package info5153.exercises.server.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * REST Controller for handling Report API requests.
 */
@CrossOrigin
@RestController
public class ReportController {

    @Autowired
    private ReportDAO reportDAO;
    private ReportRepository reportRepository;

    @PostMapping("/api/reports")
    public ResponseEntity<Report> addOne(@RequestBody Report report) {
        Report createdReport = reportDAO.create(report);
        return new ResponseEntity<Report>(createdReport, HttpStatus.OK);
    }

    @GetMapping("/api/reports/{id}")
    public ResponseEntity<Iterable<Report>> findByEmployee(@PathVariable Long id) {
        return new ResponseEntity<Iterable<Report>>(reportRepository.findByEmployeeId(id), HttpStatus.OK);
    }
}
