//CSC401 HW03
//Justin Ace Ruiz

import java.util.*;

public class main 
{	
	public static void main(String[] args) 
	{
		System.out.println("Please input two binary numbers seperated by a space.");
		
		Scanner input = new Scanner(System.in);
		String first = input.next();
		String second = input.next();
		System.out.printf("Binary multiplication of %s and %s is %d\n", first, second, mult(first, second));
		input.close();
	}
	
	public static long mult(String x, String y)
	{	
		int len1 = x.length();
		int len2 = y.length();
		
		if (len1 < len2)
			for (int i = 0; i < len2 - len1; i++)
				x = '0' + x;
		else if (len1 > len2)
			for (int i = 0; i < len1 - len2; i++)
				y = '0' + y;
		
		int n = x.length();
		
		if (n == 0)
			return 0;
		if (n == 1)
			return multSingleBit(x, y);
		
		int firstHalf = n/2;
		int secondHalf = (n-firstHalf);
		
		String leftX = x.substring(0, firstHalf);
		String rightX = x.substring(firstHalf, n);
		
		String leftY = y.substring(0, firstHalf);
		String rightY = y.substring(firstHalf, n);
		
		long p1 = mult(leftX, leftY);
		long p2 = mult(rightX, rightY);
		long p3 = mult(addBits(leftX, rightX), addBits(leftY, rightY));
		
		return p1*(1<<(2*secondHalf)) + (p3-p1-p2)*(1<<secondHalf) + p2;
	}
	
	public static int multSingleBit(Object x, Object y)
	{
		char[] a = ((String) (x)).toCharArray();
		char[] b = ((String) (y)).toCharArray();
		
		return (a[0] - '0') * (b[0] - '0');
	}
	
	public static String addBits(String first, String second)
	{
		String result = "";
		int carry = 0;
		
		int len1 = first.length();
		int len2 = second.length();
		
		if (len1 < len2)
			for (int i = 0; i < len2 - len1; i++)
				first = '0' + first;
		else if (len1 > len2)
			for (int i = 0; i < len1 - len2; i++)
				second = '0' + second;
		
		int len = first.length();
		
		for (int i = len-1; i >= 0; i--)
		{
			int bit1 = first.charAt(i) - '0';
			int bit2 = second.charAt(i) - '0';
			
			int sum = (bit1 ^ bit2 ^ carry) + '0';
			
			result = (char) sum + result;
			
			carry = (bit1 & bit2) | (bit2 & carry) | (bit1 & carry);
		}
		
		if (carry == 1)
			result = '1' + result;
		return result;
	}

}
