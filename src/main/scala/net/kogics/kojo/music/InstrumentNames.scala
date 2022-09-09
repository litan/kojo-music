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

object InstrumentNames {
  // info sourced from jmusic (https://explodingart.com/jmusic/index.html)

  // name to program change code
  val nameToPC = Map(
    "Accordion" -> 21,
    "Applausen" -> 126,
    "Bandneon" -> 23,
    "Banjo" -> 105,
    "Bagpipes" -> 109,
    "Bass   (Acoustic)" -> 32,
    "Bass   (Fingerd)" -> 33,
    "Bass   (Fretless)" -> 35,
    "Bass   (Picked)" -> 34,
    "Bass   (Slap)" -> 36,
    "Bass   (Synth)" -> 38,
    "Bassoon" -> 70,
    "Bottle" -> 76,
    "Brass  (Synthetic)" -> 62,
    "Calliope" -> 82,
    "Celeste" -> 8,
    "Cello" -> 42,
    "Charang" -> 84,
    "Choir" -> 52,
    "Clarinet" -> 71,
    "Clavinet" -> 7,
    "Contrabass" -> 43,
    "English Horn" -> 69,
    "Fiddle" -> 110,
    "French Horn" -> 60,
    "Flute" -> 73,
    "Glockenspiel" -> 9,
    "Guitar (Clean)" -> 27,
    "Guitar (Distorted)" -> 30,
    "Guitar Harmonics" -> 31,
    "Guitar (Jazz)" -> 26,
    "Guitar (Muted)" -> 28,
    "Guitar (Nylon)" -> 24,
    "Guitar (Overdrive)" -> 29,
    "Guitar (Steel)" -> 25,
    "Harmonica" -> 22,
    "Harp" -> 46,
    "Harpsichord" -> 76,
    "Marimba" -> 12,
    "Music Box" -> 10,
    "Oboe" -> 68,
    "Ocarina" -> 79,
    "Orchestra Hit" -> 55,
    "Organ" -> 16,
    "Organ (Church)" -> 19,
    "Organ (Reed)" -> 20,
    "Pan Flute" -> 75,
    "Piano" -> 0,
    "Piano (Electric)" -> 4,
    "Piano (Honkeytonk)" -> 3,
    "Piccolo" -> 72,
    "Recorder" -> 74,
    "Saxophone (Alto)" -> 65,
    "Saxophone (Soprano)" -> 64,
    "Saxophone (Tenor)" -> 66,
    "Saxophone (Baritone)" -> 67,
    "Shakuhachi" -> 77,
    "Steel Drums" -> 114,
    "Strings" -> 48,
    "Strings (Pizzicato)" -> 45,
    "Strings (Slow)" -> 51,
    "Strings (Synth)" -> 50,
    "Strings (Tremolo)" -> 44,
    "Tom-Tom" -> 119,
    "Trombone" -> 57,
    "Trumpet" -> 56,
    "Trumpet (Muted)" -> 59,
    "Tuba" -> 58,
    "Tubular Bell" -> 14,
    "Timpani" -> 47,
    "Vibraphone" -> 11,
    "Viola" -> 41,
    "Violin" -> 40,
    "Voice" -> 53,
    "Vox" -> 56,
    "Whistle" -> 78,
    "Wood" -> 115,
    "Xylophone" -> 13
  )

  val names = nameToPC.keys.toIndexedSeq.sorted
}
