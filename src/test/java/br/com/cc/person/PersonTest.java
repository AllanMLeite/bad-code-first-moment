package br.com.cc.person;
import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import br.com.cc.person.Person;

public class PersonTest {

	@Test
	public void shouldThrowErrorWhenNameIsNull() {
		assertThatThrownBy(() -> new Person().builder().withName(null).build())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("name cannot be blank");
	}
	
	@Test
	public void shouldThrowErrorWhenNameIsEmpty() {
		assertThatThrownBy(() -> new Person().builder().withName(" ").build())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("name cannot be blank");
	}
	
	@Test
	public void shouldThrowErrorWhenCpfIsNull() {
		assertThatThrownBy(() -> new Person().builder().withCpf(null).build())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("cpf cannot be blank");
	}
	
	@Test
	public void shouldThrowErrorWhenCpfIsEmpty() {
		assertThatThrownBy(() -> new Person().builder().withCpf(" ").build())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("cpf cannot be blank");
	}
	
	@Test
	public void shouldCreatePerson() {
		Person person = new Person().builder()
			.withCpf("01234567890")
			.withHomeAge(28)
			.withName("Arya")
			.withHomePhones(Collections.singletonList("12345678"))
			.withMobilePhones(Collections.singletonList("789456123"))
			.build();
		
		assertEquals("01234567890", person.getCpf());
		assertEquals(28, person.getAge());
		assertEquals("Arya", person.getName());
		assertThat(person.getHomePhones()).containsExactly("12345678");
		assertThat(person.getMobilePhones()).containsExactly("789456123");		
	}
}
