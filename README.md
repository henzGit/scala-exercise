# scalaExercise
This is an exercise in Scala to create an immutable queue. It means every enQueue and deQueue operation returns a new queue
instance instead of modifying the current instance. 
<br>
<br>
The internal mechanism of this queue is based on two different stacks:
in-stack and out-stack. In-stack is used to manage the enQueue operation while the out-stack is used to manage the deQueue 
operation. 

## Peformance Check
* enQueue(): Runs in O(1) time
* deQueue():
  Runs in O(1) time for most of the time except for the first time operation where out-stack is empty. In this case, it runs
  in O(n) time since it needs to reverse the in-stack. 
* head(): Runs in O(1) time
* isEmpty(): Runs in O(1) time
