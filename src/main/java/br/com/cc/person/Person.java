package br.com.cc.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import br.com.cc.util.CpfValidator;

public class Person {
	private String name;
	private String cpf;
	private int age;
	private List<String> homePhones = new ArrayList<String>();
	private List<String> mobilePhones = new ArrayList<String>();
	
	public String getCpf() {
		return cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getHomePhones() {
		return homePhones;
	}
	
	public int getAge() {
		return age;
	}
	
	public List<String> getMobilePhones() {
		return mobilePhones;
	}
	
	public List<String> validateFields() {

		List<String> constraints = new ArrayList<String>();
		
		constraints.addAll(checkCpf());
		constraints.addAll(checkHomePhones());
		constraints.addAll(checkMobilePhones());
		
		return constraints;
	}

	private Collection<? extends String> checkCpf() {
		boolean isValidCpf = CpfValidator.isValidCpf(cpf);
		if (!isValidCpf) {
			return Collections.singletonList(String.format("%s: CPF %s invalido.", name, cpf));
		}
		return new ArrayList<String>();
	}

	private Collection<? extends String> checkMobilePhones() {
		return mobilePhones.stream()
			.filter(phone -> StringUtils.isBlank(phone) || phone.length() != 10)
			.map(phone -> String.format("%s(%s): Telefone movel %s invalido.", name, cpf, phone))
			.collect(Collectors.toList());
	}

	private Collection<? extends String> checkHomePhones() {
		List<String> constraints = new ArrayList<String>();

		if (getHomePhones() != null) {
			for (String telefone : getHomePhones()) {
				if (telefone != null) {
					if (telefone.isEmpty()) {
						constraints.add("Erro - Telefone inválido");
					} else {
						if (telefone.length() < 7) {
							constraints.add("Erro - Telefone inválido");
						}
					}

				} else {
					constraints.add("Erro - Telefone inválido");
				}
			}
		}
		return constraints;
	}
	
	public Builder builder() {
		return new Builder();
	}
	
	public class Builder {

		private Person person = new Person();

		public Builder() {
			homePhones = new ArrayList<String>();
			mobilePhones = new ArrayList<String>();
		}

		public Builder withName(String name) {
			checkNameIsBlank(name);
			person.name = name;
			return this;
		}
		
		private void checkNameIsBlank(String name) {
			if (StringUtils.isBlank(name)) {
				throw new IllegalArgumentException("name cannot be blank");
			}
		}
		
		public Builder withCpf(String cpf) {
			checkCpfIsBlank(cpf);
			person.cpf = cpf;
			return this;
		}
		
		private void checkCpfIsBlank(String cpf) {
			if (StringUtils.isBlank(cpf)) {
				throw new IllegalArgumentException("cpf cannot be blank");
			}
		}
		
		public Builder withHomeAge(int age) {
			person.age = age;
			return this;
		}
		
		public Builder withHomePhones(List<String> homePhones) {
			person.homePhones = homePhones;
			return this;
		}
		
		public Builder withMobilePhones(List<String> mobilePhones) {
			person.mobilePhones = mobilePhones;
			return this;
		}

		public Person build() {
			return person;
		}
	}

	@Override
	public String toString() {
		return "Nome: " + name + ", Fones: " + mobilePhones + ", CPF: " + cpf;
	}
}
