package com.farmaciahigia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.farmaciahigia.model.Customer;


public class CustomerService extends Customer {

    private List<String> erros = new ArrayList<String>();

    public CustomerService() {
    };

    public CustomerService(Customer customer) {

        if (isCpf(customer.getCpf())) {
            setCpf(customer.getCpf());
        }
        if(isEmail(customer.getEmail())){
            setEmail(customer.getEmail());
        }
        if (isPassword(customer.getPassword())) {
            setPassword(customer.getPassword());
        }
        // if(isFirstName(customer.getFirstName())){
        // setFirstName(customer.getFirstName());
        // }
        // if(isLastName(customer.getLastName())){
        // setLastName(customer.getLastName());
        // }
        // if(isPhone(customer.getPhone())){
        // setPhone(customer.getPhone());
        // }
        // if(isBirthDate(customer.getBirthDate())){
        // setBirthDate(customer.getBirthDate());
        // }
        // if(isRecoverCode(customer.getRecoverCode())){
        // setRecoverCode(customer.getRecoverCode());
        // }
        // if(isIsActive(customer.getIsActive())){
        // setIsActive(customer.getIsActive());
        // }
    }

    // validators

    public boolean isCpf(String cpf) {
        boolean isValid = true;

        if (cpf == null) {
            pushError("CPF não informado");
            isValid = false;
        }

        if (cpf.length() != 11) {
            pushError("CPF invalido");
            isValid = false;
        }

        if (isValid) {
            setCpf(cpf);
        }
        return isValid;
    }

    public boolean isEmail(String email) {
        boolean isValid = true;

        if (email == null) {
            pushError("Email não informado");
            isValid = false;
        }

        // Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}([A-Z]{2,6})?$", Pattern.CASE_INSENSITIVE);
        Pattern p = Pattern.compile("^([a-zA-Z0-9].*)@([a-zA-Z0-9]+)\\.([a-zA-Z]{2,6})(\\.[a-zA-Z]{2,6})?$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            pushError("Email com formato invalido");
            isValid = false;
        }

        if (isValid) {
            setEmail(email);
        }
        return isValid;
    }

    public boolean isPassword(String password) {
        boolean isValid = true;

        if (password == null) {
            pushError("Senha não informado");
            isValid = false;
        }

        if (password.length() < 6) {
            pushError("Senha muito curta");
            isValid = false;
        }

        if (isValid) {
            setPassword(password);
        }
        return isValid;
    }

    // handlers

    public void pushError(String message) {
        this.erros.add(message);
    }

    public List<String> getErros() {
        return this.erros;
    }

    public boolean isValid() {
        return this.erros.size() == 0;
    }
}
