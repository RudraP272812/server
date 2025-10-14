package info5153.exercises.server.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling Report API requests.
 */
@CrossOrigin
@RestController
public class ReportController {

    @Autowired
    private ReportDAO reportDAO;

    @PostMapping("/api/reports")
    public ResponseEntity<Report> addOne(@RequestBody Report report) {
        Report createdReport = reportDAO.create(report);
        return new ResponseEntity<Report>(createdReport, HttpStatus.OK);
    }
}
