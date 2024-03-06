package com.example.travsky.dto;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import lombok.*;

/**
 * @author Matias
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePersonRequest {
    
    private Employee employee;
    private Person person;

}
