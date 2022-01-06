package br.com.erudio.service;

import org.springframework.stereotype.Service;

@Service
public class MathOperationImpl implements MathOperation {

	@Override
	public Double sum(Double x, Double y) {

		return x + y;
	}

	@Override
	public Double minus(Double x, Double y) {
		return x - y;
	}

	@Override
	public Double multiply(Double x, Double y) {
		return x * y;
	}

	@Override
	public Double divide(Double x, Double y) {
		return x / y;
	}

	@Override
	public Double average(Double x, Double y) {
		return (x + y) / 2;
	}

	@Override
	public Double sqrt(Double x) {
		return Math.sqrt(x);
	}

}
