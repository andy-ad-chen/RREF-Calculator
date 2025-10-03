# Andy's Personal Project 

Note to users: Console UI is more polished than GUI

## Description

When solving systems of linear equations, one can choose to represent that system in an augmented matrix. They form the rows of this matrix, where each variable's coefficient corresponds to some entry in some vertical column. The number of variables in the system is a representation, in some way, of the dimension of the domain of the matrix; each variable is a linearly independent vector (that we can call a basis vector) for the domain.

I would like to create an application in which users can enter a matrix, and where the application would *automatically* produce the necessary row operations to determine the **RREF** of said matrix. The RREF (reduced row echelon form) is a form of the matrix which can easily show some key properties of said matrix. *Any* user in need of solving a linear system can use my application. For example, a physics or engineering student solving or designing a complicated circuit could use my application to find out if it's even possible to attain a certain set of voltages and currents. 

Being a physics and maths student, this type of project interests me since it involves both mathematics and physical applications, as well as invoking computation which can be tedious to do but is well-suited to automation.
## User Stories

* As a user, I want to be able to create a matrix and add it to a collection of matrices, and I would like to specify the number of rows, the number of columns, and the entries of the matrix. I also want to be able to add a note and a name for the matrix.
* As a user, I want to be able to remove a matrix from a collection.
* As a user, I want to be able to view a list of all the matrices' names in a collection.
* As a user, I want to be able to select a matrix in a collection and I want to be able to automatically compute the RREF of said matrix and view the list of row operations necessary to reach said RREF.
* As a user, I want to be able to see the number of matrices in a collection which are invertible and the number which are not invertible. 
* As a user, I want to be able to select a matrix in a collection and modify its name and what its note says.

* As a user, I want to be able to save my selection of matrices to a file, if I so choose.
* As a user, I want to be able to load my selection of matrices from a file, if I so choose.

# Instructions for End Users

- Click the "Add a matrix" button on the bottom half of the window to start specifying a matrix. Enter values in all fields; click "Verify values and Continue" to continue. If your values are not valid, you wil be prompted to try again. A table will be generated in which you can enter the matrice's values, and you will have the option to "Very values and Continue" again.
- To display all the loaded matrices, click the drop-down menu; all matrices will be there.
- To compute the Reduced Row Echelon form of a matrix, select it from the drop-down menu and click the green "View" button.
- To visualize a matrix, select a matrix from the drop-down menu and click the green "View" button. The values of a matrix will be drawn into an appropriately-sized matrix with an outline.
- You can save the matrices list by clicking "Save and Write to File".
- You can reload the matrices list by clicking "Load from File".

# Sample Event Logs

Wed Aug 06 20:40:17 PDT 2025
List of matrices to view has been updated

Wed Aug 06 20:40:18 PDT 2025
The matrix: "Your Mum's Matrix" has had its RREF computed.

Wed Aug 06 20:40:18 PDT 2025
Added matrix to working list: Your Mum's Matrix

Wed Aug 06 20:40:18 PDT 2025
List of matrices to view has been updated

Wed Aug 06 20:40:23 PDT 2025
List of matrices to view has been updated

Wed Aug 06 20:40:32 PDT 2025
The matrix: "Joe's Matrix" has had its RREF computed.

Wed Aug 06 20:40:32 PDT 2025
Added matrix to working list: Joe's Matrix

Wed Aug 06 20:40:32 PDT 2025
List of matrices to view has been updated

Wed Aug 06 20:40:38 PDT 2025
Removed matrix from working list: Joe's Matrix

Wed Aug 06 20:40:38 PDT 2025
List of matrices to view has been updated

# Reflection

Given more time, I would like to implement many changes to my project.

Firstly, I notice from my UML diagram that I accidentally included many duplicates of the field containing a `MatrixGui` object in my `tools` package. All of the present tools extends the abstract `Tool`, which already contains a `MatrixGui` object. 

Secondly, I did not realize at the time I started my project that there exists a data type that is a 2 dimensional array. This would make my representation of a matrix much simpler. I would still need 2 fields in the `Matrix` type for the matrix originally and then after it is reduced to RREF. 

Thirdly, I think that my `ViewSelectMenu` class could also extend `Tool` as there is some common functionality, including having the need to access `MatrixGui`. Further, there is a button created inside `ViewSelectMenu` that I believe could be pulled out into its own class; it is a `JButton` that could be another extension on `Tool`. 

I originally wrote most of the code in `model` before learning about abstraction and polymorphism. Thus, fourthly, I would've modified some classes in `model` to be extensions of lists built-in to java. I actually did carry out this modification earlier on in this project.

Fifth, I would remove the `MatrixList` field in `ViewSelectMenu` as it can access it through `MatrixGui`. This increases cohesion.

Sixth, I would make changes to the way many methods in `ui` were written, as there are many actions like `mainGui.add(...)`,`mainGui.revalidate()`, and `mainGui.repaint()` which could be refactored into one helper method.

Seventh, I find `MatrixInserter` in an odd position. It is only instantiated in `AddMatrixTool` and then uses methods in `Main` to find the `MatrixGui` (`Main.getMatrixGui()` which is static) to add a matrix. It then appends the matrix onto `MatrixGui`'s matrix list. To improve cohesion, it could just pass parameters into some method in `AddMatrixTool` which would mean removing the `Matrix` field, and making the association between `MatrixGui` and `AddMatrixTool` bidirectional.



