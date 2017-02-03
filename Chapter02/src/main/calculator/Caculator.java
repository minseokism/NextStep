package main.calculator;

public class Caculator {
	public int add(int num1, int num2){
        return num1 + num2;
    }
	
	public int subtract(int num1, int num2){
        return num1 - num2;
    }
	
	public int multiply(int num1, int num2){
        return num1 * num2;
    }
	
	public int divide(int num1, int num2){
        return num1 / num2;
    }
	
	public static void main(String[] args) {
		Caculator cal = new Caculator();
		System.out.println(cal.add(3, 4));
		System.out.println(cal.subtract(5, 4));
		System.out.println(cal.multiply(2, 6));
		System.out.println(cal.divide(8, 4));
	}
}
