/*
 * Copyright (C) 2022 Lalit Pant <pant.lalit@gmail.com>
 *
 * The contents of this file are subject to the GNU General Public License
 * Version 3 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.gnu.org/copyleft/gpl.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 */
package net.kogics.kojo.music

object NoteNames {

  val noteNames = Seq(
    "A",
    "A#/Bb",
    "B",
    "C",
    "C#/Db",
    "D",
    "D#/Eb",
    "E",
    "F",
    "F#/Gb",
    "G",
    "G#/Ab"
  )

  val allNoteNames =
    (1 to 9).foldLeft(IndexedSeq.empty[String]) { (currSeq, currOctave) =>
      currSeq ++ noteNames.map(_ + currOctave.toString)
    }

  val pitchToNoteName = Map.from(
    (21 to 21 + 12 * 9).zip(allNoteNames)
  )

  val swaraNames = Seq(
    "Sa",
    "Re_komal",
    "Re",
    "Ga_komal",
    "Ga",
    "Ma",
    "Ma_tivra",
    "Pa",
    "Dha_komal",
    "Dha",
    "Ni_komal",
    "Ni"
  )

  val allSwaraNames =
    (1 to 3).foldLeft(IndexedSeq.empty[String]) { (currSeq, currOctave) =>
      currSeq ++ swaraNames.map { n =>
        if (currOctave == 2) n else n + currOctave.toString
      }
    }

  val pitchToSwaraName = Map.from(
    (48 to 48 + 12 * 3).zip(allSwaraNames)
  )
}
