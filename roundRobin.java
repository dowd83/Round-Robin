import java.util.*;
public class roundRobin
{
	Scanner keyboard = new Scanner(System.in);
	int[] burst;
	int[] process;
	int[] wait;
	int[] turnaround;
  	int size;
	int switches = 0;
	int quantum;
	float bTime=0;
	int taTime=0;
	int flag=0;
    public roundRobin(int size) //constructor
	{
		this.size=size;
		burst=new int[size];
		wait=new int[size];
		turnaround=new int[size];
		process=new int[size];
	}
    public void getTime() //gets burst time and quantum time 
    {
		boolean done = false;
		for(int i=0;i<size;i++)
       {
			System.out.print("Enter burst time of P"+(i+1)+": ");
			process[i] = keyboard.nextInt();
           burst[i] = process[i];
		}
		while (!done) 
		{
			System.out.print("Enter quantum time: ");
			quantum = keyboard.nextInt();
			if (quantum <= 0) 
			{
				System.out.println("Please enter a number greater than 0");
			}
			else 
			{
				done = true;
			}
		}
   	}
	public void roundRobin() //the round robin scheduler and gets wait times and # of context switches
	{
		System.out.println("\n" + "The timeline of the processes are: ");
		do
		{
			flag=0;
			for(int i=0;i<size;i++)
			{
				if(process[i]>=quantum)
				{
					System.out.print("P"+(i+1)+"\t");
					for(int j=0;j<size;j++) 
					{
						if(j==i) {
							process[i]=process[i]-quantum;
						}
						else if(process[j]>0) {
							wait[j]= wait[j] + quantum;
						}
					}
				}
				else if(process[i]>0)
				{
					System.out.print("P"+(i+1)+"\t");
					for(int j=0;j<size;j++)
					{
						if(j==i) {
							process[i] = 0;
						}
						else if(process[j]>0) {
							wait[j]= wait[j] + process[i];
						}
					}
				}
			}
			for (int i= 0; i<size; i++) {
				if (process[i]>0) {
					switches++;
				} 
			}
			for(int i=0;i<size;i++) 
			{
				if(process[i]>0) 
				{
					flag = 1;
				}
			}
       } while(flag==1);
       for(int i=0;i<size;i++) 
		{
			turnaround[i] = wait[i] + burst[i];
		}
		System.out.println();
		System.out.println("The number of context switches are: " + switches);
		System.out.println();
	}
   public void display() //Displays all proceses and burst times, waiting times, and turnaround times for each process. Also displays averages for waiting time and turnaround time for each process.
   {
		System.out.println("\nProcess\tBurst\tWaiting\tTurnaround");
		for(int i=0;i<size;i++)
		{
			System.out.println("P"+(i+1) + "\t" + "\t" + burst[i] + "\t" + "\t" + wait[i] + "\t" +"\t" + turnaround[i]);
			bTime= bTime + wait[i]; //Sums up the burst time of every process
			taTime= taTime + turnaround[i]; //Sums up the turnaround time of every process
       }
		//int utilization = (bTime/(bTime*switches-1)*100);
		System.out.println();
       System.out.println("Average waiting time: "+(bTime/size)); 
       System.out.println("Average Turnaround time: "+(taTime/size)); 
		//System.out.println("Cpu Utilization: " +utilization + "%");
   }
}