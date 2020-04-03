Perhaps one of the most entertaining coding projects I have done, this code solves Collatz/Fractal using a hybrid 
between Cuda Code and Open MPI!

To run in Linux:
Make sure your environment supports mpirun and make sure mpirun is enabled.

I believe if you have it enabled you need to run these commands:
module load cuda
scl enable devtoolset-2 bash
export LD_LIBRARY_PATH=/usr/local/cuda/lib64/:$LD_LIBRARY_PATH

But a sample command: mpirun -n 2 ./collatz_hyb 169555555 0

The times provided in HunterNoeyProject6 were ran on the Lonestar5 Server. Barring you are able to submit sub files, the command to run it
would be sbatch fractal_hyb.sub. These times will be much faster compared to running on Linux.
