package a5;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods

        Graph g = new GraphImpl();

        g.addNode("1");
        g.addNode("2");
        g.addNode("3");
        g.addNode("4");
        g.addNode("5");
        g.addNode("6");
        g.addNode("7");
        g.addEdge("1","2",1);
        g.addEdge("2","5",1);
        g.addEdge("5","7",1);
        g.addEdge("7","6", 1);
        g.addEdge("1","3",1);
        g.addEdge("1","4",1);
        g.addEdge("4","6",1);
        g.addEdge("4","7",1);
        g.addEdge("5","4",1);
        g.addEdge("3","6",1);
        g.addEdge("2","4",1);
        g.addEdge("4","3",1);
        g.print();

        System.out.println(g.topoSort());




    }

}
