import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class prueba extends RecursiveTask<int[]>{
    int[] arreglo;
    int n;
    int m;
    public prueba(int n, int m,int[] matriz) {
        this.n=n;
        this.m=m;
        this.arreglo=matriz;
    }
    public static void main(String[] args) {
        int[] matriz;
        matriz=new int[100];
        int i;
        for(i=0;i<100;i++)
            matriz[i]=(int)(Math.random()*100);
        ForkJoinPool commonPool=ForkJoinPool.commonPool();
        matriz=commonPool.invoke(new prueba(0,99,matriz));
        System.out.println(Arrays.toString(matriz));
    }
    @Override
    protected int[] compute() {
        int[] res=new int[(m-n)+1];
        int largo=(m-n)+1;
        if(m>n) {
            int i,d,j;
            int[] izq;
            prueba taskIzq;
            int[] der;
            taskIzq=new prueba(n,n+((m-n)/2),arreglo);
            taskIzq.fork();
            der=new prueba(n+((m-n)/2)+1,m,arreglo).compute();
            izq=taskIzq.join();
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