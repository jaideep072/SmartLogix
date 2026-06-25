package m3;

import models.Route;

import java.util.*;

public class LogisticsGraph {

    private Map<String, List<Edge>> graph =
            new HashMap<>();

    static class Edge {

        String destination;
        int distance;
        int cost;

        Edge(String destination,
             int distance,
             int cost) {

            this.destination = destination;
            this.distance = distance;
            this.cost = cost;
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
                                route.getDistance(),
                                route.getTransportCost()
                        )
                );

        graph.get(route.getDestinationWarehouse())
                .add(
                        new Edge(
                                route.getSourceWarehouse(),
                                route.getDistance(),
                                route.getTransportCost()
                        )
                );
    }

    // ================= BFS =================

    public void bfs(String start) {

        if (!graph.containsKey(start)) {

            System.out.println("Warehouse not found.");
            return;
        }

        Queue<String> queue =
                new LinkedList<>();

        Set<String> visited =
                new HashSet<>();

        queue.offer(start);
        visited.add(start);

        System.out.println(
                "\nReachable Warehouses:");

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            System.out.println(current);

            for (Edge edge :
                    graph.get(current)) {

                if (!visited.contains(
                        edge.destination)) {

                    visited.add(
                            edge.destination);

                    queue.offer(
                            edge.destination);
                }
            }
        }
    }

    // ================= DFS CYCLE =================

    public boolean hasCycle() {

        Set<String> visited =
                new HashSet<>();

        for (String node :
                graph.keySet()) {

            if (!visited.contains(node)) {

                if (dfsCycle(
                        node,
                        null,
                        visited))
                    return true;
            }
        }

        return false;
    }

    private boolean dfsCycle(
            String current,
            String parent,
            Set<String> visited) {

        visited.add(current);

        for (Edge edge :
                graph.get(current)) {

            String next =
                    edge.destination;

            if (!visited.contains(next)) {

                if (dfsCycle(
                        next,
                        current,
                        visited))
                    return true;
            }

            else if (!next.equals(parent))
                return true;
        }

        return false;
    }

    // ================= MST =================

    static class MSTEdge {

        String source;
        String destination;
        int cost;

        MSTEdge(String source,
                String destination,
                int cost) {

            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    public void displayMST() {

        List<MSTEdge> edges =
                new ArrayList<>();

        Set<String> seen =
                new HashSet<>();

        for (String src :
                graph.keySet()) {

            for (Edge edge :
                    graph.get(src)) {

                String key =
                        src + "-"
                                + edge.destination;

                String reverse =
                        edge.destination
                                + "-"
                                + src;

                if (!seen.contains(key)
                        &&
                        !seen.contains(reverse)) {

                    seen.add(key);

                    edges.add(
                            new MSTEdge(
                                    src,
                                    edge.destination,
                                    edge.cost
                            )
                    );
                }
            }
        }

        edges.sort(
                Comparator.comparingInt(
                        e -> e.cost));

        Map<String, String> parent =
                new HashMap<>();

        for (String node :
                graph.keySet()) {

            parent.put(
                    node,
                    node
            );
        }

        int totalCost = 0;

        System.out.println(
                "\nMinimum Spanning Tree:");

        for (MSTEdge edge :
                edges) {

            String root1 =
                    find(parent,
                            edge.source);

            String root2 =
                    find(parent,
                            edge.destination);

            if (!root1.equals(root2)) {

                parent.put(
                        root1,
                        root2);

                totalCost +=
                        edge.cost;

                System.out.println(
                        edge.source
                                + " -> "
                                + edge.destination
                                + " Cost="
                                + edge.cost
                );
            }
        }

        System.out.println(
                "Total MST Cost = "
                        + totalCost);
    }

    private String find(
            Map<String, String> parent,
            String node) {

        while (!parent.get(node)
                .equals(node)) {

            node =
                    parent.get(node);
        }

        return node;
    }
}