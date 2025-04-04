package com.bo.upb.algoritmica.conteo;

/**
 * Conteo
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Conteo {

    // T[n] --------------------------------------------------------
    private void swap(int[] v, int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }

    public int otro(int n) {
        int c;
        int b = 5;
        int a = 1;
        while (a <= b) {
            c = a + b;
            a++;
        }
        return n * a;
    }

    public long fibo(int n) {
        long f2 = 1;
        long f1 = 0;
        long actual = 0;
        int i = 0;
        while (i < n) {
            actual = f1 + f2;
            f2 = f1;
            f1 = actual;
            i++;
        }
        return actual;
    }

    public int pro1(int n) {
        int a = 1;
        int c = 0;
        while (a <= n) {
            System.out.println("a <= n");
            int b = 0;
            while (b < n - 1) {
                System.out.println("pro1");
                b++;
                c++;
            }
            a++;
        }
        return c;
    }

    public int pro2(int n) {
        int a = 0;
        int c = 0;
        while (a <= n + 1) {
            System.out.println("a <= n + 1");
            int b = 0;
            while (b < a) {
                System.out.println("pro2");
                b++;
                c++;
            }
            a++;
        }
        return c;
    }

    public int complex1(int n) {
        int a = 0; int c = 0;
        while (a < n) {
            int b = 0;
            while (b < n) {
                System.out.println("b < n");
                c = b;
                while (c < n) {
                    System.out.println("complex1");
                    c++;
                }
                b++;
            }
            a++;
        }
        return c;
    }

    public void complex2(int n) {
        int a = 1; int m = 1;
        while (a <= n - 1) {
            int b = a + 1;
            while (b <= n) {
                int c = 1;
                while (c <= b) {
                    System.out.println("complex2");
                    m = m * 2;
                    c++;
                }
                b++;
            }
            a++;
        }
    }

    // T[n] mejor y peor caso --------------------------------------
    public int max(int[] v) {
        int max = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] > max)
                max = v[i];
        }
        return max;
    }

//    public void bubbleSort(int[] v) {
//        int limit = v.length - 2;
//        while (limit > 0) {
//            int i = 0;
//            while (i <= limit) {
//                if (v[i] > v[i + 1])
//                    swap(v, i, i + 1);
//                i++;
//            }
//            limit--;
//        }
//    }

    public void burbujear(int[] v, int m) {
        int i = 0;
        while (i <= m) {
            if (v[i] > v[i + 1])
                swap(v, i, i + 1);
            i++;
        }
    }

    public void bubbleSort(int[] v) {
        int m = v.length - 2;
        while (m > 0) {
            burbujear(v, m);
            m--;
        }
    }

    public boolean busBin(int[] v, int nro) {
        int i = 0;
        int f = v.length - 1;
        boolean hallado = false;
        while (i <= f && !hallado) {
            int m = (i + f) / 2;
            if (v[m] == nro) {
                hallado = true;
            } else if (nro < v[m]) {
                f = m - 1;
            } else {
                i = m + 1;
            }
        }
        return hallado;
    }

    public static void main(String[] args) {
        Conteo c = new Conteo();
//        c.otro(10);
//
//        System.out.println(c.fibo(7));

        //int[] values = {0, 1, 2, 3, 4};
        //System.out.println(c.max(values));
        //c.pro2(7);
        c.complex2(5);
    }
}
