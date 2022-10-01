package net.kogics.kojo

package object music {

  private[music] def durationSum(notes: Seq[Note]): Duration = {
    notes.view
      .map(_.duration)
      .reduce((d1, d2) => Duration.CompositeDuration(d1, d2))
  }

  def tie(notes: Note*): Note = {
    val firstNote = notes.head
    require(
      notes.tail.forall(note => note.pitch == firstNote.pitch),
      "All notes in a tie need to have the same pitch"
    )
    val d = durationSum(notes)
    firstNote.copy(duration = d)
  }

  def slur(notes: Note*): MusicElem = {
    MultiNote(
      notes
        .take(notes.length - 1)
        .map(note => note.copy(length = 1))
        .appended(notes.last)
    )
  }
}
