package br.com.cc.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import br.com.cc.person.Person;
import br.com.cc.person.PersonBuilder;
import br.com.cc.person.PersonReport;
import br.com.cc.report.ReportResult;

public class PersonReportTest {
	
	private PersonReport personReport = new PersonReport();

	@Test
	public void shouldPrintReportWithOnePerson() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("9878674565"))
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);

		ReportResult actual = personReport.print(people);
		
		ReportResult expected = new ReportResult("Nome: Fulano, Fones: [9878674565], CPF: 01234567890", new ArrayList<String>());
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
		
	}
	
	@Test
	public void shouldPrintReportWithMoreThanOnePerson() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("9878654356"))
				.build();
		
		Person anotherPerson = new PersonBuilder()
				.withName("Ciclano")
				.withCpf("94748695021")
				.withMobilePhones(Arrays.asList("9856743245"))
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);
		people.add(anotherPerson);

		ReportResult actual = personReport.print(people);
		
		ReportResult expected = new ReportResult("Nome: Fulano, Fones: [9878654356], CPF: 01234567890 | Nome: Ciclano, Fones: [9856743245], CPF: 94748695021", new ArrayList<String>());
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldNotPrintPersonWhenMobilePhoneTotalDigitsAreDifferentFrom10() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("123456789"))
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);

		ReportResult actual = personReport.print(people);
		
		ReportResult expected = new ReportResult(StringUtils.EMPTY, 
				Arrays.asList("Fulano(01234567890): Telefone movel 123456789 invalido."));
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
	@Test
	public void shouldPrintPersonWhenMobilePhoneContains10Digits() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("9987876676", "5467876543"))
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);

		ReportResult actual = personReport.print(people);
		
		ReportResult expected = new ReportResult("Nome: Fulano, Fones: [9987876676, 5467876543], CPF: 01234567890", new ArrayList<String>());
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
}
