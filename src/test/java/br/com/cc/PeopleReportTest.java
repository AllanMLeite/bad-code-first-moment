package br.com.cc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import br.com.cc.person.Person;

public class PeopleReportTest {

	@Test
	public void shouldPrintReportWithOnePerson() {
		Vector<Person> people = new Vector<Person>();

		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.build();
		
		people.add(somePerson);

		ReportResult actual = PeopleReport.print(people);
		
		ReportResult expected = new ReportResult("Fulano", new ArrayList<String>());
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
		
	}
	
	@Test
	public void shouldPrintReportWithPeople() {
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.build();
		
		Person anotherPerson = new Person().builder()
				.withName("Ciclano")
				.withCpf("0123456789")
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);
		people.add(anotherPerson);

		ReportResult actual = PeopleReport.print(people);
		
		ReportResult expected = new ReportResult("Fulano, Ciclano", new ArrayList<String>());
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldNotPrintPersonWhenMobilePhoneTotalDigitsAreDifferentFrom10() {
		List<String> mobilePhones = new ArrayList<String>();
		mobilePhones.add("123456789");
		
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.withMobilePhones(mobilePhones)
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);

		ReportResult actual = PeopleReport.print(people);
		
		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(0033435457): Telefone movel 123456789 invalido.");
		ReportResult expected = new ReportResult(StringUtils.EMPTY, expectedErrors);
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
	@Test
	public void shouldPrintPersonWhenMobilePhoneContains10Digits() {
		List<String> mobilePhones = new ArrayList<String>();
		mobilePhones.add("9987876676");
		
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.withMobilePhones(mobilePhones)
				.build();
		
		Vector<Person> people = new Vector<Person>();
		people.add(somePerson);

		ReportResult actual = PeopleReport.print(people);
		
		ReportResult expected = new ReportResult("Fulano", new ArrayList<String>());
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}

}
