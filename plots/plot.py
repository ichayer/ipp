import json
from datetime import datetime
import matplotlib.pyplot as plt


def load_results(file_path):
    """Load JSON data from a file."""
    with open(file_path, "r") as file:
        data = json.load(file)
    return data


def plot_performance(data):
    """Plot the performance of matrix multiplication."""
    runs = data.pop("Runs")
    thread_counts = None

    colors = ["b", "g", "r"]
    markers = ["o", "s", "^"]

    # Loop through each method and its results
    for i, (method, results) in enumerate(data.items()):

        # Convert thread counts to integers and sort them
        thread_counts = sorted(map(int, results.keys()))

        # Collect the execution times corresponding to each thread count
        times = []
        for thread in thread_counts:
            times.append(results[str(thread)])

        # Plot the data
        plt.plot(thread_counts, times, marker=markers[i], color=colors[i], label=method)

    # Customize the plot
    plt.title(f"Average execution time per number of threads - Runs: {runs}")
    plt.xlabel("Number of Threads")
    plt.xticks(thread_counts)
    plt.ylabel("Time (ms)")
    plt.legend()
    plt.grid(True)


def save_plot():
    """Save the plot with a timestamped filename."""
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    filename = f"performance_comparison_{timestamp}.png"
    plt.savefig(filename)


def main():
    # Load the results obtained in Java
    data = load_results("../results.json")

    # Plot the performance
    plot_performance(data)

    # Save the plotS
    save_plot()


if __name__ == "__main__":
    main()