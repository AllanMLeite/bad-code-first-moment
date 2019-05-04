package br.com.cc.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class CpfValidatorTest {

	@DataPoints("validCpfs")
	public static String[] validCpfs = { "34046824085", "50074056042", "02241868094", 
			"88828577002", "50523914040", "28789026020", "49454509004", "92410034055" };

	@DataPoints("invalidCpfs")
	public static String[] invalidCpfs = { "1111111111", "2222222222", "56735467895",
			"123", "abc" };

	@Theory
	public void shouldReturnTrueWhenalidCpf(@FromDataPoints("validCpfs") String cpf) {
		assertTrue(CpfValidator.isValidCpf(cpf));
	}
	
	@Theory
	public void shouldReturnFaseWhenInvalidCpf(@FromDataPoints("invalidCpfs") String cpf) {
		assertFalse(CpfValidator.isValidCpf(cpf));
	}
}
