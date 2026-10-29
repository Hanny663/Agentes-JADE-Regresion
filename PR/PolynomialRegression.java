public class PolynomialRegression{
    private double beta0;
    private double beta1;
    private double beta2;
    private final double[] x;
    private final double[] y;

    public PolynomialRegression(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        fit();
    }

    private void fit() {
        int n = x.length;
        double sumX = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0;
        double sumY = 0, sumXY = 0, sumX2Y = 0;

        for (int i = 0; i < n; i++) {
            double xi = x[i];
            double yi = y[i];
            double xi2 = xi * xi;

            sumX += xi;
            sumX2 += xi2;
            sumX3 += xi2 * xi;
            sumX4 += xi2 * xi2;

            sumY += yi;
            sumXY += xi * yi;
            sumX2Y += xi2 * yi;
        }

        double[][] matrix = {
            {n, sumX, sumX2,sumY},
            {sumX, sumX2, sumX3,sumXY},
            {sumX2, sumX3, sumX4,sumX2Y}
        };

        for (int i = 0; i <3; i++){
            double pivot = matrix[i][i];
            for (int j = 0; j <= 3; j++){
                matrix[i][j] /= pivot;
            }
            for (int k = 0; k < 3; k++){
                if (k != i){
                    double factor = matrix[k][i];
                    for (int j = 0; j <= 3; j++){
                        matrix[k][j] -= factor * matrix[i][j];
                    }
                }
            }
        }

        this.beta0 = matrix[0][3];
        this.beta1 = matrix[1][3];
        this.beta2 = matrix[2][3];
    }

    public double predict(double xi) {
        return beta0 + (beta1 * xi) + (beta2 * xi * xi);
    }

    public double calculateRSquared() {
        double sumY = 0;
        for (double val : y) {
            sumY += val;
        }
        double meanY = sumY / y.length;

        double ssTotal = 0;
        double ssResidual = 0;
        for (int i = 0; i < y.length; i++) {
            double yPred = predict(x[i]);
            ssResidual += Math.pow(y[i] - yPred, 2);
            ssTotal += Math.pow(y[i] - meanY, 2);
        }
        return 1.0 - (ssResidual / ssTotal);
    }

    public double getBeta0() {
        return beta0;
    }
    public double getBeta1() {
        return beta1;
    }
    public double getBeta2() {
        return beta2;
    }    
}