package a5;

import java.util.*;


public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name
    Map<String, Edge> edges;
    int numEdges;
    int numNodes;
    public GraphImpl() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        numEdges = 0;
        numNodes = 0;
    }
    public Map<String, Node> getNodes() {
        return nodes;
    }
    @Override
    public boolean addNode(String name) {
        if (hasNode(name)) {return false;}
        if (name==null) {return false;}
        Node n = new NodeImpl(name);
        nodes.put(name, n);
        numNodes += 1;
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight<0) {return false;}

        if (!hasNode(src)) {return false;}
        if (!hasNode(dest)) {return false;}

        Node s = nodes.get(src); Node d = nodes.get(dest);
        if (s.outEdges().contains(dest)) {return false;}
        if (d.inEdges().contains(src)) {return false;}

        Edge e = new EdgeImpl(s, d, weight);
        numEdges += 1;
        edges.put(src+dest, e);
        return true;
    }

    @Override
    public boolean deleteNode(String name) {
        //Hint: Do you need to remove edges when you delete a node?
        if (!hasNode(name)) {return false;}
        Node n = nodes.get(name);
        for (int i = 0; i < n.outEdges().size(); i++) {
            nodes.get(n.outEdges().get(i)).remInEdgeName(name);
            edges.remove(name+n.outEdges().get(i));
            numEdges -= 1;
        }
        for (int i=0; i < n.inEdges().size(); i++) {
            nodes.get(n.inEdges().get(i)).remOutEdgeName(name);
        }
        nodes.remove(name);
        numNodes -= 1;
        return true;
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        if (!hasNode(src) || !hasNode(dest)) {return false;}
        if (!nodes.get(src).outEdges().contains(dest)) {return false;}
        nodes.get(src).remOutEdgeName(dest);
        nodes.get(dest).remInEdgeName(src);
        edges.remove(src+dest);
        numEdges -= 1;
        return true;
    }

    @Override
    public int numNodes() {
        return numNodes;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public Stack<String> topoSort() {
        Stack<String> stack = new Stack<String>();
        if (nodes.size() == 0) {return stack;}

        Queue<String> q = new LinkedList<String>();
        Collection<Node> c = nodes.values();
        for(Node n: c) {
            if (n.inEdges().size() == 0) {
                q.add(n.getName());
            }
        }

        while (q.peek() != null) {
            Node temp = nodes.get(q.remove());
            stack.push(temp.getName());
            for(String edge: temp.outEdges()) {
                if (nodes.get(edge).inEdges().size()==1) {q.add(edge);}
            }
            this.deleteNode(temp.getName());
        }

        if(this.numNodes()==0) {return stack;}
        else {return new Stack<String>();}

    }

    public boolean hasNode(String name) {
        if (nodes.containsKey(name)) {return true;}
        return false;
    }

    public void print() {
        Collection<Node> c = nodes.values();
        for(Node n: c) {
            System.out.println(n.getName());
            for(String e: n.outEdges()) {
                System.out.println("\t" + n.getName() + "-->" + e);
            }
        }
    }
}
