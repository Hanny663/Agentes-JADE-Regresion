public class MLR {
    private double[][] x;
    private double[] y;
    private double[] betas;

    public MLR(double[][] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public void calcularBetas() {
        int n = x.length;
        int variables = x[0].length + 1;

        double[][] matriz = new double[variables][variables + 1];

        for (int i = 0; i < n; i++) {
            double[] fila = new double[variables];
            fila[0] = 1;

            for (int j = 1; j < variables; j++) {
                fila[j] = x[i][j - 1];
            }

            for (int j = 0; j < variables; j++) {
                for (int k = 0; k < variables; k++) {
                    matriz[j][k] += fila[j] * fila[k];
                }

                matriz[j][variables] += fila[j] * y[i];
            }
        }

        for (int i = 0; i < variables; i++) {
            int mayor = i;

            for (int j = i + 1; j < variables; j++) {
                if (Math.abs(matriz[j][i]) >
                    Math.abs(matriz[mayor][i])) {
                    mayor = j;
                }
            }

            double[] temporal = matriz[i];
            matriz[i] = matriz[mayor];
            matriz[mayor] = temporal;

            double divisor = matriz[i][i];

            for (int j = i; j <= variables; j++) {
                matriz[i][j] /= divisor;
            }

            for (int j = 0; j < variables; j++) {
                if (j != i) {
                    double factor = matriz[j][i];

                    for (int k = i; k <= variables; k++) {
                        matriz[j][k] -= factor * matriz[i][k];
                    }
                }
            }
        }

        betas = new double[variables];

        for (int i = 0; i < variables; i++) {
            betas[i] = matriz[i][variables];
        }
    }

    public double predecir(double... valores) {
        double resultado = betas[0];

        for (int i = 0; i < valores.length; i++) {
            resultado += betas[i + 1] * valores[i];
        }

        return resultado;
    }

    public double calcularR2() {
        double promedio = 0;

        for (double valor : y) {
            promedio += valor;
        }

        promedio /= y.length;

        double total = 0;
        double error = 0;

        for (int i = 0; i < y.length; i++) {
            double prediccion = predecir(x[i]);

            total += Math.pow(y[i] - promedio, 2);
            error += Math.pow(y[i] - prediccion, 2);
        }

        return 1 - (error / total);
    }

    public double[] getBetas() {
        return betas;
    }
}