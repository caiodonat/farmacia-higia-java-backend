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
		if (isEmail(customer.getEmail())) {
			setEmail(customer.getEmail());
		}
		if (isPassword(customer.getPassword())) {
			setPassword(customer.getPassword());
		}
		if (isFirstName(customer.getFirstName())) {
			setFirstName(customer.getFirstName());
		}
		if (isLastName(customer.getLastName())) {
			setLastName(customer.getLastName());
		}
		if (isPhone(customer.getPhone())) {
			setPhone(customer.getPhone());
		}
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

		Pattern p = Pattern.compile("^([a-zA-Z0-9].*)@([a-zA-Z0-9]+)\\.([a-zA-Z]{2,6})(\\.[a-zA-Z]{2,6})?$",
				Pattern.CASE_INSENSITIVE);
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

	public boolean isFirstName(String name) {
		boolean isValid = true;

		if (name == null) {
			pushError("Primeiro nome não informado");
			isValid = false;
		}

		if (name.length() < 3) {
			pushError("Primeiro nome deve ter mais que 3 letras");
			isValid = false;
		}

		Pattern p = Pattern.compile("^([a-zA-Z ]{3,24})$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if (!m.matches()) {
			pushError("Primeiro nome deve conter apenas letras");
			isValid = false;
		}

		if (isValid) {
			setFirstName(name);
		}
		return isValid;
	}

	public boolean isLastName(String name) {
		boolean isValid = true;

		if (name == null) {
			pushError("Ultimo nome não informado");
			isValid = false;
		}

		if (name.length() < 3) {
			pushError("Ultimo nome deve ter mais que 3 letras");
			isValid = false;
		}

		Pattern p = Pattern.compile("^([a-zA-Z ]{3,24})$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if (!m.matches()) {
			pushError("Ultimo nome deve conter apenas letras");
			isValid = false;
		}

		if (isValid) {
			setLastName(name);
		}
		return isValid;
	}

	public boolean isPhone(String phone) {
		boolean isValid = true;

		if (phone == null) {
			pushError("Telefone não informado");
			isValid = false;
		}

		Pattern p = Pattern.compile(
				"^((\\+?55 ?[1-9]{2} ?)|(\\+?55 ?\\([1-9]{2}\\) ?)|(0[1-9]{2} ?)|(\\([1-9]{2}\\) ?)|([1-9]{2}\\ ?))(([0-9]{4}-?[0-9]{4})|(9[1-9]{1}[0-9]{3}-?[0-9]{4}))$",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(phone);

		// Pattern pn = Pattern.compile("\\d+");
		// Matcher mn = pn.matcher(phone);

		if (!(m.matches())) {
			pushError("Telefone com formato invalido");
			isValid = false;
		}

		// normalize
		String newPhone = "";
		for (char letter : phone.toCharArray()) {
			if (Character.isDigit(letter)) {
				newPhone += letter;
			}
		}

		if (isValid) {
			setPhone(newPhone);
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
