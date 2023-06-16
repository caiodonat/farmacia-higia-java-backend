package com.farmaciahigia.service;

import java.util.ArrayList;
import java.util.List;

import com.farmaciahigia.model.Customer;

public class CustomerService extends Customer{

    private List<String> erros = new ArrayList<String>();


    public CustomerService() {
	};

	public CustomerService(Customer customer) {
        
		setCpf(getCpf());
        setPassword(getPassword());

		// this.cpf = customer.getCpf();
		// this.email = customer.getEmail();
		// this.password = customer.getPassword();
		// this.firstName = customer.getFirstName();
		// this.lastName = customer.getLastName();
		// this.phone = customer.getPhone();
		// this.birthDate = customer.getBirthDate();
		// this.recoverCode = customer.getRecoverCode();
		// this.isActive = customer.getIsActive();
	}

    @Override
    public void setCpf(String cpf) {
        if(cpf == null){
            pushError("CPF não informado");
            return;
        }

        if(cpf.length() != 11){
            pushError("CPF invalido");
        }

        super.setCpf(cpf);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword("password");

        if(password == null){
            pushError("Senha não informado");
            return;
        }

        if(password.length() > 6){
            pushError("Senha muito curta");
        }

        super.setPassword(password);
    }

    public void pushError(String message){
        this.erros.add(message);
    }

	public List<String> getErros() {
        return this.erros;
    }

    public boolean isValid() {
        return this.erros.size() == 0;
    }
}
