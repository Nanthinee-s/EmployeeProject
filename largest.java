import java.io.*;
import java.util.*;

class largest
	{
static int largest(int []arr,int n)
	{
		Arrays.sort(arr);
                return arr[n-1];
		return arr[0];
	}
static public void main(String[] args)
	{
		int []arr={10,34,20,50};
		int n=arr.length;
		System.out.println(arr[n-1]+"");
		System.out.println(arr[0]+"");
		largest(arr,n);
	}
}
