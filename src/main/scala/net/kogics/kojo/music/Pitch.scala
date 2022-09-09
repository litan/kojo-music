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

object Pitch {
  val majorKeyPitchDeltas = Seq(0, 2, 4, 5, 7, 9, 11, 12)

  // constants from jmusic (https://explodingart.com/jmusic/index.html)

  val G9 = 127

  val GF9 = 126

  val FS9 = 126

  val F9 = 125

  val FF9 = 124

  val ES9 = 125

  val E9 = 124

  val EF9 = 123

  val DS9 = 123

  val D9 = 122

  val DF9 = 121

  val CS9 = 121

  val C9 = 120

  val CF9 = 119

  val BS8 = 120

  val B8 = 119

  val BF8 = 118

  val AS8 = 118

  val A8 = 117

  val AF8 = 116

  val GS8 = 116

  val G8 = 115

  val GF8 = 114

  val FS8 = 114

  val F8 = 113

  val FF8 = 112

  val ES8 = 113

  val E8 = 112

  val EF8 = 111

  val DS8 = 111

  val D8 = 110

  val DF8 = 109

  val CS8 = 109

  val C8 = 108

  val CF8 = 107

  val BS7 = 108

  val B7 = 107

  val BF7 = 106

  val AS7 = 106

  val A7 = 105

  val AF7 = 104

  val GS7 = 104

  val G7 = 103

  val GF7 = 102

  val FS7 = 102

  val F7 = 101

  val FF7 = 100

  val ES7 = 101

  val E7 = 100

  val EF7 = 99

  val DS7 = 99

  val D7 = 98

  val DF7 = 97

  val CS7 = 97

  val C7 = 96

  val CF7 = 95

  val BS6 = 96

  val B6 = 95

  val BF6 = 94

  val AS6 = 94

  val A6 = 93

  val AF6 = 92

  val GS6 = 92

  val G6 = 91

  val GF6 = 90

  val FS6 = 90

  val F6 = 89

  val FF6 = 88

  val ES6 = 89

  val E6 = 88

  val EF6 = 87

  val DS6 = 87

  val D6 = 86

  val DF6 = 85

  val CS6 = 85

  val C6 = 84

  val CF6 = 83

  val BS5 = 84

  val B5 = 83

  val BF5 = 82

  val AS5 = 82

  val A5 = 81

  val AF5 = 80

  val GS5 = 80

  val G5 = 79

  val GF5 = 78

  val FS5 = 78

  val F5 = 77

  val FF5 = 76

  val ES5 = 77

  val E5 = 76

  val EF5 = 75

  val DS5 = 75

  val D5 = 74

  val DF5 = 73

  val CS5 = 73

  val C5 = 72

  val CF5 = 71

  val BS4 = 72

  val B4 = 71

  val BF4 = 70

  val AS4 = 70

  val A4 = 69

  val AF4 = 68

  val GS4 = 68

  val G4 = 67

  val GF4 = 66

  val FS4 = 66

  val F4 = 65

  val FF4 = 64

  val ES4 = 65

  val E4 = 64

  val EF4 = 63

  val DS4 = 63

  val D4 = 62

  val DF4 = 61

  val CS4 = 61

  val C4 = 60

  val CF4 = 59

  val BS3 = 60

  val B3 = 59

  val BF3 = 58

  val AS3 = 58

  val A3 = 57

  val AF3 = 56

  val GS3 = 56

  val G3 = 55

  val GF3 = 54

  val FS3 = 54

  val F3 = 53

  val FF3 = 52

  val ES3 = 53

  val E3 = 52

  val EF3 = 51

  val DS3 = 51

  val D3 = 50

  val DF3 = 49

  val CS3 = 49

  val C3 = 48

  val CF3 = 47

  val BS2 = 48

  val B2 = 47

  val BF2 = 46

  val AS2 = 46

  val A2 = 45

  val AF2 = 44

  val GS2 = 44

  val G2 = 43

  val GF2 = 42

  val FS2 = 42

  val F2 = 41

  val FF2 = 40

  val ES2 = 41

  val E2 = 40

  val EF2 = 39

  val DS2 = 39

  val D2 = 38

  val DF2 = 37

  val CS2 = 37

  val C2 = 36

  val CF2 = 35

  val BS1 = 36

  val B1 = 35

  val BF1 = 34

  val AS1 = 34

  val A1 = 33

  val AF1 = 32

  val GS1 = 32

  val G1 = 31

  val GF1 = 30

  val FS1 = 30

  val F1 = 29

  val FF1 = 28

  val ES1 = 29

  val E1 = 28

  val EF1 = 27

  val DS1 = 27

  val D1 = 26

  val DF1 = 25

  val CS1 = 25

  val C1 = 24

  val CF1 = 23

  val BS0 = 24

  val B0 = 23

  val BF0 = 22

  val AS0 = 22

  val A0 = 21

  val AF0 = 20

  val GS0 = 20

  val G0 = 19

  val GF0 = 18

  val FS0 = 18

  val F0 = 17

  val FF0 = 16

  val ES0 = 17

  val E0 = 16

  val EF0 = 15

  val DS0 = 15

  val D0 = 14

  val DF0 = 13

  val CS0 = 13

  val C0 = 12

  val CF0 = 11

  val BSN1 = 12

  val BN1 = 11

  val BFN1 = 10

  val ASN1 = 10

  val AN1 = 9

  val AFN1 = 8

  val GSN1 = 8

  val GN1 = 7

  val GFN1 = 6

  val FSN1 = 6

  val FN1 = 5

  val FFN1 = 4

