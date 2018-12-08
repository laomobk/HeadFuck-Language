
import java.io.*;
import java.util.*;
/*
*The BrainFxxk Language parser in Java
*
*@author LaomoBK
**/
public class headfuck
{
	private static final int MAX_SIZE = 256;//The size of V-RAM
	private FileReader freader;
	private BufferedReader reader;
	private int[] RAM = new int[MAX_SIZE];//cerate V-RAM
	private int pointer = 0;//default 0
	private boolean canread = true;
	
	public headfuck(String path){
		try
		{
			freader = new FileReader(new File(path));
			reader = new BufferedReader(freader);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("No such file");
			canread = false;
		}
	}

	public void parser(){
		if(!canread){
			return;
		}
		String line;
		char c;
		int s_index = 0;
		int end_index = 0;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				for(int i=0;i<line.toCharArray().length;i++){
					c = line.toCharArray()[i];
					if(pointer < 0){
						pointer = 0;
					}
					switch(c){
						case '>':
							pointer++;
							break;
						case '<':
							pointer--;
							break;
						case '+':
							RAM[pointer]=RAM[pointer]+1;		
							break;
						case '-':
							RAM[pointer]=RAM[pointer]-1;
							break;
						case '.':
							System.out.print((char)RAM[pointer]);
							break;
						case '[':
							s_index = i;
							while(line.toCharArray()[end_index+s_index] != ']'){
								end_index += 1;
							}
							end_index = s_index+end_index;
							if(RAM[pointer]==0){
								i = end_index;
							}
							end_index = 0;
							break;
						case ']':
							if(RAM[pointer] != 0){
								i = s_index;
							}
							break;
					}
				}
			}
		}
		catch (IOException e)
		{}catch(ArrayIndexOutOfBoundsException e2){
			System.out.println("\n$Error:Out of memory");
			//e2.printStackTrace();
		}
	}
}
