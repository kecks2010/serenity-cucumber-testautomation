package de.mirko_werner.serenity_cucumber.mapper;

import de.mirko_werner.testdata.persistence.models.Customer;
import de.mirko_werner.testdata.repositories.CustomerRepository;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomerDataTableType {

    @DataTableType
    public Customer customerEntry(Map<String, String> entry) {
        return CustomerRepository.getInstance().getCustomer(entry.get("firstName"), entry.get("lastName"));
    }
}