  val ESN1 = 5

  val EN1 = 4

  val EFN1 = 3

  val DSN1 = 3

  val DN1 = 2

  val DFN1 = 1

  val CSN1 = 1

  val CN1 = 0

  val g9 = 127

  val gf9 = 126

  val fs9 = 126

  val f9 = 125

  val ff9 = 124

  val es9 = 125

  val e9 = 124

  val ef9 = 123

  val ds9 = 123

  val d9 = 122

  val df9 = 121

  val cs9 = 121

  val c9 = 120

  val cf9 = 119

  val bs8 = 120

  val b8 = 119

  val bf8 = 118

  val as8 = 118

  val a8 = 117

  val af8 = 116

  val gs8 = 116

  val g8 = 115

  val gf8 = 114

  val fs8 = 114

  val f8 = 113

  val ff8 = 112

  val es8 = 113

  val e8 = 112

  val ef8 = 111

  val ds8 = 111

  val d8 = 110

  val df8 = 109

  val cs8 = 109

  val c8 = 108

  val cf8 = 107

  val bs7 = 108

  val b7 = 107

  val bf7 = 106

  val as7 = 106

  val a7 = 105

  val af7 = 104

  val gs7 = 104

  val g7 = 103

  val gf7 = 102

  val fs7 = 102

  val f7 = 101

  val ff7 = 100

  val es7 = 101

  val e7 = 100

  val ef7 = 99

  val ds7 = 99

  val d7 = 98

  val df7 = 97

  val cs7 = 97

  val c7 = 96

  val cf7 = 95

  val bs6 = 96

  val b6 = 95

  val bf6 = 94

  val as6 = 94

  val a6 = 93

  val af6 = 92

  val gs6 = 92

  val g6 = 91

  val gf6 = 90

  val fs6 = 90

  val f6 = 89

  val ff6 = 88

  val es6 = 89

  val e6 = 88

  val ef6 = 87

  val ds6 = 87

  val d6 = 86

  val df6 = 85

  val cs6 = 85

  val c6 = 84

  val cf6 = 83

  val bs5 = 84

  val b5 = 83

  val bf5 = 82

  val as5 = 82

  val a5 = 81

  val af5 = 80

  val gs5 = 80

  val g5 = 79

  val gf5 = 78

  val fs5 = 78

  val f5 = 77

  val ff5 = 76

  val es5 = 77

  val e5 = 76

  val ef5 = 75

  val ds5 = 75

  val d5 = 74

  val df5 = 73

  val cs5 = 73

  val c5 = 72

  val cf5 = 71

  val bs4 = 72

  val b4 = 71

  val bf4 = 70

  val as4 = 70

  val a4 = 69

  val af4 = 68

  val gs4 = 68

  val g4 = 67

  val gf4 = 66

  val fs4 = 66

  val f4 = 65

  val ff4 = 64

  val es4 = 65

  val e4 = 64

  val ef4 = 63

  val ds4 = 63

  val d4 = 62

  val df4 = 61

  val cs4 = 61

  val c4 = 60

  val cf4 = 59

  val bs3 = 60

  val b3 = 59

  val bf3 = 58

  val as3 = 58

  val a3 = 57

  val af3 = 56

  val gs3 = 56

  val g3 = 55

  val gf3 = 54

  val fs3 = 54

  val f3 = 53

  val ff3 = 52

  val es3 = 53

  val e3 = 52

  val ef3 = 51

  val ds3 = 51

  val d3 = 50

  val df3 = 49

  val cs3 = 49

  val c3 = 48

  val cf3 = 47

  val bs2 = 48

  val b2 = 47

  val bf2 = 46

  val as2 = 46

  val a2 = 45

  val af2 = 44

  val gs2 = 44

  val g2 = 43

  val gf2 = 42

  val fs2 = 42

  val f2 = 41

  val ff2 = 40

  val es2 = 41

  val e2 = 40

  val ef2 = 39

  val ds2 = 39

  val d2 = 38

  val df2 = 37

  val cs2 = 37

  val c2 = 36

  val cf2 = 35

  val bs1 = 36

  val b1 = 35

  val bf1 = 34

  val as1 = 34

  val a1 = 33

  val af1 = 32

  val gs1 = 32

  val g1 = 31

  val gf1 = 30

  val fs1 = 30

  val f1 = 29

  val ff1 = 28

  val es1 = 29

  val e1 = 28

  val ef1 = 27

  val ds1 = 27

  val d1 = 26

  val df1 = 25

  val cs1 = 25

  val c1 = 24

  val cf1 = 23

  val bs0 = 24

  val b0 = 23

  val bf0 = 22

  val as0 = 22

  val a0 = 21

  val af0 = 20

  val gs0 = 20

  val g0 = 19

  val gf0 = 18

  val fs0 = 18

  val f0 = 17

  val ff0 = 16

  val es0 = 17

  val e0 = 16

  val ef0 = 15

  val ds0 = 15

  val d0 = 14

  val df0 = 13

  val cs0 = 13

  val c0 = 12

  val cf0 = 11

  val bsn1 = 12

  val bn1 = 11

  val bfn1 = 10

  val asn1 = 10

  val an1 = 9

  val afn1 = 8

  val gsn1 = 8

  val gn1 = 7

  val gfn1 = 6

  val fsn1 = 6

  val fn1 = 5

  val ffn1 = 4

  val esn1 = 5

  val en1 = 4

  val efn1 = 3

  val dsn1 = 3

  val dn1 = 2

  val dfn1 = 1

  val csn1 = 1

  val cn1 = 0

  val REST = Int.MinValue

}
