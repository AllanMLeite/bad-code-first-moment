package br.com.cc.report;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import br.com.cc.person.Person;

public class PersonReport {

	public static ReportResult print(Vector<Person> paramL){
		String textToPrint = StringUtils.EMPTY;
		List<String> errors = new ArrayList<String>();
		
		for (int i = 0; i < paramL.size(); i++) {
			
			Person person = (Person) paramL.get(i);
			List<String> personErrors = person.validateFields();
			errors.addAll(personErrors);
			
			if(!personErrors.isEmpty())
				continue;

			if(StringUtils.isBlank(textToPrint))
				textToPrint += person;
			else			
				textToPrint += " | " + person;
		}

		System.out.print(textToPrint);
		
		return new ReportResult(textToPrint, errors);
	}

	public static void main(String[] args) {
		Vector<Person> people = new Vector<Person>();
		
		Person someone = new Person().builder()
				.withName("Ned")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList("4119189"))
				.withMobilePhones(Arrays.asList("0123456789", "9675432456"))
				.build();
		
		Person someoneElse = new Person().builder()
				.withName("Aerys")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList("4119189"))
				.withMobilePhones(Arrays.asList("0123456789", "9675432456"))
				.build();
		
		people.add(someone);
		people.add(someoneElse);
		
		print(people);
	}
}
