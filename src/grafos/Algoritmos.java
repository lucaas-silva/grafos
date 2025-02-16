package grafos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Nesta classe devem ser implementados todos os métodos de grafos de forma
 * estática
 *
 * @author vilson.junior
 */
public class Algoritmos {

    public static ArrayList<String> percorreLargura(Grafo g, Vertice inicio) {
        ArrayList<String> resultado = new ArrayList<>();
        Queue<Vertice> fila = new LinkedList<>();
        inicio.definirDistancia(0);

        for (int i = 0; i < g.obterVertices().size(); i++) {
            g.obterVertices().get(i).zerarVisitas();
            if (g.obterVertices().get(i) != inicio) {
                g.obterVertices().get(i).zerarDistancia();
                // a chamada de método acima, na verdade está definindo a distancia como infinito
            }
        }

        inicio.visitar();
        inicio.setCaminho("");
        fila.add(inicio);
        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();

            for (Arco arco : atual.obterArcos()) {
                Vertice adjacente = arco.getDestino();
                if (adjacente.obterVisitado() == 0) {
                    adjacente.visitar();
                    adjacente.definirDistancia(atual.obterDistancia() + 1);
                    fila.add(adjacente);
                    adjacente.setCaminho(atual.getCaminho());
                }
            }
            resultado.add(atual.getCaminho() + "\n   distância: " + atual.obterDistancia());
        }
        return resultado;
    }

    public static ArrayList<String> percorreProfundidade(Grafo g, Vertice inicio) {

        ArrayList<String> resultado = new ArrayList<>();
        Stack<Vertice> pilha = new Stack<>();
        inicio.definirDistancia(0);

        for (int i = 0; i < g.obterVertices().size(); i++) {
            g.obterVertices().get(i).zerarVisitas();
            if (g.obterVertices().get(i) != inicio) {
                g.obterVertices().get(i).zerarDistancia();
                // a chamada de método acima, na verdade está definindo a distancia como infinito
            }
        }

        inicio.visitar();
        inicio.setCaminho("");
        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            Vertice atual = pilha.pop();

            for (Arco arco : atual.obterArcos()) {
                Vertice adjacente = arco.getDestino();
                if (adjacente.obterVisitado() == 0) {
                    adjacente.visitar();
                    adjacente.definirDistancia(atual.obterDistancia() + 1);
                    pilha.push(adjacente);
                    adjacente.setCaminho(atual.getCaminho());
                }
            }
            
            resultado.add(atual.getCaminho() + "\n    distância: " + atual.obterDistancia());
        }
        return resultado;
    }
    
    
    public static ArrayList<String> dijkstra(Grafo g, Vertice inicio) {
        for (int i = 0; i < g.obterVertices().size(); i++) {
            g.obterVertices().get(i).zerarDistancia();
            g.obterVertices().get(i).setCaminho("");
        }

        inicio.definirDistancia(0);

        ArrayList<Vertice> verticesNaoVisitados = new ArrayList<>(g.obterVertices());
        ArrayList<String> resultado = new ArrayList<>();

        while (!verticesNaoVisitados.isEmpty()) {
            // procura o vertice com menor distancia na lista
            Vertice verticeAtual = verticesNaoVisitados.get(0);
            for (Vertice vertice : verticesNaoVisitados) {
                if (vertice.obterDistancia() < verticeAtual.obterDistancia()) {
                    verticeAtual = vertice;
                }
            }   

            verticesNaoVisitados.remove(verticeAtual);
            
            // atualiza vertices vizinhos
            for (Arco arco : verticeAtual.obterArcos()) {
                Vertice adjacente = arco.getDestino();
                double novaDistancia = verticeAtual.obterDistancia() + arco.getPeso();

                if (novaDistancia < adjacente.obterDistancia()) {
                    adjacente.definirDistancia(novaDistancia);
                    adjacente.setCaminho(verticeAtual.getCaminho());
                }
            }
        }
  
        for (Vertice vertice : g.obterVertices()) {
            if(vertice.obterDistancia() == Double.POSITIVE_INFINITY){
                resultado.add("  " + vertice.getRotulo() + " desconexo");
            }else{
                resultado.add(vertice.getCaminho() + "\n   distância: " + vertice.obterDistancia());
            }
        }    
        return resultado;
    }
    

}
