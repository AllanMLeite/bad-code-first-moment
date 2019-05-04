package br.com.cc.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PersonTest {

	@DataPoints("emptyStrings")
	public static String[] emptyStrings = { StringUtils.EMPTY, StringUtils.SPACE, null };
	
	@Test
	public void shouldThrowErrorWhenNameIsNull() {
		assertThatThrownBy(() -> new Person(null, "01234567890", 12, new ArrayList<String>(), new ArrayList<String>()))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("name cannot be blank");
	}

	@Test
	public void shouldThrowErrorWhenNameIsEmpty() {
		assertThatThrownBy(() -> new Person(StringUtils.SPACE, "01234567890", 12, new ArrayList<String>(),
				new ArrayList<String>())).isInstanceOf(IllegalArgumentException.class)
						.hasMessage("name cannot be blank");
	}

	@Test
	public void shouldThrowErrorWhenCpfIsNull() {
		assertThatThrownBy(() -> new Person("Brian", null, 12, new ArrayList<String>(), new ArrayList<String>()))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("cpf cannot be blank");
	}

	@Test
	public void shouldThrowErrorWhenCpfIsEmpty() {
		assertThatThrownBy(
				() -> new Person("Brian", StringUtils.SPACE, 12, new ArrayList<String>(), new ArrayList<String>()))
						.isInstanceOf(IllegalArgumentException.class).hasMessage("cpf cannot be blank");
	}
	
	@Test
	public void shouldThrowErrorMobilePhonesNull() {
		assertThatThrownBy(() -> new Person("Bjorn", "01234567890", 12, null, new ArrayList<String>()))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("mobile phones list cannot be empty");
	}
	
	@Test
	public void shouldThrowErrorMobilePhonesEmpty() {
		assertThatThrownBy(() -> new Person("Bjorn", "01234567890", 12, new ArrayList<String>(), new ArrayList<String>()))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("mobile phones list cannot be empty");
	}

	@Test
	public void shouldCreatePerson() {
		Person person = new Person("Arya", "01234567890", 28, Collections.singletonList("12345678"),
				Collections.singletonList("789456123"));

		assertEquals("01234567890", person.getCpf());
		assertEquals(28, person.getAge());
		assertEquals("Arya", person.getName());
		assertThat(person.getHomePhones()).containsExactly("12345678");
		assertThat(person.getMobilePhones()).containsExactly("789456123");
	}
	
	@Theory
	public void shouldReturnErrorWhenHomePhoneIsEmpty(@FromDataPoints("emptyStrings") String phone) {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList(phone))
				.withMobilePhones(Arrays.asList("1234567890"))
				.build();

		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(01234567890): Possui um telefone fixo vazio.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validate());
	}
	
	@Test
	public void shouldReturnErrorWhenHomePhoneIsInvalid() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withHomePhones(Arrays.asList("123456"))
				.withMobilePhones(Arrays.asList("1234567890"))
				.build();

		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(01234567890): Telefone fixo 123456 invalido.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validate());
	}

	@Test
	public void shouldntReturnMobilePhonesErrors() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("9987876676"))
				.build();

		assertThat(new ArrayList<String>()).containsExactlyElementsOf(somePerson.validate());
	}

	@Test
	public void shouldReturnMobilePhoneErrorWhenInvalidLength() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList("123456789"))
				.build();

		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(01234567890): Telefone movel 123456789 invalido.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validate());
	}
	
	@Theory
	public void shouldReturnMobilePhoneErrorWhenEmpty(@FromDataPoints("emptyStrings") String phone) {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234567890")
				.withMobilePhones(Arrays.asList(phone))
				.build();

		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano(01234567890): Possui um telefone movel vazio.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validate());
	}
	
	@Test
	public void shouldReturnCpfInvalidError() {
		Person somePerson = new PersonBuilder()
				.withName("Fulano")
				.withCpf("01234569865")
				.withMobilePhones(Arrays.asList("1234567890"))
				.build();

		List<String> expectedErrors = new ArrayList<String>();
		expectedErrors.add("Fulano: CPF 01234569865 invalido.");
		assertThat(expectedErrors).containsExactlyElementsOf(somePerson.validate());
	}
}
