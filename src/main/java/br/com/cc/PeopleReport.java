package br.com.cc;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.com.cc.person.Person;

/**
 * Relatório de pessoas
 * @author Deus
 */
public class PeopleReport {
	/**
	 * a string para somar todos os nomes na lista 
	 */
	
	/**
	 * imprime a lista de pessoas
	 * @param paramL
	 */
	public static String print(Vector paramL){
		String _s = "";
		
		for (int i = 0; i < paramL.size(); i++) {
			
			Person p = (Person) paramL.get(i);
			Validation validador = new Validation();
			List<String> erro = validador.validaPessoa(p);
			
			if(!erro.isEmpty())
				continue;

			if(_s == "")
				_s += p.getName();
			else			
				_s += ", " + p.getName();
		}

		System.out.print(_s);
		return _s;
	}
	
	/**
	 * codigo principal
	 * @param args
	 */
	public static void main(String[] args) {
		Vector<Person> people = new Vector<Person>();
		
		ArrayList<String> mobilePhones = new ArrayList<String>();
		mobilePhones.add("8765343");
		
		ArrayList<String> homePhones = new ArrayList<String>();
		mobilePhones.add("4119189");
		
		Person person = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.withHomePhones(homePhones)
				.withMobilePhones(mobilePhones)
				.build();
		
		people.add(person);
		
		print(people);
		
	}

}
