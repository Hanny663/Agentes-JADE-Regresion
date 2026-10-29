import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent {

    protected void setup() {
        System.out.println("Agente MLR iniciado.");

        addBehaviour(new OneShotBehaviour() {
            public void action() {
                try {
                    Dataset dataset = new Dataset();
                    dataset.cargarDatos("Advertising.csv");

                    System.out.println("Registros leidos: "
                        + dataset.getNumeroRegistros());

                    MLR modelo = new MLR(
                        dataset.getX(),
                        dataset.getY()
                    );

                    modelo.calcularBetas();

                    double[] b = modelo.getBetas();

                    System.out.println();
                    System.out.println("Betas del modelo:");
                    System.out.println("Beta 0: " + b[0]);
                    System.out.println("Beta 1 (TV): " + b[1]);
                    System.out.println("Beta 2 (Radio): " + b[2]);
                    System.out.println("Beta 3 (Newspaper): " + b[3]);

                    System.out.println();
                    System.out.println("Ecuacion de regresion multiple:");
                    System.out.println("Sales = " + b[0]
                        + " + (" + b[1] + " * TV)"
                        + " + (" + b[2] + " * Radio)"
                        + " + (" + b[3] + " * Newspaper)");

                    System.out.println();
                    System.out.println("R cuadrada: "
                        + modelo.calcularR2());

                    System.out.println();
                    System.out.println("Predicciones de ejemplo:");

                    System.out.println(
                        "TV=100, Radio=20, Newspaper=30 -> Sales="
                        + modelo.predecir(100, 20, 30)
                    );

                    System.out.println(
                        "TV=200, Radio=40, Newspaper=50 -> Sales="
                        + modelo.predecir(200, 40, 50)
                    );

                    System.out.println();
                    System.out.println("Modelo ejecutado correctamente.");

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

                myAgent.doDelete();
            }
        });
    }
}