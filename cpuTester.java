import java.util.*;
class cpuTester
{
	public static void main(String arg[])
    {
		boolean done = false;
		while (!done) 
		{
			try { 
				Scanner keyboard=new Scanner(System.in);
				System.out.print("Enter the number of processes: ");
				int input=keyboard.nextInt();
				if (input <= 0) 
				{
					System.out.println("You must enter at least one process greater than 0");
				}
				else {
					roundRobin cpu1 = new roundRobin(input); //creating the cpu scheduler
					cpu1.getTime(); //method will get burst times from the user
					cpu1.roundRobin(); //method will schedule the processes by burst times 
					cpu1.display(); //method will display a gui for user and display other information
					done = true;
				}
			} catch (Exception e) 
			{
				System.out.println("Invalid input, please start over");
			}
		}
	}
}