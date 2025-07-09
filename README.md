# Andy's Personal Project 

## Description

When solving systems of linear equations, one can choose to represent that system in an augmented matrix. They form the rows of this matrix, where each variable's coefficient corresponds to some entry in some vertical column. The number of variables in the system is a representation, in some way, of the dimension of the domain of the matrix; each variable is a linearly independent vector (that we can call a basis vector) for the domain.

I would like to create an application in which users can enter a matrix, and where the application would *automatically* produce the necessary row operations to determine the **RREF** of said matrix. The RREF (reduced row echelon form) is a form of the matrix which can easily show some key properties of said matrix. *Any* user in need of solving a linear system can use my application. For example, a physics or engineering student solving or designing a complicated circuit could use my application to find out if it's even possible to attain a certain set of voltages and currents. 

Being a physics and maths student, this type of project interests me since it involves both mathematics and physical applications, as well as invoking computation which can be tedious to do but is well-suited to automation.

* As a user, I want to be able to create a matrix and add it to a collection of matrices, and I would like to specify the number of rows, the number of columns, and the entries of the matrix. I also want to be able to add a note and a name for the matrix.
* As a user, I want to be able to remove a matrix from a collection.
* As a user, I want to be able to view a list of all the matrices' names in a collection.
* As a user, I want to be able to select a matrix in a collection and I want to be able to automatically compute the RREF of said matrix and view the list of row operations necessary to reach said RREF.
* As a user, I want to be able to see the number of matrices in a collection which are invertible and the number which are not invertible. 
* As a user, I want to be able to select a matrix in a collection and modify its name and what its note says.

