import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import prueba15.prueba;
public class prueba extends RecursiveTask<int[]>{
	int[] arreglo;
	int n;
	int m;
	int corte;
	public prueba(int n, int m,int[] matriz, int corte) {
		this.n=n;
		this.m=m;
		this.arreglo=matriz;
		this.corte=corte;
	}
	public static void main(String[] args) {
		int[] matriz;
		matriz=new int[100];
		int i;
		for(i=0;i<100;i++)
			matriz[i]=(int)(Math.random()*100);
		ForkJoinPool commonPool=ForkJoinPool.commonPool();
		matriz=commonPool.invoke(new prueba(0,99,matriz,1));
		System.out.println(Arrays.toString(matriz));
		System.out.println(matriz.length);
	}
	@Override
	protected int[] compute() {
		int[] res=new int[(m-n)+1];
		int largo=(m-n)+1;
		if(m>n) {
			int i,d,j;
			int[] izq;
			prueba taskIzq, taskDer;
			int[] der;
			taskIzq=new prueba(n,n+((m-n)/2),arreglo,corte+1);
			taskDer=new prueba(n+((m-n)/2)+1,m,arreglo,corte+1);
			if(corte>4) {
				izq=taskIzq.compute();
				der=taskDer.compute();
			}
			else {
				taskIzq.fork();
				der=taskDer.compute();
				izq=taskIzq.join();
			}
			i=0;
			d=0;
			for(j=0;j<largo;j++) {
				if(i<izq.length && d<der.length) {
					if(izq[i]<der[d]) {
						res[j]=izq[i];
						i++;
					}
					else {
						res[j]=der[d];
						d++;						
					}
				}
				else {
					if(i<izq.length) {
						res[j]=izq[i];
						i++;
					}
					else {
						res[j]=der[d];
						d++;
					}
				}
			}
		}
		else {
			res[0]=arreglo[m];
		}
		return res;
	}


}