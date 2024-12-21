import heapq

def main():
    print("Welcome to the Pathfinding Program!")
    filename = input("Enter the graph file name: ")

    graph = build_graph(filename)
    if graph is None:
        print("Error: Failed to load the graph. Check the file format.")
        return

    print("Graph loaded successfully.")
    start_node = input("Enter the starting node: ")
    end_node = input("Enter the ending node: ")

    if not validate_nodes(graph, start_node, end_node):
        print("Error: One or both nodes are invalid or not present in the graph.")
        return

    print(f"Calculating the shortest path from {start_node} to {end_node}...")
    shortest_path, total_cost = calculate_shortest_path(graph, start_node, end_node)

    if shortest_path:
        display_results(shortest_path, total_cost)
    else:
        print(f"No path exists between {start_node} and {end_node}.")


def build_graph(filename):
    try:
        with open(filename, 'r') as file:
            graph = {}
            for line in file:
                try:
                    source, destination, weight = line.strip().split()
                    weight = int(weight)
                    if source not in graph:
                        graph[source] = []
                    if destination not in graph:
                        graph[destination] = []
                    graph[source].append((destination, weight))
                    graph[destination].append((source, weight))  # For undirected graph
                except ValueError:
                    print(f"Warning: Skipping invalid line: {line.strip()}")
                    continue
            return graph
    except FileNotFoundError:
        print("Error: Unable to open the graph file.")
        return None


def validate_nodes(graph, start_node, end_node):
    return start_node in graph and end_node in graph


def calculate_shortest_path(graph, start_node, end_node):
    distances = {node: float('inf') for node in graph}
    previous_nodes = {node: None for node in graph}
    distances[start_node] = 0
    priority_queue = [(0, start_node)]

    while priority_queue:
        current_distance, current_node = heapq.heappop(priority_queue)

        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in graph[current_node]:
            new_distance = current_distance + weight
            if new_distance < distances[neighbor]:
                distances[neighbor] = new_distance
                previous_nodes[neighbor] = current_node
                heapq.heappush(priority_queue, (new_distance, neighbor))

    if distances[end_node] == float('inf'):
        return None, None
    return reconstruct_path(previous_nodes, end_node), distances[end_node]


def reconstruct_path(previous_nodes, end_node):
    path = []
    current_node = end_node
    while current_node:
        path.insert(0, current_node)
        current_node = previous_nodes[current_node]
    return path


def display_results(shortest_path, total_cost):
    print("Shortest path:")
    print(" -> ".join(shortest_path))
    print(f"Total cost: {total_cost}")


if __name__ == "__main__":
    main()
