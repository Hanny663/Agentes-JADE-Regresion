import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class PRAgent extends Agent {
    
    @Override 
    protected void setup() {
        System.out.println("[INICIO] Agente " + getLocalName() + " iniciado.");
        addBehaviour(new PRBehaviour());
    }

    private class PRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            DataSet ds = new DataSet();

            PolynomialRegression pr = new PolynomialRegression(ds.getX(), ds.getY());
            double b0 = pr.getBeta0();
            double b1 = pr.getBeta1();
            double b2 = pr.getBeta2();

            System.out.println("\n=======================================================");
            System.out.println("  ECUACION DE REGRESION POLINOMIAL (GRADO 2)");
            System.out.println("=======================================================");
            System.out.printf("Machine Efficiency = (%.4f) + (%.4f)*BatchSize + (%.6f)*BatchSize^2\n", b0, b1, b2);
            System.out.println("-------------------------------------------------------");

            double[] valoresPrueba ={40.0,75.0,120.0};
            System.out.println("Predcciones de Eficiencia: ");
            for (double batch : valoresPrueba) {
                double prediccion = pr.predict(batch);
                System.out.printf("  Para Batch Size = %.1f  -->  Eficiencia Estimada = %.2f%%\n", batch, prediccion);
            }
            System.out.println("-------------------------------------------------------");

            double r2 = pr.calculateRSquared();
            System.out.printf("Coeficiente de determinacion R^2: %.4f (Ajuste: %.2f%%)\n", r2, r2 * 100);
            System.out.println("=======================================================\n");

            myAgent.doDelete();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("[FIN] Agente " + getLocalName() + " ha muerto tras concluir sus tareas.");
    }
}
