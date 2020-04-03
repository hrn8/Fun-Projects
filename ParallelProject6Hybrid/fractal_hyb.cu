/*
Fractal code for CS 4380 / CS 5351

Copyright (c) 2019 Texas State University. All rights reserved.

Redistribution in source or binary form, with or without modification,
is *not* permitted. Use in source and binary forms, with or without
modification, is only permitted for academic use in CS 4380 or CS 5351
at Texas State University.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

Author: Martin Burtscher
*/

#include <cstdio>
#include <cmath>
#include <cuda.h>

static const int ThreadsPerBlock = 512;

static __global__ void fractal(const int width, const int start_frame, const int gpu_frames, unsigned char* const pic)
{
  // todo: use the GPU to compute the requested frames (base the code on the previous project)
}

unsigned char* GPU_Init(const int gpu_frames, const int width)
{
  unsigned char* d_pic;
  if (cudaSuccess != cudaMalloc((void **)&d_pic, gpu_frames * width * width * sizeof(unsigned char))) {fprintf(stderr, "ERROR: could not allocate memory\n"); exit(-1);}
  return d_pic;
}

void GPU_Exec(const int start_frame, const int gpu_frames, const int width, unsigned char* d_pic)
{
  // todo: launch the kernel with ThreadsPerBlock and the appropriate number of blocks (do not wait for the kernel to finish)
}

void GPU_Fini(const int gpu_frames, const int width, unsigned char* pic, unsigned char* d_pic)
{
  // todo: copy the result from the device to the host and free the device memory
}

