package info5153.exercises.server.report;

import info5153.exercises.server.employee.EmployeeRepository;
import info5153.exercises.server.expense.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<Report> addOne(@RequestBody Report report) {
        try {
            Report createdReport = reportDAO.create(report);
            return new ResponseEntity<>(createdReport, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Report>> findByEmployee(@PathVariable Long id) {
        try {
            Iterable<Report> reports = reportRepository.findByEmployeeId(id);
            if (reports == null) {
                return ResponseEntity.ok(Collections.emptyList());
            }
            return ResponseEntity.ok(reports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> reportPDF(@PathVariable Long id) {

        ByteArrayInputStream bis = PDFGenerator.generateReport(id.toString(), employeeRepository,
                expenseRepository, reportRepository);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report_" + id.toString() + ".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
