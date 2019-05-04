package br.com.cc.printer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsolePrinter implements Printer {

	private final Logger log = LogManager.getLogger(getClass());
	
	@Override
	public boolean print(String text) {
		try {
			System.out.print(text);
		} catch (Exception e) {
			log.error("error on print text {}", text);
			return false;
		}
		return true;
	}

}
