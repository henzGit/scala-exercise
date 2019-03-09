import scala.collection.immutable.{::, List, Nil}

trait CustomQueueTrait[T] {
  def isEmpty: Boolean
  def enQueue(t: T): CustomQueue[T]
    // Removes the element at the beginning of the immutable queue, and returns the new queue.
  def deQueue(): CustomQueue[T]
  def head: Option[T]
}

sealed class CustomQueue[T] private(private val in: List[T], private val out: List[T])
  extends CustomQueueTrait[T]
{

  /** Checks if the queue is empty.
    *
    *  @return true, if there is no element in the queue.
    */
  def isEmpty: Boolean = in.isEmpty && out.isEmpty

  /** Gets the first element of this queue.
    *
    *  @return  the first element of this queue or None if queue is empty.
    */
  def head: Option[T] =
    if (out.nonEmpty) Option(out.head)
    else if (in.nonEmpty) Option(in.last)
    else None

  /** Creates a new queue with element added at the end
    *  of the old queue.
    *
    *  @param  t the element to insert
    *  @return the new queue with added element
    */
  def enQueue(t: T): CustomQueue[T] = new CustomQueue(t :: in, out)

  /** Removes the element at the beginning of the immutable queue,
    * and returns the new queue.
    *
    *  @throws java.util.NoSuchElementException
    *  @return the new queue with reduced element
    */
  def deQueue(): CustomQueue[T] = out match {
    case Nil if !in.isEmpty => val rev = in.reverse ; new CustomQueue(Nil, rev.tail)
    case x :: xs            => new CustomQueue(in, xs)
    case _                  => throw new NoSuchElementException("dequeue on empty queue")
  }

  /** Returns a string representation of this queue.
    */
  override def toString()  = {
    val rev = in.reverse
    out match {
      case Nil if !in.isEmpty => {
        in.toString().replace("List","Queue")
      }
      case x => {
        (out ++ in).toString().replace("List","Queue")
      }
      case _ => "empty queue!"
    }
  }
}

object CustomQueue {
  def empty[T]: CustomQueue[T] = EmptyQueue.asInstanceOf[CustomQueue[T]]
  def apply[T](xs: T*): CustomQueue[T] = new CustomQueue[T](Nil, xs.toList)

  private object EmptyQueue extends CustomQueue[Nothing](Nil, Nil) { }
}

