package net.kogics.kojo

package object music {

  private[music] def durationSum(notes: Seq[Note]): Duration = {
    notes.view
      .map(_.duration)
      .reduce((d1, d2) => Duration.CompositeDuration(d1, d2))
  }

  def tie(notes: Note*): Note = {
    val first = notes.head
    val firstPitch = notes.head.pitch
    require(
      notes.tail.forall(note => note.pitch == firstPitch),
      "All notes in a tie need to have the same pitch"
    )
    val d = durationSum(notes)
    Note(first.pitch, d, first.length, first.dynamic, first.pan)
  }
}
