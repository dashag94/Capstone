def main():
    print("Welcome to the Pathfinding Program!")
    
    filename = input("Enter the graph file name: ")
    graph = {}
    
    try:
        with open(filename, 'r') as file:
            for line in file:
                source, destination, weight = line.split()
                weight = int(weight)
                if source not in graph:
                    graph[source] = []
                if destination not in graph:
                    graph[destination] = []
                graph[source].append((destination, weight))
                graph[destination].append((source, weight))  # Undirected graph
    except FileNotFoundError:
        print("Error: Unable to open file.")
        return
    
    print("Graph loaded.")
    start = input("Enter the starting node: ")
    end = input("Enter the ending node: ")
    
    # No input validation
    distances = {node: float('inf') for node in graph}
    previous_nodes = {node: None for node in graph}
    distances[start] = 0
    queue = [(0, start)]
    
    while queue:
        queue.sort()  # Inefficient sorting
        current_distance, current_node = queue.pop(0)
        
        for neighbor, weight in graph[current_node]:
            new_distance = current_distance + weight
            if new_distance < distances[neighbor]:
                distances[neighbor] = new_distance
                previous_nodes[neighbor] = current_node
                queue.append((new_distance, neighbor))
    
    path = []
    current = end
    while current:
        path.insert(0, current)
        current = previous_nodes[current]
    
    print("Shortest path:", " -> ".join(path))
    print("Total cost:", distances[end])
