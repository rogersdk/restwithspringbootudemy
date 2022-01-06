package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converter.NumberConverter;
import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.service.MathOperation;

@RestController
public class MathController {

	@Autowired
	private MathOperation mathOperation;

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		validateNumberInputs(numberOne, numberTwo);

		return mathOperation.sum(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/minus/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double minus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		validateNumberInputs(numberOne, numberTwo);

		return mathOperation.minus(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		validateNumberInputs(numberOne, numberTwo);

		return mathOperation.divide(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		validateNumberInputs(numberOne, numberTwo);

		return mathOperation.multiply(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		validateNumberInputs(numberOne, numberTwo);

		return mathOperation.average(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable("number") String number) throws Exception {
		validateNumber(number);

		return mathOperation.sqrt(NumberConverter.convertToDouble(number));
	}

	private void validateNumberInputs(String numberOne, String numberTwo) {
		validateNumber(numberOne);
		validateNumber(numberTwo);
	}

	private void validateNumber(String strNumber) {
		if (!NumberConverter.isNumeric(strNumber)) {
			throw new UnsuportedMathOperationException("Please, set a numeric value!");
		}
	}

}
