package hw01;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class course {
	private String name;
	private int semester; //0 is fall; 1 is spring; -1 is both
	private int pre_num;
	private int initpreNum;
//	private ArrayList<String> pre;
	private ArrayList<String> next;
//	private boolean ischosen;
	
	
	public course(String _name, int _semester, int _preNum)
	{
		next = new ArrayList<String>();
		name = _name;
		semester = _semester;
		pre_num = _preNum;
		initpreNum = pre_num;
	}
	
	public void addNext(String _nextName)
	{
		next.add(_nextName);
	}
	
	public boolean hasNextis(String s)
	{
		for(int i = 0;i<initpreNum;i++)
		{
			if(next.get(i).equals(s))
				return true;
		}
		return false;
	}
	
	public void deleteNext(String s)
	{
		for(int i = 0;i<initpreNum;i++)
		{
			if(next.get(i).equals(s))
			{
				//next.remove(i);
				pre_num--;
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public int getSemester()
	{
		return semester;
	}
	
	public int getPreNum()
	{
		return pre_num;
	}
	public void setPreNum(int pr)
	{
		pre_num = pr;
	}
	

}
