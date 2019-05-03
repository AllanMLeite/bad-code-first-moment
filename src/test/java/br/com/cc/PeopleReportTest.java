package br.com.cc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import br.com.cc.person.Person;

public class PeopleReportTest {

	@Test
	public void shouldPrintReportWithOnePerson() {
		Vector<Person> people = new Vector();

		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("0033435457")
				.build();
		
		people.add(somePerson);

		String result = PeopleReport.print(people);
		
		assertEquals("Fulano", result);
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
		
		Vector<Person> people = new Vector();
		people.add(somePerson);
		people.add(anotherPerson);

		String result = PeopleReport.print(people);
		
		assertEquals("Fulano, Ciclano", result);
	}

}
