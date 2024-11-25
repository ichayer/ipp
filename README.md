# Introduction

This repository provides three implementations of a matrix multiplication algorithm in Java:

1. **Sequential Version:** A basic, single-threaded implementation of the matrix multiplication algorithm.
2. **Parallel Version (ExecutorService):** A parallelized version of the sequential algorithm using Java's ExecutorService API, which allows for thread management and task parallelization.
3. **Parallel Version (ForkJoin Framework):** A parallelized version using Java's ForkJoin framework, optimized for parallel task execution through recursive decomposition.

The purpose of this project is to compare the performance of the three implementations by measuring their execution times with different numbers of threads. The tests are designed to show the impact of parallelization on matrix multiplication, considering the hardware specifications used for testing (number of CPU cores and MHz speed).
