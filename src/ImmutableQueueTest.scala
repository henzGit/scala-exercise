object ImmutableQueueTest {
  val NUM_CHARS = 100000
  val LEN_CHARS = 10
  val randomStrings: List[String] = produceRandomStrings(NUM_CHARS, LEN_CHARS)

  /**
    * Create a random string from char list
    * @param length length of the random string
    * @param chars sequence of characters to be used as based
    * @return a random string from given charset and length
    */
  def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
    val sb = new StringBuilder
    for (i <- 1 to length) {
      val randomNum = util.Random.nextInt(chars.length)
      sb.append(chars(randomNum))
    }
    sb.toString
  }

  /**
    * Return a random alphanumeric string with specified length
    * @param length length of the random string
    * @return a random alphanumeric string
    */
  def randomAlphaNumericString(length: Int): String = {
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    randomStringFromCharList(length, chars)
  }

  /**
    * Produce a list of random strings
    * @param numberStrings number of desired strings
    * @param length length of each random string
    * @return list of random strings
    */
  def produceRandomStrings(numberStrings: Int, length: Int): List[String] = {
    var list: List[String] = List()
    for (i <- 1 to numberStrings) {
      list = randomAlphaNumericString(length) :: list
    }
    list
  }

  /**
    * Main test for CustomQueue
    */
  def testCustomImmutableQueue(): Unit = {
    println("\nTest 0 - Initialize Queue")
    var queue: CustomQueue[String] = CustomQueue.empty[String]
    assert(queue.isEmpty)
    assert(queue.head == None)
    println(s"Elements of queue = $queue")

    println("\nTest 1: Add multiple elements into Queue using enQueue function")
    for (i <- 1 to randomStrings.length) {
      val newQueue: CustomQueue[String] = queue.enQueue(randomStrings(i-1))
      assert(!newQueue.equals(queue) && (newQueue ne queue))
      queue = newQueue
    }
    assert(!queue.isEmpty)
    assert(queue.head.get.toString == randomStrings.head.toString)
    assert(queue.lengthInputList == randomStrings.length)
    assert(queue.lengthOutputList == 0)

    println("\nTest 2: Remove multiple elements into Queue using deQueue function")
    for (i <- 1 to randomStrings.length) {
      val newQueue: CustomQueue[String] = queue.deQueue()
      assert(!newQueue.equals(queue) && (newQueue ne queue))
      queue = newQueue
    }
    assert(queue.isEmpty)
    assert(queue.head == None)
    assert(queue.lengthInputList == 0)
    assert(queue.lengthOutputList == 0)
  }

  def main(args: Array[String]): Unit = {
    testCustomImmutableQueue()
  }
}