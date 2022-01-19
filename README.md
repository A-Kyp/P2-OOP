# P2-OOP
- Second part of the OOP project: "Santa Claus is coming to ACS students"
- Student: Chiper Alexandra-Diana
- Group: 322CD

##General Consideration
**Number of used Design Patterns:** 4

**Used Design Patterns:** SINGLETON, BUILDER, FACTORY, STRATEGY

This project is an extension of the first part. The basic functionalities 
remained the same, and some new ones were added:
- now each child has an elf 
- different actions performed based on the colour of the elf   
- now all gift have a quantity
- there are some new strategies for distributing the gifts

Here will be presented only the newly added functionalities. For details 
concerning the first part of the project check this [repo](https://github.com/A-Kyp/P1_OOP)

###General flow
1. Fix problems related to children (update, eliminate, add, calculate budget).
2. It's time to let the PINK and BLACK elves to do their magic.
3. Fix problems related to gift (add new, update existent).
4. Arrange the kids according to the current round's strategy.
5. Distribute the gift.
6. It's time for the yellow elf to shine.
7. Re-arrange the kids by their ID for printing purposes.

###Changes from the 1st part
**Flow**

Now all action performed during a round are done in Main by calling a 
new class (RoundPlayer) which specialise in keeping the flow - it has 2 
methods: one for normal rounds and one for the initial round which has a 
slightly different flow from the other rounds.

**NiceScore**

A new method for calculating the niceScore was added.
It was implemented in the AverageScoreCalculator class so that it's child 
classes can use it. There is no need to override it because it uses the 
score as is from the specialised calculator and simply adds the bonus ->
a process that is the same for all the children.
Also, the new fields were added to the BUILDER class (internal class to Child).

**City average score**

The score is calculated by a new dedicated Service class -> CityService
All the scores are then put together in the CityStrategy.

**Elf changes**

As an elf can make changes to a single child the methods related to elves were 
implemented in the ChildService class. Then the general methods that apply 
the elf methods to all children was implemented in the Round class.

**Strategy**

All the concerned classes can be found in the src/strategy package.
For implementing the 3 different strategies the STRATEGY and FACTORY Design 
Patterns were used.

For sorting the children list, List.sort() was used. A custom Comparator was 
needed for the cityScoreStrategy -> childByCityComparator. It takes as 
parameter a linked map of cities in lexicographic order (asc) and sort the 
children as follows:
1. same city case: by ID (asc)
2. by city score (desc)
3. [in case of equality at 2.] by city position in the ordered map
(= lexicographic)

##Other considerations
The new fields needed were introduced in the already existing classes for 
Child and Gift. The problem of them being printed in the JSON out file was 
solved using the **JacksonAnnotations** @JSONIgnore and @JSONIgnoreProperties.

The reading of the input data remains almost the same, just the builder for 
child and gift were modified to suite the new requirements.

The writing of the output data remains unchanged.

There were also some small changes to the src/ hierarchy:

- fileio was divided in 2 subpackages (in, out)
- pojo has a subpackage called database for the classes related to the 
  database (AnnualChanges, InitialData, Input)

New methods were added in the utility class Utils for converting strings to 
the new Enum class.

##Useful Links
- For the homework [description](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2)
- For code [skel](https://github.com/oop-pub/oop-asignments/tree/master/proiect1)
- Repository [P1](https://github.com/A-Kyp/P1_OOP) 
- Repository [P2](https://github.com/A-Kyp/P2-OOP.git) 
