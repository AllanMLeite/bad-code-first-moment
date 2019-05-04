package br.com.cc.person;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

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
	
	@Test
	public void shouldReturnMobilePhonesErrors() {
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("123456789"))
				.build();
		
		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(01234567890): Telefone movel 123456789 invalido.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validateFields());
	}

	@Test
	public void shouldntReturnMobilePhonesErrors() {
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("9987876676"))
				.build();

		assertThat(new ArrayList<String>()).containsExactlyElementsOf(somePerson.validateFields());
	}
	
	@Test
	public void shouldReturnCpfInvalidError() {
		Person somePerson = new Person().builder()
				.withName("Fulano")
				.withCpf("01234569865")
				.build();
		
		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano: CPF 01234569865 invalido.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validateFields());
	}
}
