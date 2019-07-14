import java.util.*;
import java.lang.*;

class FKS
{
	int heap_size;
	A[] build_heap(A[] ob)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			ob=min_heapify(ob,i);
		return ob;
	}
	A[] min_heapify(A[] ob,int i)
	{
		int l,r,smallest;
		A temp=new A(0,0,0,0);
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && ob[l].pw<ob[i].pw)
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && ob[r].pw<ob[smallest].pw)
			smallest=r;
		if(smallest!=i)
		{
			temp.p=ob[i].p;
			temp.w=ob[i].w;
			temp.pw=ob[i].pw;
			temp.ob_no=ob[i].ob_no;
			
			ob[i].p=ob[smallest].p;
			ob[i].w=ob[smallest].w;
			ob[i].pw=ob[smallest].pw;
			ob[i].ob_no=ob[smallest].ob_no;
			
			ob[smallest].p=temp.p;
			ob[smallest].w=temp.w;
			ob[smallest].pw=temp.pw;
			ob[smallest].ob_no=temp.ob_no;
			
			ob=min_heapify(ob,smallest);
		}
		return ob;
	}
	A[] heap_sort_dec(A[] ob)
	{
		A temp=new A(0,0,0,0);
		while(heap_size>1)
		{
			temp.p=ob[1].p;
			temp.w=ob[1].w;
			temp.pw=ob[1].pw;
			temp.ob_no=ob[1].ob_no;
			
			ob[1].p=ob[heap_size].p;
			ob[1].w=ob[heap_size].w;
			ob[1].pw=ob[heap_size].pw;
			ob[1].ob_no=ob[heap_size].ob_no;
			
			ob[heap_size].p=temp.p;
			ob[heap_size].w=temp.w;
			ob[heap_size].pw=temp.pw;
			ob[heap_size].ob_no=temp.ob_no;
			
			heap_size--;
		}
		return ob;
	}
	float knapsack(A[] ob,float cap)
	{
		int i;
		float profit=0.0f;
		for(i=1;i<=ob.length-1;i++)
		{
			if(cap>=ob[i].w)
			{
				cap=cap-ob[i].w;
				profit=profit+ob[i].p;
			}
			else
			{
				profit=profit+cap*(ob[i].p)/(ob[i].w);
				cap=0;
			}
		}
		return profit;
	}
	public static void main(String args[])
	{
		int i,n,ob_no;
		float p,w,pw;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter no. of objects: ");
		n=in.nextInt();
		A[] ob=new A[n+1];
		for(i=1;i<=n;i++)
		{
			System.out.print("Enter profit and weight associated with "+i+" object respectively: ");
			p=in.nextFloat();
			w=in.nextFloat();
			pw=p/w;
			ob[i]=new A(p,w,pw,i);
		}
		FKS x=new FKS();
		x.heap_size=n;
		ob=x.build_heap(ob);
		ob=x.heap_sort_dec(ob);
		System.out.print("Enter the capacity of the knapsack: ");
		float cap=in.nextFloat();
		float max_profit=x.knapsack(ob,cap);
		System.out.println("\nMax Profit = "+max_profit);
	}
}
class A
{
	float p;
	float w;
	int ob_no;
	float pw;
	A(float p,float w,float pw,int ob_no)
	{
		this.p=p;
		this.w=w;
		this.pw=pw;
		this.ob_no=ob_no;
	}
}