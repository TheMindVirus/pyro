# pyro
Towards a Minecraft Engine to replace blaze3d that runs in Python called pyro

WIP 1.18.2 resurrection of projectred/redpower2 by Eloraam/MrTJP

#### They broke a lot of stuff. Server-side. Closed-Source. Authoritatively.

Scala is a language built on top of Java, in the same family as Groovy and Kotlin.
Java has changed from version 8 -> 17 which has brought about a few usability complications.
Scala has changed version from 2 -> 3 which necessitated the following:
```scala
trait Iterator[A]
{
    def hasNext: AnyVal
    def next(): AnyVal //it complains if you leave the return value blank
    def tick(): AnyVal = { /* some scala3 */ } //and it complains if you don't put the equals sign in
}

//- var tooltipBuilder = {_:ListBuffer[String] => ()}
    var tooltipBuilder = {(_:ListBuffer[String]) => ()} //lambda function

//and completely remove any files with circular references
```

#### Future Work or Future Hell?

While a Python Port of Minecraft, Forge and all the Community Mods is the ideal goal, \
a more reasonable approach would be to start off with Unity C# and Shaders, \
then use that to decide what should be included in Pyro Engine.

Only those with full source code access are currently "allowed" to participate. \
Neither Mojang nor Microsoft will support this approach and will actively refuse it. \
This is the status as of 2022 with the hope that it will change in coming years.