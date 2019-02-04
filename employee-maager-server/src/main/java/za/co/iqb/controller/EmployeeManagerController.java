package za.co.iqb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.iqb.model.dto.EmployeeManagerDTO;
import za.co.iqb.persistance.model.EmployeeManager;
import za.co.iqb.service.EmployeeManagerService;

/**
 *
 * @author vincent
 */
@RestController
@RequestMapping("/employee-manager")
@Api(value = "Employee Manager Controller", description = "Operations pertaining to Employee Manager")
@Slf4j
public class EmployeeManagerController {
    
    @Autowired
    private EmployeeManagerService employeeManagerService;
    
    @GetMapping(value = "/find-employee")
    @ApiOperation(value = "Searching for Employee by employee ID", response = Iterable.class)
    ResponseEntity getEmployeeById(@RequestParam("employeeId") final Long employeeId) {
        EmployeeManager employee = employeeManagerService.getEmployeeById(employeeId);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    @GetMapping(value = "/find-all-employees")
    @ApiOperation(value = "Searching for all saved Employees", response = Iterable.class)
    ResponseEntity getAllEmployees() {
        Iterable<EmployeeManager> results = employeeManagerService.getAllEmployees();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    
    @PostMapping(value = "/save")
    @ApiOperation(value = "Configure source", response = Iterable.class)
    ResponseEntity saveEmployee(@RequestBody final EmployeeManagerDTO employeeManagerDTO) {
        employeeManagerService.saveEmployee(employeeManagerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
