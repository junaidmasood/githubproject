import java.util.Scanner;

//TERI MAA KI CHOOT
public class romaninteger {
public static void main(String args[])
{
	System.out.println("Enter String in Roman");
	Scanner s=new Scanner(System.in);
	String input=s.next();
	int answer=0;
	int dummy=0;
	for(int i=0;i<input.length();i++)
	{
	if(i<input.length()-3&&input.charAt(i)==input.charAt(i+1)&&input.charAt(i)==input.charAt(i+2)&&input.charAt(i)==input.charAt(i+3))
	{
		System.out.println("incorrect input");
		System.exit(0);
	}
	if(i<input.length()-2&&value(input.charAt(i))<value(input.charAt(i+1))&&value(input.charAt(i+1))<value(input.charAt(i+2)))
	{
		System.out.println("incorrect input");
		System.exit(0);
	}
		if(i!=input.length()-1&&value(input.charAt(i))<value(input.charAt(i+1)))
			{
		    dummy=value(input.charAt(i+1))-value(input.charAt(i));
		   i++;
			}
	else
		dummy = value(input.charAt(i));
	
		answer=answer + dummy;
	}
	System.out.println(answer);
}

public static int value(char i)
	{
	int out=0;
    if(i=='I')
	  out=1;
	else if(i=='V')
		out=5;
	else if(i=='X')
		out=10;
	else if(i=='L')
		out=50;
	else if(i=='C')
		out=100;
	
	return out;
}
}
