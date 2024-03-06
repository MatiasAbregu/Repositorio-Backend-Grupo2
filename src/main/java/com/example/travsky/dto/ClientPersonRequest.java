package com.example.travsky.dto;

import com.example.travsky.models.Client;
import com.example.travsky.models.Person;
import lombok.*;

/**
 * @author Matias
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPersonRequest {
 
    private Client client;
    private Person person;
   
}