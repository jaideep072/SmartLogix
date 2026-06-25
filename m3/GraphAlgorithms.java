package m3;

import models.Route;
import utils.DatabaseManager;
import utils.InputHelper;

import java.util.Scanner;

public class GraphAlgorithms {

    public static void runMenu(
            Scanner sc) {

        LogisticsGraph graph =
                new LogisticsGraph();

        for (Route route :
                DatabaseManager.loadRoutes()) {

            graph.addRoute(route);
        }

        while (true) {

            System.out.println(
                    "\n===== M3 MENU =====");

            System.out.println(
                    "1. BFS Reachability");

            System.out.println(
                    "2. DFS Cycle Detection");

            System.out.println(
                    "3. MST Network Design");

            System.out.println(
                    "0. Back");

            String choice =
                    sc.nextLine();

            switch (choice) {

                case "1":

                    String warehouse =
                            InputHelper.readString(
                                    sc,
                                    "Start Warehouse: "
                            );

                    graph.bfs(
                            warehouse);

                    break;

                case "2":

                    if (graph.hasCycle())
                        System.out.println(
                                "Cycle Detected");
                    else
                        System.out.println(
                                "No Cycle");

                    break;

                case "3":

                    graph.displayMST();

                    break;

                case "0":

                    return;
            }
        }
    }
}
