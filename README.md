# FastNetworkingResearch

FlatBuffers vs JSON research

JSON parsing (500 objects 100times): 110840
FlatBuffers parsing From Json(500 object 100times): 105270
Memory monitor shows near 4X better result than Gson.

JSON parsing(90 objects 100times): 19240 
FlatBuffers parsing from Bytes(90 objects 100 times): 76
Memory usage of FlatBuffer near 0.

Advantages: 
-value of memory usage
-big difference between speed when you allocate date like byte arrays.

Disadvantages:
-integration of C++ code into android project
-bad readability of models
