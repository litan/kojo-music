Experiments in Music with [Kojo](https://www.kojo.in)...

The basic goals of the project (subject to refinement as we go along) are to:
* Get children going with composing nice sounding music via an easy to use (GUI based) [music workbench](samples/workbench.kojo), and in the process learn basic music theory.
* Enable the export of the code behind the music from the workbench - to get children familiar with how the music (in the workbench) is related to the code that generates it.
* Get children coding with declarative music.
* Support both [Indian swaras](samples/examples/ons.kojo) and [Western notes](samples/examples/score1.kojo).
* Enable the creation of [infinitely long generative music](samples/examples/score_dyn.kojo).
* Provide rich support for generative music with ragas (coming soon).
* Provide support for live-coding of music (where a new run of a modified program gently modifies the running music)(coming soon).

kojo-music uses Alda (https://alda.io) as its music server (see music server [source code](https://github.com/litan/alda/tree/master/player)).

Here's a screenshot of the music workbench:

![Kojo Workbench](doc/kojo-music-workbench.png)

