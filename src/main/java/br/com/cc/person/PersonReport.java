package br.com.cc.person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.cc.printer.ConsolePrinter;
import br.com.cc.printer.Printer;
import br.com.cc.report.ReportResult;

public class PersonReport {
	
	private static final String PERSON_SEPARATOR_CHAR = " | ";
	
	private final Printer printer = new ConsolePrinter();
	
	public ReportResult print(Vector<Person> people){
		String textToPrint = StringUtils.EMPTY;
		List<String> constraints = new ArrayList<String>();

		for (Person person : people) {

			List<String> personConstraints = person.validate();
			constraints.addAll(personConstraints);

			if (CollectionUtils.isNotEmpty(personConstraints)) {
				continue;
			}

			if (StringUtils.isBlank(textToPrint)) {
				textToPrint += person;
			} else {
				textToPrint += PERSON_SEPARATOR_CHAR + person;
			}
		}

		printer.print(textToPrint);
		
		return new ReportResult(textToPrint, constraints);
	}

	public static void main(String[] args) {
		
		Vector<Person> people = new Vector<Person>();
		
		Person someone = new PersonBuilder()
				.withName("Ned")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList("4119189"))
				.withMobilePhones(Arrays.asList("9675432456"))
				.build();
		
		Person someoneElse = new PersonBuilder()
				.withName("Aerys")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList("4119189"))
				.withMobilePhones(Arrays.asList("0123456789", "9675432456"))
				.build();
		
		people.add(someone);
		people.add(someoneElse);
		
		PersonReport personReport = new PersonReport();
		personReport.print(people);
	}
}
