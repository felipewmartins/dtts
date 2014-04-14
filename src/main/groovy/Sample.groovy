println "### welcome to the heavy metal no description groovy tutorial ###"
println "Download groovy"
println "Create a GROOVY_HOME"
for (i in 0..3) { print i }
println "\n"
println "Try to remember shift + alt + x + g from eclipse to run this"
0.upto(3) { println "$it" }
println "Problems with autocomplete on eclipse"
2.times { print "$it-" }
print "\n"
0.step(21, 3) { print "$it " }
print "\n"
0.step(100, 5) { print "$it " }
println ""
println "Show information about git:"
println "git status".execute().text
println "Wow ! Let's try some more...Who i am ?"
println "git config user.name".execute().text
class Game {
   def type = "action"
   Game(t) {type = t}    
}
def g = new Game("shooter")
println g.type
levels = ['easy', 'medium', 'hard', 'expert']
for (l in levels) println l



