package br.com.cc.printer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConsolePrinterTest {

	@Test
	public void shouldPrint() {
		ConsolePrinter consolePrinter = new ConsolePrinter();
		assertTrue(consolePrinter.print("winter is coming"));
	}
}
