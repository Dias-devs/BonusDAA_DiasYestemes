# Minimum Spanning Tree (MST) – Edge Removal and Replacement 

## Description

This Java program builds a Minimum Spanning Tree (MST) using Kruskal’s algorithm, 
then removes one edge and finds a replacement edge that 
reconnects the graph while keeping the total weight minimal.

It demonstrates how MSTs can be updated efficiently after a change without rebuilding the entire tree.

## How the Code Works

- Edge.java – defines an edge (source, destination, weight) and allows sorting by weight.
- Subset.java – used for the Disjoint Set (Union-Find) structure to detect cycles.
- MST.java – main logic for:
  - Building the MST
  - Removing an edge
  - Finding and adding a replacement edge
- Main.java – contains an example graph and runs all the steps automatically.

## How to Run
1. Clone the repository
2. Compile the files
3. Run the program. It will print output such as:
   - The MST edges before removal
   - The removed edge
   - The replacement edge
   - The final MST edges