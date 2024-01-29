package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code

		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	public static Binary or(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder paddedNum1 = paddingFunction(num1.number, maxLength);
		StringBuilder paddedNum2 = paddingFunction(num2.number, maxLength);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char digit1 = paddedNum1.charAt(i);
			char digit2 = paddedNum2.charAt(i);

			if (digit1 == '1' || digit2 == '1') {
				result.append('1');
			} else {
				result.append('0');
			}
		}

		return new Binary(result.toString());
	}

	public static Binary and(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder paddedNum1 = paddingFunction(num1.number, maxLength);
		StringBuilder paddedNum2 = paddingFunction(num2.number, maxLength);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char digit1 = paddedNum1.charAt(i);
			char digit2 = paddedNum2.charAt(i);

			if (digit1 == '1' && digit2 == '1') {
				result.append('1');
			} else {
				result.append('0');
			}
		}

		return new Binary(result.toString());
	}

	public static Binary multiply(Binary num1, Binary num2) {
		Binary result = new Binary("0");
		Binary multiplier = new Binary(num1.number);

		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder paddedNum2 = paddingFunction(num2.number, maxLength);

		while (!paddedNum2.toString().equals("0")) {
			if (paddedNum2.charAt(paddedNum2.length() - 1) == '1') {
				result = add(result, multiplier);
			}

			paddedNum2 = new StringBuilder(paddedNum2.substring(0, paddedNum2.length() - 1));
			multiplier = new Binary(multiplier.number + "0");
		}

		return result;
	}
	private static StringBuilder paddingFunction(String binaryString, int length) {
		int paddingLength = Math.max(0, length - binaryString.length());
		StringBuilder paddedBinary = new StringBuilder(binaryString);
		for (int i = 0; i < paddingLength; i++) {
			paddedBinary.insert(0, '0');
		}
		return paddedBinary;
	}

}	
