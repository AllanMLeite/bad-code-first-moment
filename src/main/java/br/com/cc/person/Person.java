package br.com.cc.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.cc.util.CpfValidator;

public class Person {
	private String name;
	private String cpf;
	private int age;
	private List<String> homePhones = new ArrayList<String>();
	private List<String> mobilePhones = new ArrayList<String>();
	
	public Person(String name, String cpf, int age, List<String> homePhones, List<String> mobilePhones) {
		
		checkNameIsBlank(name);
		checkCpfIsBlank(cpf);
		checkMobilePhonesIsEmpty(mobilePhones);
		
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.homePhones = homePhones;
		this.mobilePhones = mobilePhones;
	}

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
	
	private void checkNameIsBlank(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("name cannot be blank");
		}
	}
	
	private void checkCpfIsBlank(String cpf) {
		if (StringUtils.isBlank(cpf)) {
			throw new IllegalArgumentException("cpf cannot be blank");
		}
	}
	
	private void checkMobilePhonesIsEmpty(List<String> mobilePhones) {
		if (CollectionUtils.isEmpty(mobilePhones)) {
			throw new IllegalArgumentException("mobile phones list cannot be empty");
		}
	}
	
	public List<String> validate() {

		List<String> constraints = new ArrayList<String>();
		
		constraints.addAll(checkCpf());
		constraints.addAll(checkHomePhones());
		constraints.addAll(checkMobilePhones());
		
		return constraints;
	}

	private Collection<? extends String> checkCpf() {
		boolean isValidCpf = CpfValidator.isValidCpf(cpf);
		if (!isValidCpf) {
			String invalidCpfMessage = String.format("%s: CPF %s invalido.", name, cpf);
			return Collections.singletonList(invalidCpfMessage);
		}
		return new ArrayList<String>();
	}

	private Collection<? extends String> checkMobilePhones() {
		
		List<String> constraints = new ArrayList<String>();

		mobilePhones.forEach(phone -> {
			if (StringUtils.isBlank(phone)) {
				constraints.add(String.format("%s(%s): Possui um telefone movel vazio.", name, cpf));
			} else if (phone.length() != 10) {
				constraints.add(String.format("%s(%s): Telefone movel %s invalido.", name, cpf, phone));
			}
		});
		
		return constraints;
	}

	private Collection<? extends String> checkHomePhones() {
		List<String> constraints = new ArrayList<String>();

		if(CollectionUtils.isEmpty(homePhones)) {
			return new ArrayList<String>();
		}

		homePhones.forEach(phone -> {
			if (StringUtils.isBlank(phone)) {
				constraints.add(String.format("%s(%s): Possui um telefone fixo vazio.", name, cpf));
			} else if (phone.length() < 7) {
				constraints.add(String.format("%s(%s): Telefone fixo %s invalido.", name, cpf, phone));
			}
		});
		
		return constraints;
	}

	@Override
	public String toString() {
		return "Nome: " + name + ", Fones: " + mobilePhones + ", CPF: " + cpf;
	}
}
