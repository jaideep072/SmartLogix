# 📦 SmartLogix

**SmartLogix** is a comprehensive, Java-based logistics and supply chain management application. It serves as both a practical business tool and an advanced demonstration of data structures and algorithms applied to real-world routing, inventory, and shipment problems.

---

## ✨ Features

- **Core Logistics Management:** Create, track, and manage **Warehouses**, **Shipments**, **Orders**, **Routes**, and **Inventory**.
- **PostgreSQL Integration:** Fully persistent data storage utilizing a robust JDBC database connection.
- **Dual Interface:** Run the application via a fast CLI (Command Line Interface) menu or an intuitive Java Swing Graphical User Interface (GUI).
- **Algorithmic Modules:** The application is divided into six specialized modules (M1 to M6) that apply advanced computer science concepts to logistics:
  - **M1: Balanced Search Structures** (BST, AVL Trees for rapid inventory tracking)
  - **M2: Multiway Trees & Analytics** (B-Trees, Segment Trees, and Fenwick Trees for range queries and analytics)
  - **M3: Graph Algorithms** (Minimum Spanning Trees for network design)
  - **M4: Path Optimization** (Shortest path algorithms for delivery routes)
  - **M5: Advanced Sorting** (Optimized data organization)
  - **M6: Dynamic Programming & Greedy Algorithms** (Cargo loading optimization and the Knapsack problem)

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK)** 17 or higher.
- **PostgreSQL** and **pgAdmin** installed on your local machine.
- *Note:* The necessary PostgreSQL JDBC driver (`postgresql-42.7.3.jar`) is already included in the `lib/` directory.

### Database Setup
1. Open **pgAdmin** and create a new database named `smartlogix`.
2. Open the **Query Tool** for your new database.
3. Copy the contents of the provided `schema.sql` file and execute it to generate all the necessary tables.
4. *(Optional)* Update the database password in `utils/DatabaseManager.java` if your local postgres password is not `cybersec123`.

### Running the Application

You can easily run the application using the included PowerShell scripts:

**To Compile:**
```powershell
.\compile.ps1
```
*(Alternatively: `javac -cp ".;lib/*" Main.java SmartLogixTest.java`)*

**To Run the Application:**
```powershell
.\run.ps1
```
*(Alternatively: `java -cp ".;lib/*" Main`)*

### For VSCode Users
This repository includes a `.vscode/settings.json` file configured to automatically add the `lib/` directory to the Java build path. You can simply open the project in VSCode and click the **Run** button on `Main.java` without any manual classpath configuration!

---

## 📂 Project Structure

```text
SmartLogix/
├── data/               # Legacy CSV data backups
├── gui/                # Graphical User Interface components
├── lib/                # Dependency JARs (JDBC Driver)
├── m1/ - m6/           # Advanced algorithmic modules
├── models/             # Java Data Objects (Warehouse, Shipment, etc.)
├── utils/              # DatabaseManager and Input helpers
├── Main.java           # Entry point (CLI + GUI Launcher)
├── schema.sql          # PostgreSQL Database Schema
└── Migration.java      # Script to migrate old CSV data to PostgreSQL
```

---

## 🧪 Testing

The repository includes a comprehensive integration testing suite. Run `SmartLogixTest` to verify that the database connections, queries, and algorithms are functioning correctly.

```powershell
javac -cp ".;lib/*" SmartLogixTest.java
java -cp ".;lib/*" SmartLogixTest
```

---

*Built with ❤️ to demonstrate the intersection of modern Java development, persistent databases, and advanced computer science algorithms.*
