package br.com.cc.person;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Person {
	private String name;
	private String cpf;
	private int age;
	private List<String> homePhones;
	private List<String> mobilePhones;
	
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
}
