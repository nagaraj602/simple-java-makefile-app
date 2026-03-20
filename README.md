# simple-java-makefile-app

👉 Makefile is NOT only for C/C++
👉 It is a general-purpose build automation tool

You can use it for:

Java ✅

Python ✅

Docker ✅

Kubernetes ✅

Shell scripts ✅

Anything you can run in terminal ✅

🧠 Why people think it's only for C/C++

Because traditionally:

make was heavily used with C/C++ compilers (gcc, g++)

It handles compilation dependencies very well

So it became famous in that ecosystem.

🔥 How it works (important concept)

Makefile doesn’t “know Java” or “know C”.

👉 It just runs commands like this:

javac Main.java
java Main

Same as if you typed manually.

✅ Example (Your case)

In your Makefile:

build:
	javac -d build src/Main.java

run:
	java -cp build Main

👉 Make is simply automating:

Compile → javac

Run → java
