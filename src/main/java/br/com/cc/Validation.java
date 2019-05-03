package br.com.cc;

import java.util.ArrayList;
import java.util.List;

import br.com.cc.person.Person;

public class Validation {

	protected List<String> validaPessoa(Person person) {
		
		List<String> constraints = new ArrayList<String>();
		if (person != null) {
			if (person.getCpf() != null) {
				if (person.getName() != null) {
					if (person.getHomePhones() != null) {
						for (String telefone : person.getHomePhones()) {
							if (telefone != null) {
								if (telefone.isEmpty()) {
									constraints.add("Erro - Telefone inválido");
									return constraints;
								} else {
									if(telefone.length() < 7){
										constraints.add("Erro - Telefone inválido");
										return constraints;
									}
								}
								
							} else {
								constraints.add("Erro - Telefone inválido");
								return constraints;
							}
						}
					}
				}
			}
		}

		return constraints;
	}
}