For the GET and SET methods I just went ahead and created a hashtable so that the LPUSH method could access previously created keys.
Some of the functions are able to handle invalid commands but most of the functions will cause the system to return an error if an invalid command is encountered.
The instructions say that "assuming the command is valid" so I didn't take that to be too much of a problem.
Everything else went pretty well!
