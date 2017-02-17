
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class graduate {
	
	
	private static ArrayList<course> data;
	

	private static String[][] Array;
	private static int i,j;
	private static int max_Num;
	private static int total_Num;
	private static int seme;
	private static int comj = 0;
	private static ArrayList<Integer>min = new ArrayList<Integer>();

	private static Scanner scanner;
	
	public static void countSemester()
	{
		while(!data.isEmpty())
		{
			j = 0;
			for(int n = data.size()-1;n>=0;n--)
			{
				if((data.get(n).getSemester()==i%2||data.get(n).getSemester()==-1) && data.get(n).getPreNum()==0)
				{
					Array[i][j] = data.get(n).getName();
				    j++;
				//    data.remove(n);
				}
			}
			// if can be selected courses <= than maximum, do topological sorting
			if(j<=max_Num)
			{
				deleteSemester(Array[i]);
				i++;
			}
			//else need to compare each possibilies
			if(j>max_Num)
			{
				int rows = combinationNum(j, max_Num);
				String[][] totalCombination = new String[rows][max_Num];
				String[] oneCombination = new String[max_Num];
				String[] allSet = new String[j];
				for(int ai = 0;ai<j;ai++)
					allSet[ai] = Array[i][ai];
				comj = 0;
				totalCombination = combination(allSet, max_Num, 0, 0, oneCombination, totalCombination);
				ArrayList<course> saved_data = new ArrayList<course>(data);
				ArrayList<Integer> saved_preNum = new ArrayList<Integer>();
				for(int spi=0;spi<data.size();spi++)
					saved_preNum.add(data.get(spi).getPreNum());
				int saved_i = i;
				
				for(int ti=0;ti<rows;ti++)
				{
					if(data.isEmpty())
					{
						data = new ArrayList<course>(saved_data);
						for(int ndi=0;ndi<data.size();ndi++)
							data.get(ndi).setPreNum(saved_preNum.get(ndi));
						i = saved_i;
						for(int tempi = 0;tempi<2*total_Num;tempi++){
							for(int tempj = 0;tempj<total_Num;tempj++){
								Array[tempi][tempj] = null;
							}
						}
							
					}
					deleteSemester(totalCombination[ti]);
					i++;
					countSemester();
				}			
			}
			
		}
		//System.out.println("The minimum number of semesters required to graduate is " + i);
		//i=0;
		min.add(i);
	}
	
	public static int findMin(ArrayList<Integer>_min) {
		int Min = 9999;
		for(int mi=0;mi<_min.size();mi++)
		{
			if(_min.get(mi)<=Min)
				Min = min.get(mi);
		}
		return Min;	
	}

	public static int combinationNum(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	public static int factorial(int i) {
		if(i>=0)
		{
		if (i == 0)
			return 1; 
		else 
			return i * factorial(i - 1);
		}
		else
			return -1;
	}
	
	public static void deleteSemester(String[] strings) {
		for(int ci=0;ci<strings.length;ci++)
		{
			for(int cj=data.size()-1;cj>=0;cj--)
			{
				if(data.get(cj).hasNextis(strings[ci]))
					data.get(cj).deleteNext(strings[ci]);
				if(data.get(cj).getName().equals(strings[ci]))
					data.remove(cj);
				
			}
				
		}
		
	}
	public static String[][] combination(String[] allSet, int _maxNum, int start, int index, String[] subSet, String[][] result) {
		
		if (index == _maxNum)
		{
			for (int comi = 0; comi < _maxNum; comi++) 
				result[comj][comi] = subSet[comi];
			
			comj++;
			return result;
		}
		for (int comi = start; comi <= allSet.length-1&&allSet.length-comi>=_maxNum-index; comi++) {
			subSet[index] = allSet[comi];
			combination(allSet, _maxNum, comi + 1, index+1, subSet, result);
		}
		return result;

	}
	

	public static void main(String[] args) throws FileNotFoundException
	{

//		File file = new File("graduate.txt");
		scanner = new Scanner(System.in);
		
		while(true)
		{
			
			
			data = new ArrayList<course>();
			
			total_Num = scanner.nextInt();
			max_Num = scanner.nextInt();
			if(max_Num == -1&&total_Num == -1)
			{
				scanner.close();
				break;
			}
		
				for(int p = 0;p<total_Num;p++)
				{
					String temp_name = scanner.next();
				}
				for(int p = 0;p<total_Num;p++)
				{
					
					String name = scanner.next();
					String ss = scanner.next();
					
				    if(ss.equals("F"))
				    	seme = 0;
				    else if(ss.equals("S"))
				    	seme = 1;
				    else 
				    	seme = -1;
				    int pre_Num = scanner.nextInt();
//				    System.out.println(name);
//				    System.out.println(seme);
//				    System.out.println(pre_Num);
				    data.add(new course(name,seme,pre_Num));
				    if(pre_Num!=0)
				    {
				    	for(int f = 0;f<pre_Num;f++)
				    	{
				    		//System.out.println(sin.next());
				    		//System.out.println(q);
				    		data.get(p).addNext(scanner.next());
				    		
				    	}
				    }
				 
				}
//				System.out.println("for loop end");
				Array = new String[2*total_Num][total_Num];
				i=0;
				countSemester();
				System.out.println("The minimum number of semesters required to graduate is " + findMin(min));
				data.clear();
				min.clear();
				
				
			
		}
			
	}

}
