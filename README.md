# Table of contents
- [About the repository](#about-the-repository)
- [Requirements](#requirements)
- [Installation](#installation)

# [About the repository](#about-the-repository)

This repository provides three implementations of a matrix multiplication algorithm in Java:

1. **Sequential Version:** A basic, single-threaded implementation of the matrix multiplication algorithm.
2. **Parallel Version (ExecutorService):** A parallelized version of the sequential algorithm using Java's ExecutorService API, which allows for thread management and task parallelization.
3. **Parallel Version (ForkJoin Framework):** A parallelized version using Java's ForkJoin framework, optimized for parallel task execution through recursive decomposition.

The purpose of this project is to compare the performance of the three implementations by measuring their execution times with different numbers of threads. The tests are designed to show the impact of parallelization on matrix multiplication, considering the hardware specifications used for testing (number of CPU cores and MHz speed).

# [Requirements](#requirements)
- Java SDK 23
- Maven
- Python 3.10 or higher

# [Installation](#installation)

1. Build the Java project:

Run the following command to build the project

```bash
mvn clean install
```

Once the project is built, execute the `Main.java` file.

The file defines two constants: `NUM_EXECUTIONS` (number of executions) and `THREAD_COUNT` (number of threads). You can modify these constants to adjust the execution parameters.

After running the file, a JSON file named `results.json` will be generated. This file contains metrics for the average execution time across multiple runs, with data for each thread count and method.

2. Set up the Python environment:

Create and activate a Python virtual environmet

```bash
python -m venv venv

# For Linux
source ./venv/bin/activate 

# For Windows
.\venv\Scripts\activate 

pip install -r requirements.txt
```

3. Generate plots:

Once the `results.json` file is generated, run the plots/plot.py script to visualize the results:

```bash
python plots/plot.py
```