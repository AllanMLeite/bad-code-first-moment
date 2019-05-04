package br.com.cc;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import br.com.cc.person.Person;

public class PeopleReport {

	public static ReportResult print(Vector<Person> paramL){
		String textToPrint = StringUtils.EMPTY;
		List<String> errors = new ArrayList<String>();
		
		for (int i = 0; i < paramL.size(); i++) {
			
			Person p = (Person) paramL.get(i);
			List<String> personErrors = p.validaPessoa();
			errors.addAll(personErrors);
			
			if(!personErrors.isEmpty())
				continue;

			if(StringUtils.isBlank(textToPrint))
				textToPrint += p.getName();
			else			
				textToPrint += ", " + p.getName();
		}

		System.out.print(textToPrint);
		
		return new ReportResult(textToPrint, errors);
	}
	
	public static void main(String[] args) {
		Vector<Person> people = new Vector<Person>();
		
		ArrayList<String> mobilePhones = new ArrayList<String>();
		mobilePhones.add("0123456789");
		
		ArrayList<String> homePhones = new ArrayList<String>();
		homePhones.add("4119189");
		
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
