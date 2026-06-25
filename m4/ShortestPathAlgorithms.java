package m4;

import models.Route;

import java.util.*;

public class ShortestPathAlgorithms {

    private Map<String, List<Edge>> graph =
            new HashMap<>();

    static class Edge {

        String destination;
        int weight;

        Edge(String destination,
             int weight) {

            this.destination = destination;
            this.weight = weight;
        }
    }

    public void addRoute(Route route) {

        graph.putIfAbsent(
                route.getSourceWarehouse(),
                new ArrayList<>());

        graph.putIfAbsent(
                route.getDestinationWarehouse(),
                new ArrayList<>());

        graph.get(route.getSourceWarehouse())
                .add(
                        new Edge(
                                route.getDestinationWarehouse(),
                                route.getDistance()
                        )
                );
    }

    // ======================
    // DIJKSTRA
    // ======================

    public void dijkstra(String start) {

        Map<String, Integer> distance =
                new HashMap<>();

        for (String node :
                graph.keySet()) {

            distance.put(
                    node,
                    Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                distance::get));

        pq.offer(start);

        while (!pq.isEmpty()) {

            String current =
                    pq.poll();

            for (Edge edge :
                    graph.get(current)) {

                int newDistance =
                        distance.get(current)
                                + edge.weight;

                if (newDistance <
                        distance.get(edge.destination)) {

                    distance.put(
                            edge.destination,
                            newDistance);

                    pq.offer(
                            edge.destination);
                }
            }
        }

        System.out.println(
                "\nShortest Distances:");

        distance.forEach(
                (k, v) ->
                        System.out.println(
                                k + " = " + v));
    }

    // ======================
    // BELLMAN FORD
    // ======================

    public void bellmanFord(
            String start) {

        Map<String, Integer> distance =
                new HashMap<>();

        for (String node :
                graph.keySet()) {

            distance.put(
                    node,
                    Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        int vertices =
                graph.size();

        for (int i = 1;
             i < vertices;
             i++) {

            for (String src :
                    graph.keySet()) {

                for (Edge edge :
                        graph.get(src)) {

                    if (
                            distance.get(src)
                                    != Integer.MAX_VALUE

                                    &&

                                    distance.get(src)
                                            + edge.weight

                                            <

                                            distance.get(
                                                    edge.destination)
                    ) {

                        distance.put(
                                edge.destination,
                                distance.get(src)
                                        + edge.weight);
                    }
                }
            }
        }

        System.out.println(
                "\nBellman-Ford Result:");

        distance.forEach(
                (k, v) ->
                        System.out.println(
                                k + " = " + v));
    }

    // ======================
    // FLOYD WARSHALL
    // ======================

    public void floydWarshall() {

        List<String> nodes =
                new ArrayList<>(
                        graph.keySet());

        int n = nodes.size();

        int[][] dist =
                new int[n][n];

        for (int i = 0;
             i < n;
             i++) {

            for (int j = 0;
                 j < n;
                 j++) {

                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = 999999;
            }
        }

        for (int i = 0;
             i < n;
             i++) {

            String src =
                    nodes.get(i);

            for (Edge edge :
                    graph.get(src)) {

                int j =
                        nodes.indexOf(
                                edge.destination);

                dist[i][j] =
                        edge.weight;
            }
        }

        for (int k = 0;
             k < n;
             k++) {

            for (int i = 0;
                 i < n;
                 i++) {

                for (int j = 0;
                     j < n;
                     j++) {

                    dist[i][j] =
                            Math.min(
                                    dist[i][j],
                                    dist[i][k]
                                            + dist[k][j]);
                }
            }
        }

        System.out.println(
                "\nAll-Pairs Shortest Paths");

        for (int i = 0;
             i < n;
             i++) {

            for (int j = 0;
                 j < n;
                 j++) {

                System.out.print(
                        dist[i][j]
                                + "\t");
            }

            System.out.println();
        }
    }

    // ======================
    // TOPOLOGICAL SORT
    // ======================

    public void topologicalSort() {

        Map<String, Integer> indegree =
                new HashMap<>();

        for (String node :
                graph.keySet()) {

            indegree.put(node, 0);
        }

        for (String node :
                graph.keySet()) {

            for (Edge edge :
                    graph.get(node)) {

                indegree.put(
                        edge.destination,
                        indegree.get(
                                edge.destination) + 1);
            }
        }

        Queue<String> queue =
                new LinkedList<>();

        for (String node :
                indegree.keySet()) {

            if (indegree.get(node)
                    == 0)

                queue.offer(node);
        }

        System.out.println(
                "\nTopological Order:");

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            System.out.println(
                    current);

            for (Edge edge :
                    graph.get(current)) {

                indegree.put(
                        edge.destination,
                        indegree.get(
                                edge.destination) - 1);

                if (
                        indegree.get(
                                edge.destination)
                                == 0
                ) {

                    queue.offer(
                            edge.destination);
                }
            }
        }
    }
}