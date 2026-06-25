package m4;

import models.Route;
import utils.CSVManager;
import utils.InputHelper;

import java.util.Scanner;

public class PathOptimization {

    public static void runMenu(
            Scanner sc) {

        ShortestPathAlgorithms alg =
                new ShortestPathAlgorithms();

        for (Route route :
                CSVManager.loadRoutes()) {

            alg.addRoute(route);
        }

        while (true) {

            System.out.println(
                    "\n===== M4 MENU =====");

            System.out.println(
                    "1. Dijkstra");

            System.out.println(
                    "2. Bellman-Ford");

            System.out.println(
                    "3. Floyd-Warshall");

            System.out.println(
                    "4. Topological Sort");

            System.out.println(
                    "0. Back");

            String choice =
                    sc.nextLine();

            switch (choice) {

                case "1":

                    alg.dijkstra(
                            InputHelper.readString(
                                    sc,
                                    "Start Warehouse: "
                            ));
                    break;

                case "2":

                    alg.bellmanFord(
                            InputHelper.readString(
                                    sc,
                                    "Start Warehouse: "
                            ));
                    break;

                case "3":

                    alg.floydWarshall();
                    break;

                case "4":

                    alg.topologicalSort();
                    break;

                case "0":

                    return;
            }
        }
    }
}